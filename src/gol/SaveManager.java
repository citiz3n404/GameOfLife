/* 
 * Creation : 5 nov. 2015
 */

package gol;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

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
        PrintWriter writer;
        try {
            writer = new PrintWriter(path, "UTF-8");
            try {
                writer.println("123456789");
                writer.println(Param.NB_ROWS);
                writer.println(Param.NB_COLUMNS);
                writer.println(Param.GRID);
                writer.println(Param.IS_TORIQUE);
                writer.println(Param.IS_ISOTROPE);
                writer.println(Param.IS_IMMIGRATION);
                writer.println(Param.IS_HIGHLIFE);
                writer.println(Param.IS_FREDKIN);
                writer.println(Param.IS_DAY_AND_NIGHT);
                writer.println(Param.IS_GRIFFEATH);
                
                for(int i =0; i<Param.NB_ROWS; i++){
                    for(int j=0; j<Param.NB_COLUMNS; j++){
                        if(Param.IS_GRIFFEATH){
                            writer.print(board.board[i][j].state.toString()+" ");
                        }
                        else{
                            writer.print(board.board[i][j].state.toChar()+" ");
                        }
                        
                    }
                    writer.println();
                }
                System.out.println("SAVE SUCCEEDED !");
            } finally {
                writer.close();
            }
        } catch (IOException ex) {
            System.out.println("SAVE FAILED !");
        }
    }
}
