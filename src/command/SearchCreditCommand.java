package command;

public class SearchCreditCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Пошук кредиту за параметрами...");
    }
}
