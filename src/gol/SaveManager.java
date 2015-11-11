/* 
 * Creation : 5 nov. 2015
 */
package gol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

/**
 *
 * @date 5 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class SaveManager {

    public static Board loadBoard(String path) {
        InputStream input;
        Board board = Utils.createNewBoard();
        InputStreamReader ipsr;
        BufferedReader br;
        try {
            input = new FileInputStream(path);
            ipsr = new InputStreamReader(input);
            br = new BufferedReader(ipsr);

            System.out.println("File openned");
            int cmpt = 0;
            int cmptGrid = 0;
            try {
                String ligne;
                end:
                while ((ligne = br.readLine()) != null) {
                    String str[] = ligne.split(" ");
                    switch (cmpt) {
                        case 0:
                            if (str[0].equals("123456789")) {
                                System.out.println(str[0]);
                                cmpt++;
                                break;
                            } else {
                                System.out.println("Wrong file");
                                break end;
                            }
                        case 1:
                            Param.NB_ROWS = Integer.parseInt(str[0]);
                            System.out.println(str[0]);
                            cmpt++;
                            break;
                        case 2:
                            Param.NB_COLUMNS = Integer.parseInt(str[0]);
                            System.out.println(str[0]);
                            cmpt++;
                            break;
                        case 3:
                            System.out.println(str[0]);
                            Param.GRID = Integer.parseInt(str[0]);
                            cmpt++;
                            break;
                            
                        case 4:
                            System.out.println(str[0]);
                            if (str[0].equals("true")) {
                                Param.IS_TORIQUE = true;
                            } else {
                                Param.IS_TORIQUE = false;
                            }
                            cmpt++;
                            break;
                        case 5:
                            System.out.println(str[0]);
                            if (str[0].equals("true")) {
                                Param.IS_ISOTROPE = true;
                            } else {
                                Param.IS_ISOTROPE = false;
                            }
                            cmpt++;
                            break;
                        case 6:
                            System.out.println(str[0]);
                            if (str[0].equals("true")) {
                                Param.IS_IMMIGRATION = true;
                            } else {
                                Param.IS_IMMIGRATION = false;
                            }
                            cmpt++;
                            break;
                        case 7:
                            System.out.println(str[0]);
                            if (str[0].equals("true")) {
                                Param.IS_HIGHLIFE = true;
                            } else {
                                Param.IS_HIGHLIFE = false;
                            }
                            cmpt++;
                            break;
                        case 8:
                            System.out.println(str[0]);
                            if (str[0].equals("true")) {
                                Param.IS_FREDKIN = true;
                            } else {
                                Param.IS_FREDKIN = false;
                            }
                            cmpt++;
                            break;
                        case 9:
                            System.out.println(str[0]);

                            if (str[0].equals("true")) {
                                Param.IS_DAY_AND_NIGHT = true;
                            } else {
                                Param.IS_DAY_AND_NIGHT = false;
                            }
                            cmpt++;
                            break;
                        case 10:
                            System.out.println(str[0]);
                            if (str[0].equals("true")) {
                                Param.IS_GRIFFEATH = true;
                            } else {
                                Param.IS_GRIFFEATH = false;
                            }
                            board = Utils.createNewBoard();
                            cmpt++;
                            break;
                        default:
                            for (int i = 0; i < str.length; i++) {
                                if (Param.IS_GRIFFEATH) {
                                    board.board[cmptGrid][i] = Utils.createNewCell(new GriffeathState(Integer.parseInt(str[i])));
                                } else {
                                    if (Param.IS_IMMIGRATION) {
                                        if (str[i].equals("*")) {
                                            board.board[cmptGrid][i] = Utils.createNewCell(ImmigrationState.ZOMBIE);
                                        } else if (str[i].equals("O")) {
                                            board.board[cmptGrid][i] = Utils.createNewCell(ImmigrationState.ALIVE);
                                        } else if (str[i].equals(".")) {
                                            board.board[cmptGrid][i] = Utils.createNewCell(ImmigrationState.DEAD);
                                        }
                                    }
                                    else{
                                        if(str[i].equals("O")){
                                            System.out.print(str[i]+" ");
                                            board.board[cmptGrid][i] = Utils.createNewCell(LifeState.ALIVE);
                                        }
                                        else{
                                            System.out.print(str[i]+" ");
                                            board.board[cmptGrid][i] = Utils.createNewCell(LifeState.DEAD);
                                        }
                                    }
                                }
                            }
                            cmptGrid++;
                            if(cmptGrid == Param.NB_ROWS){
                                return board;
                            }
                    }

                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            System.out.println("LOADING FAILED !");
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

                for (int i = 0; i < Param.NB_ROWS; i++) {
                    for (int j = 0; j < Param.NB_COLUMNS; j++) {
                        if (Param.IS_GRIFFEATH) {
                            writer.print(board.board[i][j].state.toString() + " ");
                        } else {
                            writer.print(board.board[i][j].state.toChar() + " ");
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
