package pl.edu.agh.student.kardzi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdziegie on 2016-04-18.
 */
public class Operations {

    public static void generateLinearNeighbourhood(List<Cell> space){
        space.get(0).getNeighbours().add(space.get(1));
        for(int i=1; i<space.size()-1; i++) {
            space.get(i).getNeighbours().add(space.get(i-1));
            space.get(i).getNeighbours().add(space.get(i+1));
        }
        space.get(space.size()-1).getNeighbours().add(space.get(space.size()-2));
    }

    public static List<Cell> generateNextSpace(List<Cell> space, Rule rule) {
        List<Cell> previousStepSpace = new ArrayList<>(space.size());
        //copying current space
        for (Cell previous : space) {
            previousStepSpace.add(new Cell(previous.getState()));
        }
        Operations.generateLinearNeighbourhood(previousStepSpace);
        //printLinear(previousStepSpace);
        for(int i=0; i<space.size(); i++) {
            space.get(i).setState(rule.nextCellState(previousStepSpace.get(i)));
        }
        return space;
    }

    public static void printLinear(List<Cell> space) {
            for (Cell cell : space) {
                if (cell.getState() == State.ALIVE) System.out.print("@");
                else System.out.print("_");
            }

    }
}
