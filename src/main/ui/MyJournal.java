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
            if (inPeronCreation) {
                personDisplayMenu();
            } else if (inListOfPeople) {
                listOfPersonDisplayMenu();
            } else {
                mainDisplayMenu();
            }
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

    private void init() {
        //listOfDates = new ArrayList<>();
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
            inListOfPeople = false;
        } else if (command.equals("lop")) {
            doPrintListOfPeople();
            inListOfPeople = true;
            inPeronCreation = false;
        } else if (command.equals("+pro")) {
            doAddToPros();
        } else if (command.equals("+con")) {
            doAddToCons();
        } else if (command.equals("+date")) {
            doAddToDates();
        } else if (command.equals("vu")) {
            doPrintProfile();
        } else if (command.equals("mod")) {
            doModifyPerson();
        } else if (command.equals("rem")) {
            doRemovePerson();
        } else if (command.equals("b")) {
            inListOfPeople = false;
            inPeronCreation = false;
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doRemovePerson() {
        System.out.println("\nWhich candidate would you like to remove? (number)");
        int personNum = input.nextInt();
        listOfPerson.remove(personNum--);
    }

    private void doModifyPerson() {
        System.out.println("\nWhich candidate would you like to modify? (number)");
        int personNum = input.nextInt();
        System.out.println("\nHere is " + listOfPerson.get(personNum - 1).getName() + "'s profile:");
        doPrintProfile();
        inPeronCreation = true;
    }

    private void doPrintProfile() {
        System.out.println("\nWhich candidate's  profile would you like to view? (number)");
        int personNum = input.nextInt();
        Person thisPerson = listOfPerson.get(personNum - 1);
        double successRate = (thisPerson.numOfSuccessfulDatesWithPerson() / thisPerson.numOfDatesWithPerson()) * 100;
        System.out.println("Here is a summary of " + thisPerson.getName() + ":");
        System.out.println("Age: " + thisPerson.getAge());
        System.out.println("Occupation: " + thisPerson.getPersonJob());
        System.out.println("Lives in: " + thisPerson.getPersonLocation());
        System.out.println("Points so far: " + thisPerson.getPersonEarnedPoints());
        System.out.println("Number of dates you have been on: " + thisPerson.numOfDatesWithPerson());
        System.out.println("Success rate for dates: " + successRate + "%");
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
        System.out.println("\t+pro -> Add to the list of this person's positive characteristics.");
        System.out.println("\t+con -> Add to the list of this person's negative characteristics.");
        System.out.println("\t+date -> Add a date or interaction you have had with this person!🍷🥩");
        System.out.println("\tb -> Go to the main menu");
        System.out.println("\tq -> quit journaling");
    }

    private void listOfPersonDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tvu -> View a candidate description and dates you have had with them.");
        System.out.println("\tmod -> Modify a candidate. 🔧");
        System.out.println("\trem -> Remove a person you are no longer seeing...💔");
        System.out.println("\tnp -> Add a new person 😈");
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
