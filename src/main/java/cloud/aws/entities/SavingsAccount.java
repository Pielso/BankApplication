package cloud.aws.entities;

public class SavingsAccount extends Account{
    private float interestRate;

    public SavingsAccount(int accountNumber, float accountBalance, float interestRate){
        super(accountNumber, accountBalance);
        this.interestRate = interestRate;
    }

    // Getters & Setters

    public float getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(float interestRate) {
        this.interestRate = interestRate;
    }

}
