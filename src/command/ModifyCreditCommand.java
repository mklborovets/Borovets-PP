package command;

public class ModifyCreditCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Зміна умов кредиту...");
    }
}
