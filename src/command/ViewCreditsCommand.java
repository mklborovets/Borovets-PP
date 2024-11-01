package command;

public class ViewCreditsCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Перегляд всіх доступних кредитів...");
    }
}
