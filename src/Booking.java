import java.util.*;

public class Booking {
	
	/**
	 * This method creates the booking for a specific hotel. It consists of hotel selection, 
	 * check in and check out input, and room selection.
	 * 
	 * @param scanner the scanner object that accepts input from the user
	 * @param hotels an array list that has all the hotels available listed
	 */
	
	public static void createBooking(Scanner scanner, List<Hotel> hotels) {
	    if (hotels.isEmpty()) {
	        System.out.println("No hotels available. Please create a hotel first.");
	        return;
	    }

	    Display.displayHeadline();
	    System.out.println("Select a hotel:");
	    for (int i = 0; i < hotels.size(); i++) {
	        System.out.println((i + 1) + ". " + hotels.get(i).getName());
	    }
	    Display.displayHeadline();
	    System.out.print("Your Choice:");
	    int hotelChoice = scanner.nextInt();
	    scanner.nextLine(); // Consume newline character
	    Hotel selectedHotel = hotels.get(hotelChoice - 1);

	    DateUtil.displayAvailableDates(selectedHotel.getName());

	    Display.displaySpacer();
	    Display.displayHeadline();
	    System.out.println("Create a booking:");
	    System.out.print("Enter guest name:");
	    String guestName = scanner.nextLine();

	    System.out.println("Enter check-in date (MM/dd/yyyy):");
	    System.out.print("Check- In::");
	    Date checkInDate = DateUtil.parseDate(scanner.nextLine());

	    Display.displaySpacer();
	    Display.displayHeadline();
	    System.out.println("Enter check-out date (MM/dd/yyyy):");
	    System.out.print("Check-Out:");
	    Date checkOutDate = DateUtil.parseDate(scanner.nextLine());

	    if (checkInDate == null || checkOutDate == null) {
	        System.out.println("Invalid date format. Please use MM/dd/yyyy format.");
	        return;
	    }

	    List<Room> availableRooms = selectedHotel.getAvailableRooms(checkInDate, checkOutDate);
	    if (availableRooms.isEmpty()) {
	        System.out.println("No available rooms for the selected dates.");
	        return;
	    }
	    Display.displaySpacer();
	    Display.displayHeadline();
	    System.out.print("Select a room:");
	    for (int i = 0; i < availableRooms.size(); i++) {
	        System.out.println((i + 1) + ". " + availableRooms.get(i).getName() + " - " + availableRooms.get(i).getBasePricePerNight());
	    }
	    Display.displaySpacer();
	    Display.displayHeadline();
	    System.out.print("Your Choice");
	    int roomChoice = scanner.nextInt();
	    scanner.nextLine(); // Consume newline character
	    Room selectedRoom = availableRooms.get(roomChoice - 1);

	    if (!isBookingValid(selectedHotel.getName(), checkInDate, checkOutDate, selectedRoom)) {
	        System.out.println("Invalid booking dates. Please check the dates and try again.");
	        System.out.println("Available dates for " + selectedRoom.getName() + ":");
	        DateUtil.displayAvailableDates(selectedHotel.getName());
	        return;
	    }

	    Booking newBooking = new Booking(guestName, checkInDate, checkOutDate, selectedRoom);
	    selectedHotel.addBooking(newBooking);

	    System.out.println("Booking created successfully!");
	}

	/**
	 * This method verifies if the booking is valid or not.
	 * 
	 * @param hotelName the name of the hotel
	 * @param checkInDate the check in date of the room
	 * @param checkOutDate the check out date of the room
	 * 
	 * @returns true if all conditions are met and false if otherwise
	 */
	
    private static boolean isBookingValid(String hotelName, Date checkInDate, Date checkOutDate, Room room) {
        return checkInDate.before(checkOutDate) && DateUtil.isDateAvailable(hotelName, room, checkInDate, checkOutDate) && DateUtil.isDateAvailable(hotelName, room, checkOutDate, checkInDate);
    }


    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private Room room;
    
    /**
     * This method is a constructor that creates a booking object that 
     * includes the guest name, check in and check out date, and the room.
     * 
     * @param guestName the name of the guest
     * @param checkInDate the check in date of the room
     * @param checkOutDate the check out date of the room
     * @param room the details of the room
     */

    public Booking(String guestName, Date checkInDate, Date checkOutDate, Room room) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;

        // Add booked dates to the room
        addBookingDates();
    }
    
    /**
     * This method is a constructor similar to the one above.
     * The only difference is that the room object is not included.
     * 
     * @param guestName the name of the guest
     * @param checkInDate the check in date of the room
     * @param checkOutDate the check out date of the room
     */

    public Booking(String guestName, Date checkInDate, Date checkOutDate) {
    	this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
     
        // Add booked dates to the room
        addBookingDates();
	}

    /**
     * This method add the check in and check out dates of the room to
     * the booked dates of the room.
     */
    
	private void addBookingDates() {
        // Add each date from check-in to check-out to the room's booked dates
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        while (!calendar.getTime().after(checkOutDate)) {
            room.addBookingDate(calendar.getTime(), checkOutDate);
            calendar.add(Calendar.DATE, 1);
        }
    }
	
	/**
     * This method cancels the booking and removes the booked
     * dates from the room
     */

    public void cancelBooking() {
        // Remove booked dates from the room
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        while (!calendar.getTime().after(checkOutDate)) {
            room.removeBookingDate(calendar.getTime(), checkOutDate);
            calendar.add(Calendar.DATE, 1);
        }
    }

    // Getters and Setters
    
    /**
     * This method gets the guest name.
     * 
     * @return the guest name in String format
     */
    
    public String getGuestName() {
        return guestName;
    }

    /**
     * This method sets the guest name.
     * 
     * @param guestName the name of the guest
     */
    
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * This method gets the check in date of the guest.
     * 
     * @return the check in date
     */
    
    public Date getCheckInDate() {
        return checkInDate;
    }

    
    /**
     * This method sets the check in date of the guest.
     * 
     * @param checkInDate the check in date
     */
    
    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    /**
     * This method gets the check in date of the guest.
     * 
     * @return the check out date
     */

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    /**
     * This method sets the check out date of the guest.
     * 
     * @param checkOutDate the check in date
     */
    
    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
    
    /**
     * This method gets the details of the room to an object.
     * 
     * @return room the details of the room
     */

    public Room getRoom() {
        return room;
    }
    
    /**
     * This method sets the details of the room to an object.
     * 
     * @param room the details of the room
     */

    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * This method gets the total price of the booking combining the base price 
     * of the room as well as the longevity of the booking.
     * 
     * @return the total price of the booking
     */
    
    public double getTotalPrice() {
        long duration = (checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24);
        return duration * room.getBasePricePerNight();
    }

    /**
     * This method adds the booking to an existing booking object.
     * 
     * @param booking the booking details
     */
    
	public void add(Booking booking) {
    booking.add(booking);
	}

		
}


