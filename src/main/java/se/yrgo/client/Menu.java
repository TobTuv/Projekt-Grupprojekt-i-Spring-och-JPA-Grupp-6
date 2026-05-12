package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Reservation;
import se.yrgo.domain.Tables;
import se.yrgo.service.CustomerService;
import se.yrgo.service.ReservationService;
import se.yrgo.service.TableService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        CustomerService customerService;
        TableService tableService;
        Customer customer = new Customer();

        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");

        tableService = container.getBean(TableService.class);
        customerService = container.getBean(CustomerService.class);

        if (tableService.findAll().isEmpty()) {

            tableService.create(new Tables(1, 2, true, null));
            tableService.create(new Tables(2, 4, true, null));
            tableService.create(new Tables(3, 6, true, null));
        }
        ReservationService reservationService = container.getBean(ReservationService.class);

        tableService.findAll().forEach(System.out::println);


        TextClass.welcomeText();
        while (true) {
            TextClass.clearScreen();
            System.out.printf("""
                    1. Registrera Kund
                    2. Boka Bord
                    3. Visa bokningar
                    4. Lämna""");

            System.out.println();

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> CustomerCreate.createProfile(customerService, customer, input);
                case 2 ->
                        BookaReservation.BookaTable(customer, customerService, input, tableService, reservationService);
                case 3 -> {
                        List<Reservation> bookings = customerService.findBookingsByCustomerId(customer.getId());
                    if (bookings == null) {
                        System.out.println("Du har inga bokningar");
                        System.out.println("Återvänd till huvudmenyn");
                        input.nextLine();
                        TextClass.clearScreen();
                    } else {


                        System.out.printf(" Dina Bokningar: ");
                        System.out.println();

                        bookings.forEach(System.out::println);

                        System.out.println("Återvänd till huvudmenyn");
                        System.out.println("Tryck Enter");

                        input.nextLine();


                    }

                }
                case 4 -> {

                    if (customer.getFirstName() != null) {
                        System.out.println("Välkommen åter " + customer.getFirstName());
                    } else {
                        System.out.println("Mcdonalds ligger ditåt!");

                    }
                    return;
                }
            }

        }

    }

    // public static void welcomeText() {
    // System.out.println("****************************");
    // System.out.println("| welcome to hells kitchen |");
    // System.out.println("****************************");
    // }

    // public static void createProfile(Customer customer) {
    // Scanner namninput = new Scanner(System.in);
    // System.out.println("name");
    // customer.setFirstName(namninput.nextLine());
    //
    // System.out.println("last name");
    // customer.setLastName(namninput.nextLine());
    //
    // System.out.println("email");
    // customer.setEmail(namninput.nextLine());
    // System.out.println("phone");
    // customer.setPhone(namninput.nextLine());
    //
    //
    // System.out.printf("""
    // Welcome %s
    // Here is your profile, Mr/Mrs %s
    // %s, %s
    // """,customer.getFirstName(),
    // customer.getLastName(),customer.getEmail(),customer.getPhone());
    // System.out.println();
    // 1
    // System.out.println("Press Enter");
    // namninput.nextLine();
    // }

    // public static void clearScreen() {
    // System.out.print("\033[H\033[2J");
    // System.out.flush();
    // }
}