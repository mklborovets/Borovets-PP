package portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import portfolio.UserPortfolio;
import credit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class UserPortfolioTest {

    private UserPortfolio userPortfolio;
    private Credit bankCredit;
    private Credit personalCredit;

    @BeforeEach
    void setUp() {
        userPortfolio = new UserPortfolio();
        bankCredit = new BankCredit("Test Bank", "Standard Credit", 10000, 5.5, 12, 1);
        personalCredit = new PersonalCredit("Джон Теллер", 5000, 6.5, 10, 2);
    }

    @Test
    void testAddCredit() {
        userPortfolio.addCredit(bankCredit);
        userPortfolio.addCredit(personalCredit);
        assertEquals(2, userPortfolio.getPortfolioCredits().size());
        assertTrue(userPortfolio.getPortfolioCredits().contains(bankCredit));
        assertTrue(userPortfolio.getPortfolioCredits().contains(personalCredit));
    }

    @Test
    void testRemoveCreditValidIndex() {
        userPortfolio.addCredit(bankCredit);
        userPortfolio.addCredit(personalCredit);

        userPortfolio.removeCredit(0);
        assertEquals(1, userPortfolio.getPortfolioCredits().size());
        assertFalse(userPortfolio.getPortfolioCredits().contains(bankCredit));
    }

    @Test
    void testRemoveCreditInvalidIndex() {
        userPortfolio.addCredit(bankCredit);

        userPortfolio.removeCredit(5);
        assertEquals(1, userPortfolio.getPortfolioCredits().size());
    }

    @Test
    void testSetCurrentCreditLimit(){
        userPortfolio.setCurrentCreditLimit(4);

        assertEquals(4,userPortfolio.getCurrentCreditLimit());
    }

    @Test
    void testAddToCurrentLimit() {
        userPortfolio.setCurrentCreditLimitZero();
        userPortfolio.addToCurrentLimit();
        userPortfolio.addToCurrentLimit();
        assertEquals(2, userPortfolio.getCurrentCreditLimit());
    }

    @Test
    void testRemoveFromCurrentLimit() {
        userPortfolio.setCurrentCreditLimitZero();
        userPortfolio.addToCurrentLimit();
        userPortfolio.addToCurrentLimit();
        userPortfolio.removeFromCurrentLimit();
        assertEquals(1, userPortfolio.getCurrentCreditLimit());
    }

    @Test
    void testSetMaxCreditLimit() {
        userPortfolio.setMaxCreditLimit(5);
        assertEquals(5, userPortfolio.getMaxCreditLimit());
    }

    @Test
    void testDisplayPortfolioCredits() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        userPortfolio.displayportfolioCredits();
        String expectedEmptyOutput = "Портфоліо порожнє.\n\n";
        assertEquals(expectedEmptyOutput.trim(), outputStream.toString().trim());

        outputStream.reset();

        userPortfolio.addCredit(bankCredit);
        userPortfolio.addCredit(personalCredit);
        userPortfolio.displayportfolioCredits();

        String expectedOutput = "Ваше портфоліо кредитів:\n" +
                "Номер кредиту: 1\n" +
                "Сума: 10000.0 | Відсоткова ставка: 5.5% | Термін: 12 місяців\n\n" +
                "Номер кредиту: 2\n" +
                "Сума: 5000.0 | Відсоткова ставка: 6.5% | Термін: 10 місяців\n\n";

        String standardizedActualOutput = outputStream.toString().replace("\r\n", "\n").replace("\r", "\n");
        String standardizedExpectedOutput = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(standardizedExpectedOutput.trim(), standardizedActualOutput.trim());
        System.setOut(System.out);
    }
}
