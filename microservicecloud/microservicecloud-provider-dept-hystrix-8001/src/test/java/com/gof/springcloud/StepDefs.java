package com.gof.springcloud;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.*;

public class StepDefs {

    @Autowired
    Environment environment;

    String port = "8001";//environment.getProperty("local.server.port");

    String baseUrl = "http://localhost:";
    String url_offset_get_by_name = "/travelPlan/{name}/?name=%s";
    String url_offset_get_by_id = "/travelPlan/{id}/?id=%s";
    String url_offset_post = "/travelPlan";

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = null;
    HttpEntity<String> entity = null;
    ResponseEntity<String> response = null;

    @Given("API is up and running")
    public void api_is_up_and_running() throws Exception{
        headers = new HttpHeaders();

        //Thread.sleep(1000000000);

    }

    @When("User hit the api with post request with valid json body")
    public void user_hit_the_api_with_post_request_with_valid_json_body() {
        String body = "  {\n" +
                "    \"from\": \"NZ\",\n" +
                "    \"id\": \"1\",\n" +
                "    \"name\": \"travel-plan-name-sample\",\n" +
                "    \"to\": \"SG\"\n" +
                "  }";
        entity = new HttpEntity<String>(body, headers);
        response = restTemplate.exchange(baseUrl + ":" + port + url_offset_post,
                HttpMethod.POST, entity, String.class);

    }

    @Then("api returns {int} status code")
    public void api_returns_status_code(int code) {
        Assert.assertEquals("Status Code",response.getStatusCode(),code);
    }

    @When("User hit the api with get request with name query parameter value as {string}")
    public void user_hit_the_api_with_get_request_with_name_query_parameter_value_as(String value) {
        entity = new HttpEntity<String>(null, headers);
        //String url = baseUrl + port + String.format(url_offset_get_by_name,value);
        String url = baseUrl + port + "/travelPlan/"+ value;
        System.out.println(url);
        response = restTemplate.exchange(url,
                HttpMethod.GET, entity, String.class);
    }

    /*
    @Then("response body contains travel plan details for name {string}")
    public void response_body_contains_travel_plan_details_for_name(String string) {
        resp.then().assertThat().body("name", equalTo(string));
    }
     */
}
