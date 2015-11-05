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
 */
public class DisplaySquareGridFX implements GridPaneDriver{
    TilePane tilePane;
    Cellule[][] grid;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public DisplaySquareGridFX(Board board){
        tilePane = new TilePane();
        tilePane.setPrefRows(Param.NB_ROWS);
        tilePane.setPrefColumns(Param.NB_COLUMNS);
        tilePane.setHgap(Param.SIZE_GAP);
        tilePane.setVgap(Param.SIZE_GAP);
        grid = board.getGrid();
        
        for(int i=0; i<Param.NB_ROWS; i++){
            for(int j=0; j<Param.NB_COLUMNS; j++){
                Color c = grid[i][j].getAlive() ? Param.COLOR_ALIVE : Param.COLOR_DEAD;
                Rectangle rectangle = new Rectangle(Param.SIZE_SQUARE_TILE,Param.SIZE_SQUARE_TILE, c);
                tilePane.getChildren().add(rectangle);
                
                //On attache ici à chaque rectangle un listener pour le clique
                attachListeners(rectangle, grid[i][j]);
            }
        }
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public void displayBoard(Board board) {
        //grid = board.getGrid();
        for(int i=0; i<Param.NB_ROWS; i++){
            for(int j=0; j<Param.NB_COLUMNS; j++){
                Rectangle r = (Rectangle) tilePane.getChildren().get(boardToPaneCoords(i, j));
                r.setFill(grid[i][j].getAlive() ? Param.COLOR_ALIVE : Param.COLOR_DEAD);
                if(grid[i][j].getAlive()){
                    //System.out.print(LifeState.ALIVE.toChar() +" ");
                }
                else{
                    //System.out.print(LifeState.DEAD.toChar() +" ");
                }
            }
            //System.out.println();
        }
        //System.out.println("\n\n----------------------------\n");
    }
    
    private int boardToPaneCoords(int i, int j) {
        return (i * Param.NB_COLUMNS) + j;
    }
    
    private void attachListeners(Rectangle r, Cellule c) {
        r.setOnMousePressed(e -> { r.setFill(Param.COLOR_BORN); });

        r.setOnMouseClicked(e -> {
            //Si la cellule est vivante on la tue autrement elle nait
            r.setFill(c.getAlive() ? Param.COLOR_DEAD : Param.COLOR_ALIVE);            
            if(c.getState() == LifeState.ALIVE){
                c.setState(LifeState.DEAD);
            }
            else{
                c.setState(LifeState.ALIVE);
            }
        });
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public Pane getPane() {
        return tilePane;
    }
    

}
