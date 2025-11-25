package com.example.stepdefs;

import com.example.stepdefs.utils.SpringTestUtils;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class MonthlyStepDef  {
    @Autowired
    SpringTestUtils springTestUtils;
    private long startTime;
    private int responseStatus;   // 記錄調用後的回應狀態

    @Given("uId {string}")
    public void u_id(String uId) {
        System.out.println(uId);
    }
    @When("Call GET monthlyService {string} and uri {string}")
    public void callGETMonthlyServiceAndUri(String userId, String uri) {
        String externalUrl = "https://deliver.mybook.momoshop.com.tw/DeliverWebV30/" + uri + "?uId=" + userId;
        RestTemplate restTemplate = new RestTemplate();
        this.responseStatus = 0;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(externalUrl, String.class);
            responseStatus = response.getStatusCode().value();
        } catch (HttpClientErrorException e) {
            this.responseStatus = e.getRawStatusCode();
            System.out.println("  -> 呼叫失敗. HTTP Status: " + this.responseStatus);
        } catch (Exception e) {
            this.responseStatus = HttpStatus.SERVICE_UNAVAILABLE.value(); // 設為 503 服務不可用
            System.out.println("  -> 呼叫失敗. Unexpected Status: 503");
        }

        this.startTime = System.currentTimeMillis();
    }
    @Then("ResponseStatus {string}")
    public void responseStatus(String arg0) {
        assertThat(String.valueOf(responseStatus)).isEqualTo(arg0);
    }

    @And("responseTimeLT {int} ms")
    public void responseTimeLtMs(int arg0) {
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - this.startTime;
        assertThat(responseTime - arg0).isLessThan(arg0);
    }
    @After
    public void after(){
        springTestUtils.resetAllFakeBean();
    }

}
