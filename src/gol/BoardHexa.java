/* 
 * Creation : 6 nov. 2015
 */
package gol;

/**
 * @date 6 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class BoardHexa extends Board {

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public BoardHexa() {
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
                    if(Param.NB_ROWS%2==0){ //pair
                        if(i==0){
                            if(j==0){
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][0]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[1][Param.NB_COLUMNS - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.WEST, board[0][Param.NB_COLUMNS - 1]);
                            } else if (j==Param.NB_COLUMNS-1){
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][j - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][j]);
                                board[i][j].setNeighbor(HexaGridNbh.EAST, board[0][0]);
                            } else {
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][j - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][j]);
                            }
                        } else if(i==Param.NB_ROWS-1){
                            if(j==0){
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[0][1]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[0][0]);
                                board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][Param.NB_COLUMNS - 1]);
                            } else if (j==Param.NB_COLUMNS-1){
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[i - 1][0]);
                                board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][0]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[0][0]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[0][j]);
                            } else {
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[0][j+1]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[0][j]);
                            }
                        } else {
                            if(j==0){
                                if(i%2==0){ //indice tableau paires
                                    board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[i-1][Param.NB_COLUMNS-1]);
                                    board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][Param.NB_COLUMNS-1]);
                                    board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[i+1][Param.NB_COLUMNS-1]);                                
                                } else if(i%2==1){ //indices tableau impaires
                                    board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][Param.NB_COLUMNS-1]);
                                }
                            } else if (j==Param.NB_COLUMNS-1){
                                if(i%2==0){
                                    board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][0]);
                                } else if(i%2==1){
                                    board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[i-1][0]);
                                    board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][0]);
                                    board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[i+1][0]);
                                }
                            }
                        }
                    } else if(Param.NB_ROWS%2==1){ //impair
                        if(i==0){
                            if(j==0){
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][0]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[1][Param.NB_COLUMNS - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.WEST, board[0][Param.NB_COLUMNS - 1]);
                            } else if (j==Param.NB_COLUMNS-1){
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][j]);
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][0]);
                                board[i][j].setNeighbor(HexaGridNbh.EAST, board[0][0]);
                            } else {
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][j]);
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][j+1]);
                            }
                        } else if(i==Param.NB_ROWS-1){
                            if(j==0){
                                board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[i - 1][Param.NB_COLUMNS - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[0][1]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[0][Param.NB_COLUMNS - 1]);
                                board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][Param.NB_COLUMNS - 1]);
                            } else if (j==Param.NB_COLUMNS-1){
                                board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][0]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[0][0]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[0][j-1]);
                            } else {
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[0][j]);
                                board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[0][j-1]);
                            }
                        } else {
                            if(j==0){
                                if(i%2==0){ //indice tableau paires
                                    board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[i-1][Param.NB_COLUMNS-1]);
                                    board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][Param.NB_COLUMNS-1]);
                                    board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[i+1][Param.NB_COLUMNS-1]);                                
                                } else if(i%2==1){ //indices tableau impaires
                                    board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][Param.NB_COLUMNS-1]);
                                }
                            } else if (j==Param.NB_COLUMNS-1){
                                if(i%2==0){
                                    board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][0]);
                                } else if(i%2==1){
                                    board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[i-1][0]);
                                    board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][0]);
                                    board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[i+1][0]);
                                }
                            }
                        }
                    }

                    if(i%2==1){  //"lignes paire" d'indice tableau impair
                        if (board[i][j].getNeighbor(HexaGridNbh.NORTH_WEST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[i - 1][j]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.NORTH_EAST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[i - 1][j + 1]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.EAST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][j + 1]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.SOUTH_EAST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[i + 1][j + 1]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.SOUTH_WEST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[i + 1][j]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.WEST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][j - 1]);
                        }
                    } else if(i%2==0){ //lignes "impaires" d'indice tableau pair
                        if (board[i][j].getNeighbor(HexaGridNbh.NORTH_WEST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.NORTH_WEST, board[i - 1][j - 1]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.NORTH_EAST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.NORTH_EAST, board[i - 1][j]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.EAST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.EAST, board[i][j + 1]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.SOUTH_EAST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.SOUTH_EAST, board[i + 1][j]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.SOUTH_WEST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.SOUTH_WEST, board[i + 1][j - 1]);
                        }
                        if (board[i][j].getNeighbor(HexaGridNbh.WEST) == null) {
                            board[i][j].setNeighbor(HexaGridNbh.WEST, board[i][j - 1]);
                        }
                    }
                } 
            }
        }
    }

    @Override
    void initNonToriqueNeighbors() {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                if(i%2==1){  //lignes "paires"
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
                } else if(i%2==0){ //lignes "impaires"
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
