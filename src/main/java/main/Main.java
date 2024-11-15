package main;

import command.*;
import credit.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import portfolio.UserPortfolio;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Запуск програми");
        List<Credit> credits = new ArrayList<>();
        credits.add(new BankCredit("АБанк", "Кредит на Машину", 375000, 4.7, 24, 1190));
        credits.add(new BankCredit("АБанк", "Кредит на Квартиру", 2100000, 3.0, 48, 1176));
        credits.add(new BankCredit("АБанк", "Кредит на Ремонт", 100000, 6.0, 8, 1193));
        credits.add(new BankCredit("Банк Універсал", "Пральна машина швидко", 25000, 5.5, 3, 3387));
        credits.add(new BankCredit("Банк Універсал", "Кредит на Освіту", 150000, 7.5, 36, 3399));
        credits.add(new BankCredit("ФінКом Банк", "Кредит на Бізнес", 500000, 10.0, 60, 4521));
        credits.add(new BankCredit("ФінКом Банк", "Кредит на Весілля", 80000, 13.0, 12, 4534));
        credits.add(new BankCredit("Стабільність Банк", "Кредит на Лікування", 100000, 3.0, 24, 5640));
        credits.add(new BankCredit("УкрБанк", "Кредит на Нерухомість", 900000, 6.5, 180, 2289));
        credits.add(new BankCredit("УкрБанк", "Зелений кредит", 200000, 9.0, 36, 2290));

        credits.add(new PersonalCredit("Джон Теллер", 10000, 3.5, 3,1111));
        credits.add(new PersonalCredit("Габріель Тур", 25000, 5, 2,2222));

        UserPortfolio portfolio = new UserPortfolio();

        Menu menu = new Menu();
        menu.addCommand(1, new ViewCreditsCommand(credits,portfolio));
        menu.addCommand(2, new SearchCreditCommand(credits,portfolio));
        menu.addCommand(3, new ModifyCreditCommand(portfolio));
        menu.addCommand(4, new CloseCreditCommand(portfolio));
        menu.addCommand(5, new IncreaseCreditLimitCommand(portfolio));
        menu.addCommand(6, new ViewPortfolio(portfolio));
        menu.addCommand(7, new ExitCommand());
        try {
            menu.displayMenu();
        }catch (Exception e){
            logger.error("Критична помилка у програмі.", e);
        }
    }
}
