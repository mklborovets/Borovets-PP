package command;

public class IncreaseCreditLimitCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Збільшення кредитного ліміту...");
    }
}
