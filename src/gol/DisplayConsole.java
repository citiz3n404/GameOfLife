/* 
 * Creation : 1 nov. 2015
 */
package gol;



/**
 * @date    1 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class DisplayConsole implements DisplayDriver{
    Cellule[][] grid;
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public DisplayConsole(Board board){
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     * displays the game board
     */
    @Override
    public void displayBoard(Board board) {
        grid = board.getGrid();
        for(int i=0; i<Param.NB_ROWS; i++){
            for(int j=0;j<Param.NB_COLUMNS; j++){
                //*******************************************************************************************
                /*                if(grid[i][j].getAlive()){
                System.out.print(LifeState.ALIVE.toChar()+ " ");
                }
                else{
                System.out.print(LifeState.DEAD.toChar()+ " ");
                }*/
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

}
