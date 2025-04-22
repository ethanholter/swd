import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAccounts {

    @Test
    public void testCheckingAccount() {
        CheckingAccount acct = new CheckingAccount("1234", "John Doe", 100.0, 50.0);
        assertTrue(acct.credit(25.0));
        assertEquals(75.0, acct.getBalance());
        assertTrue(acct.debit(25.0));
        assertEquals(100.0, acct.getBalance());
        assertTrue(acct.debit(200.0));
        assertFalse(acct.credit(400.0));
        assertEquals(300.0, acct.getBalance());
        assertEquals(50.0, acct.getOverdraftLimit());
        acct.setOverdraftLimit(100.0);
        assertEquals(100.0, acct.getOverdraftLimit());
    }

    @Test
    public void testCheckingInputValidation() {
        // https://www.baeldung.com/junit-assert-exception#junit-5
        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingAccount("1234", "John Doe", 100.0, -50.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingAccount("1234", "John Doe", -50, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingAccount("", "John Doe", 0, -50);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingAccount("123", "", 0, -50);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CheckingAccount("abc", "John Doe", 0, 50);
        });
    }

    @Test
    public void testLoanInputValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LoanAccount("1234", "John Doe", -100.0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new LoanAccount("", "John Doe", 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new LoanAccount("123", "", 0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new LoanAccount("abc", "John Doe", 0);
        });
    }

}
