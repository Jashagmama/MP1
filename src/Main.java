import java.util.*;

public class Main {
    private static final String MANAGER_PASSWORD = "0000";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Hotel> hotels = new ArrayList<>();
      
        while (true) {
            Display.displayHeadline();
            System.out.println("Choose an action:");
            System.out.println("1. Customer");
            System.out.println("2. Manager");
            System.out.println("3. Exit");
            Display.displayHeadline();

            System.out.print("Your Choice: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (userChoice == 3) {
                System.out.println("Exiting program...");
                scanner.close();
                return; // Exit the program
            }

            switch (userChoice) {
                case 1:
                    customerActions(scanner, hotels);
                    break;
                case 2:
                    System.out.print("Enter Manager Password: ");
                    String password = scanner.nextLine();
                    if (password.equals(MANAGER_PASSWORD)) {
                        managerActions(scanner, hotels);
                    } else {
                        System.out.println("Incorrect password. Please try again.");
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void customerActions(Scanner scanner, List<Hotel> hotels) {
        while (true) {
            Display.displayHeadline();
            System.out.println("Choose an action:");
            System.out.println("1. Create Booking");
            System.out.println("2. Back to main menu");
            Display.displayHeadline();

            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 2) {
                return; // Return to main menu
            }

            switch (choice) {
                case 1:
                    Booking.createBooking(scanner, hotels);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void managerActions(Scanner scanner, List<Hotel> hotels) {
        while (true) {
            Display.displayHeadline();
            System.out.println("Choose an action:");
            System.out.println("1. Create Hotel");
            System.out.println("2. Manage Hotel");
            System.out.println("3. View Bookings");
            System.out.println("4. Back to main menu");
            Display.displayHeadline();

            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 4) {
                return; // Return to main menu
            }

            switch (choice) {
                case 1:
                    Create.createHotel(scanner, hotels);
                    break;
                case 2:
                    Manage.manageHotel(scanner, hotels);
                    break;
                case 3:
                    View.viewHotel(scanner, hotels);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
