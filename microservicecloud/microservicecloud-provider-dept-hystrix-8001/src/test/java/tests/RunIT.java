package tests;

import com.gof.springcloud.TravePlanProvider8001_App;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.*;

@SpringBootTest(
        classes = TravePlanProvider8001_App.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class RunIT {
    @Autowired
    Environment environment;

    String port = "8001";//environment.getProperty("local.server.port");

    String baseUrl = "http://localhost";
    String url_offset_get_by_name = "/travelPlan/{name}/?name=%s";
    String url_offset_get_by_id = "/travelPlan/{id}/?id=%s";
    String url_offset_post = "/travelPlan";

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = null;
    HttpEntity<String> entity = null;
    ResponseEntity<String> response = null;

    @Test
    public void t_01_test_post_request(){
        headers = new HttpHeaders();
        String body = "  {\n" +
                "    \"from\": \"NZ\",\n" +
                "    \"id\": \"1\",\n" +
                "    \"name\": \"travel-plan-name-sample\",\n" +
                "    \"to\": \"SG\"\n" +
                "  }";
        entity = new HttpEntity<String>(body, headers);
        response = restTemplate.exchange(baseUrl + ":" + port + url_offset_post,
                HttpMethod.POST, entity, String.class);
        Assert.assertEquals("Status Code",response.getStatusCode(),201);
    }

    @Test
    public void t_02_test_get_request(){
        entity = new HttpEntity<String>(null, headers);
        //String url = baseUrl + port + String.format(url_offset_get_by_name,value);
        String url = baseUrl + ":" + port + "/travelPlan/travel-plan-name-sample";
        System.out.println(url);
        response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);
    }



}
