package pl.edu.agh.student.kardzi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdziegie on 2016-04-21.
 */
public class Space2D {

    private List<List<Cell>> space;
    private int rows = 0;
    private int columns = 0;

    public Space2D(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        space = new ArrayList<>(rows);
        List<Cell> internal;
        for(int i=0; i<rows;i++) {
            internal = new ArrayList<>();
            for(int j=0; j<columns; j++) {
                internal.add(new Cell());
            }
            space.add(internal);
        }
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Cell get(int row, int column) throws OutOfBoundariesException{
        List<Cell> internal = space.get(row);
        return internal.get(column);
    }
}
