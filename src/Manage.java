import java.util.*;

public class Manage {
	
	/**
	 * This method manages the details of the hotel where you can change the hotel name, 
	 * add rooms, remove rooms, update base price, remove reservations, and remove hotel.
	 * 
	 * @param scanner the scanner object that accepts input from the user
	 * @param hotels the Array List of hotels created
	 */
	
    public static void manageHotel(Scanner scanner, List<Hotel> hotels) {
        // Select a hotel
    	Display.displayHeadline();
    	System.out.println("Select a hotel to manage:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
        System.out.print("Your Choice: ");
        int hotelChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Hotel selectedHotel = hotels.get(hotelChoice - 1);

        // Display management options
        Display.displaySpacer();
        Display.displayHeadline();
        System.out.println("Manage Hotel:");
        System.out.println("1. Change hotel name");
        System.out.println("2. Add room(s)");
        System.out.println("3. Remove room(s)");
        System.out.println("4. Update base price for a room");
        System.out.println("5. Remove reservation");
        System.out.println("6. Remove hotel");
        System.out.print("Your Choice: ");
        int manageChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

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

    /**
     * This method changes the name of the hotel.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the hotel whose name will be changed
     */
    
    private static void changeHotelName(Scanner scanner, Hotel hotel) {
    	Display.displaySpacer();
    	Display.displayHeadline();
    	System.out.println("Enter new hotel name:");
        String newName = scanner.nextLine();
        hotel.setName(newName);
        Display.displayHeadline();
        System.out.println("Hotel name updated to " + newName);
    }
    
    /**
     * This method adds rooms to a hotel.
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the hotel to which rooms will be added
     */

    public static void addRooms(Scanner scanner, Hotel selectedHotel) {
        System.out.println("Enter number of rooms to add:");
        int numRooms = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String initials = Hotel.getInitials(selectedHotel.getName());

        // Add rooms to the hotel with a base price of 1299.0
        for (int i = 0; i < numRooms; i++) {
            String roomName = initials + String.format("%04d", selectedHotel.getRooms().size() + 1);
            Room room = new Room(roomName, 1299.0, selectedHotel.getName());
            selectedHotel.addRoom(room);
        }

        System.out.println(numRooms + " rooms added to the hotel " + selectedHotel.getName());
        Display.displayHeadline();
    }

    /**
     * This method removes rooms from a hotel.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the hotel to which rooms will be removed
     */

    private static void removeRooms(Scanner scanner, Hotel hotel) {
        System.out.println("Enter room name to remove:");
        String roomName = scanner.nextLine();
        Room room = hotel.getRoomByName(roomName);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        if (!room.isAvailable(null, null)) {
            System.out.println("Room cannot be removed as it has an active reservation.");
            return;
        }

        hotel.removeRoom(room);
        System.out.println("Room " + roomName + " removed from the hotel " + hotel.getName());
    }

    /**
     * This method updates the base price of the room per night.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the hotel to which the base price of the rooms will be changed
     */
    
    private static void updateRoomPrice(Scanner scanner, Hotel hotel) {
        System.out.println("Enter new base price for all rooms:");
        double newPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        if (newPrice < 100.0) {
            System.out.println("New price must be >= 100.0.");
            return;
        }

        for (Room room : hotel.getRooms()) {
            if (!room.isAvailable(null, null)) {
                System.out.println("Cannot update price as there are active reservations.");
                return;
            }
        }

        for (Room room : hotel.getRooms()) {
            room.setBasePricePerNight(newPrice);
        }

        System.out.println("Base price for all rooms updated to " + newPrice);
    }
    
    /**
     * This method removes the reservation from a booking.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the hotel to which the reservation will be removed
     */

    private static void removeReservation(Scanner scanner, Hotel hotel) {
        System.out.println("Enter guest name to remove reservation:");
        String guestName = scanner.nextLine();
        Booking booking = hotel.getBookingByGuestName(guestName);

        if (booking == null) {
            System.out.println("Booking not found.");
            return;
        }

        hotel.removeBooking(booking);
        System.out.println("Booking for guest " + guestName + " removed.");
    }
    
    /**
     * This method removes the hotel completely from the hotel Array List.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotels an Array List that has the list of hotel
     * @param hotel the hotel to be removed
     */

    private static void removeHotel(Scanner scanner, List<Hotel> hotels, Hotel hotel) {
        hotels.remove(hotel);
        System.out.println("Hotel " + hotel.getName() + " removed from the list.");
    }
}
