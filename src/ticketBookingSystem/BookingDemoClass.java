package ticketBookingSystem;

import ticketBookingSystem.booking.Booking;
import ticketBookingSystem.seat.Seat;
import ticketBookingSystem.seat.SeatStatus;
import ticketBookingSystem.seat.SeatType;

import java.time.LocalDateTime;
import java.util.*;

public class BookingDemoClass {
    public static void main(String[] args) {
//        create movie, shows, theater  these three objects once created then the ticketBookingSystem.booking logic will be applied

    // create bookingService instance first.
    BookingService bookingSystem = BookingService.getInstance();

//    Adding movies.
    Movie movie1 = new Movie("1", "Agneepath", "awsomeMovie", 120);
    Movie movie2 = new Movie("2", "RAW1", "FlopMovie", 320);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

//    Adding theatres.
        Theatre theatre1 = new Theatre("1", "banaras", "faridabad", new ArrayList<>());
        Theatre theatre2 = new Theatre("2", "IIIH", "hyderabad", new ArrayList<>());
        bookingSystem.addTheatre(theatre1);
        bookingSystem.addTheatre(theatre2);

//        adding shows.
//        creating seats for the respective show and then we will try to fill those seats as per the requirements.
        Show show1 = new Show("1", movie1, theatre1, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(movie1.getDurationInMinutes()), createSeats(10, 10));
        Show show2 = new Show("2", movie2, theatre2, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(movie2.getDurationInMinutes()), createSeats(8, 8));
        bookingSystem.addShows(show1);
        bookingSystem.addShows(show2);

        User user = new User("1", "Arpit", "sharmaarpit291@gmail.com");
        List<Seat> selectedSeats = Arrays.asList(show1.getSeats().get("1-5"), show1.getSeats().get("1-6"));
        Booking booking = bookingSystem.bookTickets(user, show1, selectedSeats);
        if (booking != null) {
            System.out.println("Booking successful. Booking ID: " + booking.getId());
            bookingSystem.confirmBooking(booking.getId());
        } else {
            System.out.println("Booking failed. Seats not available.");
        }


    }

    private static Map<String, Seat> createSeats(int rows, int cols) {
        Map<String, Seat> seats = new HashMap<>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= cols; col++) {
                String seatId = row + "-" + col;
                SeatType seatType = (row <= 2) ? SeatType.PREMIUM : SeatType.ECONOMY;
                double price = (seatType == SeatType.PREMIUM) ? 150.0 : 100;
                SeatStatus seatStatus = SeatStatus.EMPTY;
                Seat seat = new Seat(seatId, row, col, seatType, price, seatStatus);
                seats.put(seatId, seat);
            }
        }
        return seats;
    }

}