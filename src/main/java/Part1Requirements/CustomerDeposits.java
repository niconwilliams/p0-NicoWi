package Part1Requirements;

public class CustomerDeposits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

import java.util.Date;

public class Account {
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;
    private double monthlyInterestRate;

    public Account() {
        id = 0;
        balance = 0;
        annualInterestRate = 0;
    }

    public Account(int iD, double balancE) {
        id = iD;
        balance = balancE;
    }

    public void setID(int iD) {
        id = iD;
    }

    public int getID() {
        return (id);
    }

    public void setBalance(double balancE) {
        balance = balancE;
    }

    public double getBalance() {
        return (balance);
    }

    public void setAnnualInterestRate(double AIR) {
        annualInterestRate = AIR;
    }

    public double getAnnualInterestRate() {
        return (annualInterestRate);
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getMonthlyInterestRate() {
        return ((annualInterestRate / 100) / 12);
    }

    public double getMonthlyInterest() {
        return (balance * monthlyInterestRate);
    }

    public void withdraw(double ammount) {
        balance = balance - ammount;
    }

    public void deposit(double ammount) {
        balance = balance + ammount;
    }
}

public class SavingsAccount extends Account {
    public SavingsAccount(int iD, double balancE) {
        super(iD, balancE);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > getBalance()) {
            System.out.println("Current Balance: " + getBalance() 
                    + "\nThe             withdrawal  cannot be made due to insufficient  funds.");
        }

    }
}



public class Test extends Account {

    public static void main(String[] args) {
        SavingsAccount a1 = new SavingsAccount(1122, 10.00);
        a1.withdraw(5.00);
        a1.deposit(00.00);
        a1.setAnnualInterestRate(4.5);
        Date dat = new Date();

        System.out.println("Balance: " + a1.getBalance() + 
                "\nMonthly Interest: " + a1.getMonthlyInterest() + 
                "\nDate Created: " + dat);

    }
}
