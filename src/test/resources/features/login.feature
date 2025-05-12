Feature: APP 調用保護的 API

  Scenario: 帶正確 token 與 request body，回傳商品資訊
    Given 有一個 token "abc123"
    And request body 為
      """
      {
        "type": "book",
        "id": "B001"
      }
      """
    When 呼叫 POST "/api/info"
    Then 回傳狀態碼應為 200
    And 回傳的 JSON 欄位 "name" 應為 "Java 入門"
    And 回傳的 JSON 欄位 "price" 應為 "399"
    And 回應時間應小於 200 毫秒
