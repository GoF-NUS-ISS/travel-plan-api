package com.gof.springcloud.elasticsearch;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Objects;

import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLContext;

@Slf4j
@Configuration
public class ESRestClient {

	private static final int ADDRESS_LENGTH = 2;
	private static final String HTTP_SCHEME = "https";

	@Value("${spring.elasticsearch.rest.uris}")
	private String ipAddress;
	@Value("${spring.elasticsearch.rest.username}")
	private String username;
	@Value("${spring.elasticsearch.rest.password}")
	private String password;

	@Bean
	public RestClientBuilder restClientBuilder() {
		String[] split = ipAddress.split(",");
		HttpHost[] hosts = Arrays.stream(split).map(this::makeHttpHost).filter(Objects::nonNull)
				.toArray(HttpHost[]::new);
		return RestClient.builder(hosts);
	}

	@Bean(name = "highLevelClient")
	public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {


		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));

		// Set SocketTimeout and set ConnectTimeout
		restClientBuilder.setRequestConfigCallback(
				requestConfigBuilder -> requestConfigBuilder.setSocketTimeout(10000).setConnectTimeout(60000));
		// Set ThreadCount
		restClientBuilder.setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder
				.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1).build()));
		// Set FailureListener and log error
		restClientBuilder.setFailureListener(new RestClient.FailureListener() {
			@Override
			public void onFailure(Node node) {
				super.onFailure(node);
				log.error(node.getHost() + "--->this node fail");
			}
		});
		// Set username and password
		restClientBuilder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {

			@SneakyThrows
			@Override
			public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
				return httpClientBuilder
						.setDefaultCredentialsProvider(credentialsProvider)
						.setSSLContext(getSSLContext());
			}
		});
		return new RestHighLevelClient(restClientBuilder);
	}

	private HttpHost makeHttpHost(String str) {
		assert StringUtils.isNotEmpty(str);
		String[] address = str.split(":");
		if (address.length == ADDRESS_LENGTH) {
			String ip = address[0];
			int port = Integer.parseInt(address[1]);
			log.info("ES connection ip and port:{},{}", ip, port);
			return new HttpHost(ip, port, HTTP_SCHEME);
		} else {
			log.error("Imported ip params are not correct");
			return null;
		}
	}

	private SSLContext getSSLContext() throws CertificateException, IOException {
		try{
			Path caCertificatePath = Paths.get("./usr/share/elasticsearch/config/ca.crt");
			CertificateFactory factory =
					CertificateFactory.getInstance("X.509");
			Certificate trustedCa;
			try (InputStream is = Files.newInputStream(caCertificatePath)) {
				trustedCa = factory.generateCertificate(is);
			}
			KeyStore trustStore = KeyStore.getInstance("pkcs12");
			trustStore.load(null, null);
			trustStore.setCertificateEntry("ca", trustedCa);
			SSLContextBuilder sslContextBuilder = SSLContexts.custom()
					.loadTrustMaterial(trustStore, null);
			return sslContextBuilder.build();
		}
		catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
			log.error("ssl error: {}", e.getMessage());
		};
		return null;
	}

}
