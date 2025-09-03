package CaseStudy1_Day5;

import java.util.Optional;

class UserProfile {
    private final String username;
    private Optional<String> email;
    private Optional<String> phone;
    private Optional<String> address;

    public UserProfile(String username) {
        this.username = username;
        this.email = Optional.empty();
        this.phone = Optional.empty();
        this.address = Optional.empty();
    }

    // Setters with Optional
    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

    public void setPhone(String phone) {
        this.phone = Optional.ofNullable(phone);
    }

    public void setAddress(String address) {
        this.address = Optional.ofNullable(address);
    }

    // Getters with Optional
    public Optional<String> getEmail() {
        return email;
    }

    public Optional<String> getPhone() {
        return phone;
    }

    public Optional<String> getAddress() {
        return address;
    }

    // Business methods demonstrating Optional usage
    public void sendNotification(String message) {
        // Try email first, then phone
        email.ifPresentOrElse(
            e -> System.out.println("Emailing " + e + ": " + message),
            () -> phone.ifPresent(
                p -> System.out.println("Texting " + p + ": " + message)
            )
        );
    }

    public String getContactInfo() {
        return email.or(() -> phone)
                   .map(info -> "Contact: " + info)
                   .orElse("No contact information available");
    }
}

public class OptionalClassUserProfileManagement {
	public static void main(String[] args) {
		UserProfile user1 = new UserProfile("john_doe");
        user1.setEmail("john@example.com");
        
        UserProfile user2 = new UserProfile("jane_doe");
        user2.setPhone("+1234567890");

        // Demonstrate Optional handling
        System.out.println("User 1:");
        user1.getEmail().ifPresent(e -> System.out.println("Email: " + e));
        System.out.println("Phone: " + user1.getPhone().orElse("Not provided"));
        
        System.out.println("\nUser 2:");
        user2.getEmail().ifPresentOrElse(
            e -> System.out.println("Email: " + e),
            () -> System.out.println("No email provided")
        );
        
        // Using the business methods
        System.out.println("\nNotification Test:");
        user1.sendNotification("Your account was created");
        user2.sendNotification("Your account was created");
        
        System.out.println("\nContact Info:");
        System.out.println(user1.getContactInfo());
        System.out.println(user2.getContactInfo());

	}
}