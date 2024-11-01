package main;

import command.ExitCommand;
import command.ViewCreditsCommand;
import command.SearchCreditCommand;
import command.ModifyCreditCommand;
import command.CloseCreditCommand;
import command.IncreaseCreditLimitCommand;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();

        menu.addCommand(1, new ViewCreditsCommand());
        menu.addCommand(2, new SearchCreditCommand());
        menu.addCommand(3, new ModifyCreditCommand());
        menu.addCommand(4, new CloseCreditCommand());
        menu.addCommand(5, new IncreaseCreditLimitCommand());
        menu.addCommand(6, new ExitCommand());

        menu.displayMenu();
    }
}
