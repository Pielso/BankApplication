package cloud.aws.stepdefinitions;
import cloud.aws.entities.CreditAccount;
import cloud.aws.entities.SavingsAccount;
import cloud.aws.service.AccountService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class BankTestSteps {


    // Shared steps

    public void setupTwoAccounts(){
        SavingsAccount savingsAccount = new SavingsAccount(0,0,0);
        CreditAccount creditAccount = new CreditAccount(0,0,0,0);
        AccountService.accountList.add(savingsAccount);
        AccountService.accountList.add(creditAccount);
    }

    /*
    Test to assert the feature to create a new account.
    */

    @Given("The application is running")
    public void the_application_is_running() {
        // Is always true
    }

    @When("The user selects to create an account")
    public void the_user_selects() {
        AccountService.createAccount(1, 0);
    }

    @Then("A new account with a unique account number is created")
    public void a_new_account_with_a_unique_account_number_is_created() {
        Assertions.assertEquals(1, AccountService.accountList.size(), "The number of account in accountList was not the expected (1)");
    }

    /*
    Test to assert the feature to deposit precious money into account.
    */
    @Given("The application is running and the accountlist is not empty")
    public void the_Application_Is_Running_And_The_Accountlist_Is_Not_Empty() {
        setupTwoAccounts();
        Assertions.assertFalse(AccountService.accountList.isEmpty(), "AccountList was empty");
    }

    @When("The user selects to deposit money")
    public void the_user_selects_to() {
        // Is handled inside the application.
    }

    @Then("The user deposits 1000")
    public void the_user_deposits() {
        AccountService.addMoneyToAccount(1000, 0);
    }

    @Then("The balance on the account should be 1000")
    public void the_balance_on_the_account_should_be() {
        Assertions.assertEquals(1000, AccountService.accountList.getFirst().getAccountBalance(), "The balance on the account should be 1000");

    }

    /*
    Test to assert the feature to withdraw precious money from account.
    */
    @Given("The user has an account")
    public void the_user_has_an_account() {
        setupTwoAccounts();
        Assertions.assertFalse(AccountService.accountList.isEmpty(), "AccountList was empty");
    }

    @When("The user withdraws 1000")
    public Float the_user_withdraws() {
        float balanceBefore = AccountService.accountList.getFirst().getAccountBalance();
        AccountService.subtractMoneyFromAccount(1000, 0);
        return balanceBefore;
    }

    @Then("There should be 1000 less on the account")
    public void there_should_be_less_on_the_account() {
        float balanceBefore = the_user_withdraws();
        Assertions.assertEquals(AccountService.accountList.getFirst().getAccountBalance(), balanceBefore - 1000);

    }

    /*
    Test to assert the feature to transfer precious money between Fluffy Cloud Bank Accounts™️.
    */

    @Given("The application is running and the accountlist has at least two accounts")
    public void the_application_is_running_and_the_accountlist_has_at_least_two_accounts() {
       setupTwoAccounts();
       Assertions.assertTrue(AccountService.accountList.size() >= 2,"Size of accountlist was less than 2 (" + AccountService.accountList.size() + "), which makes a transfer not possible.");

    }

    @When("The user selects the option to transfer")
    public void the_user_selects_the_option_to_transfer() {
        // Handled inside the application
    }

    @Then("User chooses amount and accounts to transfer money between")
    public void user_chooses_amount_and_accounts_to_transfer_money_between() {
        // Handled inside the application
    }

    @Then("The money is transferred")
    public void the_money_is_transferred() {
        AccountService.accountList.get(0).setAccountBalance(0);
        AccountService.accountList.get(1).setAccountBalance(0);
        int transferFrom = 0;
        int transferTo = 1;
        int transferAmount = 150;
        AccountService.makeTransfer(transferAmount, transferFrom, transferTo);
        Assertions.assertEquals(300, AccountService.accountList.get(1).getAccountBalance() - AccountService.accountList.get(0).getAccountBalance(), "Balance was off");
    }


}
