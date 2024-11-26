Feature: The user can deposit money
  Scenario: The user can deposit money
    Given The application is running and the accountlist is not empty
    When The user selects to deposit money
    Then The user deposits 1000
    Then The balance on the account should be 1000