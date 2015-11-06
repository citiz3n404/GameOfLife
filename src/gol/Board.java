/* 
 * Creation : 1 nov. 2015
 */
package gol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @date 1 nov. 2015
 * @author Anthony CHAFFOT
 */
public abstract class Board implements Grid {

    Cellule[][] board;

    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Board() {
        board = new Cellule[Param.NB_ROWS][Param.NB_COLUMNS];
        initBoard(board);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    void initNeighbors() {
        if (Param.IS_TORIQUE) {
            initToriqueNeighbors();
        } else {
            initNonToriqueNeighbors();
        }
    }


    abstract void initToriqueNeighbors();

    abstract void initNonToriqueNeighbors();

    public void updateNeighbors() {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                board[i][j].getHashMapNeighbors().clear();
            }
        }
        initNeighbors();
    }

    public void initRandom(double proba) {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                if (Math.random() <= proba) {
                    board[i][j].setState(LifeState.ALIVE);
                } else {
                    board[i][j].setState(LifeState.DEAD);
                }
            }
        }
    }

    public void initBoard(Cellule[][] brd) {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                brd[i][j] = new Cellule(LifeState.DEAD);
            }
        }
        initNeighbors();
    }

    @Override
    public void update() {
        Cellule[][] boardTmp = new Cellule[Param.NB_ROWS][Param.NB_COLUMNS];
        initBoard(boardTmp);

        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                boardTmp[i][j].setState(board[i][j].nextState());
            }
        }
        //On recopie dans la bonne grille
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                board[i][j].setState(boardTmp[i][j].getState());
            }
        }
    }

    @Override
    public String stateAsString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public Cellule[][] getGrid() {
        return board;
    }

    public int getPopulation() {
        int sum = 0;
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                if (board[i][j].getAlive()) {
                    sum++;
                }
            }
        }
        return sum;
    }

}
