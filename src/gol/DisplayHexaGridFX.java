/* 
 * Creation : 3 nov. 2015
 */
package gol;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * @date 3 nov. 2015
 * @author Anthony CHAFFOT
 */
public class DisplayHexaGridFX implements GridPaneDriver {

    Pane pane;
    Cellule[][] grid;
    double x = 0, y = 0;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public DisplayHexaGridFX(Board board) {
        pane = new Pane();
        grid = board.getGrid();
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                Color c = grid[i][j].getAlive() ? Param.COLOR_ALIVE : Param.COLOR_DEAD;
                Polygon hexa = createHexa(x, y);
                hexa.setFill(c);
                pane.getChildren().add(hexa);
                attachListeners(hexa, grid[i][j]);
                x += Param.SIZE_TILE * Math.cos(Math.PI / 6) * 2; // OK
            }
            if (y % 2 == 0) {
                x = Param.SIZE_TILE * Math.cos(Math.PI / 6);
                y += Param.SIZE_TILE * 2 * 3 / 4;
            } else {
                x = 0;
                y += Param.SIZE_TILE * 2 * 3 / 4;
            }
        }
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public void displayBoard(Board board) {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                Color c = grid[i][j].getAlive() ? Param.COLOR_ALIVE : Param.COLOR_DEAD;
                Polygon hexa = (Polygon) pane.getChildren().get(boardToPaneCoords(i, j));
                hexa.setFill(c);
            }
        }
    }

    private int boardToPaneCoords(int i, int j) {
        return (i * Param.NB_COLUMNS) + j;
    }

    private Polygon createHexa(double centerX, double centerY) {
        double[] coord = new double[12];
        coord[0] = Param.SIZE_TILE * Math.sqrt(3) / 2 + centerX;
        coord[1] = Param.SIZE_TILE / 2 + centerY;

        coord[2] = 0 + centerX;
        coord[3] = Param.SIZE_TILE + centerY;

        coord[4] = -Param.SIZE_TILE * Math.sqrt(3) / 2 + centerX;
        coord[5] = Param.SIZE_TILE / 2 + centerY;

        coord[6] = -Param.SIZE_TILE * Math.sqrt(3) / 2 + centerX;
        coord[7] = -Param.SIZE_TILE / 2 + centerY;

        coord[8] = 0 + centerX;
        coord[9] = -Param.SIZE_TILE + centerY;

        coord[10] = Param.SIZE_TILE * Math.sqrt(3) / 2 + centerX;
        coord[11] = -Param.SIZE_TILE / 2 + centerY;
        return new Polygon(coord);
    }

    private void attachListeners(Polygon r, Cellule c) {
        r.setOnMousePressed(e -> {
            r.setFill(Param.COLOR_BORN);
        });

        r.setOnMouseClicked(e -> {
            //Si la cellule est vivante on la tue autrement elle nait
            r.setFill(c.getAlive() ? Param.COLOR_DEAD : Param.COLOR_ALIVE);
            if (c.getState() == LifeState.ALIVE) {
                c.setState(LifeState.DEAD);
            } else {
                c.setState(LifeState.ALIVE);
            }
        });
    }
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    @Override
    public Pane getPane() {
        return this.pane;
    }
}
