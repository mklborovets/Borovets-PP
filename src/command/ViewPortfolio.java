package command;

import credit.*;
import portfolio.UserPortfolio;

public class ViewPortfolio implements Command{
    private UserPortfolio portfolio;
    public ViewPortfolio(UserPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void execute() {

        if (portfolio.getPortfolioCredits().isEmpty()) {
            System.out.println("Портфоліо порожнє.\n");
            return;
        }

        System.out.println("Ви ще можете відкрити - "+(portfolio.getMaxCreditLimit()-portfolio.getCurrentCreditLimit())+" кредитів");

        System.out.println("Ваше портфоліо кредитів:");
        for (Credit credit : portfolio.getPortfolioCredits()) {

            if (credit instanceof PersonalCredit) {
                System.out.println("\nКредит від " + ((PersonalCredit) credit).getLenderName());
                System.out.println("Номер кредиту: "+credit.getCreditIndex());
                System.out.println("Сума: " + credit.getAmount() + " | Відсоткова ставка: " + credit.getInterestRate() + "% | Термін: " + credit.getTerm() + " місяців\n");
            }
            if (credit instanceof BankCredit) {
                System.out.println("\nКредит від банку " + ((BankCredit) credit).getBankName());
                System.out.println("Кредит: "+((BankCredit) credit).getCreditName());
                System.out.println("Номер кредиту: "+credit.getCreditIndex());
                System.out.println("Сума: " + credit.getAmount() + " | Відсоткова ставка: " + credit.getInterestRate() + "% | Термін: " + credit.getTerm() + " місяців\n");
            }

        }
    }
}
