package ticketBookingSystem;

import ticketBookingSystem.booking.Booking;
import ticketBookingSystem.booking.BookingStatus;
import ticketBookingSystem.seat.Seat;
import ticketBookingSystem.seat.SeatStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
/*
 * this is the class responsible for creation of ticketBookingSystem.booking for a particular user based on demo class arguments
 * this should handle multithreading and concurrency in order to avvoid race conditions in ticketBookingSystem.booking system*/

public class BookingService {
    private static final String BOOKING_ID_PREFIX = "BKG";
    private static final AtomicLong bookingCounter = new AtomicLong(0);
    private static BookingService instance;
    private final List<Movie> movies;
    private final List<Theatre> theatres;
    private final Map<String, Booking> bookings;
    private final Map<String, Show> shows;
    private Booking booking;
    private List<Seat> seats;

    public BookingService() {
        this.movies = new ArrayList<>();
        this.theatres = new ArrayList<>();
        this.bookings = new ConcurrentHashMap<>();
        this.shows = new ConcurrentHashMap<>();
    }

    public static synchronized BookingService getInstance() {
        if (instance == null) {
            return new BookingService();
        }
        return instance;
    }


    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addTheatre(Theatre theatre) {
        theatres.add(theatre);
    }

    public void addShows(Show show) {
        shows.put(show.getId(), show);
    }

    public synchronized Booking bookTickets(User user, Show show, List<Seat> selectedSeats) {
        if (areSeatsAvailable(show, selectedSeats)) {
            markSeatsAsBooked(show, selectedSeats);
            double totalPrice = calculateTotalPrice(selectedSeats);
            String bookingId = generateBookingId();
            Booking booking = new Booking(bookingId, selectedSeats,totalPrice, user, show, BookingStatus.PENDING);
            bookings.put(bookingId, booking);
            return booking;

        }
            return null;
    }

    private String generateBookingId() {
        long bookingNumber = bookingCounter.incrementAndGet();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return BOOKING_ID_PREFIX + timestamp + String.format("%06d", bookingNumber);
    }

    private double calculateTotalPrice(List<Seat> selectedSeats) {
        return selectedSeats.stream().mapToDouble(Seat::getPrice).sum();
    }

    private void markSeatsAsBooked(Show show, List<Seat> selectedSeats) {
        for (Seat seat: selectedSeats) {
            Seat showSeat = show.getSeats().get(seat.getId());
            showSeat.setStatus(SeatStatus.BOOKED);
        }
    }

    public boolean areSeatsAvailable(Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            Seat showSeat = show.getSeats().get(seat.getId());
            if (showSeat == null || showSeat.getStatus() == SeatStatus.BOOKED) {
                return false;
            }
        }
        return true;
    }

    public synchronized void confirmBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null && booking.getStatus() == BookingStatus.PENDING) {
            booking.setStatus(BookingStatus.CONFIRMED);
            // Process payment and send confirmation
            // ...
        }
    }
}