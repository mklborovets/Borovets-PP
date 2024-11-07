package command;

import portfolio.*;

import java.util.List;
import java.util.Scanner;

public class IncreaseCreditLimitCommand implements Command {
    private UserPortfolio portfolio;

    public IncreaseCreditLimitCommand(UserPortfolio portfolio) {

        this.portfolio = portfolio;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Максимальна кількість кредитів у вашому портфоліо - "+portfolio.getMaxCreditLimit());
        System.out.println("Ви ще можете відкрити - "+(portfolio.getMaxCreditLimit()-portfolio.getCurrentCreditLimit())+" кредитів");

        System.out.println("(1) - якщо бажаєте збільшити максимум власних кредитів натисніть - ");
        System.out.println("(2) - якщо бажаєте зменшити максимум  власних кредитів - ");
        System.out.println("(3-9) - дякую за інформацію");
        int increasLimit = scanner.nextInt();

        if(increasLimit == 1){
            portfolio.setMaxCreditLimit(portfolio.getMaxCreditLimit()+1);
        } else if (increasLimit==2) {
            portfolio.setMaxCreditLimit(portfolio.getMaxCreditLimit()-1);
        }
        System.out.println("Тепер ви можете відкрити до - "+portfolio.getMaxCreditLimit()+" кредитів\n");
    }
}
