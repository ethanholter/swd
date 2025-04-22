/**
 * Account class for tracking a single customer's balance
 */
public class Account {
    private Balance balance;
    private Boolean status;

    /**
     * Default constructor for Account which starts with a closed account
     */
    public Account(){
        status = false;
    }

    /**
     * Constructor for Account which starts with an open account
     * @param balance the initial balance of the account
     */
    public Account(Balance balance){
        openAccount(balance);
    }

    /**
     * Opens an account with the given balance
     * @param balance the initial balance of the account
     */
    public void openAccount(Balance balance){
        if (status != null && status) {
            throw new RuntimeException("Cannot open an already open account");
        }
        status = true;
        this.balance = balance;
    }

    /**
     * Closes the account and returns the balance
     * @return the balance of the account in USD
     */
    public Balance closeAccount(){
        if (!status){
            throw new RuntimeException("Cannot close an already closed account");
        }

        status = false;
        return balance;
    }

    /**
     * Withdraw the given amount into the account. Amount withdrawn may slightly differ due to conversion rates
     * @param amount the amount to withdraw in SWD
     * @return the new balance of the account in USD
     */
    public Balance withdraw(int amount){
        if (!status){
            throw new RuntimeException("Cannot withdraw from a closed account");
        }
        return balance.withdraw(amount);
    };

    public Balance getBalance(){
        return new Balance(balance.getCurrency(), balance.getBalance());
    }

}
