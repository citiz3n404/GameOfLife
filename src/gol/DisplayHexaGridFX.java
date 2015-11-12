/* 
 * Creation : 3 nov. 2015
 */
package gol;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * @date 3 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class DisplayHexaGridFX implements GridPaneDriver {

    Pane pane;
    Cellule[][] grid;
    double x = 0, y = 0;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public DisplayHexaGridFX(Board board, Controller controller) {
        pane = new Pane();
        grid = board.getGrid();
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                Color c = Utils.getColorCell(grid[i][j]);
                Polygon hexa = createHexa(x, y);
                hexa.setFill(c);
                pane.getChildren().add(hexa);
                Utils.attachListeners(hexa, grid[i][j], controller);
                x += Param.SIZE_HEXA_TILE * Math.cos(Math.PI / 6) * 2 + Param.SIZE_GAP; // OK
            }
            if (y % 2 == 0) {
                x = Param.SIZE_HEXA_TILE * Math.cos(Math.PI / 6) + Param.SIZE_GAP / 2;
                y += Param.SIZE_HEXA_TILE * 2 * 3 / 4 + Param.SIZE_GAP * 2;
            } else {
                x = 0;
                y += Param.SIZE_HEXA_TILE * 2 * 3 / 4 + Param.SIZE_GAP * 2;
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
                Color c = Utils.getColorCell(grid[i][j]);
                Polygon hexa = (Polygon) pane.getChildren().get(Utils.boardToPaneCoords(i, j));
                hexa.setFill(c);
            }
        }
    }

    private int boardToPaneCoords(int i, int j) {
        return (i * Param.NB_COLUMNS) + j;
    }

    private Polygon createHexa(double centerX, double centerY) {
        double[] coord = new double[12];
        coord[0] = Param.SIZE_HEXA_TILE * Math.sqrt(3) / 2 + centerX;
        coord[1] = Param.SIZE_HEXA_TILE / 2 + centerY;

        coord[2] = 0 + centerX;
        coord[3] = Param.SIZE_HEXA_TILE + centerY;

        coord[4] = -Param.SIZE_HEXA_TILE * Math.sqrt(3) / 2 + centerX;
        coord[5] = Param.SIZE_HEXA_TILE / 2 + centerY;

        coord[6] = -Param.SIZE_HEXA_TILE * Math.sqrt(3) / 2 + centerX;
        coord[7] = -Param.SIZE_HEXA_TILE / 2 + centerY;

        coord[8] = 0 + centerX;
        coord[9] = -Param.SIZE_HEXA_TILE + centerY;

        coord[10] = Param.SIZE_HEXA_TILE * Math.sqrt(3) / 2 + centerX;
        coord[11] = -Param.SIZE_HEXA_TILE / 2 + centerY;
        return new Polygon(coord);
    }

    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    @Override
    public Pane getPane() {
        return this.pane;
    }
}
