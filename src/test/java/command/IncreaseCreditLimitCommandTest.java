package command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import portfolio.UserPortfolio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IncreaseCreditLimitCommandTest {

    private UserPortfolio portfolio;
    private IncreaseCreditLimitCommand increaseCreditLimitCommand;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        portfolio = new UserPortfolio();
        portfolio.setMaxCreditLimit(5);
        increaseCreditLimitCommand = new IncreaseCreditLimitCommand(portfolio);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testIncreaseLimit() {
        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        increaseCreditLimitCommand.execute();

        assertEquals(6, portfolio.getMaxCreditLimit());
        assertTrue(outputStream.toString().contains("Тепер ви можете відкрити до - 6 кредитів"));
    }

    @Test
    void testDecreaseLimit() {
        String simulatedInput = "2\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        increaseCreditLimitCommand.execute();

        assertEquals(4, portfolio.getMaxCreditLimit());
        assertTrue(outputStream.toString().contains("Тепер ви можете відкрити до - 4 кредитів"));
    }

    @Test
    void testNeutralOption() {
        String simulatedInput = "3\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        increaseCreditLimitCommand.execute();

        assertEquals(5, portfolio.getMaxCreditLimit());
        assertTrue(outputStream.toString().contains("Тепер ви можете відкрити до - 5 кредитів"));
    }
}
