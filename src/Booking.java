import java.util.*;

public class Booking {
    public static void createBooking(Scanner scanner, List<Hotel> hotels) {
        if (hotels.isEmpty()) {
            System.out.println("No hotels available. Please create a hotel first.");
            return;
        }

        System.out.println("Select a hotel:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
        int hotelChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Hotel selectedHotel = hotels.get(hotelChoice - 1);

        DateUtil.displayAvailableDates(selectedHotel.getName());

        System.out.println("Create a booking:");
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();

        System.out.println("Enter check-in date (MM/dd/yyyy):");
        Date checkInDate = DateUtil.parseDate(scanner.nextLine());

        System.out.println("Enter check-out date (MM/dd/yyyy):");
        Date checkOutDate = DateUtil.parseDate(scanner.nextLine());

        if (checkInDate == null || checkOutDate == null) {
            System.out.println("Invalid date format. Please use MM/dd/yyyy format.");
            return;
        }

        if (!isBookingValid(selectedHotel.getName(), checkInDate, checkOutDate)) {
            System.out.println("Invalid booking dates. Please check the dates and try again.");
            return;
        }

        // Select a room from the selected hotel
        List<Room> availableRooms = selectedHotel.getAvailableRooms(checkInDate, checkOutDate);
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms for the selected dates.");
            return;
        }

        System.out.println("Select a room:");
        for (int i = 0; i < availableRooms.size(); i++) {
            System.out.println((i + 1) + ". " + availableRooms.get(i).getName() + " - " + availableRooms.get(i).getBasePricePerNight());
        }
        int roomChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Room selectedRoom = availableRooms.get(roomChoice - 1);

        // Create the booking
        Booking newBooking = new Booking(guestName, checkInDate, checkOutDate, selectedRoom);
        selectedHotel.addBooking(newBooking);

        System.out.println("Booking created successfully!");
    }

    private static boolean isBookingValid(String hotelName, Date checkInDate, Date checkOutDate) {
        return checkInDate.before(checkOutDate) && DateUtil.isDateAvailable(hotelName, checkInDate) && DateUtil.isDateAvailable(hotelName, checkOutDate);
    }

    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private Room room;

    public Booking(String guestName, Date checkInDate, Date checkOutDate, Room room) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        DateUtil.bookDate(room.getHotelName(), checkInDate);
        DateUtil.bookDate(room.getHotelName(), checkOutDate);
    }

    public String getGuestName() {
        return guestName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public double getTotalPrice() {
        long duration = (checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24);
        return duration * room.getBasePricePerNight();
    }
}
