package se.yrgo.client;

import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;
import se.yrgo.service.TableService;

import java.util.Scanner;

public class BookaReservation {

    private static TableService tableService;

    public static void BookaTable(Customer customer, Scanner input, Reservation reservation) {

        System.out.println("Welcome Sir/Madame " + customer.getFirstName());


        System.out.println("Which table would you like to book?");
        tableService.findTablesWithoutReservation();
    }
}
