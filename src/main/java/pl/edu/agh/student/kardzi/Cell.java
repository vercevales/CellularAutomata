package pl.edu.agh.student.kardzi;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import pl.edu.agh.student.kardzi.enums.State;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdziegie on 2016-04-18.
 */
public class Cell implements Observable{
    private State state;
    InvalidationListener listener;
    /*
    internal variables
    */
    private List<Cell> neighbours;

    Cell(){
        this.state=State.DEAD;
        this.neighbours = new ArrayList<>();
    }

    Cell(State state) {
        this.state = state;
        this.neighbours = new ArrayList<>();
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

    @Override
    public void addListener(InvalidationListener listener) {
        this.listener = listener;
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        this.listener = null;
    }
}
