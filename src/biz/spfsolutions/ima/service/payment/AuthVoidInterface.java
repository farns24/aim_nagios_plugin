package biz.spfsolutions.ima.service.payment;

public interface AuthVoidInterface {

	public String getTransactionKey();
	
	public String getApiLogin();
	
	public String getAimUrl();
	
	public String getCimUrl();
	
	public Long getProfileId();
	
	public Long getPaymentProfileId();
}
