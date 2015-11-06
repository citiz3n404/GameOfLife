/* 
 * Creation : 6 nov. 2015
 */
package gol;

/**
 * @date 6 nov. 2015
 * @author Anthony CHAFFOT
 */
public class HexaBoard extends Board {

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public HexaBoard() {
        super();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    @Override
    void initToriqueNeighbors() {
        //TODO
    }

    @Override
    void initNonToriqueNeighbors() {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                if(i%2==1){  
                    if (i > 0) {
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[i - 1][j]);
                    }
                    if (i > 0 && j < Param.NB_COLUMNS - 1) {
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[i - 1][j + 1]);
                    }
                    if (j < Param.NB_COLUMNS - 1) {
                        board[i][j].setNeighbor(SquareGridNbh.EAST, board[i][j + 1]);
                    }
                    if (i < Param.NB_ROWS - 1&&j < Param.NB_COLUMNS - 1) {
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[i + 1][j + 1]);
                    }
                    if (i < Param.NB_ROWS - 1) {
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[i + 1][j]);
                    }
                    if (j > 0) {
                        board[i][j].setNeighbor(SquareGridNbh.WEST, board[i][j - 1]);
                    }
                } else if(i%2==0){ 
                    if (i > 0 && j > 0) {
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[i - 1][j - 1]);
                    }
                    if (i > 0) {
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[i - 1][j]);
                    }
                    if (j < Param.NB_COLUMNS - 1) {
                        board[i][j].setNeighbor(SquareGridNbh.EAST, board[i][j + 1]);
                    }
                    if (i < Param.NB_ROWS - 1) {
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[i + 1][j]);
                    }
                    if (i < Param.NB_ROWS - 1 && j > 0) {
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[i + 1][j - 1]);
                    }
                    if (j > 0) {
                        board[i][j].setNeighbor(SquareGridNbh.WEST, board[i][j - 1]);
                    }
                }
            } 
        }
    }

}
