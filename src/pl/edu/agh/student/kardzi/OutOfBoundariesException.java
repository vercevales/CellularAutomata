package pl.edu.agh.student.kardzi;

/**
 * Created by kdziegie on 2016-04-21.
 */
public class OutOfBoundariesException extends Exception {
    OutOfBoundariesException(){
        super("You try to access element behind boundaries of the space");
    }
}
