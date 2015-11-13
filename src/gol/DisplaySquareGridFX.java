/* 
 * Creation : 1 nov. 2015
 */
package gol;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/**
 * @date    1 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class DisplaySquareGridFX implements GridPaneDriver{
    TilePane tilePane;
    Cellule[][] grid;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public DisplaySquareGridFX(Board board, Controller controller){
        tilePane = new TilePane();
        tilePane.setPrefRows(Param.NB_ROWS);
        tilePane.setPrefColumns(Param.NB_COLUMNS);
        tilePane.setHgap(Param.SIZE_GAP);
        tilePane.setVgap(Param.SIZE_GAP);
        grid = board.getGrid();
        
        for(int i=0; i<Param.NB_ROWS; i++){
            for(int j=0; j<Param.NB_COLUMNS; j++){
                Color c = Utils.getColorCell(grid[i][j]);
                Rectangle rectangle = new Rectangle(Param.SIZE_SQUARE_TILE,Param.SIZE_SQUARE_TILE, c);
                tilePane.getChildren().add(rectangle);
                
                //On attache ici à chaque rectangle un listener pour le clique
                Utils.attachListeners(rectangle, grid[i][j], controller);
            }
        }
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * display the board with squares
     * @param board 
     */
    @Override
    public void displayBoard(Board board) {
        //grid = board.getGrid();
        for(int i=0; i<Param.NB_ROWS; i++){
            for(int j=0; j<Param.NB_COLUMNS; j++){
                Rectangle r = (Rectangle) tilePane.getChildren().get(Utils.boardToPaneCoords(i, j));
                r.setFill(Utils.getColorCell(grid[i][j]));
            }
        }
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     * 
     * @return Panel with the square grid inside
     */
    public Pane getPane() {
        return tilePane;
    }
    

}
