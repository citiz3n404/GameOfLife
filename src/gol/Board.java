/* 
 * Creation : 1 nov. 2015
 */
package gol;

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
                        if(Math.random()<0.5) board[i][j].setState(StateImmigration.ALIVE);
                        else board[i][j].setState(StateImmigration.ZOMBIE);
                    }else{
                        board[i][j].setState(StateImmigration.DEAD);
                    }
                } else if(Param.IS_GRIFFEATH){
                    Random rand = new Random();
                    int nombreAleatoire = rand.nextInt(4) ;
                    board[i][j].setState(new StateInt(nombreAleatoire));
                        
                } else if(Param.IS_GRIFFEATH_N){
                    Random rand = new Random();
                    int nombreAleatoire = rand.nextInt(Param.ETAT_MAX_GRIFFEAT + 1);
                    board[i][j].setState(new StateInt(nombreAleatoire));
                    
                } else if(Param.IS_MOYENNE || Param.IS_MATHS){
                    if(Math.random() <= proba){
                        double x = Math.random();
                        if(x!=0.0){
                            board[i][j].setState(new StateDouble(x));
                        } else {
                            board[i][j].setState(new StateDouble(1.0));
                        }
                    } else {
                        board[i][j].setState(new StateDouble(0.0));
                    }
                } else {
                    //traite les modes restants se servants de SateLife
                    //Classique, Isotrope, HighLife, DayAndNight et Fredkin
                    if (Math.random() <= proba) {
                        board[i][j].setState(StateLife.ALIVE);
                    } else {
                        board[i][j].setState(StateLife.DEAD);
                    }
                }

            }
        }
    }

    /**
     * InitBoard
     * Init the grid with a dead state or 0 (if int or double state) new cells 
     * and then call the initNeighbors()
     * @param brd Cell[][]
     */
    public void initBoard(Cellule[][] brd) {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                if(Param.IS_IMMIGRATION){
                    brd[i][j] = Utils.createNewCell(StateImmigration.DEAD);
                } else if(Param.IS_GRIFFEATH || Param.IS_GRIFFEATH_N){
                    brd[i][j] = Utils.createNewCell(new StateInt(0));
                }else if(Param.IS_MOYENNE || Param.IS_MATHS){
                    brd[i][j] = Utils.createNewCell(new StateDouble(0.0));
                } else {
                    //traite les modes restants se servants de SateLife
                    //Classique, Isotrope, HighLife, DayAndNight et Fredkin
                    brd[i][j] = Utils.createNewCell(StateLife.DEAD);
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
     * Retourne la grille sous forme de chaine de caractères
     * @return String
     */
    @Override
    public String stateAsString() {
        String res = "";
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                res += board[i][j].getState().toChar()+" ";
            }
            res += "\n";
        }
        return res;
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
