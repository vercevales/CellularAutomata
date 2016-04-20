package pl.edu.agh.student.kardzi;

import java.util.List;

/**
 * Created by kdziegie on 2016-04-20.
 */
public interface Rule {
    State nextCellState(Cell cell, List<Cell> space);
}
