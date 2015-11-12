/* 
 * Creation : 5 nov. 2015
 */
package gol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

            int cmpt = 0;
            int cmptGrid = 0;
            try {
                String ligne;
                end:
                while ((ligne = br.readLine()) != null) {
                    String str[] = ligne.split(" ");
                    switch (cmpt) {
                        case 0:
                            if (str[0].equals("123456789-LineTest")) {
                                cmpt++;
                                break;
                            } else {
                                System.out.println("Wrong file");
                                break end;
                            }
                        case 1:
                            Param.NB_ROWS = Integer.parseInt(str[1]);
                            cmpt++;
                            break;
                        case 2:
                            Param.NB_COLUMNS = Integer.parseInt(str[1]);
                            cmpt++;
                            break;
                        case 3:
                            Param.GRID = Integer.parseInt(str[1]);
                            cmpt++;
                            break;
                            
                        case 4:
                            Param.IS_TORIQUE = str[1].equals("true");
                            cmpt++;
                            break;
                        case 5:
                            Param.IS_ISOTROPE = str[1].equals("true");
                            cmpt++;
                            break;
                        case 6:
                            Param.IS_IMMIGRATION = str[1].equals("true");
                            cmpt++;
                            break;
                        case 7:
                            Param.IS_HIGHLIFE = str[1].equals("true");
                            cmpt++;
                            break;
                        case 8:
                            Param.IS_FREDKIN = str[1].equals("true");
                            cmpt++;
                            break;
                        case 9:
                            Param.IS_DAY_AND_NIGHT = str[1].equals("true");
                            cmpt++;
                            break;
                        case 10:
                            Param.IS_GRIFFEATH = str[1].equals("true");
                            cmpt++;
                            break;
                        case 11:
                            Param.IS_GRIFFEATH_N = str[1].equals("true");
                            cmpt++;
                            break;
                        case 12:
                            Param.IS_MOYENNE =str[1].equals("true");
                            cmpt++;
                            break;
                        case 13:
                            Param.IS_MATHS= str[1].equals("true");
                            cmpt++;
                            
                            board = Utils.createNewBoard();
                            break;
                            
                        default:
                            
                            for (int i = 0; i < str.length; i++) {
                                if (Param.IS_GRIFFEATH) {
                                    board.board[cmptGrid][i].setState(new SateInt(Integer.parseInt(str[i])));
                                } else {
                                    if (Param.IS_IMMIGRATION) {
                                        if (str[i].equals("*")) {
                                            board.board[cmptGrid][i].setState(ImmigrationState.ZOMBIE);
                                        } else if (str[i].equals("O")) {
                                            board.board[cmptGrid][i].setState(ImmigrationState.ALIVE);
                                        } else if (str[i].equals(".")) {
                                            board.board[cmptGrid][i].setState(ImmigrationState.DEAD);
                                        }
                                    }
                                    else{
                                        if(str[i].equals("O")){
                                            //System.out.print(str[i]+" ");
                                            board.board[cmptGrid][i].setState(StateLife.ALIVE);
                                        }
                                        else{
                                            //System.out.print(str[i]+" ");
                                            board.board[cmptGrid][i].setState(StateLife.DEAD);
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
        return board;
    }

    public static void saveBoard(String path, Board board) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(path, "UTF-8");
            try {
                writer.println("123456789-LineTest");
                writer.println("NbRows: "+Param.NB_ROWS);
                writer.println("NbCol: "+Param.NB_COLUMNS);
                writer.println("Grid: "+Param.GRID);
                writer.println("Torique: "+Param.IS_TORIQUE);
                writer.println("Isotrope: "+Param.IS_ISOTROPE);
                writer.println("Immigration: "+Param.IS_IMMIGRATION);
                writer.println("HighLife: "+Param.IS_HIGHLIFE);
                writer.println("Fredkin: "+Param.IS_FREDKIN);
                writer.println("DayAndNight: "+Param.IS_DAY_AND_NIGHT);
                writer.println("Griffeath: "+Param.IS_GRIFFEATH);
                writer.println("GriffeathN: "+Param.IS_GRIFFEATH_N);
                writer.println("Moyenne: "+Param.IS_MOYENNE);
                writer.println("Maths: "+Param.IS_MATHS);

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
