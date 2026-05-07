package Client;

import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        System.out.println("****************************");
        System.out.println("| welcome to hells kitchen |");
        System.out.println("****************************");

        System.out.printf("""
                1. Registrera kund
                2. Boka bord
                3. lämna""");


        Scanner input = new Scanner(System.in);

        int choice = input.nextInt();


        switch (choice) {
            case 1 -> System.out.println("ph");
            case 2 -> System.out.println("ph");
            case 3 -> System.exit(0);
        }


    }

    public static void joe() {
        Scanner nameinput = new Scanner(System.in);
        System.out.println("name");

        String name = nameinput.nextLine();

        System.out.println();



    }
}
