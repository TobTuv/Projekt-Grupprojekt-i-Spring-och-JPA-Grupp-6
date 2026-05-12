package se.yrgo.client;

import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;
import se.yrgo.domain.Tables;
import se.yrgo.exception.TableNotFoundException;
import se.yrgo.service.CustomerService;
import se.yrgo.service.ReservationService;
import se.yrgo.service.TableService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookaReservation {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MM-dd");

    public static void BookaTable(Customer customer, CustomerService customerService, Scanner input, TableService tableService,
                                  ReservationService reservationService) {


        Reservation reservations = new Reservation();

        if (customer.getFirstName() == null) {
            System.out.println("Du måste registrera dig först");

            System.out.println("Tryck 'Enter' för att går till menun");
            input.nextLine();
            TextClass.clearScreen();
            return;
        }

        System.out.println("Välkommen " + customer.getFirstName());
        tableService.findAll().forEach(System.out::println);

        System.out.println("Var vänlig och välj ett tillgängligt bord.");

        Tables tables;

        while (true) {
            try{

            System.out.println("Välj ett bordsnummer: ");
            Long choice = input.nextLong();
            input.nextLine();

            tables = tableService.findById(choice);


            if (!tableService.findById(choice).isAvailable()) {
                System.out.println("Bordet är ej tillgängligt, försök igen");
                continue;
            }


            break;
            }catch (TableNotFoundException e) {
                System.out.println("table was not found, try again");
            }catch (InputMismatchException e) {
                System.out.println("Var vänlig och skriv in rätt siffra");
                input.nextLine();
            }

        }

        // date
        System.out.print("Datum och tid (yyyy-MM-dd): ");

        LocalDate date = null;

        while (date == null) {
            try {
                date = LocalDate.parse(input.nextLine());

                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Kan inte välja tidigare datum. ");
                    date = null;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Ogiltigt format, försök igen (yyyy-MM-dd):");
            }
        }

        //Tid
        LocalTime start = LocalTime.of(17, 0);
        LocalTime end = LocalTime.of(22, 0);

        List<LocalTime> availableTimes = new ArrayList<>();

        while (!start.isAfter(end)) {
            availableTimes.add(start);
            start = start.plusMinutes(30);
        }

        System.out.println("Available times: ");
        for (int i = 0; i < availableTimes.size(); i++) {
            System.out.println((i + 1) + ". " + availableTimes.get(i));
        }

        int timeChoice = input.nextInt();
        input.nextLine();

        LocalTime chosenTime = availableTimes.get(timeChoice - 1);
        LocalDateTime dateTime =
                LocalDateTime.of(date, chosenTime);

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
            tables.setAvailable(false);
            tableService.update(tables);
            System.out.println("Ditt bord är bokad " + r);
        } catch (IllegalArgumentException e) {
            System.out.println("Det gick inte att boka: " + e.getMessage());
        }
        System.out.println("\nTryck enter för att fortsätta");
        input.nextLine();

    }
}
