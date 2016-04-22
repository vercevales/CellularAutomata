package pl.edu.agh.student.kardzi;

import pl.edu.agh.student.kardzi.exceptions.OutOfBoundariesException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdziegie on 2016-04-21.
 */
public class Space2D {

    private List<List<Cell>> spaceAsList;
    private int rows = 0;
    private int columns = 0;

    public Space2D(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        spaceAsList = new ArrayList<>(rows);
        List<Cell> internal;
        for(int i=0; i<rows;i++) {
            internal = new ArrayList<>();
            for(int j=0; j<columns; j++) {
                internal.add(new Cell());
            }
            spaceAsList.add(internal);
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Cell get(int row, int column) throws OutOfBoundariesException {
        List<Cell> internal = spaceAsList.get(row);
        return internal.get(column);
    }

    public List<List<Cell>> getSpaceAsList() {
        return spaceAsList;
    }

    public void setSpaceAsList(List<List<Cell>> spaceAsList) {
        this.spaceAsList = spaceAsList;
    }
}
