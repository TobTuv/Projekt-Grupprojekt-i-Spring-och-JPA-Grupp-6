package se.yrgo.client;

import se.yrgo.domain.Customer;

import java.util.Scanner;

public class CustomerService {

    public static void createProfile(Customer customer, Scanner namninput) {

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
                """, customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhone());
        System.out.println();

        System.out.println("Press Enter");
        namninput.nextLine();
    }

}

