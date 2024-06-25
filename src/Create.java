import java.util.*;

public class Create {
	
	/**
	 * This method creates a hotel that consists of a hotel name, number of rooms, 
	 * base price of the rooms per night, and the room names.
	 * 
	 * @param scanner the scanner object that accepts input from the user
	 * @param hotels the Array List of hotels
	 */
	
	public static void createHotel(Scanner scanner, List<Hotel> hotels) {
	    // Get hotel name from user
		Display.displayHeadline();
		System.out.print("Hotel Name:");
	    String hotelName = scanner.nextLine();
	  
	    
	    // Extract initials from hotel name
	    String initials = Hotel.getInitials(hotelName);

	    // Get number of rooms from user, limited to 50
	    int numRooms;
	    do {
	    	Display.displayHeadline();
	    	System.out.print("Number of rooms (max 50):");
	        numRooms = scanner.nextInt();
	        scanner.nextLine(); // Consume newline character
	        Display.displayHeadline();
	    } while (numRooms <= 0 || numRooms > 50);

	    // Default base price per night for all rooms
	    double basePricePerNight = 1299.0;

	    // Create a list to store the rooms
	    List<Room> rooms = new ArrayList<>();

	    // Add rooms to the list with universal naming
	    for (int i = 0; i < numRooms; i++) {
	        String roomName = initials + String.format("%04d", i + 1);
	        Room room = new Room(roomName, basePricePerNight, roomName);
	        rooms.add(room);
	    }

	    // Create a new Hotel instance and add rooms directly
	    Hotel hotel = new Hotel(hotelName);
	    for (Room room : rooms) {
	        hotel.addRoom(room);
	    }

	    // Add the hotel to the list of hotels
	    hotels.add(hotel);

	    // Optionally, display hotel information to verify
	    System.out.println("Hotel " + hotelName + " has been created with " + numRooms + " rooms.");
	    if (!rooms.isEmpty()) {
	        Room exampleRoom = rooms.get(0);
	        System.out.println("Example room: " + exampleRoom.getName() + " with base price " + exampleRoom.getBasePricePerNight());
	    }
	}

}
