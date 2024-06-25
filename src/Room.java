import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private String name;
    private double basePricePerNight;
    private List<Date> bookedDates;
    private String hotelName;
    private List<Booking> bookings;

    public Room(String name, double basePricePerNight, String hotelName) {
        this.name = name;
        this.basePricePerNight = basePricePerNight;
        this.hotelName = hotelName;
        this.bookedDates = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public double getBasePricePerNight() {
        return basePricePerNight;
    }

    public List<Date> getBookedDates() {
        return bookedDates;
    }

    public String getHotelName() {
        return hotelName;
    }

    public boolean isAvailable(Date checkInDate, Date checkOutDate) {
        for (Booking booking : bookings) {
            if (!booking.getCheckOutDate().before(checkInDate) && !booking.getCheckInDate().after(checkOutDate)) {
                return false;
            }
        }
        return true;
    }

    public boolean bookRoom(String guestName, Date checkInDate, Date checkOutDate) {
        if (!isAvailable(checkInDate, checkOutDate)) {
            return false; // Room is not available for booking
        }

        Booking booking = new Booking(guestName, checkInDate, checkOutDate);
        bookings.add(booking);
        addBookingDate(checkInDate, checkOutDate);
        return true;
    }

    public void addBookingDate(Date checkInDate, Date checkOutDate) {
        // Add booked dates to the room's bookedDates list
        Date currentDate = checkInDate;
        while (!currentDate.after(checkOutDate)) {
            bookedDates.add(currentDate);
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // Increment to the next day
        }
    }

    public void cancelBooking(Date checkInDate, Date checkOutDate) {
        // Remove booked dates from the room's bookedDates list
        bookings.removeIf(booking -> booking.getCheckInDate().equals(checkInDate) && booking.getCheckOutDate().equals(checkOutDate));
        removeBookingDate(checkInDate, checkOutDate);
    }

   public void removeBookingDate(Date checkInDate, Date checkOutDate) {
        // Remove booked dates from the room's bookedDates list
        Date currentDate = checkInDate;
        while (!currentDate.after(checkOutDate)) {
            bookedDates.remove(currentDate);
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // Increment to the next day
        }
    }

    public void setBasePricePerNight(double newPrice) {
        this.basePricePerNight = newPrice;
    }
}
