package com.example.stepdefs;

import io.restassured.response.Response;
import io.cucumber.java.en.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApiStepDefs{

private String token;
private String requestBody;
private Response response;
    @Given("有一個 token {string}")
    public void setToken(String token) {
        this.token = token;
    }

    @Given("request body 為")
    public void setRequestBody(String docString) {
        this.requestBody = docString;
    }

    @When("呼叫 POST {string}")
    public void callPostApi(String endpoint) {
        // 實作與前面 RestAssured 一樣
    }

    @Then("回傳狀態碼應為 {int}")
    public void assertStatusCode(int expectedStatus) {
        // 實作
    }

    @Then("回傳的 JSON 欄位 {string} 應為 {string}")
    public void assertJsonField(String key, String expectedValue) {
        // 實作
    }

    @Then("回應時間應小於 {int} 毫秒")
    public void assertResponseTimeUnder(int maxMillis) {
        // 實作
    }

}
