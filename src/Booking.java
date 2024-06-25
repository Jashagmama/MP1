import java.util.*;

public class Booking {
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

    private static boolean isBookingValid(String hotelName, Date checkInDate, Date checkOutDate, Room room) {
        return checkInDate.before(checkOutDate) && DateUtil.isDateAvailable(hotelName, room, checkInDate, checkOutDate) && DateUtil.isDateAvailable(hotelName, room, checkOutDate, checkInDate);
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

        // Add booked dates to the room
        addBookingDates();
    }

    public Booking(String guestName, Date checkInDate, Date checkOutDate) {
    	this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
     
        // Add booked dates to the room
        addBookingDates();
	}

	private void addBookingDates() {
        // Add each date from check-in to check-out to the room's booked dates
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(checkInDate);
        while (!calendar.getTime().after(checkOutDate)) {
            room.addBookingDate(calendar.getTime(), checkOutDate);
            calendar.add(Calendar.DATE, 1);
        }
    }

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
    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getTotalPrice() {
        long duration = (checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24);
        return duration * room.getBasePricePerNight();
    }


	public void add(Booking booking) {
    booking.add(booking);
	}

		
}


