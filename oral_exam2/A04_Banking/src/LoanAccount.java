/**
 * LoanAccount class extends Account class and represents a loan account.
 */
public class LoanAccount extends Account {

    /**
     * Constructor
     * @param accountNumber the account number
     * @param accountHolder the account holder
     * @param initialBalance the initial balance
     */
    public LoanAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
    }

    /**
     * Constructor - default initial balance of 0
     * @param accountNumber the account number
     * @param accountHolder the account holder
     */
    public LoanAccount(String accountNumber, String accountHolder) {
        super(accountNumber, accountHolder);
    }

    /**
     * Credit the account (add to balance)
     * @param amount the amount to credit
     * @return true if successful, false if not
     */
    @Override
    public boolean credit(double amount) {
        if (amount < 0) {
            return false;
        }

        balance += amount;
        postTransaction("Credit", amount);
        return true;
    }

    /**
     * Debit the account (subtract from balance)
     * @param amount the amount to debit
     * @return true if successful, false if not
     */
    @Override
    public boolean debit(double amount) {
        if (amount < 0) {
            return false;
        }

        if (balance - amount < 0) {
            return false;
        }
        balance -= amount;
        postTransaction("Debit", amount);
        return true;
    }

    /**
     * Get a transaction report
     * @return the transaction report
     */
    @Override
    public String getTransactionReport() {
        return "Account Type: Loan\n" + super.getTransactionReport();
    }

    /**
     * Get a string representation of the account
     * @return the string representation
     */
    @Override
    public String toString() {
        return super.toString() + "\tAccount Type: Loan";
    }
}
