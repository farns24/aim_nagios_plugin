package biz.spfsolutions.ima.service.payment;

import java.util.HashMap;
/**
 * 
 * @author Michael Farnsworth
 *
 */
public class SavedPaymentModel {
	
	//Constants
	
	public SavedPaymentModel(SavedPaymentModel savedPayment) {
		this.data = new HashMap<String, String>(savedPayment.data);
	}
	
	public SavedPaymentModel(HashMap<String, String> data) {
		super();
		this.data = data;
	}
	public SavedPaymentModel() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SavedPaymentModel other = (SavedPaymentModel) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SavedPaymentModel [data=" + data + "]";
	}
	public static final String AUTH_USERID = "auth_user_id";
	public static final String AUTH_PROFILEID = "auth_profile_id";
	public static final String AUTH_PROFILE_PAYMENT_ID = "auth_profile_payment_id";
	public static final String AUTH_CARD_ID = "auth_card_id";
	public static final String AUTH_PREFERED_CARD = "auth_prefered_card";
	public static final String AUTH_DESCRIPTION = "auth_description";
	public static final String AUTH_LOGIN_ID = "auth_login_id";
	
	public static final String MERC_TOKEN = "Mercury_Token";
	public static final String MERC_CARD_ID = "Mercury_card_id";
	
	
	
	
	public HashMap<String, String> data = new HashMap<String, String>();
	
	public void create_authorize_entry(
			String userId, String profileId,
			String profilePaymentId,
			String cardId, 
			int preferedCard,
			String description, 
			String authLogin)
	{
		
		data.clear();
		data.put(AUTH_USERID, userId);
		data.put(AUTH_PROFILEID, profileId);
		data.put(AUTH_PROFILE_PAYMENT_ID, profilePaymentId);
		data.put(AUTH_CARD_ID, cardId);
		data.put(AUTH_PREFERED_CARD, String.valueOf(preferedCard));
		data.put(AUTH_DESCRIPTION, description);
		data.put(AUTH_LOGIN_ID, authLogin);
	}
	/**
	 * 
	 * @param profilePaymentId
	 * @param cardId
	 */
	public void create_mercury_entry(String profilePaymentId, String cardId)
	{
		data.clear();
		data.put(MERC_TOKEN, profilePaymentId);
		data.put(MERC_CARD_ID, cardId);
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public String getAuthId() throws Exception {
		
		if (!data.containsKey(AUTH_CARD_ID))
		{
			throw new Exception("No Auth card contained");
		}
		
		return data.get(SavedPaymentModel.AUTH_CARD_ID);
	
	}

	public String getMaskedAuthId()  {
		return data.get(AUTH_CARD_ID);
	} 
}

