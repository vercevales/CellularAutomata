package pl.edu.agh.student.kardzi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdziegie on 2016-04-18.
 */
public class Operations {

    public static void generateLinearNeighbourhood(List<Cell> space1D){
        space1D.get(0).getNeighbours().add(space1D.get(1));
        for(int i=1; i<space1D.size()-1; i++) {
            space1D.get(i).getNeighbours().add(space1D.get(i-1));
            space1D.get(i).getNeighbours().add(space1D.get(i+1));
        }
        space1D.get(space1D.size()-1).getNeighbours().add(space1D.get(space1D.size()-2));
    }

    public static List<Cell> generateNextSpace(List<Cell> space1D, Rule rule) {
        List<Cell> previousStepSpace = new ArrayList<>(space1D.size());
        //copying current space
        for (Cell previous : space1D) {
            previousStepSpace.add(new Cell(previous.getState()));
        }
        Operations.generateLinearNeighbourhood(previousStepSpace);
        //printLinear(previousStepSpace);
        for(int i=0; i<space1D.size(); i++) {
            space1D.get(i).setState(rule.nextCellState(previousStepSpace.get(i)));
        }
        return space1D;
    }

    public static Space2D generatePeriodicMooresNeighbourhood(Space2D space) {

        for(int i=0; i<space.getRows(); i++) {
            for(int j=0; j<space.getColumns(); j++) {
                //top boudary
                if (i == 0) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(space.getRows()-1,j));
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //bottom boundary
                if (i == space.getRows() - 1) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(0, j));
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //left boundary
                if (j == 0) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(i, space.getColumns() - 1));
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //right boundary
                if (j == space.getColumns() - 1) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(i, 0));
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //the rest
                if (i != 0 && i != space.getRows() - 1 && j != 0 && j != space.getColumns() - 1) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(i-1, j-1));
                        space.get(i, j).getNeighbours().add(space.get(i-1, j));
                        space.get(i, j).getNeighbours().add(space.get(i-1, j+1));
                        space.get(i, j).getNeighbours().add(space.get(i, j-1));
                        space.get(i, j).getNeighbours().add(space.get(i, j+1));
                        space.get(i, j).getNeighbours().add(space.get(i+1, j-1));
                        space.get(i, j).getNeighbours().add(space.get(i+1, j));
                        space.get(i, j).getNeighbours().add(space.get(i+1, j+1));
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
        return  space;
    }

    public static void printLinear(List<Cell> space1D) {
            for (Cell cell : space1D) {
                if (cell.getState() == State.ALIVE) System.out.print("@");
                else System.out.print("_");
            }

    }
}
