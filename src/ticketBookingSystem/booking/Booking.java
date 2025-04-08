package ticketBookingSystem.booking;

import ticketBookingSystem.Movie;
import ticketBookingSystem.Show;
import ticketBookingSystem.User;
import ticketBookingSystem.seat.Seat;

import java.util.List;

public class Booking {
    private final String id;
    private final List<Seat> seats;
    private final double totalPrice;
    private final User user;
    private final Show show;
    private BookingStatus status;


    public Booking(String id, List<Seat> seats, double totalPrice, User user, Show show, BookingStatus status) {
        this.id = id;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.user = user;
        this.show = show;
        this.status = status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public BookingStatus getStatus() {
        return status;
    }
}
