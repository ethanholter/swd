import java.util.Scanner;

public class ExchangeComputationDriver {
    public static void main(String[] args) {
        Register register = new Register();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1) Withdraw | 2) Open Account | 3) Close Account | 4) Set Exchange Rate | 5) Exit\n");
            int choice = scanner.nextInt();
            if (choice == 1) {
                System.out.println("Enter the amount to withdraw: ");
                float amount = scanner.nextFloat();
                try {
                    System.out.println(register.withdraw(1, amount));
                } catch(Exception e) {
                        System.out.println(e.getMessage());
                }
            } else if (choice == 2) {
                System.out.println("Enter the amount to deposit: ");
                float amount = scanner.nextFloat();
                try {
                    register.openAccount(1, amount);
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 3) {
                try {
                    System.out.println(register.closeAccount(1));
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (choice == 4) {
                System.out.println("Enter the exchange rate: ");
                float rate = scanner.nextFloat();
                register.setUSDtoSWD(rate);
            } else if (choice == 5) {
                break;
            }

        }

    }
}
