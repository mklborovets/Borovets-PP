package portfolio;

import credit.Credit;

import java.util.ArrayList;
import java.util.List;

public class UserPortfolio {
    private List<Credit> portfolioCredits = new ArrayList<>();
    private int maxCreditLimit=3;
    private int currentCreditLimit = 0;



    public List<Credit> getPortfolioCredits() {
        return portfolioCredits;
    }

    public int getMaxCreditLimit() {
        return maxCreditLimit;
    }

    public void setMaxCreditLimit(int maxCreditLimit) {
        this.maxCreditLimit = maxCreditLimit;
    }

    public int getCurrentCreditLimit() {
        return currentCreditLimit;
    }
    public void setCurrentCreditLimit(int currentCreditLimit) {
        this.currentCreditLimit=currentCreditLimit;
    }

    public void setCurrentCreditLimitZero() {
        this.currentCreditLimit = 0;
    }

    public void addToCurrentLimit() {
        this.currentCreditLimit++;
    }
    public void removeFromCurrentLimit() {
        this.currentCreditLimit--;
    }


    public void addCredit(Credit credit) {
        portfolioCredits.add(credit);
    }

    public void removeCredit(int index) {
        if (index >= 0 && index < portfolioCredits.size()) {
            portfolioCredits.remove(index);
            System.out.println("Кредит видалено з портфоліо.\n");
        } else {
            System.out.println("Невірний номер кредиту.\n");
        }
    }

    public List<Credit> getportfolioCredits() {
        return portfolioCredits;
    }

    public void displayportfolioCredits() {
        if (getPortfolioCredits().isEmpty()) {
            System.out.println("Портфоліо порожнє.\n");
            return;
        }

        System.out.println("Ваше портфоліо кредитів:");
        for (Credit credit : getPortfolioCredits()) {
            System.out.println("Номер кредиту: " + credit.getCreditIndex());
            System.out.println("Сума: " + credit.getAmount() +
                    " | Відсоткова ставка: " + credit.getInterestRate() + "%" +
                    " | Термін: " + credit.getTerm() + " місяців\n");
        }
    }
}
