/* 
 * Creation : 3 nov. 2015
 */
package gol;

import javafx.scene.paint.Color;



/**
 * Contains all the parameters of the game
 * @date    3 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class Param {
    //**************************************************************************
    // BASE PARAM
    //**************************************************************************
    public static int       NB_ROWS                 = 50;
    public static int       NB_COLUMNS              = 50;
    public static int       GRID                    = 1;
    // 1 -> Square 2-> Hexa 3->Triangle ?

    //**************************************************************************
    // GRIFFEATH
    //**************************************************************************
    public static int       ETAT_MAX_GRIFFEAT       = 3;
    
    //**************************************************************************
    // SIMULATION
    //**************************************************************************
    public static boolean   IS_TORIQUE              = false;
    public static boolean   IS_IMMIGRATION          = false;
    public static boolean   IS_GRIFFEATH            = false;
    public static boolean   IS_ISOTROPE             = false;
    public static boolean   IS_HIGHLIFE             = false;
    public static boolean   IS_DAY_AND_NIGHT        = false;
    public static boolean   IS_FREDKIN              = false;
    public static boolean   IS_MOYENNE              = false;
    public static boolean   IS_GRIFFEATH_N          = false;
    public static boolean   IS_MATHS                = false;
    
    //**************************************************************************
    // GRAPHICAL
    //**************************************************************************
    public static int       SIZE_HEXA_TILE          = 5;
    public static int       SIZE_SQUARE_TILE        = 8;
    public static int       SIZE_GAP                = 1;
    

}
