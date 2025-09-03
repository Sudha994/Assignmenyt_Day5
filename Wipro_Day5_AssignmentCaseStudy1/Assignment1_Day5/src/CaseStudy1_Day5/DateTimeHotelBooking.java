package CaseStudy1_Day5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;

public class DateTimeHotelBooking {

	public static void main(String[] args) {
		HotelBookingSystem system = new HotelBookingSystem();

        // Valid booking
        boolean success1 = system.makeBooking(
            LocalDate.now().plusDays(1),
            LocalDate.now().plusDays(3),
            "John Doe"
        );
        System.out.println("Booking 1 " + (success1 ? "succeeded" : "failed"));

        // Invalid dates
        boolean success2 = system.makeBooking(
            LocalDate.now().minusDays(1), // Past date
            LocalDate.now().plusDays(2),
            "Invalid Guest"
        );
        System.out.println("Booking 2 " + (success2 ? "succeeded" : "failed"));

        // Recurring bookings every Friday for 3 weeks
        system.createRecurringBookings(
            LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY)),
            DayOfWeek.FRIDAY,
            3
        );

        // Display upcoming bookings
        System.out.println("\nUpcoming Bookings:");
        system.getUpcomingBookings().forEach(System.out::println);

        // Calculate stay duration
        Period stayDuration = Period.between(
            LocalDate.now().plusDays(1),
            LocalDate.now().plusDays(4)
        );
        System.out.println("\nStay duration: " + stayDuration.getDays() + " days");
	}

}
