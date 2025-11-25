Feature: APP查詢月租服務正常

  Scenario Outline: APP查詢月租服務正
    Given uId "<uId>"
    When Call GET monthlyService "<uId>" and uri "<uri>"
    Then ResponseStatus "<status>"
    And responseTimeLT 50 ms
    Examples:
      | uId             |uri                |status |
      | MM_202202726299 |getMonthlyService  |200    |
      | MM_202202726298 |getMonthlyService  |200    |
      | MM_202202726297 |getMonthlyServiceX |404    |
