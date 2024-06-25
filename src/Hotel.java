import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Booking> bookings;
    
    /**
     * This method is a constructor that creates a hotel object including its name and an Array List of rooms and bookings.
     * 
     * @param the name of the hotel
     */

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

   
    /**
     * This method gets the name of the hotel.
     * 
     * @return the name of the hotel in String format
     */
    
    public String getName() {
        return name;
    }
    
    /**
     * This method gets the Array List of the rooms in the hotel.
     * 
     * @return the list of rooms
     */

    public List<Room> getRooms() {
        return rooms;
    }
    
    /**
     * This method gets the Array List of the bookings in the hotel.
     * 
     * @return the list of bookings
     */

    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * This method adds a room to the Array List of rooms.
     * 
     * @param room the room to be added
     */
    
    public void addRoom(Room room) {
        rooms.add(room);
    }

    /**
     * This method adds a booking to the Array List of bookings.
     * 
     * @param booking the booking to be added
     */
    
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    /**
     * This method gets the current available rooms in the hotel.
     * 
     * @param checkInDate the check in date of the booking
     * @param checkOutDate the check out date of the booking
     * 
     * @return an array list of available rooms
     */

    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    /**
     * This method converts the hotel name into its first letter initials.
     * 
     * @param name the hotel name
     * 
     * @return an Array List containing the first capitalized initials of the hotel name
     */
    
    public static String getInitials(String hotelName) {
        StringBuilder initials = new StringBuilder();
        for (String word : hotelName.split(" ")) {
            if (!word.isEmpty()) {
                initials.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        return initials.toString();
    }

    /**
     * This method gets and matches the room by its name.
     * 
     * @param roomName the name of the room
     * 
     * @return will return a room object if room name matches with the name being searched on and null if not found
     */
    
    public Room getRoomByName(String roomName) {
        for (Room room : rooms) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    /**
     * This method searches the bookings in accordance to the guest name.
     * 
     * @param guestName the name of the guest
     * 
     * @return the booking that matches with the guest name and null if not found
     */
    
    public Booking getBookingByGuestName(String guestName) {
        for (Booking booking : bookings) {
            if (booking.getGuestName().equals(guestName)) {
                return booking;
            }
        }
        return null;
    }
    
    /**
     * This method removes a booking from the booking list.
     * 
     * @param booking the booking object with the booking details
     */

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }
    
    /**
     * This method sets the name of the hotel.
     * 
     * @param newHotelName the new name of the hotel
     */

    public void setName(String newHotelName) {
        this.name = newHotelName;
    }

    /**
     * This method removes a room from the room list.
     * 
     * @param room the room object with the room details
     */
    
    public void removeRoom(Room room) {
        rooms.remove(room);
    }
}
