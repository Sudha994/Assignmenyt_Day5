package CaseStudy1_Day5;

public interface PaymentGateway {

	// Abstract method(must implement)
	void processPayment(double amount, String currency);
	
	// Default method (can be shared)
	default void logTransaction(double amount, String currency) {
		System.out.printf("\n[LOG] Processing %s %.2f%n", currency, amount);
	}
	
	 default double convertToDefaultCurrency(double amount, String fromCurrency) {
	        // Simple conversion rates for demo
	        switch(fromCurrency) {
	            case "EUR": return amount * 1.18; // EUR to USD
	            case "GBP": return amount * 1.38; // GBP to USD
	            default: return amount; // USD
	        }
	    }
}


class PayPalGateway implements PaymentGateway{

	@Override
	public void processPayment(double amount, String currency) {
		logTransaction(amount, currency);
		double usdAmount = convertToDefaultCurrency(amount, currency);
		System.out.printf("Processing PayPal payment: $%.2f USD%n", usdAmount);		
	}
}


class UPIGateway implements PaymentGateway{

	@Override
	public void processPayment(double amount, String currency) {
		logTransaction(amount, currency);
        System.out.printf("Processing UPI payment: %.2f %s%n", amount, currency);
	}
}


class CardGateway implements PaymentGateway {

	@Override
	public void processPayment(double amount, String currency) {
		logTransaction(amount, currency);
		double usdAmount = convertToDefaultCurrency(amount, currency);
        System.out.printf("Processing Card payment: $%.2f USD%n", usdAmount);
	}
}
