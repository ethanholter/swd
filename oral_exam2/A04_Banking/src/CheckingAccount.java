/**
 * Account subclass for checking accounts
 */
public class CheckingAccount extends Account {
    private double overdraftLimit;

    /**
     * Constructor
     * @param accountNumber the account number
     * @param accountHolder the account holder
     * @param initialBalance the initial balance
     * @param overdraftLimit the overdraft limit
     */
    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance, double overdraftLimit) {
        super(accountNumber, accountHolder, initialBalance);
        setOverdraftLimit(overdraftLimit);
    }

    /**
     * Constructor - default overdraft limit of 0
     * @param accountNumber the account number
     * @param accountHolder the account holder
     * @param initialBalance the initial balance
     */
    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance) {
        super(accountNumber, accountHolder, initialBalance);
        this.overdraftLimit = 0.0;
    }

    /**
     * Constructor - default initial balance of 0 and overdraft limit of 0
     * @param accountNumber the account number
     * @param accountHolder the account holder
     */
    public CheckingAccount(String accountNumber, String accountHolder) {
        super(accountNumber, accountHolder);
        this.overdraftLimit = 0.0;
    }

    /**
     * Credit the account (subtract from balance)
     * @param amount the amount to credit
     * @return true if successful, false if not
     */
    @Override
    public boolean credit(double amount) {
        if (amount < 0) {
            return false;
        }

        if (balance - amount < -overdraftLimit) {
            return false;
        }
        balance -= amount;
        postTransaction("Credit", amount);
        return true;
    }

    /**
     * Debit the account (add to balance)
     * @param amount the amount to debit
     * @return true if successful, false if not
     */
    @Override
    public boolean debit(double amount) {
        if (amount < 0) {
            return false;
        }

        balance += amount;
        postTransaction("Debit", amount);
        return true;
    }

    /**
     * Get a transaction report including account type
     * @return the transaction report
     */
    @Override
    public String getTransactionReport() {
        return "Account Type: Checking\n" + super.getTransactionReport();
    }

    /**
     * Get a string representation of the account including account type
     * @return the string representation
     */
    @Override
    public String toString() {
        return  super.toString() + "\tAccount Type: Checking";
    }

    /**
     * Get the overdraft limit
     * @return the overdraft limit
     */
    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    /**
     * Set the overdraft limit
     * @param overdraftLimit the overdraft limit
     */
    public void setOverdraftLimit(double overdraftLimit) {
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }
}
