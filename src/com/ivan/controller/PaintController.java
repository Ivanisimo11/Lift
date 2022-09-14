package com.ivan.controller;

import com.ivan.entity.Floor;
import com.ivan.entity.Lift;

import java.util.List;

public class PaintController {
    private final StringBuilder picture = new StringBuilder();

    public void paintStep(int step, Lift lift, List<Floor> floors, int maxFloor, List<Integer> transportsPerformed) {
        StringBuilder stringBuilder = new StringBuilder("*** Step " + step + " ***\n\n");
        for (int i = maxFloor - 1; i >= 0; i--) {
            stringBuilder.append("[").append(i + 1).append("]  ")
                    .append(transportsPerformed.get(i))
                    .append(" |").append(paintLift(i, lift)).append("| ")
                    .append(paintFloor(i, floors))
                    .append("\n");
        }
        stringBuilder.append("\n");
        picture.append(stringBuilder);
    }

    private String paintLift(int floor, Lift lift) {
        if (lift.getFloor() == floor && lift.isGoingUp()) {
            return "^ " + lift.toString() + "^";
        } else if (lift.getFloor() == floor && !lift.isGoingUp()) {
            return "˅ " + lift.toString() + "˅";
        }
        return "             ";
    }

    private String paintFloor(int floor, List<Floor> floors) {
        return floors.get(floor).toString();
    }

    public StringBuilder getPicture() {
        return picture;
    }
}
