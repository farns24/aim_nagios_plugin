package biz.spfsolutions.ima.service.payment;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;

public class AimCore {

	private PaymentResponseObject response;
	private URL post_url;
	private StringBuffer post_string;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PaymentResponseObject processAimTransaction(Integer orderNumber, String loginId,
			String transactionKey, String url, Double totalForOrder, String cardNumber, String expDate, String nameOnCard, String zipCode){
		response = new PaymentResponseObject();
		// By default, this sample code is designed to post to our test server
		// for
		// developer accounts: https://test.authorize.net/gateway/transact.dll
		// for real accounts (even in test mode), please make sure that you are
		// posting to: https://secure.authorize.net/gateway/transact.dll
		// URL post_url;
		// This section takes the input fields and converts them to the proper
		// format
		// for an http post. For example: "x_login=username&x_tran_key=a1B2c3D4"
		// StringBuffer post_string;
		try {
			post_url = new URL(url);
			// this is passed in with the menu with variable name auth_url:
			Hashtable post_values = new Hashtable();
			// auth_login
			// auth_key
			// the API Login ID and Transaction Key must be replaced with valid
			// values
			post_values.put("x_login", loginId);
			post_values.put("x_tran_key", transactionKey);

			post_values.put("x_version", "3.1");
			post_values.put("x_delim_data", "TRUE");
			post_values.put("x_delim_char", "|");
			post_values.put("x_relay_response", "FALSE");

			post_values.put("x_type", "AUTH_CAPTURE");
			post_values.put("x_method", "CC");
			post_values.put("x_card_num", cardNumber);
			post_values.put("x_exp_date", expDate);
			post_values.put("x_invoiceNumber", orderNumber);
			post_values.put("x_amount", totalForOrder);
			post_values.put("x_description", "Sample Transaction");

			post_values.put("x_first_name", nameOnCard);
			post_values.put("x_last_name", "");
			post_values.put("x_zip", zipCode);
			// Additional fields can be added here as outlined in the AIM
			// integration
			// guide at: http://developer.authorize.net

			post_string = new StringBuffer();
			Enumeration keys = post_values.keys();
			while (keys.hasMoreElements()) {
				String key = URLEncoder.encode(keys.nextElement().toString(),
						"UTF-8");
				String value = URLEncoder.encode(post_values.get(key)
						.toString(), "UTF-8");
				post_string.append(key + "=" + value + "&");
			}

			// The following section provides an example of how to add line item
			// details to
			// the post string. Because line items may consist of multiple
			// values with the
			// same key/name, they cannot be simply added into the above array.
			//
			// This section is commented out by default.

			// Open a URLConnection to the specified post url
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


				// TODO Auto-generated method stub
				try {

					URLConnection connection = post_url.openConnection();
					connection.setDoOutput(true);
					connection.setUseCaches(false);

					// this line is not necessarily required but fixes a bug
					// with some servers
					connection.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");

					// submit the post_string and close the connection
					DataOutputStream requestObject = new DataOutputStream(
							connection.getOutputStream());
					requestObject.write(post_string.toString().getBytes());
					requestObject.flush();
					requestObject.close();

					// process and read the gateway response
					BufferedReader rawResponse = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
					// String line;
					String responseData = rawResponse.readLine();
					rawResponse.close(); // no more data

					// split the response into an array
					String[] responses = responseData.split("\\|");

					// Load response object
					response.setResponseCode(responses[0].toString());
					response.setResponseReasonCode(responses[1].toString());
					response.setAuthorizationId(responses[4].toString());
					response.setTransactionId(responses[6].toString());
					response.setDisplayErrorToUser(responses[3]);
					response.setMaskedCardId(responses[50]);
					return response;

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(2);
				}
				System.exit(3);
				return response;


	}

	public PaymentResponseObject refundPayment(String transId, String loginId, String url, String transactionKey){

		response = new PaymentResponseObject();
		// By default, this sample code is designed to post to our test server
		// for
		// developer accounts: https://test.authorize.net/gateway/transact.dll
		// for real accounts (even in test mode), please make sure that you are
		// posting to: https://secure.authorize.net/gateway/transact.dll
		// URL post_url;
		// This section takes the input fields and converts them to the proper
		// format
		// for an http post. For example: "x_login=username&x_tran_key=a1B2c3D4"
		// StringBuffer post_string;
		try {
			post_url = new URL(url);
			// this is passed in with the menu with variable name auth_url:
			Hashtable post_values = new Hashtable();
			// auth_login
			// auth_key
			// the API Login ID and Transaction Key must be replaced with valid
			// values
			post_values.put("x_login", loginId);
			post_values.put("x_tran_key", transactionKey);

			post_values.put("x_version", "3.1");
			post_values.put("x_delim_data", "TRUE");
			post_values.put("x_delim_char", "|");
			post_values.put("x_relay_response", "FALSE");

			post_values.put("x_type", "VOID");
			post_values.put("x_trans_id", transId);
						// Additional fields can be added here as outlined in the AIM
			// integration
			// guide at: http://developer.authorize.net

			post_string = new StringBuffer();
			Enumeration keys = post_values.keys();
			while (keys.hasMoreElements()) {
				String key = URLEncoder.encode(keys.nextElement().toString(),
						"UTF-8");
				String value = URLEncoder.encode(post_values.get(key)
						.toString(), "UTF-8");
				post_string.append(key + "=" + value + "&");
			}

			// The following section provides an example of how to add line item
			// details to
			// the post string. Because line items may consist of multiple
			// values with the
			// same key/name, they cannot be simply added into the above array.
			//
			// This section is commented out by default.

			// Open a URLConnection to the specified post url
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

				// TODO Auto-generated method stub
				try {

					URLConnection connection = post_url.openConnection();
					connection.setDoOutput(true);
					connection.setUseCaches(false);

					// this line is not necessarily required but fixes a bug
					// with some servers
					connection.setRequestProperty("Content-Type",
							"application/x-www-form-urlencoded");

					// submit the post_string and close the connection
					DataOutputStream requestObject = new DataOutputStream(
							connection.getOutputStream());
					requestObject.write(post_string.toString().getBytes());
					requestObject.flush();
					requestObject.close();

					// process and read the gateway response
					BufferedReader rawResponse = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
					// String line;
					String responseData = rawResponse.readLine();
					rawResponse.close(); // no more data

					// split the response into an array
					String[] responses = responseData.split("\\|");

					// Load response object
					response.setResponseCode(responses[0].toString());
					response.setResponseReasonCode(responses[1].toString());
					response.setAuthorizationId(responses[4].toString());
					response.setTransactionId(responses[6].toString());
					response.setDisplayErrorToUser(responses[3]);
					response.setMaskedCardId(responses[50]);
					return response;

				} catch (Exception e) {
					e.printStackTrace();
					
				}
				return response;

			}

		

	
	
}
