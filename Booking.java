package bus;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Booking {
    String passengerName;
    int busNo;
    Date date;


    Booking(Date travelDate) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of passenger: ");
        passengerName = scanner.next();
        System.out.println("Enter bus no: ");
        busNo = scanner.nextInt();
        this.date = travelDate;
    }

    public boolean isAvailable(ArrayList<Booking> bookings, ArrayList<Bus> buses) {
        int capacity = 0;

        for (Bus bus : buses) {
            if (bus.getBusNo() == busNo) {
                capacity = bus.getCapacity();
                break;
            }
        }

        int booked = 0;
        for (Booking b : bookings) {
            if (b.busNo == busNo && b.date.equals(date)) {
                booked++;
            }
        }

        return booked < capacity;
    }
}
