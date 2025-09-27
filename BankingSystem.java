import java.util.ArrayList;
abstract class BankAccount {
    private int accountNumber;   
    private String holderName;
    private double balance;

    public BankAccount(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public int getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(holderName + " deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(holderName + " withdrew: " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public abstract double calculateInterest();
}

interface Loanable {
    void applyForLoan(double amount);
    boolean calculateLoanEligibility();
}

class SavingsAccount extends BankAccount implements Loanable {
    private double interestRate = 0.04; // 4% interest

    public SavingsAccount(int accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void applyForLoan(double amount) {
        if (calculateLoanEligibility()) {
            System.out.println(getHolderName() + " applied for loan of Rs." + amount + " (Savings Account).");
        } else {
            System.out.println(getHolderName() + " is not eligible for loan.");
        }
    }

    @Override
    public boolean calculateLoanEligibility() {
        return getBalance() >= 5000; // Eligible if balance ≥ 5000
    }
}

// CurrentAccount Class
class CurrentAccount extends BankAccount implements Loanable {
    private double interestRate = 0.02; // 2% interest

    public CurrentAccount(int accountNumber, String holderName, double balance) {
        super(accountNumber, holderName, balance);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * interestRate;
    }

    @Override
    public void applyForLoan(double amount) {
        if (calculateLoanEligibility()) {
            System.out.println(getHolderName() + " applied for loan of Rs." + amount + " (Current Account).");
        } else {
            System.out.println(getHolderName() + " is not eligible for loan.");
        }
    }

    @Override
    public boolean calculateLoanEligibility() {
        return getBalance() >= 10000; // Eligible if balance ≥ 10000
    }
}

// Main Class
public class BankingSystem {
    public static void main(String[] args) {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        BankAccount acc1 = new SavingsAccount(101, "Aman", 7000);
        BankAccount acc2 = new CurrentAccount(102, "Adarsh", 15000);

        accounts.add(acc1);
        accounts.add(acc2);

        // Perform Transactions & Polymorphism
        for (BankAccount acc : accounts) {
            System.out.println("\nAccount Holder: " + acc.getHolderName());
            System.out.println("Account Number: " + acc.getAccountNumber());
            System.out.println("Balance: Rs." + acc.getBalance());

            acc.deposit(2000);
            acc.withdraw(3000);

            double interest = acc.calculateInterest();
            System.out.println("Interest Earned: Rs." + interest);

            // Loan Processing
            if (acc instanceof Loanable) {
                Loanable loanAcc = (Loanable) acc;
                loanAcc.applyForLoan(50000);
            }

            System.out.println("-------------------------");
        }
    }
}
