package command;

import command.ViewPortfolio;
import credit.BankCredit;
import credit.PersonalCredit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import portfolio.UserPortfolio;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ViewPortfolioTest {
    private UserPortfolio portfolio;
    private ViewPortfolio viewPortfolio;

    @BeforeEach
    void setUp() {
        portfolio = new UserPortfolio();
        viewPortfolio = new ViewPortfolio(portfolio);
    }

    @Test
    void testExecuteWithEmptyPortfolio() {
        portfolio.getPortfolioCredits().clear();
        viewPortfolio.execute();

        assertTrue(portfolio.getPortfolioCredits().isEmpty());
    }

    @Test
    void testExecuteWithPersonalCreditInPortfolio() {
        PersonalCredit personalCredit = new PersonalCredit("Джон Теллер", 5000.0, 5, 5, 12);
        portfolio.addCredit(personalCredit);

        viewPortfolio.execute();

        assertEquals(1, portfolio.getPortfolioCredits().size());
        assertEquals(personalCredit, portfolio.getPortfolioCredits().get(0));
        assertEquals("Джон Теллер", personalCredit.getLenderName());
        assertEquals(5000.0, personalCredit.getAmount());
    }

    @Test
    void testExecuteWithBankCreditInPortfolio() {
        BankCredit bankCredit = new BankCredit("MyBank", "Mortgage", 10000.0, 5, 4, 24);
        portfolio.addCredit(bankCredit);

        viewPortfolio.execute();

        assertEquals(1, portfolio.getPortfolioCredits().size());
        assertEquals(bankCredit, portfolio.getPortfolioCredits().get(0));
        assertEquals("MyBank", bankCredit.getBankName());
        assertEquals(10000.0, bankCredit.getAmount());
    }

    @Test
    void testExecuteWithMultipleCreditsInPortfolio() {
        PersonalCredit personalCredit = new PersonalCredit("Джон Теллер", 5000.0, 2, 5, 12);
        BankCredit bankCredit = new BankCredit("MyBank", "Mortgage", 10000.0, 3, 4, 24);
        portfolio.addCredit(personalCredit);
        portfolio.addCredit(bankCredit);

        viewPortfolio.execute();

        assertEquals(2, portfolio.getPortfolioCredits().size());
        assertEquals(personalCredit, portfolio.getPortfolioCredits().get(0));
        assertEquals(bankCredit, portfolio.getPortfolioCredits().get(1));
        assertEquals("Джон Теллер", personalCredit.getLenderName());
        assertEquals("MyBank", bankCredit.getBankName());
    }
}
