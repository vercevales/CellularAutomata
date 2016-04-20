package pl.edu.agh.student.kardzi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Cell> space = new ArrayList<>(99);
        for(int i=0; i < 99; i++) {
            space.add(new Cell());
        }
        Operations.generateLinearNeighbourhood(space);

        space.get(48).setState(State.ALIVE);

        for(int i=0; i<40; i++) {
            Operations.printLinear(space);
            Operations.generateNextSpace(space, new Rule90());
            try {
                System.in.read();
            } catch (IOException e) {
                //donotking
            }
        }
    }
}
