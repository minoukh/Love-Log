package model;

import java.util.*;

// Represents a person having a name, age, list of good (pros) and bad (cons) characteristics, and points
public class Person {
    private final String name;
    private final int age;
    private String personLocation;
    private String personJob;
    private int personEarnedPoints;
    private List<String> cons;
    private List<String> pros;
    private List<DateEntry> datesWeHaveBeenOn;

    /*
     * REQUIRES: name has a non-zero length; age is a positive integer.
     * EFFECTS: name of person is set to name, personPoints is initiated as 0,
     *          age of person is set to age, cons / pros are set to empty
     *          ArrayLists, dates went on with this person so far set to
     *          empty LinkedList
     */
    public Person(String name, int age, String location, String job) {
        this.personEarnedPoints = 0;
        this.name = name;
        this.age = age;
        this.personLocation = location;
        this.personJob = job;
        this.cons = new ArrayList<>();
        this.pros = new ArrayList<>();
        this.datesWeHaveBeenOn = new LinkedList<>();
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds 1 point to the total points the person has earned
     */
    public void addOnePoint() {
        personEarnedPoints++;
    }

    /*
     * MODIFIES: this
     * EFFECTS: deducts 1 point from the total points the person has earned
     */
    public void deductOnePoint() {
        personEarnedPoints--;
    }

    /*
     * REQUIRES: newCon is a string of length >= 1
     * MODIFIES: this
     * EFFECTS: consumes a new negative trait (con) and adds it to the cons list
     */
    public void addToCons(String newCon) {
        cons.add(newCon);
    }

    /*
     * REQUIRES: newPro is a string of length >= 1
     * MODIFIES: this
     * EFFECTS: consumes a new positive trait (pro) and adds it to the pros list
     */
    public void addToPros(String newPro) {
        pros.add(newPro);
    }

    /*
     * MODIFIES: this
     * EFFECTS: consumes a new date of type DateEntry and adds it to the list
     *          of dates user has been on with them
     */
    public void addDateToListOfDates(DateEntry date) {
        datesWeHaveBeenOn.add(date);
    }


    /*
     * MODIFIES: this
     * EFFECTS: removes a DateEntry with the given name and number from this.
     *          If removal is successful, return true - else return false
     */
    public boolean removeTheDate(int num) {
        for (DateEntry dt : datesWeHaveBeenOn) {
            if (dt.getDateNumber() == num) {
                datesWeHaveBeenOn.remove(dt);
                return true;
            }
        }
        return false;
    }


    /*
     * EFFECTS: returns the total number of times user has interacted with / gone out
     *          with this person
     */
    public int numOfDatesWithPerson() {
        return datesWeHaveBeenOn.size();
    }


    /*
     * EFFECTS: counts and returns the number of successful dates user has
     *          been on with this person.
     */
    public int numOfSuccessfulDatesWithPerson() {
        int count = 0;
        for (DateEntry dt : datesWeHaveBeenOn) {
            if (dt.isDateSuccessful()) {
                count++;
            }
        }
        return count;
    }

    // getters:
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPersonLocation() {
        return personLocation;
    }

    public String getPersonJob() {
        return personJob;
    }

    public int getPersonEarnedPoints() {
        return personEarnedPoints;
    }

    public List<String> getListOfCons() {
        return cons;
    }

    public List<String> getListOfPros() {
        return pros;
    }

    public List<DateEntry> getDatesWeHaveBeenOn() {
        return datesWeHaveBeenOn;
    }

    // setters:
    public void setPersonPoints(int newPoint) {
        personEarnedPoints = newPoint;
    }
}
