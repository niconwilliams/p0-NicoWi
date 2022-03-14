package com.revature.driver;

import java.time.LocalDateTime;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Account.AccountType;
import com.revature.beans.User;
import com.revature.beans.User.UserType;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoDB;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.dao.UserDaoFile;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.utils.SessionCache;




public class BankApplicationDriver { 
	

		public static void printLine() {
			for (int i = 0; i < 60; i++) {
				System.out.print("*"); }
		}
	
			
public static void main(String[] args) {
	BankApplicationDriver newDriver = new BankApplicationDriver();
	
	newDriver.showMenu(); 
}

	// TODO Auto-generated method stub
	
	
	

		static boolean logged = false;
		static boolean showMenu = false;
		int choice = 0;
		int id = 0;
		Scanner input = new Scanner(System.in);
		String fName = null;
		String lName = null;
		String username = null;
		String password = null;
		UserDao userDao = new UserDaoDB();
		AccountDao accountDao = new AccountDaoDB();
		UserService userService = new UserService(userDao, accountDao);
		AccountService accountService = new AccountService(accountDao);	{
	
			System.out.print("Welcome to Algorithm Bank, the BEST in Banking!" +'\n'+ "How may we help today?");
			System.out.println('\n'+"...................................................................................");
			System.out.print("Choose one of the following options" +'\n'+ "or come into a branch!");
			System.out.println('\n'+"...................................................................................");
		}
		


