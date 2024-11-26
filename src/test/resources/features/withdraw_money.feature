Feature: The user can withdraw money
  Scenario: The user wants to withdraw money from an account
    Given The user has an account
    When The user withdraws 1000
    Then There should be 1000 less on the account