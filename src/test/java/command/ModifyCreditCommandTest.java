package command;

import command.ModifyCreditCommand;
import credit.BankCredit;
import credit.PersonalCredit;
import portfolio.UserPortfolio;
import credit.Credit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class ModifyCreditCommandTest {
    private UserPortfolio portfolio;
    private ModifyCreditCommand command;

    @BeforeEach
    void setUp() {
        portfolio = new UserPortfolio();
        portfolio.addCredit(new BankCredit("Bank Credit 1", "Car loan", 5000.0, 5.0, 12, 1563));
        portfolio.addCredit(new PersonalCredit("Personal Credit 1", 2000.0, 10.0, 24, 3261));
        command = new ModifyCreditCommand(portfolio);
    }

    @Test
    void testExecute_ModifyBankCredit() {
        String input = "1\n1563\n4\n24\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        command.execute();

        Credit modifiedCredit = portfolio.getPortfolioCredits().get(0);
        assertEquals(4, modifiedCredit.getInterestRate());
        assertEquals(24, modifiedCredit.getTerm());
    }

    @Test
    void testExecute_ModifyPersonalCredit() {
        String input = "1\n3261\n6\n18\n";

        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        command.execute();

        Credit modifiedCredit = portfolio.getPortfolioCredits().get(1);
        assertEquals(6, modifiedCredit.getInterestRate());
        assertEquals(18, modifiedCredit.getTerm());
    }

    @Test
    void testExecute_ModifyNonExistingCredit() {
        String input = "3\n9999\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        command.execute();

        Credit bankCredit = portfolio.getPortfolioCredits().get(0);
        assertEquals(5.0, bankCredit.getInterestRate());
        assertEquals(12, bankCredit.getTerm());
    }

    @Test
    void testExecute_NoCreditsAvailableForType() {
        portfolio.getPortfolioCredits().clear();
        String input = "1\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
        command.execute();

        assertTrue(portfolio.getPortfolioCredits().isEmpty());
    }
}
