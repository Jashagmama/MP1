import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Display {
    
	 public static void displayHeadline() {
		 System.out.println("-----------------------------");
	 }
	 
	public static void displayNoOfRooms() {
    	System.out.println("Enter number of rooms:");
    }

    public static void displayHotelNamePrompt() {
        System.out.println("Hotel Name:");
    }

    public static void displayBasePricePerNightPrompt() {
        System.out.println("Enter base price per night for all rooms:");
    }

    public static void displayCheckInDatePrompt() {
        System.out.println("Enter check-in date (MM/dd/yyyy):");
    }

    public static void displayCheckOutDatePrompt() {
        System.out.println("Enter check-out date (MM/dd/yyyy):");
    }

    public static void displayGuestNamePrompt() {
        System.out.println("Enter guest name:");
    }

    public static void displayAvailableRooms(List<Room> availableRooms) {
        System.out.println("Select a room:");
        for (int i = 0; i < availableRooms.size(); i++) {
            System.out.println((i + 1) + ". " + availableRooms.get(i).getName() + " - " + availableRooms.get(i).getBasePricePerNight());
        }
    }

    public static void displayHotels(List<Hotel> hotels) {
        System.out.println("Select a hotel:");
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ". " + hotels.get(i).getName());
        }
    }

    public static void displayAvailableCheckInDates(List<Date> availableDates) {
        System.out.println("Available check-in dates:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Date date : availableDates) {
            System.out.println(dateFormat.format(date));
        }
    }

    public static void displayAvailableCheckOutDates(List<Date> availableDates) {
        System.out.println("Available check-out dates:");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (Date date : availableDates) {
            System.out.println(dateFormat.format(date));
        }
    }

    public static void displayBookingCreatedSuccess() {
        System.out.println("Booking created successfully!");
    }

    public static void displayInvalidDateFormat() {
        System.out.println("Invalid date format. Please use MM/dd/yyyy format.");
    }

    public static void displayInvalidBookingDates() {
        System.out.println("Invalid booking dates. Please check the dates and try again.");
    }

    public static void displayNoAvailableRooms() {
        System.out.println("No available rooms for the selected dates.");
    }
}
