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
            System.out.println("Du måste registrera dig först");
            //TextClass.clearScreen();
            return;
        }

        System.out.println("Välkommen " + customer.getFirstName());
        tableService.findAll().forEach(System.out::println);

        System.out.println("Var vänlig och välj ett tillgängligt bord.");

        Tables tables;

        while (true) {
            System.out.println("Välj ett bordsnummer: ");
            Long choice = input.nextLong();
            
    
            tables = tableService.findById(choice);

            if (!tableService.findById(choice).isAvailable()){
                System.out.println("Bordet är inte tillgängligt.");
                return;
            }

            if (tables != null) {
                break;
            }

            System.out.println("Bordet hittades inte, försök igen");
        }

        // date
        System.out.print("Datum och tid (yyyy-MM-dd HH:mm): ");
        LocalDateTime dateTime = null;
        while (dateTime == null) {
            try {
                dateTime = LocalDateTime.parse(input.nextLine(), FMT);
            } catch (DateTimeParseException e) {
                System.out.println("Ogiltigt format, försök igen (yyyy-MM-dd HH:mm):");
            }
        }

        // Number of guests
        System.out.println("Antal gäster ");
        int guests = input.nextInt();
        input.nextLine();

        // Notes
        System.out.println("Anteckningar ( tryck enter för att fortsätta): ");
        String notes = input.nextLine();

        // Save booking via ReservationService
        try {
            Reservation r = reservationService.bookTable(
                    customer.getId(), tables.getId(), dateTime, guests, notes);
            System.out.println("your table is booked!/n" + r);
        } catch (IllegalArgumentException e) {
            System.out.println("Det gick inte att boka: " + e.getMessage());
        }
        System.out.println("\nTryck enter för att fortsätta");
        input.nextLine();

    }
}
