package pl.edu.agh.student.kardzi.impl;

import pl.edu.agh.student.kardzi.Cell;
import pl.edu.agh.student.kardzi.Rule;
import pl.edu.agh.student.kardzi.enums.State;

/**
 * Created by kdziegie on 2016-04-21.
 */
public final class GameOfLifeRule implements Rule {
    @Override
    public State nextCellState(Cell cell) {
        State newState = State.DEAD;
        int aliveNeighbours = (int)cell.getNeighbours().stream()
                .filter(c -> c.getState() == State.ALIVE)
                .count();
        if(cell.getState()==State.DEAD && aliveNeighbours == 3) newState = State.ALIVE;
        else if(cell.getState()==State.ALIVE && (aliveNeighbours == 3 || aliveNeighbours == 2) ) newState = State.ALIVE;
                else newState=State.DEAD;

        return newState;
    }
}
