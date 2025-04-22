import javax.swing.*;
import java.awt.*;

public class RegisterGUI extends JFrame {
    Register register;
    JLabel balance;
    JLabel exchangeRate;
    JButton withdraw;
    JTextArea withdrawInput;
    JButton setExchangeRate;
    JTextArea setExchangeRateInput;
    JButton openAccount;
    JTextArea openAccountInput;
    JButton closeAccount;
    JTextArea output;

    public RegisterGUI() {
        super("Register");
        setTitle("Register");
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(new GridLayout(5, 2, 5, 5));

        balance = new JLabel("Balance: N/A");
        add(balance);

        exchangeRate = new JLabel("Exchange Rate: 1.0");
        add(exchangeRate);


        withdraw = new JButton("Withdraw");
        withdraw.addActionListener(e -> onWithdraw());
        add(withdraw);

        withdrawInput = new JTextArea("");
        withdrawInput.setBackground(new Color(200, 200, 200));
        add(withdrawInput);

        setExchangeRate = new JButton("Set Exchange Rate");
        setExchangeRate.addActionListener(e -> onSetExchangeRate());
        add(setExchangeRate);

        setExchangeRateInput = new JTextArea("");
        setExchangeRateInput.setBackground(new Color(200, 200, 200));
        add(setExchangeRateInput);

        openAccount = new JButton("Open Account");
        openAccount.addActionListener(e -> onOpenAccount());
        add(openAccount);

        openAccountInput = new JTextArea("");
        openAccountInput.setBackground(new Color(200, 200, 200));
        add(openAccountInput);

        closeAccount = new JButton("Close Account");
        closeAccount.addActionListener(e -> onCloseAccount());
        add(closeAccount);

        output = new JTextArea("Output:\n");
        output.setEditable(false);
        output.setBackground(new Color(200, 200, 200));
        output.setFont(new Font("Arial", Font.PLAIN, 8));

        add(output);

        register = new Register();
        register.setUSDtoSWD(1);
    }

    private void updateBalance() {
        balance.setText("Balance: " + register.getAccountBalance(1).getBalance());
    }

    private void updateExchangeRate(float newRate) {
        exchangeRate.setText("Exchange Rate: " + newRate);

    };

    private void onWithdraw() {
        float amount = Float.parseFloat(withdrawInput.getText());
        Balance bal = register.withdraw(1, amount);
        if (bal != null) {
            output.setText("Output:\n" + bal + " withdrawn");
            updateBalance();
        }
        updateBalance();
    }

    private void onSetExchangeRate() {
        float newRate = Float.parseFloat(setExchangeRateInput.getText());
        register.setUSDtoSWD(newRate);
        updateExchangeRate(newRate);
    }

    private void onOpenAccount() {
        register.openAccount(1, Float.parseFloat(openAccountInput.getText()));
        updateBalance();
    }

    private void onCloseAccount() {
        output.setText("Output:\n" + register.closeAccount(1));
        updateBalance();
    }



    // ==== TODO ====
    // Balance display
    // Exchange rate display
    // change exchange rate
    // open account
    // close account
    // withdraw
    // select account


    /*
    gather the user input, the current exchange rate, account balance in US dollars, and account balance in SWD dollars
    , should be displayed in the GUI and refreshed as appropriate.
     */

}
