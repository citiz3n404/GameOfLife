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
    // METHODS
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
        if (Param.IS_GRIFFEAT) {
            cell = new CelluleClassique((LifeState) st);
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
    
    public static void attachListeners(Shape r, Cellule c) {
        r.setOnMousePressed(e -> {
            r.setFill(Param.COLOR_BORN);
        });

        r.setOnMouseClicked(e -> {
            //Si la cellule est vivante on la tue autrement elle nait
            if (c.isAlive()) {
                r.setFill(Param.COLOR_DEAD);
                c.kill();
            } else {
                r.setFill(Param.COLOR_ALIVE);
                c.born();
            }
        });
    }
    
    public static int boardToPaneCoords(int i, int j) {
        return (i * Param.NB_COLUMNS) + j;
    }
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    public static Color getColorCell(Cellule cell) {
        Color c;
        if (Param.IS_IMMIGRATION) {
            if (cell.getState() == ImmigrationState.ALIVE) {
                c = Param.COLOR_ALIVE;
            } else if (cell.getState() == ImmigrationState.BORN) {
                c = Param.COLOR_BORN;
            } else {
                c = Param.COLOR_DEAD;
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
