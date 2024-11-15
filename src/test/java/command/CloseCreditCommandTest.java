package command;

import credit.PersonalCredit;
import credit.BankCredit;
import portfolio.UserPortfolio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CloseCreditCommandTest {

    private UserPortfolio portfolio;
    private CloseCreditCommand closeCreditCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        portfolio = new UserPortfolio();
        closeCreditCommand = new CloseCreditCommand(portfolio);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testCloseAllCredits() {
        portfolio.addCredit(new PersonalCredit("Джон Теллер", 10000, 3.5, 3, 1111));
        portfolio.addCredit(new BankCredit("АБанк", "Кредит на Машину", 375000, 4.7, 24, 1190));

        String simulatedInput = "-1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        closeCreditCommand.execute();

        assertTrue(portfolio.getPortfolioCredits().isEmpty());
        assertEquals(0, portfolio.getCurrentCreditLimit());
        assertTrue(outputStream.toString().contains("Всі кредити були видалені з портфоліо."));
    }

    @Test
    void testCloseSpecificCredit() {
        portfolio.addCredit(new PersonalCredit("Джон Теллер", 10000, 3.5, 3, 1111));
        portfolio.addCredit(new BankCredit("АБанк", "Кредит на Машину", 375000, 4.7, 24, 1190));

        String simulatedInput = "1111\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        closeCreditCommand.execute();

        assertEquals(1, portfolio.getPortfolioCredits().size());
        assertEquals(1190, portfolio.getPortfolioCredits().get(0).getCreditIndex());
        assertTrue(outputStream.toString().contains("Кредит видалено з портфоліо."));
    }

    @Test
    void testInvalidCreditIndex() {
        portfolio.addCredit(new PersonalCredit("Джон Теллер", 10000, 3.5, 3, 1111));

        String simulatedInput = "1234\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        closeCreditCommand.execute();

        assertEquals(1, portfolio.getPortfolioCredits().size());
        assertTrue(outputStream.toString().contains("Кредит з таким індексом не знайдено."));
    }
}
