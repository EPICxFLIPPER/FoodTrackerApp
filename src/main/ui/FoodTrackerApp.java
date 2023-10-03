package ui;

import model.CookBook;

import java.util.Scanner;

public class FoodTrackerApp {

    private CookBook cookBook;
    private Scanner input;

    // EFFECTS: runs the teller application
    public FoodTrackerApp() {
        runFoodTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runFoodTracker() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes cookBook
    private void init() {
        cookBook = new CookBook();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> View Pantry");
        System.out.println("\tr -> View Recipes");
        System.out.println("\tc -> Can Cook?");
        System.out.println("\tn -> Needed to Cook");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            System.out.println("Works");
        } else if (command.equals("r")) {
            System.out.println("Works");
        } else if (command.equals("c")) {
            System.out.println("Works");
        } else if (command.equals("n")) {
            System.out.println("Works");
        } else {
            System.out.println("Selection not valid...");
        }
    }

}
