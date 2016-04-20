package pl.edu.agh.student.kardzi;

import java.util.List;

/**
 * Created by kdziegie on 2016-04-20.
 */
public final class Rule90 implements Rule {

    @Override
    public State nextCellState(Cell cell, List<Cell> space) {
        if(cell.getNeighbours().size() >=2) {
            State left = cell.getNeighbours().get(0).getState();
            State right = cell.getNeighbours().get(1).getState();
            State self = cell.getState();
            State newState = null;
            if (left == State.ALIVE && self == State.ALIVE && right == State.ALIVE) newState = State.DEAD;
            if (left == State.ALIVE && self == State.ALIVE && right == State.DEAD) newState = State.ALIVE;
            if (left == State.ALIVE && self == State.DEAD && right == State.ALIVE) newState = State.DEAD;
            if (left == State.ALIVE && self == State.DEAD && right == State.DEAD) newState = State.ALIVE;
            if (left == State.DEAD && self == State.ALIVE && right == State.ALIVE) newState = State.ALIVE;
            if (left == State.DEAD && self == State.ALIVE && right == State.DEAD) newState = State.DEAD;
            if (left == State.DEAD && self == State.DEAD && right == State.ALIVE) newState = State.ALIVE;
            if (left == State.DEAD && self == State.DEAD && right == State.DEAD) newState = State.DEAD;
            return newState;
        }else return State.DEAD;
    }
}
