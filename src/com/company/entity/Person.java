package com.company.entity;

public class Person {
    private int floor;

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
