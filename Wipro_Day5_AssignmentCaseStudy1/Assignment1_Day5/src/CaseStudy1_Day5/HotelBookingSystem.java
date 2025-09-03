package CaseStudy1_Day5;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class HotelBookingSystem {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    public static class Booking {
        private final LocalDate checkIn;
        private final LocalDate checkOut;
        private final String guestName;

        public Booking(LocalDate checkIn, LocalDate checkOut, String guestName) {
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.guestName = guestName;
        }

        public long getNights() {
            return ChronoUnit.DAYS.between(checkIn, checkOut);
        }

        public boolean overlapsWith(Booking other) {
            return !checkOut.isBefore(other.checkIn) && !checkIn.isAfter(other.checkOut);
        }

        @Override
        public String toString() {
            return String.format("%s: %s to %s (%d nights)", 
                guestName, checkIn.format(DATE_FORMAT), 
                checkOut.format(DATE_FORMAT), getNights());
        }
    }

    private final List<Booking> bookings = new ArrayList<>();

    public boolean makeBooking(LocalDate checkIn, LocalDate checkOut, String guestName) {
        if (!validateDates(checkIn, checkOut)) {
            return false;
        }

        Booking newBooking = new Booking(checkIn, checkOut, guestName);
        if (isAvailable(newBooking)) {
            bookings.add(newBooking);
            return true;
        }
        return false;
    }

    private boolean validateDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate today = LocalDate.now();
        return !checkIn.isBefore(today) && 
               !checkOut.isBefore(checkIn.plusDays(1));
    }

    private boolean isAvailable(Booking newBooking) {
        return bookings.stream()
                      .noneMatch(existing -> existing.overlapsWith(newBooking));
    }

    public List<Booking> getUpcomingBookings() {
        LocalDate today = LocalDate.now();
        return bookings.stream()
                      .filter(b -> !b.checkOut.isBefore(today))
                      .toList();
    }

    // Recurring booking example
    public void createRecurringBookings(LocalDate firstDate, DayOfWeek dayOfWeek, int occurrences) {
        LocalDate nextDate = firstDate;
        for (int i = 0; i < occurrences; i++) {
            makeBooking(nextDate, nextDate.plusDays(2), "Recurring Guest");
            nextDate = nextDate.with(TemporalAdjusters.next(dayOfWeek));
        }
    }
}