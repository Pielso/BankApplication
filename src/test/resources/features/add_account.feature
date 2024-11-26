Feature: Creating a new account

Scenario: User can create a new account with unique account number
  Given The application is running
  When The user selects "Create New Account"
  Then A new account with a unique account number is created