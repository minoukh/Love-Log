package ui;

import model.DateEntry;
import model.Person;

import java.util.ArrayList;
import java.util.Scanner;


public class MyJournal {
    private ArrayList<Person> listOfPerson;
    private ArrayList<DateEntry> listOfDates;
    private Person newPerson;
    private DateEntry newDate;
    private Scanner input;
    private Boolean inPeronCreation = false;

    private Boolean inListOfPeople = false;

    public MyJournal() {
        runJournal();
    }

    private void runJournal() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            if (!inPeronCreation) {
                mainDisplayMenu();
                command = input.next();
                command = command.toLowerCase();
            } else {
                command = input.next();
                command = command.toLowerCase();
            }

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    private void init() {
        listOfDates = new ArrayList<>();
        listOfPerson = new ArrayList<>();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("np")) {
            doCreatePerson();
            inPeronCreation = true;
            inListOfPeople = true;
        } else if (command.equals("lop")) {
            doPrintListOfPeople();
            inListOfPeople = true;
        } else if (command.equals("ap")) {
            doAddToPros();
            personDisplayMenu();
        } else if (command.equals("an")) {
            doAddToCons();
            personDisplayMenu();
        } else if (command.equals("ad")) {
            doAddToDates();
            personDisplayMenu();
        } else if (command.equals("b")) {
            mainDisplayMenu();
            inPeronCreation = false;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doAddToDates() {
        newDate = new DateEntry(newPerson.getName(), newPerson.numOfDatesWithPerson() + 1);
        newPerson.addDateToListOfDates(newDate);

        System.out.println("\nYou can add some highlights to this date:");
        String highlight = input.next();
        newDate.addHighlightEvent(highlight);

        System.out.println("\nYou can add some redflags you noticed to this date:");
        String redFlag = input.next();
        newDate.addRedFlagEvent(redFlag);

        System.out.println("\nWould you go out with this person again? (true/false)");
        Boolean successStatus = input.nextBoolean();
        newDate.setSuccessfulness(successStatus);

        System.out.println("\nDate " + newDate.getDateNumber() + " with " + newDate.getDateName() + " is now added!");
    }

    private void doAddToCons() {
        System.out.println("\nWhat is a bad trait in this person that you have noticed?");
        String con = input.next();
        newPerson.addToCons(con);
        newPerson.deductOnePoint();
    }

    private void doAddToPros() {
        System.out.println("\nWhat is a good quality of this person that you have noticed?");
        String pro = input.next();
        newPerson.addToPros(pro);
        newPerson.addOnePoint();
    }


    public void doCreatePerson() {

        System.out.println("\nWhat is the name of the person you are seeing?");
        String name = input.next();
        System.out.println("\nHow old are they?");
        int age = input.nextInt();
        System.out.println("\nWhere do they live?");
        String location = input.next();
        System.out.println("\nWhat do they do for a living?");
        String job = input.next();

        newPerson = new Person(name, age, location, job);
        listOfPerson.add(newPerson);
        System.out.println("\n" + name + " is now added to your list!");
        personDisplayMenu();
    }

    // EFFECTS: displays menu of options to user
    private void mainDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tnp -> create a new person");
        System.out.println("\tlop -> see the list of people you are dating");
        System.out.println("\tq -> quit journaling");
    }

    private void personDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tap -> Add to the list of this person's positive characteristics.");
        System.out.println("\tan -> Add to the list of this person's negative characteristics.");
        System.out.println("\tad -> Add a date or interaction you have had with this person!🍷🥩");
        System.out.println("\tb -> Go to the main menu");
        System.out.println("\tq -> quit journaling");
    }

    private void listOfPersonDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> View a candidate description and dates you have had with them.");
        System.out.println("\tmod -> Modify a candidate. 🔧");
        System.out.println("\trem -> Remove a person you are no longer seeing...💔");
        System.out.println("\tan -> Add a new person 😈");
        System.out.println("\tb -> Go to the main menu");
        System.out.println("\tq -> quit journaling");
    }

    // MODIFIES: this
    // EFFECTS:
    private void doPrintListOfPeople() {
        int i = 1;
        for (Person p : listOfPerson) {
            System.out.println("\nCandidate " + i + " : " + p.getName());
            i++;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the Journal

}
