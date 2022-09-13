package com.company.controller;

import com.company.entity.Floor;
import com.company.entity.Lift;
import com.company.entity.Person;

import java.util.ArrayList;
import java.util.Random;

public class MainController {
    private Random random = new Random();
    private int maxFloor = random.nextInt(16) + 5;
    private ArrayList<Floor> floors = new ArrayList<>(maxFloor);
    private Lift lift = new Lift(0);
    private ArrayList<Integer> transportsPerformed = new ArrayList<>(maxFloor);


    public String start() {
        putPersonsOnTheFloors();
        doAllElementsTransportsPerformedZero();
        return liftIsGoing();
    }

    private String liftIsGoing() {
        StringBuilder paintStep = new StringBuilder(paintStep(1));
        int counter = 1;
        while (!liftIsStopping()) {
            counter++;
            personComesOut();
            liftDirection();
            addedPersonInTheLift();
            liftNextStep();
            paintStep.append("\n").append(paintStep(counter));
        }
        return paintStep.toString();
    }

    private void personComesOut() {
        if (lift.getPersonsInTheLift().size() > 0) {
            for (int i = 0; i < lift.getPersonsInTheLift().size(); i++) {
                if (lift.getPersonsInTheLift().get(i).getFloor() - 1 == lift.getFloor()) {
                    lift.removePersonInTheLift(lift.getPersonsInTheLift().get(i));
                    transportsPerformedOnTheFloor(lift.getFloor());
                    i = -1;
                }
            }
        }
    }

    private void addedPersonInTheLift() {
        if (lift.isGoingUp()) {
            while (lift.canEnterTheLift() && floors.get(lift.getFloor()).getPersonsWhoWantsUp().size() > 0) {
                Person person = floors.get(lift.getFloor()).getPersonsWhoWantsUp().get(0);
                lift.addPersonInTheLift(person);
                floors.get(lift.getFloor()).removePersonOnTheFloor(person);
            }
        } else {
            while (lift.canEnterTheLift() && floors.get(lift.getFloor()).getPersonsWhoWantsDown().size() > 0) {
                Person person = floors.get(lift.getFloor()).getPersonsWhoWantsDown().get(0);
                lift.addPersonInTheLift(person);
                floors.get(lift.getFloor()).removePersonOnTheFloor(person);
            }
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
        int temp;
        if (lift.isGoingUp()) {
            temp = maxFloor - 1;
            for (int i = lift.getFloor() + 1; i < maxFloor; i++) {
                if (floors.get(i).getPersonsWhoWantsUp().size() > 0) {
                    temp = i;
                    break;
                }
            }
            if (lift.smallestFloorForPerson(maxFloor) < temp) {
                temp = lift.smallestFloorForPerson(maxFloor) - 1;
            }
        } else {
            temp = 0;
            for (int i = lift.getFloor() - 1; i >= 0; i--) {
                if (floors.get(i).getPersonsWhoWantsDown().size() > 0) {
                    temp = i;
                    break;
                }
            }
            if (lift.biggestFloorForPerson() > temp) {
                temp = lift.biggestFloorForPerson() - 1;
            }
        }
        lift.setFloor(temp);
    }


    private boolean liftIsStopping() {
        for (Floor floor : floors) {
            if (floor.getPersonsOnTheFloor().size() > 0 || lift.getPersonsInTheLift().size() > 0) {
                return false;
            }
        }
        return true;
    }


    private void putPersonsOnTheFloors() {
        for (int i = 0; i < maxFloor; i++) {
            int personsOnThisFloor = random.nextInt(11);
            ArrayList<Person> personsOnTheFloor = new ArrayList<>(personsOnThisFloor);
            for (int j = 0; j < personsOnThisFloor; j++) {
                personsOnTheFloor.add(j, new Person(randomFloorForPerson(i)));
            }
            floors.add(new Floor(i + 1, personsOnTheFloor));
        }
    }

    private String paintStep(int step) {
        StringBuilder picture = new StringBuilder("*** Step " + step + " ***\n\n");
        for (int i = maxFloor - 1; i >= 0; i--) {
            picture.append("[").append(i + 1).append("]  ").append(transportsPerformed.get(i)).append(" |").append(paintLift(i)).append("| ").append(paintFloor(i)).append("\n");
        }
        picture.append("\n");
        return picture.toString();
    }

    private String paintLift(int floor) {
        if (lift.getFloor() == floor && lift.isGoingUp()) {
            return "^ " + lift.toString() + "^";
        } else if (lift.getFloor() == floor && !lift.isGoingUp()) {
            return "˅ " + lift.toString() + "˅";
        }
        return "             ";
    }

    private String paintFloor(int floor) {
        return floors.get(floor).toString();
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

    private void doAllElementsTransportsPerformedZero() {
        for (int i = 0; i < maxFloor; i++) {
            transportsPerformed.add(i, 0);
        }
    }
}
