package ui;

import model.CookBook;

import java.util.Scanner;

public class MainMenu implements Menu {

    private Scanner input;
    private CookBook cookBook;

    public MainMenu(Scanner input, CookBook cookbook) {
        this.input = input;
        this.cookBook = cookbook;
    }


    @Override
    public void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tp ->  Pantry");
        System.out.println("\tap -> Add to Pantry");
        System.out.println("\tr -> View Recipes");
        System.out.println("\tc -> Can Cook?");
        System.out.println("\tn -> Needed to Cook");
        System.out.println("\tq -> quit");
    }

    @Override
    public void processCommand() {

    }
}
