import java.util.*;

public class Manage {
    public static void manageHotel(Scanner scanner, List<Hotel> hotels) {
        // Select a hotel
        System.out.println("Select a hotel:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
        int hotelChoice = scanner.nextInt();
        scanner.nextLine(); 
        Hotel selectedHotel = hotels.get(hotelChoice - 1);

        // Display management options
        System.out.println("Manage Hotel:");
        System.out.println("1. Change hotel name");
        System.out.println("2. Add room(s)");
        System.out.println("3. Remove room(s)");
        System.out.println("4. Update base price for a room");
        System.out.println("5. Remove reservation");
        System.out.println("6. Remove hotel");
        int manageChoice = scanner.nextInt();
        scanner.nextLine(); 

        switch (manageChoice) {
            case 1:
                changeHotelName(scanner, selectedHotel);
                break;
            case 2:
                addRooms(scanner, selectedHotel);
                break;
            case 3:
                removeRooms(scanner, selectedHotel);
                break;
            case 4:
                updateRoomPrice(scanner, selectedHotel);
                break;
            case 5:
                removeReservation(scanner, selectedHotel);
                break;
            case 6:
                removeHotel(scanner, hotels, selectedHotel);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private static void changeHotelName(Scanner scanner, Hotel hotel) {
        System.out.println("Enter new hotel name:");
        String newHotelName = scanner.nextLine();
        String initials = Hotel.getInitials(newHotelName);
        hotel.setName(newHotelName);
        for (Room room : hotel.getRooms()) {
            String roomName = initials + room.getName().substring(3); // Keep the numeric part of the room name
            room.setName(roomName);
        }
        System.out.println("Hotel name changed to " + newHotelName);
    }

    private static void addRooms(Scanner scanner, Hotel hotel) {
        System.out.println("Enter number of rooms to add:");
        int numRooms = scanner.nextInt();
        scanner.nextLine(); 
        System.out.println("Enter base price per night for new rooms:");
        double basePricePerNight = scanner.nextDouble();
        scanner.nextLine(); 

        String initials = Hotel.getInitials(hotel.getName());
        List<Room> rooms = hotel.getRooms();
        int existingRoomCount = rooms.size();

        for (int i = 0; i < numRooms; i++) {
            String roomName = initials + String.format("%04d", existingRoomCount + i + 1);
            Room room = new Room(roomName, basePricePerNight, hotel.getName());
            hotel.addRoom(room);
        }

        DateUtil.addRoomAvailability(hotel.getName(), numRooms);

        System.out.println(numRooms + " rooms added to " + hotel.getName());
    }

    private static void removeRooms(Scanner scanner, Hotel hotel) {
        System.out.println("Enter number of rooms to remove:");
        int numRoomsToRemove = scanner.nextInt();
        scanner.nextLine(); 

        List<Room> rooms = hotel.getRooms();
        int roomCount = rooms.size();
        int removableRoomsCount = roomCount - hotel.getBookings().size();

        if (numRoomsToRemove > removableRoomsCount) {
            System.out.println("Only " + removableRoomsCount + " rooms can be removed.");
            return;
        }

        for (int i = 0; i < numRoomsToRemove; i++) {
            rooms.remove(roomCount - i - 1);
        }

        DateUtil.removeRoomAvailability(hotel.getName(), numRoomsToRemove);

        System.out.println(numRoomsToRemove + " rooms removed from " + hotel.getName());
    }

    private static void updateRoomPrice(Scanner scanner, Hotel hotel) {
        if (!hotel.getBookings().isEmpty()) {
            System.out.println("Cannot update room price. Active reservations exist.");
            return;
        }

        System.out.println("Enter new base price (>= 100.0):");
        double newBasePrice = scanner.nextDouble();
        scanner.nextLine(); 

        if (newBasePrice < 100.0) {
            System.out.println("New price must be >= 100.0.");
            return;
        }

        for (Room room : hotel.getRooms()) {
            room.setBasePricePerNight(newBasePrice);
        }

        System.out.println("Base price updated to " + newBasePrice);
    }

    private static void removeReservation(Scanner scanner, Hotel hotel) {
        System.out.println("Enter guest name for the reservation to remove:");
        String guestName = scanner.nextLine();
        Booking booking = hotel.getBookingByGuestName(guestName);

        if (booking == null) {
            System.out.println("Reservation not found.");
            return;
        }

        hotel.removeBooking(booking);
        System.out.println("Reservation removed for guest: " + guestName);
    }

    private static void removeHotel(Scanner scanner, List<Hotel> hotels, Hotel hotel) {
        if (!hotel.getBookings().isEmpty()) {
            System.out.println("Cannot remove hotel. Active reservations exist.");
            return;
        }

        hotels.remove(hotel);
        System.out.println("Hotel " + hotel.getName() + " has been removed.");
    }
}
