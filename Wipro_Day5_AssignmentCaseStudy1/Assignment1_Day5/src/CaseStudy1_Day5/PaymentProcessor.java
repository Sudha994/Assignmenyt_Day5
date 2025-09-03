package CaseStudy1_Day5;

public class PaymentProcessor {

	public static void main(String[] args) {
		PaymentGateway paypal = new PayPalGateway();
		PaymentGateway upi = new UPIGateway();
		PaymentGateway card = new CardGateway();
		
		paypal.processPayment(100.0, "EUR");
		upi.processPayment(5000.0, "INR");
         card.processPayment(150.0, "GBP");
	}

}
