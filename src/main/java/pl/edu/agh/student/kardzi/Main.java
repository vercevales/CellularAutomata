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
        Space2D space = new Space2D(20, 20);
        Operations.generateNeighbourhood(space, NeighbourhoodType.MOORE, BoundaryCondition.PERIODIC);
        try {
            space.get(1, 1).setState(State.ALIVE);
            space.get(2, 2).setState(State.ALIVE);
            space.get(3, 0).setState(State.ALIVE);
            space.get(3, 1).setState(State.ALIVE);
            space.get(3, 2).setState(State.ALIVE);
        } catch (OutOfBoundariesException ex) {
            System.err.println(ex.getMessage());
        }
        //space.getSpaceAsList().stream().forEach(cells -> cells.stream().forEach(cell -> cell.setState(State.ALIVE)));
        for(int i=0; i< 100; i++){
            Operations.print2D(space);
            System.out.println();
            Operations.generateNextSpace2D(space, new GameOfLifeRule());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
