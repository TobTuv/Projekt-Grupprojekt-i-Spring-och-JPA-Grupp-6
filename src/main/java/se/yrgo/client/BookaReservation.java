package se.yrgo.client;

import se.yrgo.exception.TableNotFoundException;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;
import se.yrgo.domain.Tables;
import se.yrgo.exception.ReservationNotFoundException;
import se.yrgo.service.CustomerService;
import se.yrgo.service.ReservationService;
import se.yrgo.service.TableService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BookaReservation {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void BookaTable(Customer customer, CustomerService customerService, Scanner input, TableService tableService,
            ReservationService reservationService) {
        
        
        Reservation reservations = new Reservation();

        if (customer.getFirstName() == null) {
            System.out.println("Sir you need to register yourself");
            TextClass.clearScreen();
            return;
        }

        System.out.println("Welcome Sir/Madame " + customer.getFirstName());
        tableService.findAll().forEach(System.out::println);

        System.out.println("Please book a available table");

        Tables tables;

        while (true) {
            System.out.println("Choose table id:");
            Long choice = input.nextLong();

            tables = tableService.findById(choice);

            if (tables != null) {
                break;
            }

            System.out.println("Table not found, try again");
        }

        // date
        System.out.print("Date and time (yyyy-MM-dd HH:mm): ");
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            try {
                dateTime = LocalDateTime.parse(input.nextLine(), FMT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format, try again (yyyy-MM-dd HH:mm):");
            }
        }

        // Number of guests
        System.out.println("Number of guests ");
        int guests = input.nextInt();
        input.nextLine();

        // Notes
        System.out.println("Notes ( or press enter to skip): ");
        String notes = input.nextLine();

        // Save booking via ReservationService
        try {
            Reservation r = reservationService.bookTable(
                    customer.getId(), tables.getId(), dateTime, guests, notes);
            System.out.println("your table is booked!/n" + r);
        } catch (IllegalArgumentException e) {
            System.out.println("could not book: " + e.getMessage());
        }
        System.out.println("\nPress Enter to continue");
        input.nextLine();

    }
}
