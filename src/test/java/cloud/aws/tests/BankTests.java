package cloud.aws.tests;
import cloud.aws.service.AccountService;
import org.junit.jupiter.api.*;

public class BankTests {

    @BeforeEach
    public void setup(){
        AccountService.createAccount(1, 0);
        AccountService.createAccount(2, 1);
    }

    // Story about why I have a @AfterEach that is not needed.
    /// Had to go on an information safari on the www to find why the h*ll my testDepositMoney worked when run by itself,
    /// but not when running all tests together.
    ///
    /// Immediately after the deposit the balance on the account (index 0) was zero, even though the deposit was made.
    /// So I couldn't understand why it was reset, or rejected.
    ///
    /// It works now as it is, but at last I figured out why. I saw that at the time of
    /// running testDepositMoney the size of the accountList was 7. So that meant that the test was running last in order.
    /// That shouldn't matter, since I was checking balance at index 0.
    /// However. The second last test was the withdrawal, where I withdrew exactly 1000 from the account at index 0, so
    /// a deposit of +1000 on an account that has a balance of -1000 made the zero balance. It was not because of anything not working.
    ///
    /// This day I learned to test with different sums. If I had tested the withdrawal with another sum, say 123 instead,
    /// I would have saved me at least two hours of debug mania. #listOfFails.add();

    @AfterEach
    public void tearDown(){
        AccountService.accountList.clear();
    }

    @Test
    public void testDepositMoney(){
        // Arrange (done in setup)

        // Act
        AccountService.addMoneyToAccount(1000,0);
        float checkBalance = AccountService.accountList.getFirst().getAccountBalance();

        // Assert
        Assertions.assertEquals(1000, checkBalance, "Balance is off");
    }

    @Test
    public void testCreateAccount(){
        // Arrange (done in setup)

        // Act
        AccountService.createAccount(1,2);

        // Assert
        Assertions.assertEquals(3, AccountService.accountList.size(), "The number of accounts in the accountlist was off");
    }

    @Test
    public void testWithdrawMoney(){
        // Arrange (done in setup)

        // Act
        AccountService.subtractMoneyFromAccount(1000,0);

        // Assert
        Assertions.assertEquals(-1000, AccountService.accountList.getFirst().getAccountBalance());
    }

    @Test
    public void testTransferMoney(){
        // Arrange (done in setup)

        // Act
        int transferFrom = 0;
        int transferTo = 1;
        int transferAmount = 150;
        AccountService.makeTransfer(transferAmount, transferFrom, transferTo);


        // Assert
        Assertions.assertEquals(300, AccountService.accountList.get(1).getAccountBalance() - AccountService.accountList.get(0).getAccountBalance(), "Balance was off");
    }

}
