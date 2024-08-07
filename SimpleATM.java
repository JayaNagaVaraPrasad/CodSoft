import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleATM {
    static class Account {
        private String accountNumber;
        private String pin;
        private double balance;

        public Account(String accountNumber, String pin, double initialBalance) {
            this.accountNumber = accountNumber;
            this.pin = pin;
            this.balance = initialBalance;
        }

        public boolean authenticate(String enteredPin) {
            return this.pin.equals(enteredPin);
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: $" + amount);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        public boolean withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: $" + amount);
                return true;
            } else {
                System.out.println("Invalid or insufficient funds.");
                return false;
            }
        }
    }

    static class ATM {
        private Map<String, Account> accounts;

        public ATM() {
            accounts = new HashMap<>();
            // Adding some initial accounts
            accounts.put("123456", new Account("123456", "1234", 500.00));
            accounts.put("654321", new Account("654321", "4321", 1000.00));
        }

        public Account getAccount(String accountNumber) {
            return accounts.get(accountNumber);
        }

        public void addAccount(Account account) {
            accounts.put(account.getAccountNumber(), account);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.println("Welcome to the ATM");

        // User authentication
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        Account account = atm.getAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        if (!account.authenticate(enteredPin)) {
            System.out.println("Incorrect PIN.");
            return;
        }

        // Main Menu
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Exiting. Thank you for using the ATM.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
