package com.company.entity;

import java.util.ArrayList;

public class Lift {
    private ArrayList<Person> personsInTheLift = new ArrayList<>(5);
    private int floor;
    private boolean isGoingUp = true;

    public void addPersonInTheLift(Person person) {
        personsInTheLift.add(person);
    }

    public void removePersonInTheLift(Person person) {
        personsInTheLift.remove(person);
    }

    public int smallestFloorForPerson(int max) {
        int min = max;
        for (Person person : personsInTheLift) {
            if (person.getFloor() < min && person.getFloor() > floor) {
                min = person.getFloor();
            }
        }
        return min;
    }

    public int biggestFloorForPerson() {
        int max = 0;
        for (Person person : personsInTheLift) {
            if (person.getFloor() > max && person.getFloor() < floor) {
                max = person.getFloor();
            }
        }
        return max;
    }


    public Lift(int floor) {
        this.floor = floor;
    }

    public boolean canEnterTheLift() {
        return personsInTheLift.size() < 5;
    }


    public void setPersonsInTheLift(ArrayList<Person> personsInTheLift) {
        this.personsInTheLift = personsInTheLift;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public boolean isGoingUp() {
        return isGoingUp;
    }

    public void setGoingUp(boolean goingUp) {
        isGoingUp = goingUp;
    }

    @Override
    public String toString() {
        int personInLift = personsInTheLift.size();
        StringBuilder lift = new StringBuilder();
        for (Person person : personsInTheLift) {
            lift.append(person.toString()).append(" ");
        }
        lift.append("  ".repeat(Math.max(0, 5 - personInLift)));
        return lift.toString();
    }

    public ArrayList<Person> getPersonsInTheLift() {
        return personsInTheLift;
    }
}
