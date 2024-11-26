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

    @Given("The application is running")
    public void the_application_is_running() {
        // Is always true
    }

    /*
    Test to assert the feature to create a new account.
    */
    @When("The user selects {string}")
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
        // Handled in setup.
    }

    @When("The user selects the option to transfer")
    public void the_user_selects_the_option_to_transfer() {
        // Handled inside the application

    }

    @Then("User is given a list of available accounts and chooses one to transfer the money from")
    public void user_is_given_a_list_of_available_accounts_and_chooses_one_to_transfer_the_money_from() {
        AccountService.viewAccounts();


    }

    @Then("User is given a list of available accounts and chooses one to transfer money to")
    public void user_is_given_a_list_of_available_accounts_and_chooses_one_to_transfer_money_to() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("User is asked how much money they would like to transfer")
    public void user_is_asked_how_much_money_they_would_like_to_transfer() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("The money is transferred")
    public void the_money_is_transferred() {
        // Write code here that turns the phrase above into concrete actions

    }


}
