package CaseStudy1_Day5;

import java.util.function.BiConsumer;

class EmailService {
    public static void sendEmail(String recipient, String message) {
        System.out.println("Sending email to " + recipient + ": " + message);
    }
}

class SmsService {
    public void sendSms(String phoneNumber, String text) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + text);
    }
}

class PushNotificationService {
    public void sendPush(String deviceId, String alert) {
        System.out.println("Sending push to device " + deviceId + ": " + alert);
    }
}

public class MethodReferenceNotificationSystem {

	
	private final SmsService smsService = new SmsService();
    private final PushNotificationService pushService = new PushNotificationService();

    public void dispatch(String recipient, String message, 
                        BiConsumer<String, String> notifier) {
        notifier.accept(recipient, message);
    }
    
	public static void main(String[] args) {
		MethodReferenceNotificationSystem m = new MethodReferenceNotificationSystem();
		
		// Using static method reference
        m.dispatch("sanjithkumarhr17@gmail.com", "Hello via email", EmailService::sendEmail);
        
        // Using instance method reference
        m.dispatch("+91 7760264194", "Hello via SMS", m.smsService::sendSms);
        
        // Using instance method reference
        m.dispatch("device-Sanjith123", "Hello via push", m.pushService::sendPush);
        
        // Can also use lambda as alternative
        m.dispatch("user@example.com", "Reminder", 
            (r, m1) -> EmailService.sendEmail(r, m1));
    }

}
