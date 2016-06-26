package biz.spfsolutions.ima.service.payment;

public class PaymentResponseObject {

	
	

	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PaymentResponseObject [maskedCardId=" + maskedCardId
				+ ", transactionId=" + transactionId + ", authorizationId="
				+ authorizationId + ", responseCode=" + responseCode
				+ ", responseReasonCode=" + responseReasonCode
				+ ", displayErrorToUser=" + displayErrorToUser + "]";
	}
	private String maskedCardId;

	private String transactionId;

	private String authorizationId;
	
	private String responseCode;
	
	private String responseReasonCode;
	
	private String displayErrorToUser;

	public String getDisplayErrorToUser() {
		return displayErrorToUser;
	}
	public void setDisplayErrorToUser(String displayErrorToUser) {
		this.displayErrorToUser = displayErrorToUser;
	}
	//-----------------------------------------------------------------------
	public PaymentResponseObject(String transactionId, String authorizationId,
			String responseCode, String responseReasonCode) {
		super();
		this.transactionId = transactionId;
		this.authorizationId = authorizationId;
		this.responseCode = responseCode;
		this.responseReasonCode = responseReasonCode;
	}
	//-------------------------------------------------------------------------
	public PaymentResponseObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	//-----------------------------------Getters and setters
	
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}


	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}


	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}


	/**
	 * @return the authorizationId
	 */
	public String getAuthorizationId() {
		return authorizationId;
	}


	/**
	 * @param authorizationId the authorizationId to set
	 */
	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}


	/**
	 * @return the responseReasonCode
	 */
	public String getResponseReasonCode() {
		return responseReasonCode;
	}


	public String getMaskedCardId() {
		return maskedCardId;
	}
	/**
	 * @param responseReasonCode the responseReasonCode to set
	 */
	public void setResponseReasonCode(String responseReasonCode) {
		this.responseReasonCode = responseReasonCode;
	}
	
	public void setMaskedCardId(String cardId) {
		this.maskedCardId = cardId;
		
	}
	public boolean isSuccessful() {
		if (responseCode==null)
		{
		return false;
		}
		else if (responseReasonCode==null)
		{
			return false;
		}
		else if (responseCode.equals("1")&& responseReasonCode.equals("1"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
