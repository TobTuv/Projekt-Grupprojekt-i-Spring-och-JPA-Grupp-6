package se.yrgo.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import se.yrgo.domain.Customer;
import se.yrgo.domain.Tables;
import se.yrgo.service.TableService;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        Customer customer = new Customer();
        TableService tableService;
        Tables tables = new Tables(2, 4, true, null);
        ClassPathXmlApplicationContext container = new ClassPathXmlApplicationContext("application.xml");


        tableService = container.getBean(TableService.class);
        tableService.findAll().forEach(System.out::println);

//        Tables tablesToFind = new Tables(5);
//        tableService.destroyTable(tablesToFind);

        TextClass.welcomeText();
        while (true) {
            TextClass.clearScreen();
            System.out.printf("""
                    1. Register Customer
                    2. Book A Table
                    3. Cancel Reservation.
                    4. Leave""");


            System.out.println();

            int choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1 -> CustomerService.createProfile(customer, input);
                case 2 -> BookaReservation.BookaTable(customer, input, tableService);
                case 3 -> System.out.println("reservation cancel här");
                case 4 -> {

                    if (customer.getFirstName() != null) {
                        System.out.println("We hope to see you again " + customer.getFirstName());
                        return;
                    } else {
                        System.out.println("Mcdonalds that way");

                        return;
                    }
                }
            }


        }

    }

//    public static void welcomeText() {
//        System.out.println("****************************");
//        System.out.println("| welcome to hells kitchen |");
//        System.out.println("****************************");
//    }

//    public static void createProfile(Customer customer) {
//        Scanner namninput = new Scanner(System.in);
//        System.out.println("name");
//         customer.setFirstName(namninput.nextLine());
//
//        System.out.println("last name");
//         customer.setLastName(namninput.nextLine());
//
//        System.out.println("email");
//         customer.setEmail(namninput.nextLine());
//        System.out.println("phone");
//         customer.setPhone(namninput.nextLine());
//
//
//        System.out.printf("""
//                Welcome %s
//                Here is your profile, Mr/Mrs  %s
//                %s, %s
//                """,customer.getFirstName(), customer.getLastName(),customer.getEmail(),customer.getPhone());
//        System.out.println();
//1
//        System.out.println("Press Enter");
//        namninput.nextLine();
//    }

//    public static void clearScreen() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }
}