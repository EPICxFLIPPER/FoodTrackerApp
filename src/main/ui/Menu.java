package ui;

//A menu is a dispaly with a set of commands that leads to specific results
public interface Menu {

    //EFFECTS: Creates the console interface of a specific menu.
    public void displayMenu();

    //EFFECTS: runs the result of user commands.
    public void processCommand(String command);

}
