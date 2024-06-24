import java.util.*;
import java.text.SimpleDateFormat;

public class View {
    public static void viewHotel(Scanner scanner, List<Hotel> hotels) {
        // Select a hotel
        System.out.println("Select a hotel:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
        int hotelChoice = scanner.nextInt();
        scanner.nextLine(); 
        Hotel selectedHotel = hotels.get(hotelChoice - 1);

        // Display high-level information
        displayHighLevelInfo(selectedHotel);

        // Ask for low-level information
        System.out.println("Select low-level information:");
        System.out.println("1. Total number of available and booked rooms for a selected date");
        System.out.println("2. Information about a selected room");
        System.out.println("3. Information about a selected reservation");
        int infoChoice = scanner.nextInt();
        scanner.nextLine();

        switch (infoChoice) {
            case 1:
                displayRoomAvailabilityForDate(scanner, selectedHotel);
                break;
            case 2:
                displayRoomInfo(scanner, selectedHotel);
                break;
            case 3:
                displayReservationInfo(scanner, selectedHotel);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void displayHighLevelInfo(Hotel hotel) {
        System.out.println("Hotel Name: " + hotel.getName());
        System.out.println("Total number of rooms: " + hotel.getRooms().size());
        System.out.println("Estimated earnings for the month: " + calculateEarnings(hotel));
    }

    private static double calculateEarnings(Hotel hotel) {
        double earnings = 0.0;
        for (Booking booking : hotel.getBookings()) {
            earnings += booking.getTotalPrice();
        }
        return earnings;
    }

    private static void displayRoomAvailabilityForDate(Scanner scanner, Hotel hotel) {
        System.out.println("Enter date (MM/dd/yyyy):");
        String dateStr = scanner.nextLine();
        Date date = DateUtil.parseDate(dateStr);

        if (date == null) {
            System.out.println("Invalid date format.");
            return;
        }

        int availableRooms = 0;
        int bookedRooms = 0;
        for (Room room : hotel.getRooms()) {
            if (room.isAvailable(date)) {
                availableRooms++;
            } else {
                bookedRooms++;
            }
        }
        System.out.println("Available rooms: " + availableRooms);
        System.out.println("Booked rooms: " + bookedRooms);
    }

    private static void displayRoomInfo(Scanner scanner, Hotel hotel) {
        System.out.println("Enter room name:");
        String roomName = scanner.nextLine();
        Room room = hotel.getRoomByName(roomName);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.println("Room Name: " + room.getName());
        System.out.println("Price per night: " + room.getBasePricePerNight());
        System.out.println("Availability for the month:");
        DateUtil.displayRoomAvailabilityForMonth(room);
    }

    private static void displayReservationInfo(Scanner scanner, Hotel hotel) {
        System.out.println("Enter guest name:");
        String guestName = scanner.nextLine();
        Booking booking = hotel.getBookingByGuestName(guestName);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        System.out.println("Guest Name: " + booking.getGuestName());
        System.out.println("Room: " + booking.getRoom().getName());
        System.out.println("Check-in Date: " + DateUtil.formatDate(booking.getCheckInDate()));
        System.out.println("Check-out Date: " + DateUtil.formatDate(booking.getCheckOutDate()));
        System.out.println("Total Price: " + booking.getTotalPrice());
        System.out.println("Price Breakdown:");
        Date checkInDate = booking.getCheckInDate();
        Date checkOutDate = booking.getCheckOutDate();
        long duration = (checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24);
        for (int i = 0; i < duration; i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkInDate);
            cal.add(Calendar.DAY_OF_MONTH, i);
            Date date = cal.getTime();
            System.out.println(DateUtil.formatDate(date) + ": " + booking.getRoom().getBasePricePerNight());
        }
    }
}
