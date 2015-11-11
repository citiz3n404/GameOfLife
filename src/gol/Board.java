/* 
 * Creation : 1 nov. 2015
 */
package gol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * @date 1 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public abstract class Board implements Grid, Serializable {

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
    /**
     * Initialise correctly the neighbors, each grid must Override the initalisation for
     * the Torique mode and the non torique
     */
    void initNeighbors() {
        if (Param.IS_TORIQUE) {
            initToriqueNeighbors();
        } else {
            initNonToriqueNeighbors();
        }
    }

    /**
     * Abstract methode for the mode Torique
     */
    abstract void initToriqueNeighbors();

    /**
     * Abstract methode for the mode non Torique
     */
    abstract void initNonToriqueNeighbors();

    /**
     * Update Neighbors
     * Empty the list of neighbors for each cell in the grid and then recalculate
     * them all.
     */
    public void updateNeighbors() {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                board[i][j].getHashMapNeighbors().clear();
            }
        }
        initNeighbors();
    }

    /**
     * Init the grid randomly
     * @param proba Probability set by a slider for an estimation of the state 
     * of the grid
     */
    public void initRandom(double proba) {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                //******************************************************************************
                if (Param.IS_IMMIGRATION) {
                    if(Math.random() <= proba){
                        if(Math.random()<0.5) board[i][j].setState(ImmigrationState.ALIVE);
                        else board[i][j].setState(ImmigrationState.ZOMBIE);
                    }else{
                        board[i][j].setState(ImmigrationState.DEAD);
                    }
                } else if(Param.IS_ISOTROPE){
                    if (Math.random() <= proba) {
                        board[i][j].setState(LifeState.ALIVE);
                    } else {
                        board[i][j].setState(LifeState.DEAD);
                    }
                } else if(Param.IS_HIGHLIFE){
                    if (Math.random() <= proba) {
                        board[i][j].setState(LifeState.ALIVE);
                    } else {
                        board[i][j].setState(LifeState.DEAD);
                    }
                } else if(Param.IS_DAY_AND_NIGHT){
                    if (Math.random() <= proba) {
                        board[i][j].setState(LifeState.ALIVE);
                    } else {
                        board[i][j].setState(LifeState.DEAD);
                    }
                } else if(Param.IS_FREDKIN){
                    if (Math.random() <= proba) {
                        board[i][j].setState(LifeState.ALIVE);
                    } else {
                        board[i][j].setState(LifeState.DEAD);
                    }
                } 
                else if(Param.IS_GRIFFEATH){
                    //Proportion de case en vie
                    if(Math.random() >= proba){
                        //On tire un aléatoire au pif pour son état inital
                        Random rand = new Random();
                        int nombreAleatoire = rand.nextInt(Param.ETAT_MAX_GRIFFEAT - 0 + 1) + 0;
                        board[i][j].setState(new GriffeathState(nombreAleatoire));
                    }
                } else if(Param.IS_MOYENNE){
                    if(Math.random() >= proba){
                        //A revoir pour inclure 1
                        board[i][j].setState(new MoyenneState(Math.random()));
                    }
                } else {
                    if (Math.random() <= proba) {
                        board[i][j].setState(LifeState.ALIVE);
                    } else {
                        board[i][j].setState(LifeState.DEAD);
                    }
                }

            }
        }
    }

    /**
     * InitBoard
     * Init the grid with a dead state new cells and then call the initNeighbors()
     * @param brd Cell[][]
     */
    public void initBoard(Cellule[][] brd) {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                //****************************************************************************
                if(Param.IS_IMMIGRATION){
                    brd[i][j] = Utils.createNewCell(ImmigrationState.DEAD);
                } else if(Param.IS_ISOTROPE) {
                    brd[i][j] = Utils.createNewCell(LifeState.DEAD);
                } else if(Param.IS_HIGHLIFE) {
                    brd[i][j] = Utils.createNewCell(LifeState.DEAD);
                } else if(Param.IS_DAY_AND_NIGHT) {
                    brd[i][j] = Utils.createNewCell(LifeState.DEAD);
                } else if(Param.IS_FREDKIN) {
                    brd[i][j] = Utils.createNewCell(LifeState.DEAD);
                } else if(Param.IS_GRIFFEATH){
                    brd[i][j] = Utils.createNewCell(new GriffeathState(1));
                } else if(Param.IS_MOYENNE){
                    brd[i][j] = Utils.createNewCell(new MoyenneState(0.0));
                } else {
                    brd[i][j] = Utils.createNewCell(LifeState.DEAD);
                }
                
            }
        }
        initNeighbors();
    }

    /**
     * Update
     * Each update move forward the generation to the next state.
     */
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

    /**
     * Unused methode because we preffered to use the serialization to store our grid
     * @return String
     */
    @Override
    public String stateAsString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     * Getter of the grid
     * @return Cellule[][]
     */
    public Cellule[][] getGrid() {
        return board;
    }

    /**
     * Getter of population of cell alive
     * @return int
     */
    public int getPopulation() {
        int sum = 0;
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                if(board[i][j].isAlive()){
                    sum++;
                }
            }
        }
        return sum;
    }

}
