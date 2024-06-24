import java.util.*;

public class Room {
    private String name;
    private double basePricePerNight;
    private String hotelName; // Added hotel name

    public Room(String name, double basePricePerNight, String hotelName) {
        this.name = name;
        this.basePricePerNight = basePricePerNight;
        this.hotelName = hotelName; // Initialize hotel name
    }

    public String getName() {
        return name;
    }

    public double getBasePricePerNight() {
        return basePricePerNight;
    }

    public String getHotelName() {
        return hotelName;
    }

    public boolean isAvailable(Date checkInDate, Date checkOutDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(checkInDate);
        while (cal.getTime().before(checkOutDate) || cal.getTime().equals(checkOutDate)) {
            if (!DateUtil.isDateAvailable(hotelName, cal.getTime())) {
                return false;
            }
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return true;
    }
}
