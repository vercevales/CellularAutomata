package pl.edu.agh.student.kardzi.impl;

import pl.edu.agh.student.kardzi.Cell;
import pl.edu.agh.student.kardzi.Rule;
import pl.edu.agh.student.kardzi.enums.State;

/**
 * Created by kdziegie on 2016-04-20.
 */
public final class Rule90 implements Rule {

    @Override
    public State nextCellState(Cell previousStepCell) {
        
        //this if has to be replaced with something else based on a type of the neighbourhood
        if(previousStepCell.getNeighbours().size() >=2) {
            State left = previousStepCell.getNeighbours().get(0).getState();
            State right = previousStepCell.getNeighbours().get(1).getState();
            State self = previousStepCell.getState();
            State newState = null;
            if (left == State.ALIVE && self == State.ALIVE && right == State.ALIVE){
                newState = State.DEAD;
                //System.out.println("1110");
            }

            if (left == State.ALIVE && self == State.ALIVE && right == State.DEAD){
                newState = State.ALIVE;
                //System.out.println("1101");
            }
            if (left == State.ALIVE && self == State.DEAD && right == State.ALIVE){
                newState = State.DEAD;
                //System.out.println("1010");
            }
            if (left == State.ALIVE && self == State.DEAD && right == State.DEAD){
                newState = State.ALIVE;
                //System.out.println("1001");
            }
            if (left == State.DEAD && self == State.ALIVE && right == State.ALIVE){
                newState = State.ALIVE;
                //System.out.println("0111");
            }
            if (left == State.DEAD && self == State.ALIVE && right == State.DEAD){
                newState = State.DEAD;
                //System.out.println("0100");
            }
            if (left == State.DEAD && self == State.DEAD && right == State.ALIVE){
                newState = State.ALIVE;
                //System.out.println("0011");
            }
            if (left == State.DEAD && self == State.DEAD && right == State.DEAD){
                newState = State.DEAD;
                //System.out.println("0000");
            }
            return newState;
        }else return State.DEAD;
    }
}
