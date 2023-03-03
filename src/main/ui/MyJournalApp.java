package ui;

import model.DateEntry;
import model.MyJournal;
import model.Person;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// Represents the journal application
public class MyJournalApp {
    private static final String JSON_STORE = "./data/myJournal.json";
    private List<Person> listOfPerson;
    private Person newPerson;
    private DateEntry newDate;
    private Scanner input;
    private MyJournal myJournal;
    private Boolean inPeronCreation = false;

    private Boolean inListOfPeople = false;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs journal and runs application
    public MyJournalApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        myJournal = new MyJournal("Minou's journal");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runJournal();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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

    // MODIFIES: this
    // EFFECTS: initializes the Journal
    private void init() {
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
            inListOfPeople = false;
        } else if (command.equals("b")) {
            inListOfPeople = false;
            inPeronCreation = false;
        } else if (command.equals("s")) {
            saveMyJournal();
        } else if (command.equals("l")) {
            loadMyJournal();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads journal from file
    private void loadMyJournal() {
        try {
            myJournal = jsonReader.read();
            listOfPerson = jsonReader.read().getListOfPerson();
            System.out.println("Loaded " + myJournal.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the journal to file
    private void saveMyJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(myJournal);
            jsonWriter.close();
            System.out.println("Saved " + myJournal.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void doRemovePerson() {
        System.out.println("\nWhich candidate would you like to remove? (number)");
        int personNum = input.nextInt() - 1;
        listOfPerson.remove(personNum);
        //myJournal.remove(personNum);

        System.out.println("Candidate " + (personNum + 1) + " was removed successfully.");
    }

    private void doModifyPerson() {
        System.out.println("\nWhich candidate would you like to modify? (number)");
        int personNum = input.nextInt() - 1;
        System.out.println("\nHere is " + listOfPerson.get(personNum).getName() + "'s profile:");
        Person thisPerson = listOfPerson.get(personNum);
        thisPerson.printProfile();
        newPerson = thisPerson;
        inPeronCreation = true;
    }

    private void doPrintProfile() {
        System.out.println("\nWhich candidate's  profile would you like to view? (number)");
        int personNum = input.nextInt() - 1;
        Person thisPerson = listOfPerson.get(personNum);
        thisPerson.printProfile();
        newPerson = thisPerson;
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

        newPerson = new Person(name, age, location, job, 0);
        listOfPerson.add(newPerson);
        myJournal.addPerson(newPerson);
        System.out.println("\n" + name + " is now added to your list!");
    }

    // EFFECTS: displays menu of options to user
    private void mainDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tnp -> create a new person");
        System.out.println("\tlop -> see the list of people you are dating");
        System.out.println("\ts -> save journal to file");
        System.out.println("\tl -> load journal from file");
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
    // EFFECTS: prints out the list of persons user is
    private void doPrintListOfPeople() {
        int i = 1;
        if (listOfPerson.isEmpty()) {
            System.out.println("\nYour list is empty. Would you like to add a new person or load your journal?");
            inListOfPeople = false;
        } else {
            for (Person p : listOfPerson) {
                System.out.println("\nCandidate " + i + " : " + p.getName());
                i++;
            }
            inListOfPeople = true;
        }
    }


}
