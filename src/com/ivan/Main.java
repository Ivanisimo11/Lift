package com.ivan;


import com.ivan.controller.BuildingController;

public class Main {
    public static void main(String[] args) {
        BuildingController mainController = new BuildingController();
        System.out.println(mainController.start());
    }
}
