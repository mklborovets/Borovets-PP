package command;

public class CloseCreditCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Закриття кредиту достроково...");
    }
}
