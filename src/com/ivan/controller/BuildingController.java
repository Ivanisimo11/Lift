package com.ivan.controller;

import com.ivan.entity.Floor;
import com.ivan.entity.Lift;
import com.ivan.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BuildingController {
    private int maxFloor;
    private final Random random;
    private final List<Floor> floors = new ArrayList<>(maxFloor);
    private final Lift lift = new Lift(0);
    private final List<Integer> transportsPerformed = new ArrayList<>(maxFloor);
    private final PaintController paintController = new PaintController();


    public BuildingController() {
        this.random = new Random();
        this.maxFloor = random.nextInt(16) + 5;
    }

    public String start() {
        initTransportsPerformedToZero();
        putPeopleOnTheFloors();
        launchLift();
        return paintController.getPicture().toString();
    }

    private void launchLift() {
        int counter = 0;
        while (!liftIsStopping()) {
            counter++;
            paintController.paintStep(counter, lift, floors, maxFloor, transportsPerformed);
            personComesOut();
            liftDirection();
            addedPersonInTheLift();
            liftNextStep();
        }
    }

    private void personComesOut() {
        if (!lift.getPeopleInTheLift().isEmpty()) {
            for (int i = 0; i < lift.getPeopleInTheLift().size(); i++) {
                if (lift.getPeopleInTheLift().get(i).getFloor() - 1 == lift.getFloor()) {
                    lift.removePersonInTheLift(lift.getPeopleInTheLift().get(i));
                    transportsPerformedOnTheFloor(lift.getFloor());
                    i = -1;
                }
            }
        }
    }

    private void addedPersonInTheLift() {
        Floor currentFloor = floors.get(lift.getFloor());
        while (lift.canCarryMorePeople() && currentFloor.anyPersonWantToTransport(lift.isGoingUp())) {
            Person person = currentFloor.getNextPersonToTransport(lift.isGoingUp());
            lift.addPersonInTheLift(person);
            currentFloor.removePersonOnTheFloor(person);
        }
    }

    private void liftDirection() {
        if (lift.getFloor() + 1 == maxFloor) {
            lift.setGoingUp(false);
        }
        if (lift.getFloor() == 0) {
            lift.setGoingUp(true);
        }
    }

    private void liftNextStep() {
        int nextFloor;
        if (lift.isGoingUp()) {
            nextFloor = maxFloor - 1;
            for (int i = lift.getFloor() + 1; i < maxFloor; i++) {
                if (floors.get(i).getPeopleWhoWantUp().size() > 0) {
                    nextFloor = i;
                    break;
                }
            }
            if (lift.smallestFloorForPerson(maxFloor) - 1 < nextFloor) {
                nextFloor = lift.smallestFloorForPerson(maxFloor) - 1;
            }
        } else {
            nextFloor = 0;
            for (int i = lift.getFloor() - 1; i >= 0; i--) {
                if (floors.get(i).getPeopleWhoWantsDown().size() > 0) {
                    nextFloor = i;
                    break;
                }
            }
            if (lift.biggestFloorForPerson() - 1 > nextFloor) {
                nextFloor = lift.biggestFloorForPerson() - 1;
            }
        }
        lift.setFloor(nextFloor);
    }


    private boolean liftIsStopping() {
        for (Floor floor : floors) {
            if (floor.anyPeopleOnTheFloor() || !lift.isEmpty()) {
                return false;
            }
        }
        return true;
    }


    private void putPeopleOnTheFloors() {
        for (int i = 0; i < maxFloor; i++) {
            int personsOnThisFloor = random.nextInt(11);
            List<Person> personsOnTheFloor = new ArrayList<>(personsOnThisFloor);
            for (int j = 0; j < personsOnThisFloor; j++) {
                personsOnTheFloor.add(new Person(randomFloorForPerson(i)));
            }
            floors.add(new Floor(i + 1, personsOnTheFloor));
        }
    }

    private int randomFloorForPerson(int personFloorNumber) {
        int floor = random.nextInt(maxFloor);
        while (floor == personFloorNumber) {
            floor = random.nextInt(maxFloor);
        }
        return floor + 1;
    }


    private void transportsPerformedOnTheFloor(int floor) {
        transportsPerformed.set(floor, transportsPerformed.get(floor) + 1);
    }

    private void initTransportsPerformedToZero() {
        for (int i = 0; i < maxFloor; i++) {
            transportsPerformed.add(0);
        }
    }
}
