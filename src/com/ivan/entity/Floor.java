package com.ivan.entity;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final List<Person> peopleOnTheFloor;
    private final List<Person> peopleWhoWantsUp = new ArrayList<>();
    private final List<Person> peopleWhoWantsDown = new ArrayList<>();

    public Floor(int floorNumber, List<Person> peopleOnTheFloor) {
        this.peopleOnTheFloor = peopleOnTheFloor;
        for (Person person : peopleOnTheFloor) {
            if (person.getFloor() > floorNumber) {
                peopleWhoWantsUp.add(person);
            } else {
                peopleWhoWantsDown.add(person);
            }
        }
    }

    public void removePersonOnTheFloor(Person person) {
        peopleOnTheFloor.remove(person);
        peopleWhoWantsDown.remove(person);
        peopleWhoWantsUp.remove(person);
    }

    public boolean anyPeopleOnTheFloor() {
        return !peopleOnTheFloor.isEmpty();
    }

    public boolean anyPersonWantToTransport(boolean goingUp) {
        if (goingUp) {
            return !peopleWhoWantsUp.isEmpty();
        }
        return !peopleWhoWantsDown.isEmpty();
    }

    public Person getNextPersonToTransport(boolean goingUp) {
        if (goingUp) {
            if (!peopleWhoWantsUp.isEmpty()) {
                return peopleWhoWantsUp.get(0);
            }
        } else {
            return peopleWhoWantsDown.get(0);
        }
        throw new IllegalStateException("Request next people while floor is empty");
    }

    public List<Person> getPeopleWhoWantUp() {
        return peopleWhoWantsUp;
    }

    public List<Person> getPeopleWhoWantsDown() {
        return peopleWhoWantsDown;
    }

    @Override
    public String toString() {
        StringBuilder floor = new StringBuilder();
        for (Person person : peopleOnTheFloor) {
            floor.append(person.toString()).append(" ");
        }
        return floor.toString();
    }
}
