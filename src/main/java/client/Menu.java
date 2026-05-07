package client;

import java.util.Scanner;

import domain.Customer;

public class Menu {

    public static void main(String[] args) {

        Customer customer = new Customer();
        welcomeText();
        while (true) {
            clearScreen();
            System.out.printf("""
                    1. Registrera kund
                    2. Boka bord
                    3. lämna""");


            System.out.println();
            Scanner input = new Scanner(System.in);

            int choice = input.nextInt();


            switch (choice) {
                case 1 -> createProfile(customer);
                case 2 -> System.out.println("ph");
                case 3 -> {
                    System.out.println("bye bye " + customer.getId());
                    return;
                }
            }


        }

    }

    public static void welcomeText() {
        System.out.println("****************************");
        System.out.println("| welcome to hells kitchen |");
        System.out.println("****************************");
    }

    public static void createProfile(Customer customer) {
        Scanner namninput = new Scanner(System.in);
        System.out.println("name");
         customer.setFirstName(namninput.nextLine());

        System.out.println("last name");
         customer.setLastName(namninput.nextLine());

        System.out.println("email");
         customer.setEmail(namninput.nextLine());
        System.out.println("phone");
         customer.setPhone(namninput.nextLine());


        System.out.printf("""
                Welcome %s 
                Here is your profile, Mr/Mrs  %s
                %s, %s
                """,customer.getFirstName(), customer.getLastName(),customer.getEmail(),customer.getPhone());
        System.out.println();

        System.out.println("Press Enter");
        namninput.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}