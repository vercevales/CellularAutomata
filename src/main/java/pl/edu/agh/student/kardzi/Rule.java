package pl.edu.agh.student.kardzi;

import pl.edu.agh.student.kardzi.enums.State;

/**
 * Created by kdziegie on 2016-04-20.
 */
public interface Rule {
    State nextCellState(Cell cell);
}
