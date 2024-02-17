import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private double balance;
    private ArrayList<Transaction> transactionHistory = new ArrayList<>();

    public ATM() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your initial balance: ");
        this.balance = input.nextDouble();
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance -= amount;
            transactionHistory.add(new Transaction(TransactionType.WITHDRAW, amount, LocalDateTime.now()));
            System.out.println("Please collect your cash.");
            System.out.println("Your new balance is: " + balance);
        }
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction(TransactionType.DEPOSIT, amount, LocalDateTime.now()));
        System.out.println("Your money has been deposited successfully.");
        System.out.println("Your new balance is: " + balance);
    }

    public void checkBalance() {
        System.out.println("Your balance is: " + balance);
    }

    public void viewTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    public static void main(String[] args) {
        ATM atm = new ATM();

        while (true) {
            System.out.println("\nAutomated Teller Machine");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            Scanner input = new Scanner(System.in);
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    atm.withdraw(input.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    atm.deposit(input.nextDouble());
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 4:
                    atm.viewTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private enum TransactionType {
        WITHDRAW, DEPOSIT
    }

    private static class Transaction {
        private final TransactionType type;
        private final double amount;
        private final LocalDateTime timestamp;

        public Transaction(TransactionType type, double amount, LocalDateTime timestamp) {
            this.type = type;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return String.format("%s - %.2f on %s", type, amount, timestamp);
        }
    }
}
