package credit;

public class PersonalCredit extends Credit {
    private String lenderName;

    public PersonalCredit(String lenderName, double amount, double interestRate, int term,int index) {
        super(amount, interestRate, term, index);
        this.lenderName = lenderName;
    }

    public String getLenderName() {
        return lenderName;
    }

    public void setLenderName(String lenderName) {
        this.lenderName = lenderName;
    }

    @Override
    public void displayInfo() {
        System.out.println("\nКредит від " + lenderName);
        System.out.println("Номер кредиту: "+creditIndex);
        System.out.println("Сума: " + amount + " | Відсоткова ставка: " + interestRate + "% | Термін: " + term + " місяців\n");
    }
}
