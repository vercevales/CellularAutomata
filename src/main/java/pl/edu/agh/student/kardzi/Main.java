package pl.edu.agh.student.kardzi;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import pl.edu.agh.student.kardzi.enums.BoundaryCondition;
import pl.edu.agh.student.kardzi.enums.NeighbourhoodType;
import pl.edu.agh.student.kardzi.enums.State;
import pl.edu.agh.student.kardzi.exceptions.OutOfBoundariesException;
import pl.edu.agh.student.kardzi.impl.GameOfLifeRule;


public class Main extends Application{

    GridPane root = new GridPane();
    final int size = 10;
    SimpleBooleanProperty simpleBooleanProperty = new SimpleBooleanProperty(false);


    public void start(Stage primaryStage) {

        Space2D space = new Space2D(size, size);
        Operations.generateNeighbourhood(space, NeighbourhoodType.MOORE, BoundaryCondition.PERIODIC);
        try {
            space.get(1, 1).setState(State.ALIVE);
            space.get(2, 2).setState(State.ALIVE);
            space.get(3, 0).setState(State.ALIVE);
            space.get(3, 1).setState(State.ALIVE);
            space.get(3, 2).setState(State.ALIVE);
        } catch (OutOfBoundariesException ex) {
            System.err.println(ex.getMessage());
        }

        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle square = new Rectangle();
                try {
                    simpleBooleanProperty.set(space.get(row, col).getState() == State.ALIVE);
                    square.fillProperty().bind(Bindings.when(simpleBooleanProperty).then(Color.BLACK).otherwise(Color.WHITE));
                } catch (OutOfBoundariesException e) {
                    e.printStackTrace();
                }
                root.add(square, col, row);
                square.widthProperty().bind(root.widthProperty().divide(size));
                square.heightProperty().bind(root.heightProperty().divide(size));
            }
        }

        Thread t = new Thread(() -> {
            for(int i = 0; i<100; i++) {
                Operations.generateNextSpace2D(space, new GameOfLifeRule());
                System.out.println(i);
                for(int j =0; j<size; j++) {
                    for (int k = 0; k < size; k++){
                        try {
                            simpleBooleanProperty.set(space.get(j, k).getState() == State.ALIVE);
                            System.out.print(simpleBooleanProperty);
                        } catch (OutOfBoundariesException e) {
                            e.printStackTrace();
                        }
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }

    public static void main(String[] args) {

        launch(args);
        /*//linear
        List<Cell> space = new ArrayList<>(99);
        for(int i=0; i < 99; i++) {
            space.add(new Cell());
        }
        Operations.generateLinearNeighbourhood(space);

        space.get(48).setState(State.ALIVE);

        for(int i=0; i<40; i++) {
            Operations.printLinear(space);
            Operations.generateNextLinearSpace(space, new Rule90());
            try {
                System.in.read();
            } catch (IOException e) {
                //donotking
            }
        }*/


        //2D
        /*Space2D space = new Space2D(20, 20);
        Operations.generateNeighbourhood(space, NeighbourhoodType.MOORE, BoundaryCondition.PERIODIC);
        try {
            space.get(1, 1).setState(State.ALIVE);
            space.get(2, 2).setState(State.ALIVE);
            space.get(3, 0).setState(State.ALIVE);
            space.get(3, 1).setState(State.ALIVE);
            space.get(3, 2).setState(State.ALIVE);
        } catch (OutOfBoundariesException ex) {
            System.err.println(ex.getMessage());
        }
        //space.getSpaceAsList().stream().forEach(cells -> cells.stream().forEach(cell -> cell.setState(State.ALIVE)));
        for(int i=0; i< 100; i++){
            Operations.printNeighbours2D(space);
            System.out.println();
            Operations.generateNextSpace2D(space, new GameOfLifeRule());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/



    }
}
