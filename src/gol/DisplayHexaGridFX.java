/* 
 * Creation : 3 nov. 2015
 */
package gol;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;



/**
 * @date    3 nov. 2015
 * @author  Anthony CHAFFOT
 */
public class DisplayHexaGridFX implements GridPaneDriver{
    Pane pane;
    Cellule[][] grid;
    double x=0, y=0;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public DisplayHexaGridFX(Board board){
        pane = new Pane();
        grid = board.getGrid();
        Color c = Param.COLOR_ALIVE;
        Polygon hexa = createHexa();
        hexa.setFill(c);
        pane.getChildren().add(hexa);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public void displayBoard(Board board) {
        
    }
    
    private Polygon createHexa() {
        double angle = Math.PI/3; //2*PI/6
        double centerX = Param.SIZE_TILE /2;
        double centerY = Param.SIZE_TILE /2;
        //Premier point
        double x0 = centerX;
        double y0 = 0;
        
        double[] coord = new double[12];
        for(int i=0; i<12; i+=2){
            //A refaire !
            coord[i] = centerX + Param.SIZE_TILE*angle+ Param.SIZE_GAP;
            coord[i+1] =centerY + Param.SIZE_TILE*angle+ Param.SIZE_GAP;
        }

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
