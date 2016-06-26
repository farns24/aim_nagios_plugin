package biz.spfsolutions.ima.service.payment;

public enum PaymentTypesEnum {
		 
		 AUTHORIZE(1),
		 MERCURY(2);
	
		 private int code;
		  
		  private PaymentTypesEnum(int c) {
		    code = c;
		  }
		  
		  public int getCode() {
		    return code;
		  }
	}

