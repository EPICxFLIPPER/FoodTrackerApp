package ui;

import model.CookBook;

import java.util.Scanner;

public class PantryMenu implements Menu {

    private Scanner input;
    private CookBook cookBook;

    public PantryMenu(Scanner input, CookBook coodbook) {
        this.input = input;
        this.cookBook = coodbook;
    }


    @Override
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv ->  View Pantry");
        System.out.println("\tap -> Add to Pantry");
        System.out.println("\tr -> Remove from Pantry");
        System.out.println("\tm -> To Main Menu");
    }

    @Override
    public void processCommand() {

    }
}
