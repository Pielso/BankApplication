Feature: The user can transfer money between accounts
  Scenario: The user wishes to move money from one account to another
    Given The application is running and the accountlist has at least two accounts
    When The user selects the option to transfer
    Then User chooses amount and accounts to transfer money between
    Then The money is transferred