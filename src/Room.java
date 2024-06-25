import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Room {
    private String name;
    private double basePricePerNight;
    private List<Date> bookedDates;
    private String hotelName;
    private List<Booking> bookings;

    /**
     * This method is a constructor that creates a room object, including the room name, 
     * base price per night, and the hotel name.
     * 
     * @param name the name of the room
     * @param basePricePerNight the base price of the room per night
     * @param hotelName the name of the hotel
     */
    
    public Room(String name, double basePricePerNight, String hotelName) {
        this.name = name;
        this.basePricePerNight = basePricePerNight;
        this.hotelName = hotelName;
        this.bookedDates = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    /**
     * This method gets the name of the room.
     * 
     * @return the room name in String format
     */
    
    public String getName() {
        return name;
    }

    /**
     * This method gets base price of the room per night.
     * 
     * @return the base price per night in double format
     */
    
    public double getBasePricePerNight() {
        return basePricePerNight;
    }

    /**
     * This method gets the dates currently booked.
     * 
     * @return the Array List of dates booked
     */
    
    public List<Date> getBookedDates() {
        return bookedDates;
    }

    /**
     * This method gets the name of the hotel.
     * 
     * @return the name of the hotel
     */
    
    public String getHotelName() {
        return hotelName;
    }

    /**
     * This method checks the availability of the room.
     * 
     * @param checkInDate the check in date of the room
     * @param checkOutDate the check out date of the room
     * 
     * @return true if the room is available and false if not 
     */
    
    public boolean isAvailable(Date checkInDate, Date checkOutDate) {
        for (Booking booking : bookings) {
            if (!booking.getCheckOutDate().before(checkInDate) && !booking.getCheckInDate().after(checkOutDate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method creates a booking for the selected room.
     * 
     * @param guestName the name of the guest
     * @param checkInDate the check in date of the room
     * @param checkOutDate the check out date of the room
     * 
     * @return true if booking is successful and false if room is unavailable
     */
    
    public boolean bookRoom(String guestName, Date checkInDate, Date checkOutDate) {
        if (!isAvailable(checkInDate, checkOutDate)) {
            return false; // Room is not available for booking
        }

        Booking booking = new Booking(guestName, checkInDate, checkOutDate);
        bookings.add(booking);
        addBookingDate(checkInDate, checkOutDate);
        return true;
    }
    
    /**
     * This method adds the booking date to the list of booked dates.
     * 
     * @param checkInDate the check in date of the room
     * @param checkOutDate the check out date of the room
     */
    
    public void addBookingDate(Date checkInDate, Date checkOutDate) {
        // Add booked dates to the room's bookedDates list
        Date currentDate = checkInDate;
        while (!currentDate.after(checkOutDate)) {
            bookedDates.add(currentDate);
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // Increment to the next day
        }
    }
    
    /**
     * This method cancels a created booking.
     * 
     * @param checkInDate the check in date of the room
     * @param checkOutDate the check out date of the room
     */

    public void cancelBooking(Date checkInDate, Date checkOutDate) {
        // Remove booked dates from the room's bookedDates list
        bookings.removeIf(booking -> booking.getCheckInDate().equals(checkInDate) && booking.getCheckOutDate().equals(checkOutDate));
        removeBookingDate(checkInDate, checkOutDate);
    }

    /**
     * This method removes the booking date from the list of booked dates
     * 
     * @param checkInDate the check in date of the room
     * @param checkOutDate the check out date of the room
     */
    
   public void removeBookingDate(Date checkInDate, Date checkOutDate) {
        // Remove booked dates from the room's bookedDates list
        Date currentDate = checkInDate;
        while (!currentDate.after(checkOutDate)) {
            bookedDates.remove(currentDate);
            currentDate = new Date(currentDate.getTime() + 24 * 60 * 60 * 1000); // Increment to the next day
        }
    }
   
   /**
    * This method sets the base price per night of the room selected.
    * 
    * @param newPrice the new base price of the room
    */
   
    public void setBasePricePerNight(double newPrice) {
        this.basePricePerNight = newPrice;
    }
}