	public void showMenu() {
		
	 showMenu = true;
while (choice < 6) {
	
	BankApplicationDriver.printLine();
	System.out.println("...... \t\t\t\t\t\t\t\t\t\t ......");
	System.out.println("...... \t\t\t\t\t\t\t\t\t\t ......");
	System.out.println("...... \t\t\t\t CHOOSE A OPTION \t\t\t .....");
	System.out.println("......\t\t\t\t\t\t\t\t\t\t ......");
	System.out.println("...... \t\t\t\t\t\t\t\t\t\t......");
	BankApplicationDriver.printLine();
	System.out.println("\n 1. Register with us! ");
	System.out.println(" 2. Login Checkings or Savings ");
	System.out.println(" 3. View Customers ");
	System.out.println(" 4. Remove Customer ");
	
	System.out.println(" 5. Update Customer ");
	System.out.println("\n ...................\n 6. Exit Now");
	System.out.print("\n............Choose an option here! [1-6] :");
	choice = input.nextInt();
	

	switch (choice) {
	case 1:
		id = UserDaoFile.usersList.size();
		System.out.print("Enter First Name :");
		fName = input.next();
		System.out.print("Enter Last Name :");
		lName = input.next();
		System.out.print("Enter Username :");
		username = input.next();
		System.out.print("Enter Password :");
		password = input.next();

		User user = new User( username, password, fName, lName, UserType.CUSTOMER);
		userService.register(user);
		break;
	


	case 2:
		
		System.out.print("Enter Username :");
		username = input.next();
		System.out.print("Enter Password :");
		password = input.next();
		User loggedUser = userService.login(username, password);
		System.out.println("logged user :" + loggedUser);
		
		if (loggedUser != null) {
			System.out.println("Logged in Successfully!!!");
			SessionCache.setCurrentUser(loggedUser);

			int option = 0;
			int accountType = 0;
			double startingBalance = 0;

			while (option <= 6) {
				System.out.println("\t\t\t 1.Apply Account ");
				System.out.println("\t\t\t 2.Deposit");
				System.out.println("\t\t\t 3.Withdraw ");
				System.out.println("\t\t\t 4.Fund Transfer ");
				System.out.println("\t\t\t 5.Approve/Reject Account ");
				System.out.println("\t\t\t 6.View Balance ");
				System.out.println("\t\t\t 7.Logout ");
				System.out.print("Enter your option [1-7]:");
				option = input.nextInt();
				
				switch (option) {
				case 1:
					System.out.print("select the Account Type [1.Checking/2.Saving]: ");
					accountType = input.nextInt();
					System.out.print("Enter Starting balance:");
					startingBalance = input.nextDouble();
					Account account = new Account();
					account.setBalance(startingBalance);
					System.out.println("Logged user ID :" + SessionCache.getCurrentUser().get().getId());
					account.setOwnerId(loggedUser.getId());
					account.setType(accountType == 1 ? AccountType.CHECKING.toString()
							: AccountType.SAVINGS.toString());
					List<Account> accountList = new ArrayList<Account>();
					accountList.add(account);
					loggedUser.setAccounts(accountList);
					accountService.createNewAccount(loggedUser);
					account.setBalance(account.getBalance() );
					break;
				case 2:
					
					System.out.print("Enter Account ID to Deposit :");
					int accountId = 0;
					accountId = input.nextInt();
					System.out.print("Enter the amount to deposit :");
					double amount = 0;
					amount = input.nextDouble();
					account = accountDao.getAccount(accountId);
					accountService.deposit(account, amount);
					account.setBalance(account.getBalance() - amount);
					System.out.println("Deposit Complete");
					break;
				case 3:
					
					System.out.print("Enter Account ID to Withdraw :");
					accountId = 0;
					accountId = input.nextInt();
					
					System.out.print("Enter the amount to withdraw :");
					amount = 0;
					amount = input.nextDouble();
					account = accountDao.getAccount(accountId);
					accountService.withdraw(account, amount);
					System.out.println("Withdrawal Complete");
					
					break;
				case 4:
					
					accountId = 0;
					System.out.print("Enter From Account ID to Transfer Fund :");
					accountId = input.nextInt();
					int toAccountId = 0;
					System.out.print("Enter To Account ID to Transfer Fund :");
					toAccountId = input.nextInt();
					amount = 0;
					System.out.print("Enter the amount to transfer :");
					amount = input.nextDouble();
					Account fromAccount = accountDao.getAccount(accountId);
					Account toAccount = accountDao.getAccount(toAccountId);
					accountService.transfer(fromAccount, toAccount, amount);
					System.out.println("Transfer Complete");
					break;
//				
				case 5: // Approving / Rejecting Accounts
					
					
					
					
                    System.out.println("Approving / Rejecting Accounts");
                    System.out.println();
                    System.out.println("1 - Approved");
                    System.out.println("2 - Reject");
                   

                    choice = input.nextInt();
                
                    switch (choice) {

                    // ----APPROVE ACCOUNT
                    case 1:
                    	if (user.getUserType() == UserType.EMPLOYEE) {
                            System.out.println("Available accounts for all users");

                            accountDao.getAccounts().forEach(System.out::println);
                            System.out.print("Enter account ID to approve or reject: ");
                            accountId = 0;
                            accountId = input.nextInt();
                            System.out.print("Select an option [true for approve, false for reject]: ");
                            boolean optionAR = false;
                            optionAR = input.nextBoolean();
                            account = accountDao.getAccount(accountId);
                            accountService.approveOrRejectAccount(account, optionAR);
                            accountDao.updateAccount(account);
                        }
                        else {
                            System.out.println("Only employees can approve or reject transactions.");
                        }
                        System.out.println("Enter account ID:");
                        accountId = input.nextInt();
                        account = accountDao.getAccount(accountId);
                        account.setApproved(true);
                        System.out.println(account);
                    	
    				
    					
    					break;
    					
    				
                     

                    // ----REJECT ACCOUNT
                    case 2:
                        System.out.println("Enter account ID:");
                        accountId = input.nextInt();
                        account = accountDao.getAccount(accountId);
                        account.setApproved(false);
                        System.out.println(account);
                        System.out.println("Rejected Transaction");
                        break;
                       
    				default:
    					System.out.println("Enter a number between 1 to 6");
    					break;
    				}
                    
              
				case 6: // View Balance

                    System.out.println("View Balance");
                    System.out.println("Enter account ID");
                    System.out.println();

                    int balance = input.nextInt();
                    account = accountDao.getAccount(balance);
                    System.out.println(account);
                    break;	
				case 7:
					System.out.print("Do you want to Logout? (1.Yes/2.No) :");
					int logout = 0;
					logout = input.nextInt();
					if (logout == 1) {
						SessionCache.setCurrentUser(null);
						System.out.println("Algorithm Bank Thanks You! ");
						showMenu();
						
						
						
						break;
					}
					
					break;
				default:
					System.out.println("Enter a number between 1 to 6");
					break;
				}

			}
		}
		break;

	
	case 3:
		userDao.getAllUsers().forEach(System.out::println);
		break;
	case 4:
		System.out.print("Enter client Id for removal: ");
		id = input.nextInt();
		User u = userDao.getUser(id);
		userDao.removeUser(u);
		break;
	case 5:
		System.out.print("Enter client Id for Update: ");
		id = input.nextInt();
		System.out.print("Enter First Name to Update :");
		fName = input.next();
		System.out.print("Enter Last Name to Update:");
		lName = input.next();
		System.out.print("Enter Password to Update:");
		password = input.next();
		User updatedUser = new User();
		updatedUser.setId(id);
		updatedUser.setFirstName(fName);
		updatedUser.setLastName(lName);
		updatedUser.setPassword(password);
		userDao.updateUser(updatedUser);
		break;
	case 6:
		showMenu();
		System.out.println("Algorithm Bank Thanks You! ");
		
		
		break;
		
	default:
		break;
		
	}

}


input.close();


}


}

		