import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    // Map to store availability per hotel
    private static final Map<String, Map<Integer, Set<Integer>>> hotelAvailability = new HashMap<>();
    
    /**
     * This method converts a date in String format into a date object.
     * 
     * @param dateString the date in String format
     * 
     * @return null if the date format is invalid
     */
    
    public static Date parseDate(String dateString) {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use MM/dd/yyyy format.");
            return null;
        }
    }
    
    /**
     * This method converts a date object into a string.
     * 
     * @param date the date
     * 
     * @return the date in String format
     */

    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }
    
    /**
     * This method initializes the availability of the hotel with the given number of rooms.
     * 
     * @param hotelName the name of the hotel
     * @param numRooms the number of rooms in the hotel
     */

    // Initialize the hotel's availability with the given number of rooms
    public static void initializeHotelAvailability(String hotelName, int numRooms) {
        Map<Integer, Set<Integer>> availability = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            Set<Integer> days = new HashSet<>();
            for (int j = 1; j <= 31; j++) {
                days.add(j);
            }
            availability.put(i, days);
        }
        hotelAvailability.put(hotelName, availability);
    }

    /**
     * This method checks whether the room is available or not on a specified date.
     * 
     * @param hotelName the name of the hotel
     * @param date the date
     * 
     * @return true if the parameter days is not null and 
     * contains the specified day and false ifvotherwise
     */
    
    public static boolean isDateAvailable(String hotelName, Room room, Date checkInDate, Date checkOutDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        int startMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int startDay = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(checkOutDate);
        int endMonth = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int endDay = cal.get(Calendar.DAY_OF_MONTH);

        Map<Integer, Set<Integer>> availability = hotelAvailability.get(hotelName);
        if (availability == null) {
            return false;
        }

        for (int month = startMonth; month <= endMonth; month++) {
            Set<Integer> days = availability.get(month);
            if (days == null) {
                return false;
            }

            if (month == startMonth && month == endMonth) {
                for (int day = startDay; day <= endDay; day++) {
                    if (!days.contains(day)) {
                        return false;
                    }
                }
            } else if (month == startMonth) {
                for (int day = startDay; day <= 31; day++) {
                    if (!days.contains(day)) {
                        return false;
                    }
                }
            } else if (month == endMonth) {
                for (int day = 1; day <= endDay; day++) {
                    if (!days.contains(day)) {
                        return false;
                    }
                }
            } else {
                for (int day = 1; day <= 31; day++) {
                    if (!days.contains(day)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    
    /**
     * This method marks the date as booked in relation to the booking date.
     * 
     * @param hotelName the name of the hotel
     * @param date the date
     */
    
    public static void bookDate(String hotelName, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int day = cal.get(Calendar.DAY_OF_MONTH);

        Map<Integer, Set<Integer>> availability = hotelAvailability.get(hotelName);
        if (availability != null) {
            Set<Integer> days = availability.get(month);
            if (days != null) {
                days.remove(day);
            }
        }
    }

    /**
     * This method marks the date as available and adds it to the available dates at the hotel.
     * 
     * @param hotelName the name of the hotel
     * @param date the date
     */
    
    public static void releaseDate(String hotelName, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH) + 1; // Calendar.MONTH is zero-based
        int day = cal.get(Calendar.DAY_OF_MONTH);

        Map<Integer, Set<Integer>> availability = hotelAvailability.get(hotelName);
        if (availability != null) {
            Set<Integer> days = availability.get(month);
            if (days != null) {
                days.add(day);
            }
        }
    }

    /**
     * This method displays the dates available at the hotel.
     * 
     * @param hotelName the name of the hotel
     */
    
    public static void displayAvailableDates(String hotelName) {
        Map<Integer, Set<Integer>> availability = hotelAvailability.get(hotelName);
        if (availability == null) {
            System.out.println("No availability information for hotel: " + hotelName);
            return;
        }

        for (Map.Entry<Integer, Set<Integer>> entry : availability.entrySet()) {
            int month = entry.getKey();
            Set<Integer> days = entry.getValue();
            System.out.println(getMonthName(month) + " - " + days.size() + " days available");
        }
    }
    
    /**
     * This method converts the month in integer format to String format.
     * 
     * @param month the month in integer format
     * 
     * @return returns the month in String forma
     */

    private static String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return monthNames[month - 1];
    }

    /**
     * This method displays the room availabilty for a selected month.
     * 
     * @param room the room object containing its details

     */
    
    public static void displayRoomAvailabilityForMonth(Room room) {
        String hotelName = room.getHotelName();
        System.out.println("Availability for Room: " + room.getName());
        Map<Integer, Set<Integer>> availability = hotelAvailability.get(hotelName);
        if (availability == null) {
            System.out.println("No availability information for hotel: " + hotelName);
            return;
        }

        for (Map.Entry<Integer, Set<Integer>> entry : availability.entrySet()) {
            int month = entry.getKey();
            Set<Integer> days = entry.getValue();
            System.out.println(getMonthName(month) + " - Available days: " + days);
        }
    }
}
