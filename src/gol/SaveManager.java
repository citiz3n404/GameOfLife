package gol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveManager {

    public static Board loadBoard(String path) {
        ObjectInputStream input;
        Board board;
        //Cellule[][] grid = board.getGrid();
        try {
            input = new ObjectInputStream(new FileInputStream(path));
            System.out.println("fichier ouvert");
            try {
                Integer tempInt = (Integer) input.readObject();

                if (tempInt.equals(123456789)) {
                    Param.NB_ROWS = (Integer) input.readObject();
                    Param.NB_COLUMNS = (Integer) input.readObject();
                    Param.GRID = (Integer) input.readObject();
                    Param.NEIGHBORS_MIN_TO_LIVE = (Integer) input.readObject();
                    Param.NEIGHBORS_MAX_TO_LIVE = (Integer) input.readObject();
                    Param.NEIGHBORS_MIN_TO_BORN = (Integer) input.readObject();
                    Param.NEIGHBORS_MAX_TO_BORN = (Integer) input.readObject();
                    System.out.println("Param loaded");
                    
                    board = new Board();
                    for (int i = 0; i < Param.NB_ROWS; i++) {
                        for (int j = 0; j < Param.NB_COLUMNS; j++) {
                            //System.out.println(i + " " + j);
                            try {
                                board.getGrid()[i][j] = (Cellule) input.readObject();
                            } catch (ClassNotFoundException ex) {
                                System.out.println("ERREUR LORS DE LA LECTURE DES CASES DU BOARD");
                            }
                        }
                    }
                    
                    System.out.println("fin tableau");
                    System.out.println("Load succeeded");
                    return board;
                } else {
                    System.out.println("ERREUR DE FICHIER");
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            System.out.println("WORLD LOADING FAILED !");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
        return new Board();
    }

    public static void saveBoard(String path, Board board) {
        ObjectOutputStream output;
        try {
            output = new ObjectOutputStream(new FileOutputStream(path));
            try {
                output.writeObject((Integer) 123456789);
                output.writeObject((Integer) Param.NB_ROWS);
                output.writeObject((Integer) Param.NB_COLUMNS);
                output.writeObject((Integer) Param.GRID);
                output.writeObject((Integer) Param.NEIGHBORS_MIN_TO_LIVE);
                output.writeObject((Integer) Param.NEIGHBORS_MAX_TO_LIVE);
                output.writeObject((Integer) Param.NEIGHBORS_MIN_TO_BORN);
                output.writeObject((Integer) Param.NEIGHBORS_MAX_TO_BORN);
                System.out.println("Param saved");
                for (int i = 0; i < Param.NB_ROWS; i++) {
                    for (int j = 0; j < Param.NB_COLUMNS; j++) {
                        //System.out.println(i+" "+j);
                        output.writeObject((Cellule) board.getGrid()[i][j]);
                    }
                }
                System.out.println("SAVE SUCCEEDED !");
            } finally {
                output.close();
            }
        } catch (IOException ex) {
            System.out.println("SAVE FAILED !");
        }
    }
}
