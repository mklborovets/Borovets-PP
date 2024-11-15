package credit;

public class BankCredit extends Credit {
    private String bankName;
    private String creditName;

    public BankCredit(String lenderName,String creditName, double amount, double interestRate, int term,int index) {
        super(amount, interestRate, term, index);
        this.bankName = lenderName;
        this.creditName = creditName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCreditName() {
        return creditName;
    }

    public void setCreditName(String bankName) {
        this.creditName = creditName;
    }

    @Override
    public void displayInfo() {
            System.out.println("\nКредит від банку " + bankName);
        System.out.println("Кредит: "+creditName);
        System.out.println("Номер кредиту: "+creditIndex);
        System.out.println("Сума: " + amount + " | Відсоткова ставка: " + interestRate + "% | Термін: " + term + " місяців\n");
    }
}
