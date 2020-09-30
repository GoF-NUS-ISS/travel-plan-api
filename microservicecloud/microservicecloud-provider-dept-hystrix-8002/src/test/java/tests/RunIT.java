package tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.gof.springcloud.TravePlanProvider8002_App;

@SpringBootTest(
        classes = TravePlanProvider8002_App.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RunIT {
    @Autowired
    Environment environment;

    String port = "8002";//environment.getProperty("local.server.port");

    String baseUrl = "http://localhost";

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = null;
    HttpEntity<String> entity = null;
    ResponseEntity<String> response = null;


}
