import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestAccount {

    @Test
    void closedAccount() {
        Account account = new Account();
        assertThrows(Exception.class, () -> account.withdraw(100));
    }

    @Test
    void openAccount() {
        Account account = new Account(new Balance(Currency.USD, 100));
        assertNotNull(account.withdraw(100));
         assertThrows(Exception.class, () -> account.openAccount(new Balance(Currency.USD, 100)));
    }

    @Test
    void insufficientFunds() {
        Account account = new Account(new Balance(Currency.USD, 100));
        assertThrows(Exception.class,() -> account.withdraw(200));
    }

    @Test
    void closeAccount() {
        Account account = new Account();
        assertThrows(Exception.class, () -> account.closeAccount());
        assertThrows(Exception.class, () -> account.withdraw(100));
    }

    @Test
    void withdraw() {
        Account account = new Account(new Balance(Currency.USD, 100));
        assertNotNull(account.withdraw(100));
    }

    @Test
    void withdrawClosedAccount() {
        Account account = new Account();
        assertThrows(Exception.class, () -> account.withdraw(100));
    }

    @Test
    void withdrawOpenAccount() {
        Account account = new Account(new Balance(Currency.USD, 1000));
        assertNotNull(account.withdraw(100));
        assertNotNull(account.withdraw(300));
        assertNotNull(account.withdraw(150));
        assertNotNull(account.withdraw(190));
        assertNotNull(account.withdraw(260));
        assertThrows(Exception.class, () -> account.withdraw(1));


    }

    @Test
    void withdrawNegativeAmount() {
        Account account = new Account(new Balance(Currency.USD, 100));
        assertThrows(Exception.class, () -> account.withdraw(-100));
    }
}
