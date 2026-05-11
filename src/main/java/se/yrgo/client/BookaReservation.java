package se.yrgo.client;

import se.yrgo.dao.TableNotFoundException;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;
import se.yrgo.domain.Tables;
import se.yrgo.service.TableService;

import java.util.Scanner;

public class BookaReservation {


    public static void BookaTable(Customer customer, Scanner input, TableService tableService) {
        Reservation reservations = new Reservation();

        if (customer.getFirstName() == null) {
            System.out.println("Sir you need to register yourself");
            TextClass.clearScreen();
            return;
        }


        System.out.println("Welcome Sir/Madame " + customer.getFirstName());
        tableService.findAll().forEach(System.out::println);

        System.out.println("Please book a available table");

        Long choice = input.nextLong();
        while (true) {
            Tables tables = tableService.findById(choice);
            try {
                if (tables == null) {
                    throw new TableNotFoundException();
                }
            } catch (TableNotFoundException ex) {
                System.out.println("Table not found, try again");
            }

            tableService.findAll().forEach(System.out::println);

            choice = input.nextLong();
            if (tables != null) {
                break;
            }


        }

        System.out.println("your table is booked");


    }
}
