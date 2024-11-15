package credit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class BankCreditTest {

    @Test
    void testConstructorAndGetters() {
        BankCredit bankCredit = new BankCredit("Test Bank", "Standard Credit", 10000, 5.5, 12, 1);

        assertEquals("Test Bank", bankCredit.getBankName());
        assertEquals("Standard Credit", bankCredit.getCreditName());
        assertEquals(10000, bankCredit.getAmount());
        assertEquals(5.5, bankCredit.getInterestRate());
        assertEquals(12, bankCredit.getTerm());
        assertEquals(1, bankCredit.getCreditIndex());
    }

    @Test
    void testSetters() {
        BankCredit bankCredit = new BankCredit("Test Bank", "New Credit", 10000, 5.5, 12, 1);

        bankCredit.setBankName("New Bank");
        bankCredit.setCreditName("New Credit");
        bankCredit.setAmount(20000);
        bankCredit.setInterestRate(4.5);
        bankCredit.setTerm(24);

        assertEquals("New Bank", bankCredit.getBankName());
        assertEquals("New Credit", bankCredit.getCreditName());
        assertEquals(20000, bankCredit.getAmount());
        assertEquals(4.5, bankCredit.getInterestRate());
        assertEquals(24, bankCredit.getTerm());
    }


    @Test
    void testDisplayInfo() {
        BankCredit bankCredit = new BankCredit("Test Bank", "Standard Credit", 10000, 5.5, 12, 1);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        bankCredit.displayInfo();

        System.setOut(System.out);

        String actualOutput = outputStream.toString();

        String expectedOutput = "\nКредит від банку Test Bank\n" +
                "Кредит: Standard Credit\n" +
                "Номер кредиту: 1\n" +
                "Сума: 10000.0 | Відсоткова ставка: 5.5% | Термін: 12 місяців\n\n";

        String standardizedExpected = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");
        String standardizedActual = actualOutput.replace("\r\n", "\n").replace("\r", "\n");
        assertEquals(standardizedExpected, standardizedActual);

    }
}
