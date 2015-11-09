/* 
 * Creation : 5 nov. 2015
 */

package gol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * @date    5 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
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
                    Param.NB_ROWS           = (Integer) input.readObject();
                    Param.NB_COLUMNS        = (Integer) input.readObject();
                    Param.GRID              = (Integer) input.readObject();
                    
                    Param.IS_TORIQUE        = (Boolean) input.readObject();
                    Param.IS_ISOTROPE       = (Boolean) input.readObject();
                    Param.IS_IMMIGRATION    = (Boolean) input.readObject();
                    Param.IS_HIGHLIFE       = (Boolean) input.readObject();
                    Param.IS_FREDKIN        = (Boolean) input.readObject();
                    Param.IS_DAY_AND_NIGHT  = (Boolean) input.readObject();
                    Param.IS_GRIFFEATH      = (Boolean) input.readObject();
                    
                    
                    System.out.println("Param loaded");
                    board = (Board) input.readObject();
                    
                    Board board2 = Utils.createNewBoard();
                    for(int i=0; i<Param.NB_ROWS; i++){
                        for(int j=0; j<Param.NB_COLUMNS; j++){
                            board2.board[i][j] = board.board[i][j];
                        }
                    }
                    
                    System.out.println("fin tableau");
                    System.out.println("Load succeeded");
                    return board2;
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
        return Utils.createNewBoard();
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
                output.writeObject((Boolean) Param.IS_TORIQUE);
                output.writeObject((Boolean) Param.IS_ISOTROPE);
                output.writeObject((Boolean) Param.IS_IMMIGRATION);
                output.writeObject((Boolean) Param.IS_HIGHLIFE);
                output.writeObject((Boolean) Param.IS_FREDKIN);
                output.writeObject((Boolean) Param.IS_DAY_AND_NIGHT);
                output.writeObject((Boolean) Param.IS_GRIFFEATH);
                output.writeObject(board);
                System.out.println("SAVE SUCCEEDED !");
            } finally {
                output.close();
            }
        } catch (IOException ex) {
            System.out.println("SAVE FAILED !");
        }
    }
}
