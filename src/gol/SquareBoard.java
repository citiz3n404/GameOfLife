/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class SquareBoard extends Board{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public SquareBoard(){
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
        if (Param.NB_COLUMNS == 1 || Param.NB_ROWS == 1) {
            initNonToriqueNeighbors();
        } else {
            for (int i = 0; i < Param.NB_ROWS; i++) {
                for (int j = 0; j < Param.NB_COLUMNS; j++) {

                    //Cases en bord et en coin de grille
                    if (i == 0) {
                        if (j == 0) {
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][1]);
                            board[i][j].setNeighbor(SquareGridNbh.NORTH, board[Param.NB_ROWS - 1][0]);
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.WEST, board[0][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[1][Param.NB_COLUMNS - 1]);
                        } else if (j == Param.NB_COLUMNS - 1) {
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 2]);
                            board[i][j].setNeighbor(SquareGridNbh.NORTH, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][0]);
                            board[i][j].setNeighbor(SquareGridNbh.EAST, board[0][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[1][0]);
                        } else {
                            if (j > 0 && j < Param.NB_COLUMNS - 1) {
                                board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][j - 1]);
                                board[i][j].setNeighbor(SquareGridNbh.NORTH, board[Param.NB_ROWS - 1][j]);
                                board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][j + 1]);
                            }
                        }
                    } else if (i == Param.NB_ROWS - 1) {
                        if (j == 0) {
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 2][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.WEST, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[0][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[0][0]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[0][1]);
                        } else if (j == Param.NB_COLUMNS - 1) {
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 2][0]);
                            board[i][j].setNeighbor(SquareGridNbh.EAST, board[Param.NB_ROWS - 1][0]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[0][0]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[0][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[0][Param.NB_COLUMNS - 2]);
                        } else {
                            if (j > 0 && j < Param.NB_COLUMNS - 1) {
                                board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[0][j - 1]);
                                board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[0][j]);
                                board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[0][j + 1]);
                            }
                        }
                    } else if (i > 0 && i < Param.NB_ROWS - 1) {
                        if (j == 0) {
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[i - 1][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.WEST, board[i][Param.NB_COLUMNS - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[i + 1][Param.NB_COLUMNS - 1]);
                        } else if (j == Param.NB_COLUMNS - 1) {
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[i - 1][0]);
                            board[i][j].setNeighbor(SquareGridNbh.EAST, board[i][0]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[i + 1][0]);
                        }
                    }
                    //Cases en milieu de grille
                    if (board[i][j].getNeighbor(SquareGridNbh.NORTH_WEST) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[i - 1][j - 1]);
                    }
                    if (board[i][j].getNeighbor(SquareGridNbh.NORTH) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.NORTH, board[i - 1][j]);
                    }
                    if (board[i][j].getNeighbor(SquareGridNbh.NORTH_EAST) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[i - 1][j + 1]);
                    }
                    if (board[i][j].getNeighbor(SquareGridNbh.EAST) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.EAST, board[i][j + 1]);
                    }
                    if (board[i][j].getNeighbor(SquareGridNbh.SOUTH_EAST) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[i + 1][j + 1]);
                    }
                    if (board[i][j].getNeighbor(SquareGridNbh.SOUTH) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[i + 1][j]);
                    }
                    if (board[i][j].getNeighbor(SquareGridNbh.SOUTH_WEST) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[i + 1][j - 1]);
                    }
                    if (board[i][j].getNeighbor(SquareGridNbh.WEST) == null) {
                        board[i][j].setNeighbor(SquareGridNbh.WEST, board[i][j - 1]);
                    }
                }
            }
        }

    }

    @Override
    void initNonToriqueNeighbors() {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                if (i > 0 && j > 0) {
                    board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[i - 1][j - 1]);
                }
                if (i > 0) {
                    board[i][j].setNeighbor(SquareGridNbh.NORTH, board[i - 1][j]);
                }
                if (i > 0 && j < Param.NB_COLUMNS - 1) {
                    board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[i - 1][j + 1]);
                }
                if (j < Param.NB_COLUMNS - 1) {
                    board[i][j].setNeighbor(SquareGridNbh.EAST, board[i][j + 1]);
                }
                if (i < Param.NB_ROWS - 1 && j < Param.NB_COLUMNS - 1) {
                    board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[i + 1][j + 1]);
                }
                if (i < Param.NB_ROWS - 1) {
                    board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[i + 1][j]);
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
