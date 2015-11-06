/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
 */
public class Utils {
    //**************************************************************************
    // METHODS
    //**************************************************************************
    public static Board createNewBoard(){
        Board board;
        if(Param.GRID==1){
            board = new SquareBoard();
        }
        else if(Param.GRID ==2){
            board = new HexaBoard();
        }
        else{
            board = new SquareBoard();
        }
        return board;
    }
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}
