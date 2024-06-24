import java.util.*;

public class Create {
    public static void createHotel(Scanner scanner, List<Hotel> hotels) {
        // Get hotel name from user
        System.out.println("Hotel Name:");
        String hotelName = scanner.nextLine();

        // Extract initials from hotel name
        String initials = Hotel.getInitials(hotelName);

        // Get number of rooms from user
        System.out.println("Enter number of rooms:");
        int numRooms = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Get base price per night for all rooms
        System.out.println("Enter base price per night for all rooms:");
        double basePricePerNight = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        // Create a list to store the rooms
        List<Room> rooms = new ArrayList<>();

        // Add rooms to the list with universal naming
        for (int i = 0; i < numRooms; i++) {
            String roomName = initials + String.format("%04d", i + 1);
            Room room = new Room(roomName, basePricePerNight, hotelName);
            rooms.add(room);
        }

        // Initialize the hotel using the Create class
        Hotel hotel = initializeHotel(hotelName, rooms);
        hotels.add(hotel); // Add the hotel to the original list

        // Initialize the hotel's availability in DateUtil
        DateUtil.initializeHotelAvailability(hotelName, numRooms);

        //  display hotel information to verify
        System.out.println("Hotel " + hotelName + " has been created with " + numRooms + " rooms.");
        if (!rooms.isEmpty()) {
            Room exampleRoom = rooms.get(0);
            System.out.println("Example room: " + exampleRoom.getName() + " with base price " + exampleRoom.getBasePricePerNight());
        }
    }

    public static Hotel initializeHotel(String hotelName, List<Room> rooms) {
        Hotel hotel = new Hotel(hotelName);
        for (Room room : rooms) {
            hotel.addRoom(room);
        }
        return hotel;
    }
}
