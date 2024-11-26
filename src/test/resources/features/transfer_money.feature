Feature: The user can transfer money between accounts
  Scenario: The user wishes to move money from one account to another
    Given The application is running and the accountlist has at least two accounts
    When The user selects the option to transfer
    Then User is given a list of available accounts and chooses one to transfer the money from
    Then User is given a list of available accounts and chooses one to transfer money to
    Then User is asked how much money they would like to transfer
    Then The money is transferred