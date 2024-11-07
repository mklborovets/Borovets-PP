package main;

import command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private Map<Integer, Command> commands = new HashMap<>();

    public void addCommand(int option, Command command) {
        commands.put(option, command);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("==== Меню ====");
            System.out.println("Виберіть дію:");
            System.out.println("1. Переглянути всі доступні кредити");
            System.out.println("2. Виконати пошук кредита за параметрами");
            System.out.println("3. Змінити умови кредиту");
            System.out.println("4. Закрити кредит достроково");
            System.out.println("5. Збільшити кредитний ліміт");
            System.out.println("6. Переглянути портфоліо кредитів");
            System.out.println("7. Вихід");

            option = scanner.nextInt();
            scanner.nextLine();

            Command cmd = commands.get(option);
            if (cmd != null) {
                cmd.execute();
            } else if (option != 7) {
                System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        } while (option != 7);

        System.out.println("Програма завершена.");
        scanner.close();
    }
}
