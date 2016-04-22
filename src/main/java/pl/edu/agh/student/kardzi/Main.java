package pl.edu.agh.student.kardzi;


import pl.edu.agh.student.kardzi.enums.BoundaryCondition;
import pl.edu.agh.student.kardzi.enums.NeighbourhoodType;
import pl.edu.agh.student.kardzi.enums.State;
import pl.edu.agh.student.kardzi.exceptions.OutOfBoundariesException;
import pl.edu.agh.student.kardzi.impl.GameOfLifeRule;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        /*
        //linear
        List<Cell> space = new ArrayList<>(99);
        for(int i=0; i < 99; i++) {
            space.add(new Cell());
        }
        Operations.generateLinearNeighbourhood(space);

        space.get(48).setState(State.ALIVE);

        for(int i=0; i<40; i++) {
            Operations.printLinear(space);
            Operations.generateNextSpace(space, new Rule90());
            try {
                System.in.read();
            } catch (IOException e) {
                //donotking
            }
        }*/
        //2D
        Space2D space = new Space2D(10, 10);
        Operations.generateNeighbourhood(space, NeighbourhoodType.MOORE, BoundaryCondition.PERIODIC);
        try {
            space.get(3, 4).setState(State.ALIVE);
            space.get(4, 4).setState(State.ALIVE);
            space.get(5, 4).setState(State.ALIVE);
        } catch (OutOfBoundariesException e) {
            System.out.print(e.getMessage());
        }
        for(int i=0; i< 100; i++){


            Operations.print2D(space);
            Operations.generateNextSpace2D(space, new GameOfLifeRule());
            try {
                System.in.read();
            } catch (IOException e) {
                //donotking
            }

        }

    }
}
