import java.util.Arrays;

/**
 * Acts as a grouping of cash for a given currency
 */
public class Balance {
    private Currency currency;
    private int[] bills;

    /**
     * Creates a new balance with the given currency and amount
     *
     * @param currency
     * @param amount
     */
    public Balance(Currency currency, float amount) {
        this.currency = currency;
        this.bills = new int[currency.numDenominations()];
        Arrays.fill(bills, 0);
        this.deposit(amount);
    }

    // Helper methods
    private int dollarsToCents(float dollars) {
        return Math.round(dollars * 100);
    }

    private float centsToDollars(int cents) {
        return cents / 100f;
    }

    private int getCents() {
        int total = 0;
        for (int i = 0; i < bills.length; i++) {
            total += bills[i] * currency.getDenomination(i);
        }
        return total;
    }

    private void deposit(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount cannot be negative");
        }

        int cents = dollarsToCents(amount);

        for (int i = 0; i < bills.length; i++) {
            int x = cents / currency.getDenomination(i);
            bills[i] += x;
            cents -= x * currency.getDenomination(i);

            if (cents == 0) {
                break;
            }
        }
    }

    // Public methods

    /**
     * Deposits the given amount into the balance. Assumes the currency is the same as the balance
     * @param amount
     */
    public Balance withdraw(float amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive");
        }

        if (amount > getBalance()) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        int cents = dollarsToCents(amount);

        int bal = getCents();
        bal -= cents;
        Arrays.fill(bills, 0);
        deposit(centsToDollars(bal));
        return new Balance(currency, amount);
    }

    /**
     * Converts balance to a new currency. If the new currency cannot be represented
     * will be rounded by at most 1 unit of the more valuable currency
     *
     * @param newCurrency
     * @param exchangeRate
     */
    public void exchangeCurrency(Currency newCurrency, float exchangeRate) {
        if (exchangeRate <= 0) {
            throw new IllegalArgumentException("exchange rate must be positive");
        }

        int newBal = Math.round((getBalance() * exchangeRate));
        this.currency = newCurrency;
        this.bills = new int[currency.numDenominations()];
        deposit(newBal);

    }

    // Getters
    public Currency getCurrency() {
        return currency;
    }

    public int[] getBills() {
        return bills;
    }

    public float getBalance() {
        return centsToDollars(getCents());
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(String.format("Total: $%,.2f %s\n", (float) getBalance(), currency.getName()));
        for (int i = 0; i < bills.length; i++) {
            out.append(String.format("%.0f x $%,.2f\n", (float) bills[i], (float) currency.getDenomination(i) / 100));
        }
        return out.toString();
    }
}
