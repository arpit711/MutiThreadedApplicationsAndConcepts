package ticketBookingSystem.seat;

public class Seat {
    private final String id;
    private final int row;
    private final int Column;
    private final SeatType type;
    private double price;
    private SeatStatus status;


    public Seat(String id, int row, int column, SeatType type, double price, SeatStatus status) {
        this.id = id;
        this.row = row;
        Column = column;
        this.type = type;
        this.price = price;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return Column;
    }

    public SeatType getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}
