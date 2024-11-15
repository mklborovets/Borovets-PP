package credit;

public abstract class Credit {
    protected double amount;
    protected double interestRate;
    protected int term;
    protected int creditIndex;

    public Credit(double amount, double interestRate, int term, int creditIndex) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.term = term;
        this.creditIndex = creditIndex;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getCreditIndex() {
        return creditIndex;
    }

    public void setCreditIndex(int creditIndex) {
        this.creditIndex = creditIndex;
    }

    public abstract void displayInfo();

    public void modifyTerms(double newInterestRate, int newTerm) {
        this.interestRate=newInterestRate;
        this.term=newTerm;
    }

    public void increaseLimit(double additionalAmount) {
        this.amount+=additionalAmount;
    }


}
