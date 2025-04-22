import java.util.ArrayList;

/**
 * Abstract class for an account
 */
public abstract class Account {
    /**
     * Account number
     */
    protected final String accountNumber;

    /**
     * List of transactions
     */
    protected final ArrayList<String> transactions = new ArrayList<>();

    /**
     * Account balance
     */
    protected double balance;

    /**
     * Account holder
     */
    protected String accountHolder;

    /**
     * Constructor
     * @param accountNumber the account number
     * @param accountHolder the account holder
     * @param initialBalance the initial balance
     */
    public Account(String accountNumber, String accountHolder, double initialBalance) {
        // make sure account number is alphanumeric
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("account number cannot be null or blank");
        }

        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("account holder cannot be null or blank");
        }

        for (int i = 0; i < accountNumber.length(); i++) {
            if (!Character.isDigit(accountNumber.charAt(i))) {
                throw new IllegalArgumentException("account number must only contain digits 0-9");
            }
        }

        if (initialBalance < 0) {
            throw new IllegalArgumentException("initial balance cannot be negative");
        }

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    /**
     * Constructor - initial balance of 0
     * @param accountNumber the account number
     * @param accountHolder the account holder
     */
    public Account(String accountNumber, String accountHolder) {
        this(accountNumber, accountHolder, 0.0);
    }

    /**
     * Credit the account
     * @param amount the amount to credit
     * @return true if successful, false if not
     */
    public abstract boolean credit(double amount);

    /**
     * Debit the account
     * @param amount the amount to debit
     * @return true if successful, false if not
     */
    public abstract boolean debit(double amount);

    /**
     * Post a transaction to transaction history
     * @param type the type of transaction
     * @param amount the amount of the transaction
     */
    protected void postTransaction(String type, double amount) {
        transactions.add(type + ": " + amount + "    New balance: " + balance);
    }

    /**
     * Get a list of previous transactions
     * @return the transaction report
     */
    public String getTransactionReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Account Number: ").append(accountNumber).append("\n");
        sb.append("Account Holder: ").append(accountHolder).append("\n");
        sb.append("Current Balance: ").append(balance).append("\n");
        sb.append("Transactions:\n");
        for (String transaction : transactions) {
            sb.append("\t").append(transaction).append("\n");
        }
        return sb.toString();
    }

    /**
     * Get a string representation of the account
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Account #: " + accountNumber + "\n" +
                "\tAccount Holder: " + accountHolder + "\n" +
                "\tCurrent Balance: " + balance + "\n";
    }

    /**
     * Get the account number
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Get the balance
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Get the account holder
     * @return the account holder
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * Set the account holder
     * @param accountHolder the account holder
     */
    public void setAccountHolder(String accountHolder) {
        if (accountHolder == null || accountHolder.isBlank()) {
            throw new IllegalArgumentException("account holder cannot be null or blank");
        }

        this.accountHolder = accountHolder;
    }
}
