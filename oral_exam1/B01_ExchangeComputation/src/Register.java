import java.util.HashMap;

public class Register {

    // Conversion Coefficients
    static float USDtoSWD = 1;
    static float SWDtoUSD = 1;

    // Accounts
    private static HashMap<Integer, Account> accounts = new HashMap<>();


    public void setUSDtoSWD(float USDtoSWD) {
        Register.USDtoSWD = USDtoSWD;
        Register.SWDtoUSD = 1/USDtoSWD;
    }

    public void setSWDtoUSD(float SWDtoUSD) {
        Register.SWDtoUSD = SWDtoUSD;
        Register.USDtoSWD = 1/SWDtoUSD;
    }

    public void openAccount(int ID, float amountUSD) {
        if(accounts.containsKey(ID)) {
            throw new IllegalArgumentException("Account already exists");
        }
        if(amountUSD <= 0) {
            throw new IllegalArgumentException("Amount must be >0");
        }

        accounts.put(ID, new Account(new Balance(Currency.USD, amountUSD)));
    }

    public Balance closeAccount(int ID) {
        if(!accounts.containsKey(ID)) {
            throw new IllegalArgumentException("Account does not exist");
        }
        return accounts.remove(ID).closeAccount();
    }

    public Balance withdraw(int ID, float amountSWD) {
        if(!accounts.containsKey(ID)) {
            throw new IllegalArgumentException("Account does not exist");
        }
        Balance withdrawnBalance = accounts.get(ID).withdraw(Math.round(amountSWD*SWDtoUSD));
        withdrawnBalance.exchangeCurrency(Currency.SWD, USDtoSWD);
        return withdrawnBalance;
    }

    public Balance getAccountBalance(int ID) {
        if(!accounts.containsKey(ID)) {
            throw new IllegalArgumentException("Account does not exist");
        }
        return accounts.get(ID).getBalance();
    }
}