package pl.edu.agh.student.kardzi;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by kdziegie on 2016-04-18.
 */
public class Cell {
    private State state;
    /*
    internal variables
    */
    private List<Cell> neighbours;

    Cell(){
        state=State.DEAD;
        neighbours = new ArrayList<>();
    }

    public State getState(){
        return state;
    }

    public void setState(State state){
        this.state = state;
    }

    public void setState(int state){
        this.state = (state == 1) ? State.ALIVE : State.DEAD;
    }

    public List<Cell> getNeighbours(){
        return neighbours;
    }

    @Override
    public int hashCode(){
        int num1 = (this.state==State.DEAD) ? 7 : 13;
        int num2 = this.neighbours.size();
        return num1 * num2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Cell.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Cell givenCell = (Cell) obj;
        return (this.state==givenCell.getState() );
    }
}
