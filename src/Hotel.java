import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

   

    public String getName() {
        return name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Room> getAvailableRooms(Date checkInDate, Date checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public static String getInitials(String hotelName) {
        StringBuilder initials = new StringBuilder();
        for (String word : hotelName.split(" ")) {
            if (!word.isEmpty()) {
                initials.append(Character.toUpperCase(word.charAt(0)));
            }
        }
        return initials.toString();
    }

    public Room getRoomByName(String roomName) {
        for (Room room : rooms) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    public Booking getBookingByGuestName(String guestName) {
        for (Booking booking : bookings) {
            if (booking.getGuestName().equals(guestName)) {
                return booking;
            }
        }
        return null;
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public void setName(String newHotelName) {
        this.name = newHotelName;
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }
}
