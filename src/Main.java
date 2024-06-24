import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Hotel> hotels = new ArrayList<>();

        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Create Hotel");
            System.out.println("2. Create Booking");
            System.out.println("3. Manage Hotel");
            System.out.println("4. View Bookings");
            System.out.println("5. View Hotel");
            System.out.println("6. Exit");
            System.out.print("Your Choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    Create.createHotel(scanner, hotels);
                    break;
                case 2:
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Please create a hotel first.");
                    } else {
                        Booking.createBooking(scanner, hotels);
                    }
                    break;
                case 3:
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Please create a hotel first.");
                    } else {
                        Manage.manageHotel(scanner, hotels);
                    }
                    break;
                case 4:
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Please create a hotel first.");
                    } else {
                     
                    }
                    break;
                case 5:
                    if (hotels.isEmpty()) {
                        System.out.println("No hotels available. Please create a hotel first.");
                    } else {
                        View.viewHotel(scanner, hotels);
                    }
                    break;
                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
