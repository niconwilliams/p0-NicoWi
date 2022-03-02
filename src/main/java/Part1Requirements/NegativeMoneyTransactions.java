package Part1Requirements;

public class NegativeMoneyTransactions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@Test(expected=UnsupportedOperationException.class)
		@Points(1)
		public void testInvalidNegativeWithdrawal() {
			Account testAct = getNewApprovedAccount();
			testAct.setBalance(18.34);
			actSrv.withdraw(testAct, -5d);

	@Test(expected=UnsupportedOperationException.class)
		@Points(1)
		public void testInvalidNegativeDeposit() {
			Account testAct = getNewApprovedAccount();
			testAct.setBalance(18.34);
			actSrv.deposit(testAct, -5d);

		
		
	}

}
