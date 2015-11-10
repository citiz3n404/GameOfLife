/* 
 * Creation : 6 nov. 2015
 */
package gol;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @date 6 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class Utils {

    //**************************************************************************
    // FUNCTIONS
    //**************************************************************************
    public static Board createNewBoard() {
        Board board;
        if (Param.GRID == 1) {
            board = new BoardSquare();
        } else if (Param.GRID == 2) {
            board = new BoardHexa();
        } else {
            board = new BoardSquare();
        }
        return board;
    }

    public static Cellule createNewCell(State st) {
        Cellule cell;
        if (Param.IS_GRIFFEATH) {
            cell = new CelluleGriffeath((GriffeathState) st);
        } else if (Param.IS_IMMIGRATION) {
            cell = new CelluleImmigration((ImmigrationState) st);
        } else if (Param.IS_ISOTROPE) {
            cell = new CelluleIsotrope((LifeState) st);
        } else if (Param.IS_HIGHLIFE) {
            cell = new CelluleHighLife((LifeState) st);
        } else if (Param.IS_DAY_AND_NIGHT) {
            cell = new CelluleDayAndNight((LifeState) st);
        } else if (Param.IS_FREDKIN) {
            cell = new CelluleFredkin((LifeState) st);
        } else {
            cell = new CelluleClassique((LifeState) st);
        }
        return cell;
    }
    
    //**************************************************************************
    // GRAPHICAL INTERFACE
    //**************************************************************************
    public static void attachListeners(Shape r, Cellule c, Controller controller) {
        r.setOnMousePressed(e -> {
            r.setFill(Param.COLOR_BORN);
        });

        r.setOnMouseClicked(e -> {
            //Si la cellule est vivante on la tue autrement elle nait
            if (c.isAlive()) {
                r.setFill(Param.COLOR_DEAD);
                c.kill();
                controller.incrementPopulation();
            } else {
                r.setFill(Param.COLOR_ALIVE);
                c.born();
                controller.incrementPopulation();
            }
        });
    }

    public static int boardToPaneCoords(int i, int j) {
        return (i * Param.NB_COLUMNS) + j;
    }
    

    public static Color getColorCell(Cellule cell) {
        Color c;
        if (Param.IS_IMMIGRATION) {
            if (cell.getState() == ImmigrationState.ALIVE) {
                c = Param.COLOR_ALIVE;
            } else if (cell.getState() == ImmigrationState.ZOMBIE) {
                c = Param.COLOR_ZOMBIE;
            } else {
                c = Param.COLOR_DEAD;
            }
        } else if (Param.IS_GRIFFEATH) {
            if (((GriffeathState) cell.getState()).val == 0) {
                    c = Color.BLACK;
                } else {
                    int percentVal = (((GriffeathState) cell.getState()).val)*360/Param.ETAT_MAX_GRIFFEAT;
                    c = Color.hsb(percentVal, 0.7, 0.94);
                    
                }
        } else {
            if (cell.getState() == LifeState.ALIVE) {
                c = Param.COLOR_ALIVE;
            } else {
                c = Param.COLOR_DEAD;
            }
        }
        return c;
    }
}
