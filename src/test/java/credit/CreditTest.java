package credit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import credit.Credit;

class CreditTest {


    @Test
    void testConstructorAndGetters() {
        double amount = 10000.0;
        double interestRate = 5.5;
        int term = 12;
        int creditIndex = 1;

        Credit credit = new Credit(amount, interestRate, term, creditIndex) {
            @Override
            public void displayInfo() {

            }
        };

        assertEquals(amount, credit.getAmount());
        assertEquals(interestRate, credit.getInterestRate());
        assertEquals(term, credit.getTerm());
        assertEquals(creditIndex, credit.getCreditIndex());
    }

    @Test
    void testModifyTerms(){
        double amount = 10000.0;
        double interestRate = 5.5;
        int term = 12;
        int creditIndex = 1;
        Credit credit = new Credit(amount, interestRate, term, creditIndex) {
            @Override
            public void displayInfo() {

            }
        };
        credit.modifyTerms(2.4,22);
        assertEquals(2.4,credit.getInterestRate());
        assertEquals(22,credit.getTerm());

        credit.increaseLimit(2000);
        assertEquals(12000,credit.getAmount());
    }

    @Test
    void testSetters() {
        Credit credit = new Credit(0, 0, 0, 0) {
            @Override
            public void displayInfo() {

            }
        };

        credit.setAmount(20000.0);
        credit.setInterestRate(6.5);
        credit.setTerm(24);
        credit.setCreditIndex(2);

        assertEquals(20000.0, credit.getAmount());
        assertEquals(6.5, credit.getInterestRate());
        assertEquals(24, credit.getTerm());
        assertEquals(2, credit.getCreditIndex());
    }
}
