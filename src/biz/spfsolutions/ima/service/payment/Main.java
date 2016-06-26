package biz.spfsolutions.ima.service.payment;

public class Main {

	public static void main(String[] args) {
		Main testInstance = new Main();
		
		
		Integer orderNumber =Integer.parseInt(args[0]);
		
		String loginId = args[1];
		String transactionKey = args[2];
		String url = args[3];
		String cardNumber = args[4];
		String expDate = args[5];
		String nameOnCard = args[6];
		String zipCode = args[7];
		
		testInstance.testPlugin(orderNumber , loginId , transactionKey, url, cardNumber, expDate, nameOnCard, zipCode);
	}

	private void testPlugin(Integer orderNumber, String loginId, String transactionKey, String url, String cardNumber, String expDate, String nameOnCard, String zipCode) {
		AimCore aim = new AimCore();
		 PaymentResponseObject result = aim.processAimTransaction(orderNumber, loginId, transactionKey, url, 0.1, cardNumber, expDate, nameOnCard, zipCode);
		
		 aim.refundPayment(result.getTransactionId(), loginId, url, transactionKey);
		if (result.isSuccessful())
		{
			System.out.println("OK Payment processed Successfully");
			System.exit(0);
		}
		else
		{
			System.out.println("WARNING "+ result.toString());
			System.exit(1);
		}
		
	}

}
