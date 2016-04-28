package pl.edu.agh.student.kardzi;

import pl.edu.agh.student.kardzi.enums.BoundaryCondition;
import pl.edu.agh.student.kardzi.enums.NeighbourhoodType;
import pl.edu.agh.student.kardzi.enums.State;
import pl.edu.agh.student.kardzi.exceptions.OutOfBoundariesException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kdziegie on 2016-04-18.
 */
public class Operations {

    public static void generateLinearNeighbourhood(List<Cell> space1D){
        space1D.get(0).getNeighbours().add(space1D.get(1));
        for(int i=1; i<space1D.size()-1; i++) {
            space1D.get(i).getNeighbours().add(space1D.get(i-1));
            space1D.get(i).getNeighbours().add(space1D.get(i+1));
        }
        space1D.get(space1D.size()-1).getNeighbours().add(space1D.get(space1D.size()-2));
    }

    public static List<Cell> generateNextLinearSpace(List<Cell> space1D, Rule rule) {
        List<Cell> previousStepSpace = new ArrayList<>(space1D.size());
        //copying current space
        for (Cell previous : space1D) {
            previousStepSpace.add(new Cell(previous.getState()));
        }
        Operations.generateLinearNeighbourhood(previousStepSpace);
        //printLinear(previousStepSpace);
        for(int i=0; i<space1D.size(); i++) {
            space1D.get(i).setState(rule.nextCellState(previousStepSpace.get(i)));
        }
        return space1D;
    }

    public static Space2D generateNextSpace2D (Space2D space, Rule rule) {
        //copying current space
        int rows = space.getRows();
        int columns = space.getColumns();
        List<List<Cell>> previousStepSpaceAsList = new ArrayList<>(rows);
        List<Cell> internal;
        for(int i=0; i<rows;i++) {
            internal = new ArrayList<>();
            for(int j=0; j<columns; j++) {
                try {
                    internal.add(new Cell(space.get(i,j).getState()));
                } catch (OutOfBoundariesException e) {
                    System.out.println(e.getMessage());
                }
            }
            previousStepSpaceAsList.add(internal);
        }
        Space2D previousStepSpace = new Space2D(space.getRows(), space.getColumns());
        previousStepSpace.setSpaceAsList(previousStepSpaceAsList);
        Operations.generateNeighbourhood(previousStepSpace, NeighbourhoodType.MOORE, BoundaryCondition.PERIODIC);

        //applying the rules
        for(int i=0; i<rows;i++) {
            for(int j=0; j<columns; j++) {
                try {
                    space.get(i,j).setState(rule.nextCellState(previousStepSpace.get(i,j)));
                } catch (OutOfBoundariesException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        return space;
    }

    public static Space2D generateNeighbourhood(Space2D space, NeighbourhoodType neighbourhoodType, BoundaryCondition boundaryCondition) {
    if(neighbourhoodType == NeighbourhoodType.MOORE && boundaryCondition == BoundaryCondition.PERIODIC) {
        for (int i = 0; i < space.getRows(); i++) {
            for (int j = 0; j < space.getColumns(); j++) {
                //top boudary
                if (i == 0) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(space.getRows() - 1, j));
                        space.get(i, j).getNeighbours().add(space.get(i + 1, j));
                        if (j == 0) {
                            space.get(i, j).getNeighbours().add(space.get(i, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j+1));
                        } else if (j == space.getColumns()-1) {
                            space.get(i, j).getNeighbours().add(space.get(i, j-1));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j-1));
                        }else{
                            space.get(i, j).getNeighbours().add(space.get(i, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i, j-1));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j-1));
                        }
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //bottom boundary
                if (i == space.getRows() - 1) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(space.getRows() - 2, j));
                        space.get(i, j).getNeighbours().add(space.get(0, j));
                        if (j == 0) {
                            space.get(i, j).getNeighbours().add(space.get(i, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i-1, j+1));
                        } else if (j == space.getColumns()-1) {
                            space.get(i, j).getNeighbours().add(space.get(i, j-1));
                            space.get(i, j).getNeighbours().add(space.get(i-1, j-1));
                        }else{
                            space.get(i, j).getNeighbours().add(space.get(i, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i-1, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i, j-1));
                            space.get(i, j).getNeighbours().add(space.get(i-1, j-1));
                        }
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //left boundary
                if (j == 0) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(i, space.getColumns() - 1));
                        if (i != 0 && i != space.getRows() - 1) {
                            space.get(i, j).getNeighbours().add(space.get(i-1, j));
                            space.get(i, j).getNeighbours().add(space.get(i-1, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i, j+1));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j+1));
                        }
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //right boundary
                if (j == space.getColumns() - 1) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(i, 0));
                        if (i != 0 && i != space.getRows() - 1) {
                            space.get(i, j).getNeighbours().add(space.get(i-1, j));
                            space.get(i, j).getNeighbours().add(space.get(i-1, j-1));
                            space.get(i, j).getNeighbours().add(space.get(i, j-1));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j));
                            space.get(i, j).getNeighbours().add(space.get(i+1, j-1));
                        }
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
                //the rest
                if (i != 0 && i != space.getRows() - 1 && j != 0 && j != space.getColumns() - 1) {
                    try {
                        space.get(i, j).getNeighbours().add(space.get(i - 1, j - 1));
                        space.get(i, j).getNeighbours().add(space.get(i - 1, j));
                        space.get(i, j).getNeighbours().add(space.get(i - 1, j + 1));
                        space.get(i, j).getNeighbours().add(space.get(i, j - 1));
                        space.get(i, j).getNeighbours().add(space.get(i, j + 1));
                        space.get(i, j).getNeighbours().add(space.get(i + 1, j - 1));
                        space.get(i, j).getNeighbours().add(space.get(i + 1, j));
                        space.get(i, j).getNeighbours().add(space.get(i + 1, j + 1));
                    } catch (OutOfBoundariesException e) {
                        System.out.print(e.getMessage());
                    }
                }
            }
        }
    }
        return  space;
}

    public static void printLinear(List<Cell> space1D) {
            for (Cell cell : space1D) {
                if (cell.getState() == State.ALIVE) System.out.print("@");
                else System.out.print("_");
            }

    }

    public static void print2D(Space2D space) {
        space.getSpaceAsList().stream()
                .forEach((list) -> {
                    list.stream().forEach(cell -> System.out.print((cell.getState()==State.ALIVE) ? "@ " : "_ "));
                    System.out.println("");
                });
    }

    public static void printNeighbours2D(Space2D space2D) {
        space2D.getSpaceAsList().stream()
                .forEach(list -> {
                    list.stream().forEach(cell -> System.out.print(cell.getNeighbours().stream().filter(c -> c.getState() == State.ALIVE).count()+" "));
                    System.out.println();
                });

    }
}
