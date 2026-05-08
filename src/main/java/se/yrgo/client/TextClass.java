package se.yrgo.client;

public class TextClass {


    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void welcomeText() {
        System.out.println("****************************");
        System.out.println("| welcome to hells kitchen |");
        System.out.println("****************************");
    }
}
