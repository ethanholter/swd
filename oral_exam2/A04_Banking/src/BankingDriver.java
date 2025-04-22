import java.util.ArrayList;
import java.util.Scanner;

/**
 * Driver class for the Banking system
 */
public class BankingDriver {

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("""
                    select an option (Leave blank to quit):
                    \ta. Add a new Account
                    \tb. post a transaction
                    \tc. Print the transactions for an account
                    \td. Print account list.
                    >>\s""");
            String option = scanner.nextLine();

            if (option.isBlank()) {
                System.out.println("Goodbye");
                break;
            }

            if (!validChoice(option, new String[]{"a", "b", "c", "d"})) {
                System.out.println("Invalid choice");
                continue;
            }

            if (option.equals("a")) {
                handleAccountCreation(scanner, accounts);
            }
            if (option.equals("b")) {
                handleTransaction(scanner, accounts);
            }
            if (option.equals("c")) {
                handleTransactionReport(scanner, accounts);
            }
            if (option.equals("d")) {
                handleAccountList(accounts);
            }
        }
    }

    /**
     * Check if a choice is inside a list of valid choices
     * @param choice the choice
     * @param choices the valid choices
     * @return true if valid, false if not
     */
    private static boolean validChoice(String choice, String[] choices) {
        for (String c : choices) {
            if (c.equals(choice)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handle printing a list of accounts
     * @param accounts the list of accounts
     */
    private static void handleAccountList(ArrayList<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found");
            return;
        }

        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * Handle printing the transaction report for an account
     * @param scanner the scanner
     * @param accounts the list of accounts
     */
    private static void handleTransactionReport(Scanner scanner, ArrayList<Account> accounts) {
        Account account = null;

        System.out.print("""
                Enter the account number:
                >>\s""");
        String accountNumber = scanner.nextLine();

        for (Account a : accounts) {
            if (a.accountNumber.equals(accountNumber)) {
                account = a;
                break;
            }
        }

        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.println(account.getTransactionReport());
    }

    /**
     * Handle posting a transaction to an account
     * @param scanner the scanner
     * @param accounts the list of accounts
     */
    private static void handleTransaction(Scanner scanner, ArrayList<Account> accounts) {
        Account account = null;

        System.out.print("""
                Enter the account number:
                >>\s""");
        String accountNumber = scanner.nextLine();

        for (Account a : accounts) {
            if (a.accountNumber.equals(accountNumber)) {
                account = a;
                System.out.println("Account found");
                break;
            }
        }

        if (account == null) {
            System.out.println("Account not found");
            return;
        }

        System.out.print("""
                Enter the transaction type:
                \ta. Credit
                \tb. Debit
                >>\s""");
        String transactionType = scanner.nextLine();

        if (!validChoice(transactionType, new String[]{"a", "b"})) {
            System.out.println("Invalid choice");
            return;
        }

        System.out.print("""
                Enter the transaction amount:
                >>\s""");
        double transactionAmount = Double.parseDouble(scanner.nextLine());

        boolean isSuccessful = false;
        if (transactionType.equals("a")) {
            isSuccessful = account.credit(transactionAmount);
        } else if (transactionType.equals("b")) {
            isSuccessful = account.debit(transactionAmount);
        }

        if (isSuccessful) {
            System.out.println("Transaction successful");
        } else {
            System.out.println("Transaction failed (Make sure you have a sufficient balance)");
        }
    }

    /**
     * Handle creating a new account
     * @param scanner the scanner
     * @param accounts the list of accounts
     */
    private static void handleAccountCreation(Scanner scanner, ArrayList<Account> accounts) {
        System.out.print("""
                Enter the account type:
                \ta. Checking
                \tb. Loan
                >>\s""");
        String accountType = scanner.nextLine();

        if (!validChoice(accountType, new String[]{"a", "b"})) {
            System.out.println("Invalid choice");
            return;
        }

        System.out.print("""
                Enter the account number:
                >>\s""");
        String accountNumber = scanner.nextLine();

        for (Account a : accounts) {
            if (a.accountNumber.equals(accountNumber)) {
                System.out.println("Account number already exists");
                return;
            }
        }

        System.out.print("""
                Enter the account holder:
                >>\s""");
        String accountHolder = scanner.nextLine();


        System.out.print("""
                Enter the initial balance (leave blank for 0.0):
                >>\s""");
        // weird workaround to handle empty string and also Scanner.nextDouble() not working
        String initialBalanceString = scanner.nextLine();
        double initialBalance;

        try {
            initialBalance = Double.parseDouble(initialBalanceString.isEmpty() ? "0" : initialBalanceString);
        } catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }

        try {
            if (accountType.equals("a")) {
                System.out.print("""
                        Enter the overdraft limit (leave blank for 0.0):
                        >>\s""");
                // weird workaround to handle empty string and also Scanner.nextDouble() not working
                String overdraftLimitString = scanner.nextLine();
                double overdraftLimit;
                try {
                    overdraftLimit = Double.parseDouble(overdraftLimitString.isEmpty() ? "0" : overdraftLimitString);
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    return;
                }

                accounts.add(new CheckingAccount(accountNumber, accountHolder, initialBalance, overdraftLimit));
            } else if (accountType.equals("b")) {
                accounts.add(new LoanAccount(accountNumber, accountHolder, initialBalance));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating account: " + e.getMessage());
        }
    }
}
