package bus;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BusDemo {

    
    public static boolean hasAvailableSeats(Bus bus, Date date, ArrayList<Booking> bookings) {
        int booked = 0;
        for (Booking b : bookings) {
            if (b.busNo == bus.getBusNo() && b.date.equals(date)) {
                booked++;
            }
        }
        return booked < bus.getCapacity();
    }

    
    public static int getAvailableSeats(Bus bus, Date date, ArrayList<Booking> bookings) {
        int booked = 0;
        for (Booking b : bookings) {
            if (b.busNo == bus.getBusNo() && b.date.equals(date)) {
                booked++;
            }
        }
        return bus.getCapacity() - booked;
    }

    public static void main(String[] args) {

        ArrayList<Bus> buses = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        buses.add(new Bus(1, true, 2));
        buses.add(new Bus(2, false, 50));
        buses.add(new Bus(3, true, 48));

        int userOpt = 0;
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        while (userOpt != 3) {
            System.out.println("\n📋 Menu:");
            System.out.println("1. Book Ticket");
            System.out.println("2. View All Bookings");
            System.out.println("3. Exit");
            System.out.print("Your choice: ");
            userOpt = scanner.nextInt();

            switch (userOpt) {
                case 1:
                    System.out.print("Enter travel date (dd-MM-yyyy): ");
                    String dateInput = scanner.next();
                    Date selectedDate;

                    try {
                        selectedDate = sdf.parse(dateInput);
                    } catch (ParseException e) {
                        System.out.println(" Invalid date format.");
                        break;
                    }

                   
                    boolean busFound = false;
                    System.out.println("\n Buses available on " + dateInput + ":");
                    for (Bus b : buses) {
                        int availableSeats = getAvailableSeats(b, selectedDate, bookings);
                        if (availableSeats > 0) {
                            System.out.println("Bus No: " + b.getBusNo() +
                                               " | AC: " + b.isAc() +
                                               " | Total Capacity: " + b.getCapacity() +
                                               " | Available Seats: " + availableSeats);
                            busFound = true;
                        }
                    }

                    if (!busFound) {
                        System.out.println(" No buses available for this date.");
                        break;
                    }

                  
                    Booking booking = new Booking(selectedDate);
                    if (booking.isAvailable(bookings, buses)) {
                        bookings.add(booking);
                        System.out.println(" Your booking is confirmed.");
                    } else {
                        System.out.println(" Sorry. Bus is full. Try another bus or date.");
                    }
                    break;

                case 2:
                    if (bookings.isEmpty()) {
                        System.out.println(" No bookings yet.");
                    } else {
                        System.out.println(" Confirmed Bookings:");
                        for (Booking b : bookings) {
                            System.out.println("Passenger: " + b.passengerName +
                                               " | Bus No: " + b.busNo +
                                               " | Date: " + sdf.format(b.date));
                        }
                    }
                    break;

                case 3:
                    System.out.println(" Exiting the system. Thank you!");
                    break;

                default:
                    System.out.println(" Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
