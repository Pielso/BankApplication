package cloud.aws.entities;

public class CreditAccount extends Account{
    private int creditLimit;
    private int usedCredit;

    public CreditAccount(int accountNumber, float balance, int creditLimit, int usedCredit){
        super(accountNumber, balance);
        this.creditLimit = creditLimit;
        this.usedCredit = usedCredit;
    }

    // Getters & Setters

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public int getUsedCredit() {
        return usedCredit;
    }

    public void setUsedCredit(int usedCredit) {
        this.usedCredit = usedCredit;
    }
}
