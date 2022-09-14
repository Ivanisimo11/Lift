package com.ivan.entity;

public class Person {
    private final int floor;

    public int getFloor() {
        return floor;
    }

    @Override
    public String toString() {
        return "" + floor ;
    }

    public Person(int floor) {
        this.floor = floor;
    }
}
