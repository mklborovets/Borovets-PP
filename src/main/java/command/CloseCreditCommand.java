package command;

import credit.Credit;
import portfolio.UserPortfolio;

import java.util.Scanner;

public class CloseCreditCommand implements Command {
    private UserPortfolio portfolio;

    public CloseCreditCommand(UserPortfolio portfolio) {

        this.portfolio = portfolio;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        portfolio.displayportfolioCredits();

        if (portfolio.getPortfolioCredits().isEmpty()) {
            return;
        }

        System.out.println("Оберіть кредит для дострокового закриття");
        System.out.println("Введіть (-1) для закриття усіх кредитів");
        System.out.println("Введіть індекс кредиту:");
        int chosenCredit = scanner.nextInt();

        if(chosenCredit == -1){
            portfolio.getPortfolioCredits().clear();
            portfolio.setCurrentCreditLimitZero();
            System.out.println("Всі кредити були видалені з портфоліо.\n");
            return;
        }

        Credit selectedCredit = null;
        for (Credit credit : portfolio.getportfolioCredits()) {
            if (credit.getCreditIndex() == chosenCredit) {
                selectedCredit = credit;
                break;
            }
        }

        if (selectedCredit != null) {
            portfolio.getPortfolioCredits().remove(selectedCredit);
            portfolio.removeFromCurrentLimit();
            System.out.println("Кредит видалено з портфоліо.\n");
        } else {
            System.out.println("Кредит з таким індексом не знайдено.\n");
        }

    }
}
