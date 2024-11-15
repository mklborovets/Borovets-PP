package credit;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PersonalCreditTest {

    @Test
    void testConstructorAndGetters() {
        PersonalCredit personalCredit = new PersonalCredit("Джон Теллер", 5000, 6.5, 10, 2);

        assertEquals("Джон Теллер", personalCredit.getLenderName());
        assertEquals(5000, personalCredit.getAmount());
        assertEquals(6.5, personalCredit.getInterestRate());
        assertEquals(10, personalCredit.getTerm());
        assertEquals(2, personalCredit.getCreditIndex());
    }

    @Test
    void testSetLenderName() {
        PersonalCredit personalCredit = new PersonalCredit("Джон Теллер", 5000, 6.5, 10, 2);

        personalCredit.setLenderName("Габріель Тур");

        assertEquals("Габріель Тур", personalCredit.getLenderName());
    }

    @Test
    void testDisplayInfo() {
        PersonalCredit personalCredit = new PersonalCredit("Джон Теллер", 5000, 6.5, 10, 2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        personalCredit.displayInfo();

        System.setOut(System.out);

        String actualOutput = outputStream.toString();

        String standardizedActual = actualOutput.replace("\r\n", "\n").replace("\r", "\n");
        String expectedOutput = "\nКредит від Джон Теллер\n" +
                "Номер кредиту: 2\n" +
                "Сума: 5000.0 | Відсоткова ставка: 6.5% | Термін: 10 місяців\n\n";
        String standardizedExpected = expectedOutput.replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(standardizedExpected, standardizedActual);
    }
}
