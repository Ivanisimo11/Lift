package com.ivan.entity;

import java.util.ArrayList;
import java.util.List;

public class Lift {
    private final List<Person> peopleInTheLift = new ArrayList<>(5);
    private int floor;
    private boolean isGoingUp = true;

    public void addPersonInTheLift(Person person) {
        peopleInTheLift.add(person);
    }

    public void removePersonInTheLift(Person person) {
        peopleInTheLift.remove(person);
    }

    public int smallestFloorForPerson(int max) {
        int min = max;
        for (Person person : peopleInTheLift) {
            if (person.getFloor() < min && person.getFloor() > floor) {
                min = person.getFloor();
            }
        }
        return min;
    }

    public int biggestFloorForPerson() {
        int max = 0;
        for (Person person : peopleInTheLift) {
            if (person.getFloor() > max && person.getFloor() < floor) {
                max = person.getFloor();
            }
        }
        return max;
    }

    public Lift(int floor) {
        this.floor = floor;
    }

    public boolean canCarryMorePeople() {
        return peopleInTheLift.size() < 5;
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
        int personInLift = peopleInTheLift.size();
        StringBuilder lift = new StringBuilder();
        for (Person person : peopleInTheLift) {
            lift.append(person.toString()).append(" ");
        }
        lift.append("  ".repeat(Math.max(0, 5 - personInLift)));
        return lift.toString();
    }

    public List<Person> getPeopleInTheLift() {
        return peopleInTheLift;
    }


    public boolean isEmpty() {
        return peopleInTheLift.isEmpty();
    }
}
