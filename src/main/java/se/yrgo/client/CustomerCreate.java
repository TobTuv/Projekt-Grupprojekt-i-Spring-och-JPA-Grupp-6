package se.yrgo.client;

import se.yrgo.domain.Customer;

import java.beans.Customizer;
import java.util.Scanner;
import se.yrgo.service.*;

public class CustomerCreate {

    CustomerService customer;

    public static void createProfile(CustomerService customerService, Customer customer, Scanner namninput) {
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
        customerService.save(customer);
    }

}

