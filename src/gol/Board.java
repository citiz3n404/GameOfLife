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
public class Board implements Grid {

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
    public void initNeighbors() {
        //Grille Carrée
        if (Param.GRID == 1) {
            if (Param.IS_TORIQUE) { // Carrée Grille torique
                initSquareToriqueNeighbors();
            } else { //Grille Carrée non torique
                initSquareNonToriqueNeighbors();
            }
        } else if (Param.GRID == 2) {
            if (Param.IS_TORIQUE) {
                initHexaToriqueNeighbors();
            } else {
                initHexaNonToriqueNeighbors();
            }
        }
    }

    private void initSquareToriqueNeighbors() {
        for (int i = 0; i < Param.NB_ROWS; i++) {
            for (int j = 0; j < Param.NB_COLUMNS; j++) {
                //Cases en bord et en coin de grille
                if(i==0){
                    if(j==0){
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][1]);
                        board[i][j].setNeighbor(SquareGridNbh.NORTH, board[Param.NB_ROWS - 1][0]);
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 1]);
                        board[i][j].setNeighbor(SquareGridNbh.WEST, board[0][Param.NB_COLUMNS - 1]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[1][Param.NB_COLUMNS - 1]);
                    } else if(j==Param.NB_COLUMNS-1){
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][Param.NB_COLUMNS - 2]);
                        board[i][j].setNeighbor(SquareGridNbh.NORTH, board[Param.NB_ROWS - 1][Param.NB_COLUMNS-1]);
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][0]);
                        board[i][j].setNeighbor(SquareGridNbh.EAST, board[0][Param.NB_COLUMNS - 1]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[1][0]);
                    } else {
                        if(j>0 && j<Param.NB_COLUMNS-1){
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 1][j - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.NORTH, board[Param.NB_ROWS - 1][j]);
                            board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 1][j + 1]);
                        }
                    }
                } else if(i==Param.NB_ROWS - 1){
                    if(j==0){
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[Param.NB_ROWS - 2][Param.NB_COLUMNS-1]);
                        board[i][j].setNeighbor(SquareGridNbh.WEST, board[Param.NB_ROWS-1][Param.NB_COLUMNS-1]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[0][Param.NB_COLUMNS-1]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[0][0]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[0][1]);
                    } else if(j==Param.NB_COLUMNS-1){
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[Param.NB_ROWS - 2][0]);
                        board[i][j].setNeighbor(SquareGridNbh.EAST, board[Param.NB_ROWS - 1][0]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[0][0]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[0][Param.NB_COLUMNS - 1]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[0][Param.NB_COLUMNS - 2]);
                    } else {
                        if(j>0 && j<Param.NB_COLUMNS-1){
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[0][j - 1]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[0][j]);
                            board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[0][j + 1]);
                        }
                    }
                } else if(i>0 && i<Param.NB_ROWS-1){
                    if(j==0){
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[i - 1][Param.NB_COLUMNS - 1]);
                        board[i][j].setNeighbor(SquareGridNbh.WEST, board[i][Param.NB_COLUMNS - 1]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[i + 1][Param.NB_COLUMNS - 1]);
                    } else if(j==Param.NB_COLUMNS-1){
                        board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[i - 1][0]);
                        board[i][j].setNeighbor(SquareGridNbh.EAST, board[i][0]);
                        board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[i + 1][0]);
                    }
                }
                //Cases en milieu de grille
                if (board[i][j].getNeighbor(SquareGridNbh.NORTH_WEST)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.NORTH_WEST, board[i - 1][j - 1]);
                }
                if (board[i][j].getNeighbor(SquareGridNbh.NORTH)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.NORTH, board[i - 1][j]);
                }
                if (board[i][j].getNeighbor(SquareGridNbh.NORTH_EAST)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.NORTH_EAST, board[i - 1][j + 1]);
                }
                if (board[i][j].getNeighbor(SquareGridNbh.EAST)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.EAST, board[i][j + 1]);
                }
                if (board[i][j].getNeighbor(SquareGridNbh.SOUTH_EAST)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.SOUTH_EAST, board[i + 1][j + 1]);
                }
                if (board[i][j].getNeighbor(SquareGridNbh.SOUTH)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.SOUTH, board[i + 1][j]);
                }
                if (board[i][j].getNeighbor(SquareGridNbh.SOUTH_WEST)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.SOUTH_WEST, board[i + 1][j - 1]);
                }
                if (board[i][j].getNeighbor(SquareGridNbh.WEST)==null) {
                    board[i][j].setNeighbor(SquareGridNbh.WEST, board[i][j - 1]);
                }
            }
        }
    }

    private void initSquareNonToriqueNeighbors() {
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

    private void initHexaToriqueNeighbors() {
        //TODO
    }

    private void initHexaNonToriqueNeighbors() {
        //TODO
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

        //ENLEVER LE INIT NEIGHBIR DU INIBOARD
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
    
    public Board loadBoard(String path){
        ObjectInputStream input;

        try {
            input = new ObjectInputStream(new FileInputStream(path));
            System.out.println("fichier ouvert");
            try{
                Integer tempInt = (Integer)input.readObject();
                
                
                if (tempInt.equals(123456789)) {
                    //resetModel();
                    //validWorld = true;
                    
                    Param.NB_ROWS = (Integer)input.readObject();
                    Param.NB_COLUMNS = (Integer) input.readObject();
                    Param.GRID = (Integer) input.readObject();
                    Param.NEIGHBORS_MIN_TO_LIVE = (Integer) input.readObject();
                    Param.NEIGHBORS_MAX_TO_LIVE = (Integer) input.readObject();
                    Param.NEIGHBORS_MIN_TO_BORN = (Integer) input.readObject();
                    Param.NEIGHBORS_MAX_TO_BORN = (Integer) input.readObject();
                    System.out.println("Param loaded");
                    for(int i=0; i<Param.NB_ROWS; i++){
                        for(int j=0; j<Param.NB_COLUMNS; j++){
                            System.out.println(i+" "+j);
                            try{
                                board[i][j]= (Cellule)input.readObject();
                            } catch (ClassNotFoundException ex) {
                            System.out.println("ERREUR LORS DE LA LECTURE DES CASES DU BOARD");
                        }
                        }
                    }
                    System.out.println("fin tableau");
                    System.out.println("Load succeeded");
                    return this;
                } else {
                    System.out.println("ERREUR DE FICHIER");
                }
            }finally{
                input.close();
            }
        } catch (IOException ex) {
            System.out.println("WORLD LOADING FAILED !");
        } catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }
        return this;
    }
    
    public void saveBoard(String path){
        ObjectOutputStream output;
        try{
            output = new ObjectOutputStream(new FileOutputStream(path));
            try{
                output.writeObject((Integer)123456789);
                output.writeObject((Integer)Param.NB_ROWS);
                output.writeObject((Integer)Param.NB_COLUMNS);
                output.writeObject((Integer)Param.GRID);
                output.writeObject((Integer)Param.NEIGHBORS_MIN_TO_LIVE);
                output.writeObject((Integer)Param.NEIGHBORS_MAX_TO_LIVE);
                output.writeObject((Integer)Param.NEIGHBORS_MIN_TO_BORN);
                output.writeObject((Integer)Param.NEIGHBORS_MAX_TO_BORN);
                System.out.println("Param saved");
                for(int i=0; i<Param.NB_ROWS; i++){
                    for(int j=0; j<Param.NB_COLUMNS; j++){
                        //System.out.println(i+" "+j);
                        output.writeObject((Cellule)board[i][j]);
                    }
                }
                System.out.println("SAVE SUCCEEDED !");
            }finally{
                output.close();
            }
        } catch (IOException ex) {
            System.out.println("SAVE FAILED !");
        } 
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
