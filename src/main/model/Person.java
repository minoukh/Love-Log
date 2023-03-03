package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a person having a name, age, list of good (pros) and bad (cons) characteristics, and points
public class Person implements Writable {
    private final String name;
    private final int age;
    private String personLocation;
    private String personJob;
    private int personEarnedPoints;
    public List<String> cons;
    public List<String> pros;
    public List<DateEntry> datesWeHaveBeenOn;

    /*
     * REQUIRES: name has a non-zero length; age is a positive integer.
     * EFFECTS: name of person is set to name, personPoints is initiated as 0,
     *          age of person is set to age, cons / pros are set to empty
     *          ArrayLists, dates went on with this person so far set to
     *          empty LinkedList
     */
    public Person(String name, int age, String location, String job, int personEarnedPoints) {
        this.personEarnedPoints = personEarnedPoints;
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("location", personLocation);
        json.put("job", personJob);
        json.put("points", personEarnedPoints);
        json.put("cons", cons);
        json.put("pros", pros);
        json.put("dates", datesWeHaveBeenOn);
        return json;
    }

    public void printProfile() {
        //double successRate = (this.numOfSuccessfulDatesWithPerson() / this.numOfDatesWithPerson()) * 100;
        System.out.println("Here is a summary of " + this.getName() + ":");
        System.out.println("Age: " + this.getAge());
        System.out.println("Occupation: " + this.getPersonJob());
        System.out.println("Lives in: " + this.getPersonLocation());
        System.out.println("Some of their good qualities: " + this.getListOfPros());
        System.out.println("Some of their bad qualities: " + this.getListOfCons());
        System.out.println("Points so far: " + this.getPersonEarnedPoints());
        System.out.println("Number of dates you have been on: " + this.numOfDatesWithPerson());
        //System.out.println("Success rate for dates: " + successRate + "%");
    }
}

