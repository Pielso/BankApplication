package cloud.aws.service;

import cloud.aws.entities.Account;
import cloud.aws.entities.CreditAccount;
import cloud.aws.entities.SavingsAccount;

import java.util.*;

public interface AccountService {
    ArrayList <Account> accountList = new ArrayList<>();

    // Just prints out the menuText. Loop is in Main.
    static void menuText(){
        System.out.println("\nWelcome to Fluffy Cloud Banking™️");
        System.out.println("- Your money is safe in the clouds!");
        System.out.println("\nWhat can we help you with today?\n");
        System.out.println("""
                [1]: Deposit precious money
                [2]: Withdraw money from one of your accounts *sad face*
                [3]: Transfer money between accounts
                [4]: Create a fresh brand-spanking-new account
                [5]: View your account information**
                [6]: Leave
                
                (**may be monetized through third-party-vendors)""");
    }

    // A switch case for the six options in menu (including exiting, by sending bool back to main).
    static boolean handleUserChoice(int userChoice){
        boolean exit = false;
        try {
            switch (userChoice){
                case 1:{
                    depositOptionChosen();
                    break;
                } // Deposit
                case 2:{
                    withdrawOptionChosen();
                    break;
                } // Withdraw
                case 3:{
                    transferOptionChosen();
                    break;
                } // Transfer
                case 4:{
                    createNewAccountOptionChosen();
                    break;
                } // New Account
                case 5:{
                    System.out.println("View");
                    break;
                } // View accounts
                case 6:{
                    exit = true;
                    System.out.println("Goodbye, trusted and beautiful customer. We sorely await you return. Please bring us more money at your next visit!");
                } // Exit
            }
        } catch (InputMismatchException e) {
            System.out.println("Please choose one of your six options, by typing the respective number and pressing enter at the terminal!");
        }
        return exit;
    }

    // Is part of the method "startNewAccount".
    static int generateUniqueAccountNumber(){
        Random random = new Random();

        int accountNumber = random.nextInt(100000000,1000000000);
        ArrayList <Integer> accountNumberList =new ArrayList<>();
        for (Account account: accountList){
            accountNumberList.add(account.getAccountNumber());
        }
        while (accountNumberList.contains(accountNumber)){
            accountNumber = random.nextInt(100000000,1000000000);
        }
        return accountNumber;
    }

