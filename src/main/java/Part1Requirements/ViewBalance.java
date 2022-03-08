package Part1Requirements;

public class ViewBalance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
import java.io.*;
import java.util.*;
 
public class CheckingAccountBalance
 
{
	static final double MINIMUM_BALANCE = 1000.00;
	static final double SERVICE_CHARGE = 25.00;
 
	public static void main (String[] args)
				throws FileNotFoundException
{
		int acctNumber;
		double beginningBalance;
		double accountBalance;
 
		double amountDeposited = 0.0;
		int numberOfDeposits = 0;
 
		double amountWithdrawn = 0.0;
		int numberOfWithdrawals = 0;
 
		double interestPaid = 0.0;
 
		char transactionCode;
		double transactionAmount;
 
		boolean isServiceCharged = false;
 
		Scanner inFile = new Scanner (new FileReader ("money.txt"));
 
		PrintWriter outFile = new PrintWriter("money.out");
 
		acctNumber = inFile.nextInt();
		beginningBalance = inFile.nextDouble();
 
		accountBalance = beginningBalance;
 
		while (inFile.hasNext())
		{
			transactionCode = inFile.next().charAt(0);
			transactionAmount = inFile.nextDouble();
 
			switch (transactionCode)
			{
				case 'D':
				case 'd':
 
					accountBalance = accountBalance + transactionAmount;
					amountDeposited = amountDeposited + transactionAmount;
 
					numberOfDeposits++;
					break;
 
				case 'I':
				case 'i':
 
					accountBalance = accountBalance + transactionAmount;
					interestPaid = interestPaid + transactionAmount;
 
					break;
 
				case 'W':
				case 'w':
 
					accountBalance = accountBalance - transactionAmount;
					amountWithdrawn = amountWithdrawn + transactionAmount;
 
					numberOfWithdrawals++;
 
				if ((accountBalance < MINIMUM_BALANCE) && (!isServiceCharged))
				{
					accountBalance = accountBalance - SERVICE_CHARGE;
					isServiceCharged = true;
				}
				break;
 
				default:
					System.out.println("Invalid transaction code: "
						+ transactionCode + " "
							+ transactionAmount);
 
			}
 
		}
 
				outFile.printf("Account Number: %d%n", acctNumber);
				outFile.printf("Beginning Balance: $%.2f %n", beginningBalance);
				outFile.printf("Ending Balance: $%.2f %n", accountBalance);
				outFile.println();
				outFile.printf("Interest Paid: $%.2f %n", interestPaid);
				outFile.printf("Amount Deposited: $%.2f %n", amountDeposited);
				outFile.printf("Number of Deposits: $%.2f %n", numberOfDeposits);
				outFile.println();
				outFile.printf("Amount Withdraw: $%.2f %n", amountWithdrawn);
				outFile.printf("Number of Withdrawals: %d%n", numberOfWithdrawals);
 
				outFile.println();
 
				if (isServiceCharged)
					outFile.printf("Service Charge: $%.2f %n", SERVICE_CHARGE);
 
						outFile.close();
 
 
 
 
	}
}
