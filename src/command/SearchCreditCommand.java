package command;

import credit.Credit;
import credit.BankCredit;
import credit.PersonalCredit;
import portfolio.UserPortfolio;

import java.util.List;
import java.util.Scanner;

public class SearchCreditCommand implements Command {
    private List<Credit> credits;
    private UserPortfolio portfolio;

    public SearchCreditCommand(List<Credit> credits, UserPortfolio portfolio) {
        this.credits = credits;
        this.portfolio = portfolio;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        int limit = portfolio.getCurrentCreditLimit();
        if(limit==portfolio.getMaxCreditLimit()){
            System.out.println("У вашому портфоліо нараховується "+limit+"/"+portfolio.getMaxCreditLimit()+" кредитів");
            System.out.println("Закрийте попередні кредити, або збільште кількість власних кредитів\n");
            return;
        }

        System.out.println("Оберіть тип кредиту для пошуку:");
        System.out.println("1. Кредити від банку");
        System.out.println("2. Кредити від фізичних осіб");
        System.out.println("3. Всі кредити");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть мінімальну суму кредиту для пошуку (або -1, якщо не потрібно): ");
        double minAmount = scanner.nextDouble();

        System.out.print("Введіть максимальний відсоток кредиту для пошуку (або -1, якщо не потрібно): ");
        double maxInterestRate = scanner.nextDouble();

        System.out.print("Введіть мінімальний термін кредиту (в місяцях) для пошуку (або -1, якщо не потрібно): ");
        int minTerm = scanner.nextInt();

        System.out.println("\nЗнайдені кредити:");
        boolean found = false;

        for (Credit credit : credits) {
            boolean isBankCredit = credit instanceof BankCredit && (choice == 1 || choice == 3);
            boolean isPersonalCredit = credit instanceof PersonalCredit && (choice == 2 || choice == 3);

            boolean matchesCriteria = (isBankCredit || isPersonalCredit) &&
                    (minAmount == -1 || credit.getAmount() >= minAmount) &&
                    (maxInterestRate == -1 || credit.getInterestRate() <= maxInterestRate) &&
                    (minTerm == -1 || credit.getTerm() >= minTerm);

            if (matchesCriteria) {
                credit.displayInfo();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Кредити, що відповідають зазначеним критеріям, не знайдені.\n");
        }

        System.out.println("Який кредит бажаєте взяти?\nВведіть номер кредиту: ");
        int chosenIndex = scanner.nextInt();

        Credit selectedCredit = null;
        for (Credit credit : credits) {
            if (credit.getCreditIndex() == chosenIndex) {
                selectedCredit = credit;
                break;
            }
        }

        if (selectedCredit != null) {
            portfolio.addCredit(selectedCredit);
            portfolio.addToCurrentLimit();
            System.out.println("Кредит з індексом " + chosenIndex + " додано до портфоліо.\n");
        } else {
            System.out.println("Кредит з таким індексом не знайдено.\n");
        }
    }
}
