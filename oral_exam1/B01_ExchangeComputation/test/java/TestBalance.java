import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestBalance {

    @Test
    void zeroBalance() {
        Balance balance = new Balance(Currency.USD, 0);
        assertEquals(0, balance.getBalance());
    }

    @Test
    void positiveBalance() {
        Balance balance = new Balance(Currency.USD, 100);
        assertEquals(100, balance.getBalance());
    }

    @Test
    void negativeBalance() {
        assertThrows(Exception.class, () -> new Balance(Currency.USD, -100));
    }

    @Test
    void overWithdraw() {
        Balance balance = new Balance(Currency.USD, 100);
        assertThrows(Exception.class, () -> balance.withdraw(200));
    }

    @Test
    void exchangeCurrency() {
        Balance balance = new Balance(Currency.USD, 100);
        balance.exchangeCurrency(Currency.SWD, 1.2f);
        assertEquals(120, balance.getBalance());
    }

    @Test
    void exchangeCurrencyWithZeroBalance() {
        Balance balance = new Balance(Currency.USD, 0);
        balance.exchangeCurrency(Currency.SWD, 1.2f);
        assertEquals(0, balance.getBalance());
    }

    @Test
    void negativeExchangeCurrency() {
        Balance balance = new Balance(Currency.USD, 100);
        assertThrows(Exception.class, () -> balance.exchangeCurrency(Currency.SWD, -1.2f));
    }

    @ParameterizedTest(name="run #{index} with [{arguments}]")
    @CsvSource({"100, 1.5", "100, 1.2", "100, 1.0", "100, 0.8", "100, 0.5"})
    void multipleExchangeCurrency(float initialBal, float rate) {
        Balance balance = new Balance(Currency.USD, initialBal);
        balance.exchangeCurrency(Currency.SWD, rate);
        balance.exchangeCurrency(Currency.USD, 1/rate);
        assertEquals(initialBal, balance.getBalance());
    }

    @Test
    void exchangeCurrencyWithZeroRate() {
        Balance balance = new Balance(Currency.USD, 100);
        assertThrows(Exception.class, () -> balance.exchangeCurrency(Currency.SWD, 0));
    }

    void largeExchangeCurrency() {
        Balance balance = new Balance(Currency.USD, 100);
        balance.exchangeCurrency(Currency.SWD, 1000000);
        assertEquals(100000000, balance.getBalance());
    }
}