    // Is part of the method "startNewAccount". Was extracted due to testing, since "startNewAccount" relies on user input.
    static void createAccount(int accountChoice, int accountNumber){
        try {
            switch (accountChoice){
                case 1:{
                    SavingsAccount savingsAccount = new SavingsAccount(accountNumber,0.0F,4.5F);
                    accountList.add(savingsAccount);
                    System.out.println("\nYour new savings account was created and can now be viewed by accessing the terminal outside, or of course in your Fluffy Cloud Phone™️");
                    break;
                }
                case 2:{
                    CreditAccount creditAccount = new CreditAccount(accountNumber,0.0F, 75000, 0);
                    accountList.add(creditAccount);
                    System.out.println("""
                            
                            Your new credit account was created and can now be viewed by accessing the terminal outside,
                            or of course in your Fluffy Cloud Phone™️""");
                    break;
                }
                default: {
                    System.out.println("Please choose a valid option. You only have two numbers to choose between, and there are people in line.");
                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println("\tPlease choose a valid option. You only have two numbers to choose between, and there are people in line.");
        }
    }

    // Asks user which of the two available accounts they'd like to start (Savings or Credit).
    static void createNewAccountOptionChosen() {
        System.out.println("\nWhich type of account do you want to open?");
        System.out.println("1: Savings Account");
        System.out.println("2: Credit Account");

        Scanner scan = new Scanner(System.in);
        int accountChoice = scan.nextInt();

        int accountNumber = generateUniqueAccountNumber();

        createAccount(accountChoice, accountNumber);


    }

    // Extracted due to testing, since "depositOptionChosen" relies on user input. Part of depositOptionChosen and transferOptionChosen
    static void addMoneyToAccount(float depositSum, int listIndexOfAccount){
        float balance = accountList.get(listIndexOfAccount).getAccountBalance();
        accountList.get(listIndexOfAccount).setAccountBalance(balance + depositSum);
    }

    // Extracted due to testing, since "withdrawOptionChosen" relies on user input. Part of withdrawOptionChosen and TransferOptionChosen.
    static void subtractMoneyFromAccount(float withdrawalSum, int listIndexOfAccount){
        float balance = accountList.get(listIndexOfAccount).getAccountBalance();
        accountList.get(listIndexOfAccount).setAccountBalance(balance - withdrawalSum);
    }

    // Shows a list
    static void viewAccounts(){
        if (accountList.isEmpty()){
            accountListIsEmpty();
        }
        else {
            int counter = 1;
            for (Account account: accountList){
                int accountNumber = account.getAccountNumber();
                String identifier = (account instanceof SavingsAccount) ? "Savings Account" : "Credit Account";
                System.out.println("🪙 ["+ counter +"] - ACCOUNT NUMBER: " + accountNumber + " - " + identifier + " - Has a balance of: " + account.getAccountBalance());
                counter++;
            }
        }
    }

    // Checks that accountList is not empty, asks how much to deposit, shows the user a list of the accounts, and user chooses which account to deposit money into.
    static void depositOptionChosen(){
        if (accountList.isEmpty()){
            accountListIsEmpty();
        }
        else {
            System.out.println("How much money do you want to deposit?");
            Scanner scanner = new Scanner(System.in);
            float depositSum = scanner.nextFloat();
            viewAccounts();
            System.out.println("\nWhich account do you want to deposit all that money to?\n");
            int listIndexOfAccount = scanner.nextInt()-1;
            addMoneyToAccount(depositSum, listIndexOfAccount);
            System.out.println("\nYour money is now safe in the clouds!");
        }

        System.out.println();
    }

    // Checks that accountList is not empty, asks how much to withdraw, shows the user a list of the accounts, and user chooses which account to withdraw money from.
    static void withdrawOptionChosen(){
        if (accountList.isEmpty()){
            accountListIsEmpty();
        }
        else {
            System.out.println("How much money do you want to withdraw?");
            Scanner scanner = new Scanner(System.in);
            float withdrawalSum = scanner.nextFloat();
            viewAccounts();
            System.out.println("\nWhich account do you want to withdraw all that money to?\n");
            int listIndexOfAccount = scanner.nextInt()-1;
            subtractMoneyFromAccount(withdrawalSum, listIndexOfAccount);
            System.out.println("\nHere is your " + withdrawalSum + " money! " +
            "\nThe best way to spend those are to deposit them into a Fluffy Cloud Bank Account™️");
        }
    }

    // Checks first that accountList is not empty, then that its size i above 1, and the lets the user choose accounts, then uses the subtractMoneyFromAccount and addMoneyToAccount to make the transfer.
    static void transferOptionChosen(){
        if (accountList.isEmpty()){
            accountListIsEmpty();
        }
        else if (accountList.size() == 1)
            System.out.println("""
        You only have one Fluffy Cloud Bank Account™️ available.
        A transfer is illogical. Please open a new account if you wish to use this service.
        Live long and prosper! 🖖""");
        else {
            System.out.println("Of course dear customer. Which of your available accounts would you like to transfer the money from?");
            viewAccounts();
            Scanner scan = new Scanner(System.in);
            int transferFrom = 0;
            int transferTo = 0;
            try {
                transferFrom = scan.nextInt()-1;
            } catch (RuntimeException e) {
                System.out.println("Not a valid choice. Please contain you input to the extent of the list of accounts.");
            }
            System.out.println("And which of your accounts do you wish to transfer the money to?");
            try {
                transferTo = scan.nextInt()-1;
            } catch (RuntimeException e) {
                System.out.println("Not a valid choice. Please contain you input to the extent of the list of accounts.");
            }
            System.out.println("And how much precious money would you like to transfer?");
            int transferAmount = scan.nextInt();
            makeTransfer(transferAmount, transferFrom, transferTo);
            viewAccounts();
            System.out.println("Your transfer is complete. Here is your new balance.");
        }

    }

    // Only prints out a message. Size check is done in each individual method, not in this. Part of depositOptionChosen, WithdrawOptionChosen, ViewAccounts
    static void accountListIsEmpty(){
        System.out.println("""
            You don't have a Fluffy Cloud Account™️
            Either start one or escort yourself out of the building.""");
    }

    // Subtracts the transferAmount from one account (transferFrom) and adds the transferAmount to another account (transferTo). Part of transferOptionChosen.
    static void makeTransfer(int transferAmount, int transferFrom, int transferTo){
        subtractMoneyFromAccount(transferAmount, transferFrom);
        addMoneyToAccount(transferAmount, transferTo);
    }


}
