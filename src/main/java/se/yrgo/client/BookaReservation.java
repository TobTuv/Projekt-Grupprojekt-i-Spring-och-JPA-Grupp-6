package se.yrgo.client;

import se.yrgo.exception.TableNotFoundException;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;
import se.yrgo.domain.Tables;
import se.yrgo.exception.ReservationNotFoundException;
import se.yrgo.service.CustomerService;
import se.yrgo.service.ReservationService;
import se.yrgo.service.TableService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookaReservation {

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("MM-dd");

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
            input.nextLine();

            tables = tableService.findById(choice);

            if (tables != null) {
                break;
            }

            System.out.println("Table not found, try again");
        }

        // date
        System.out.print("Enter date (yyyy-MM-dd): ");

        LocalDate date = null;

        while (date == null) {
            try {
                date = LocalDate.parse(input.nextLine());

                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Cannot choose past dates.");
                    date = null;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Invalid format.");
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
        for(int i = 0; i < availableTimes.size(); i++){
            System.out.println((i + 1) + ". " + availableTimes.get(i));
        }

        int timeChoice = input.nextInt();
        input.nextLine();

        LocalTime chosenTime = availableTimes.get(timeChoice - 1);
        LocalDateTime dateTime =
                LocalDateTime.of(date, chosenTime);

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
            System.out.println("Ditt bord är bokad " + r);
        } catch (IllegalArgumentException e) {
            System.out.println("could not book: " + e.getMessage());
        }
        System.out.println("\nPress Enter to continue");
        input.nextLine();

    }
}
