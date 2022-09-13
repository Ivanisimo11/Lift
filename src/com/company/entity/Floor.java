package com.company.entity;

import java.util.ArrayList;

public class Floor {
    int floorNumber;
    private ArrayList<Person> personsOnTheFloor;
    private ArrayList<Person> personsWhoWantsUp = new ArrayList<>();
    private ArrayList<Person> personsWhoWantsDown = new ArrayList<>();


    public void removePersonOnTheFloor(Person person) {
        personsOnTheFloor.remove(person);
        personsWhoWantsDown.remove(person);
        personsWhoWantsUp.remove(person);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public ArrayList<Person> getPersonsOnTheFloor() {
        return personsOnTheFloor;
    }

    public void setPersonsOnTheFloor(ArrayList<Person> personsOnTheFloor) {
        this.personsOnTheFloor = personsOnTheFloor;
    }

    public ArrayList<Person> getPersonsWhoWantsUp() {
        return personsWhoWantsUp;
    }

    public void setPersonsWhoWantsUp(ArrayList<Person> personsWhoWantsUp) {
        this.personsWhoWantsUp = personsWhoWantsUp;
    }

    public ArrayList<Person> getPersonsWhoWantsDown() {
        return personsWhoWantsDown;
    }

    public void setPersonsWhoWantsDown(ArrayList<Person> personsWhoWantsDown) {
        this.personsWhoWantsDown = personsWhoWantsDown;
    }

    public Floor(int floorNumber, ArrayList<Person> personsOnTheFloor) {
        this.floorNumber = floorNumber;
        this.personsOnTheFloor = personsOnTheFloor;
        for (Person person : personsOnTheFloor) {
            if (person.getFloor() > floorNumber) {
                personsWhoWantsUp.add(person);
            } else {
                personsWhoWantsDown.add(person);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder floor = new StringBuilder();
        for (Person person : personsOnTheFloor) {
            floor.append(person.toString()).append(" ");
        }
        return floor.toString();
    }
}
