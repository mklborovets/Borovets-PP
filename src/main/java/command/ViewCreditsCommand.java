package command;

import credit.BankCredit;
import credit.Credit;
import credit.PersonalCredit;
import portfolio.UserPortfolio;

import java.util.List;
import java.util.Scanner;

public class ViewCreditsCommand implements Command {
    private List<Credit> credits;
    private UserPortfolio portfolio;

    public ViewCreditsCommand(List<Credit> credits, UserPortfolio portfolio) {
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

        System.out.println("Оберіть тип кредиту для перегляду:");
        System.out.println("1. Кредити від банку");
        System.out.println("2. Кредити від фізичних осіб");
        System.out.println("3. Всі кредити");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Кредити від банку:");
                for (Credit credit : credits) {
                    if (credit instanceof BankCredit) {
                        credit.displayInfo();
                    }
                }
                break;

            case 2:
                System.out.println("Кредити від фізичних осіб:");
                for (Credit credit : credits) {
                    if (credit instanceof PersonalCredit) {
                        credit.displayInfo();
                    }
                }
                break;

            case 3:
                System.out.println("\tВсі кредити:");
                for (Credit credit : credits) {
                    credit.displayInfo();
                }
                break;

            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
                break;
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
