package command;

import command.ViewCreditsCommand;
import credit.BankCredit;
import credit.Credit;
import credit.PersonalCredit;
import portfolio.UserPortfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewCreditsCommandTest {
    private UserPortfolio portfolio;
    private List<Credit> credits;
    private ViewCreditsCommand command;

    @BeforeEach
    void setUp() {
        portfolio = new UserPortfolio();
        credits = new ArrayList<>();
        credits.add(new BankCredit("Bank A", "Car Loan", 5000.0, 5.0, 12, 1001));
        credits.add(new PersonalCredit("Джон Теллер", 2000.0, 10.0, 24, 1002));
        credits.add(new BankCredit("Bank B", "Home Loan", 10000.0, 4.0, 36, 1003));
        command = new ViewCreditsCommand(credits, portfolio);
    }

    @Test
    void testExecute_ViewBankCredits() {
        String input = "1\n1001\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        command.execute();

        assertEquals(1, portfolio.getPortfolioCredits().size());
        assertEquals(1001, portfolio.getPortfolioCredits().get(0).getCreditIndex());
    }

    @Test
    void testExecute_ViewPersonalCredits() {
        String input = "2\n1002\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        command.execute();

        assertEquals(1, portfolio.getPortfolioCredits().size());
        assertEquals(1002, portfolio.getPortfolioCredits().get(0).getCreditIndex());
    }

    @Test
    void testExecute_ViewAllCredits() {
        String input = "3\n1003\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        command.execute();

        assertEquals(1, portfolio.getPortfolioCredits().size());
        assertEquals(1003, portfolio.getPortfolioCredits().get(0).getCreditIndex());
    }

    @Test
    void testExecute_InvalidChoice() {
        String input = "4\n1004\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        command.execute();

        assertEquals(0, portfolio.getPortfolioCredits().size());
    }

    @Test
    void testExecute_ExceedPortfolioLimit() {
        portfolio.setMaxCreditLimit(1);
        portfolio.addToCurrentLimit();

        String input = "1\n1001\n";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        command.execute();

        assertEquals(1, portfolio.getCurrentCreditLimit());
    }
}
