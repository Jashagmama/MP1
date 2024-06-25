import java.util.*;

public class View {
	
	/**
	 * This method views the details of the hotel and asks the user to choose a piece of low level information to view. 
	 * It includes the information about a selected room or reservation or the total number of available 
	 * and booked rooms on a selected date.
	 * 
	 * @param scanner the scanner object that accepts input from the user
	 * @param hotels an Array List that has the list of hotels
	 */
	
    public static void viewHotel(Scanner scanner, List<Hotel> hotels) {
        // Select a hotel
    	Display.displayHeadline();
    	System.out.println("Select a hotel:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
        System.out.print("Your Choice");
        int hotelChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Hotel selectedHotel = hotels.get(hotelChoice - 1);
        Display.displayHeadline();
       
        Display.displaySpacer();
        Display.displayHeadline();
        System.out.println("1: High Level:");
        System.out.println("2: Low Level:");
        System.out.print("Your Choice: ");
        
        int LevelChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        switch (LevelChoice) {
        		case 1:
        			// Display high-level information
        	        displayHighLevelInfo(selectedHotel);
        	        break;
        	        
        		case 2:{
        		Display.displayHeadline();
                System.out.println("Select low-level information:");
                System.out.println("1. Total number of available and booked rooms for a selected date");
                System.out.println("2. Information about a selected room");
                System.out.println("3. Information about a selected reservation");
                
                System.out.print("Your Choice");
                int infoChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                Display.displayHeadline();
                
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
                } break; 
                
        			}
        		default:
        		System.out.println("Invalid choice. Please try again.");
                break;
        }
        
        		}

    /**
     * This method displays the high level information of a selected hotel. It includes the hotel name, 
     * the total number of rooms, and the estimated earnings for a selected month.
     * 
     * @param hotel the selected hotel
     */
        
    private static void displayHighLevelInfo(Hotel hotel) {
        System.out.println("Hotel Name: " + hotel.getName());
        System.out.println("Total number of rooms: " + hotel.getRooms().size());
        System.out.println("Estimated earnings for the month: " + calculateEarnings(hotel));
    }
    
    /**
     * This method calculates the earnings of a hotel in a selected month.
     * 
     * @param hotel the selected hotel
     * 
     * @return the computed earnings of the hotel
     */

    private static double calculateEarnings(Hotel hotel) {
        double earnings = 0.0;
        for (Booking booking : hotel.getBookings()) {
            earnings += booking.getTotalPrice();
        }
        return earnings;
    }
    
    /**
     * This method displays the room availability for a specified date. 
     * It shows the number of available and booked rooms.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the selected hotel
     */

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
            if (room.isAvailable(date, date)) {
                availableRooms++;
            } else {
                bookedRooms++;
            }
        }
        System.out.println("Available rooms: " + availableRooms);
        System.out.println("Booked rooms: " + bookedRooms);
    }
    
    /**
     * This method displays information about a room. It includes the name, 
     * the base price per night, and its availability for the month.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the selected hotel
     */

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
    
    /**
     * This method displays the information on a reservation. It includes the guest name, room booked, 
     * check in and check out dates, total price, and the price breakdown.
     * 
     * @param scanner the scanner object that accepts input from the user
     * @param hotel the selected hotel
     */

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
