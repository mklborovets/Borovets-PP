package command;

import credit.Credit;
import credit.BankCredit;
import credit.PersonalCredit;
import portfolio.UserPortfolio;

import java.util.Scanner;

public class ModifyCreditCommand implements Command {
    private UserPortfolio portfolio;

    public ModifyCreditCommand(UserPortfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Оберіть тип кредиту для зміни умов:");
        System.out.println("1. Кредити від банку");
        System.out.println("2. Кредити від фізичних осіб");
        System.out.println("3. Всі кредити");
        int choice = scanner.nextInt();

        // Перебір кредитів відповідного типу
        boolean found = false;
        for (Credit credit : portfolio.getPortfolioCredits()) {
            boolean isBankCredit = credit instanceof BankCredit && (choice == 1 || choice == 3);
            boolean isPersonalCredit = credit instanceof PersonalCredit && (choice == 2 || choice == 3);

            if (isBankCredit || isPersonalCredit) {
                found = true;
                credit.displayInfo();
                System.out.println("Індекс цього кредиту: " + credit.getCreditIndex());
            }
        }

        if (!found) {
            System.out.println("Немає доступних кредитів для обраного типу.");
            return;
        }

        // Вибір конкретного кредиту для зміни
        System.out.print("Введіть індекс кредиту, який ви хочете змінити: ");
        int chosenIndex = scanner.nextInt();

        Credit chosenCredit = null;
        for (Credit credit : portfolio.getPortfolioCredits()) {
            if (credit.getCreditIndex() == chosenIndex) {
                chosenCredit = credit;
                break;
            }
        }

        if (chosenCredit != null) {
            System.out.println("Введіть нову відсоткову ставку: ");
            double newInterestRate = scanner.nextDouble();

            System.out.println("Введіть новий термін у місяцях: ");
            int newTerm = scanner.nextInt();

            chosenCredit.modifyTerms(newInterestRate, newTerm);
            System.out.println("Умови кредиту оновлено.");
        } else {
            System.out.println("Кредит з таким індексом не знайдено.");
        }
    }
}
