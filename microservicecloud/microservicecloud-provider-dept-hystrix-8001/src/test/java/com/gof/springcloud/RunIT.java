package com.gof.springcloud;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@SpringBootTest(classes = TravePlanProvider8001_App.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberOptions(
        features="classpath:features",//to tell cucumber where is ur feature file
        glue="com.gof.springcloud", // to tell cucumber where is ur step def code
        tags="@t", // to tell which tagged feature file to execute
        plugin = {"pretty", // to generate reports
                "html:target/html/htmlreport.html",
                "json:target/json/file.json",
        },
        publish=true,
        dryRun=false //actual run(false)
)
public class RunIT {

}
