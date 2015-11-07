/* 
 * Creation : 3 nov. 2015
 */
package gol;

import javafx.scene.paint.Color;



/**
 * Parameters of the game
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
    // CLASSIC
    //**************************************************************************
    public static int       NEIGHBORS_MIN_TO_LIVE   = 2;
    public static int       NEIGHBORS_MAX_TO_LIVE   = 3;
    public static int       NEIGHBORS_TO_BORN   = 3;
    
    //**************************************************************************
    // GRIFFEATH
    //**************************************************************************
    public static int       ETAT_MAX_GRIFFEAT       = 10;
    
    //**************************************************************************
    // SIMULATION
    //**************************************************************************
    public static boolean   IS_TORIQUE              = false;
    
    public static boolean   IS_IMMIGRATION          = false;
    public static boolean   IS_GRIFFEAT             = false;
    public static boolean   IS_ISOTROPE             = false;
    public static boolean   IS_HIGHLIFE             = false;
    public static boolean   IS_DAY_AND_NIGHT        = false;
    
    //**************************************************************************
    // GRAPHICAL
    //**************************************************************************
    public static int       SIZE_HEXA_TILE          = 5;
    public static int       SIZE_SQUARE_TILE        = 8;
    public static int       SIZE_GAP                = 1;
    public static Color     COLOR_ALIVE             = Color.web("#D50000");
    public static Color     COLOR_DEAD              = Color.web("#EF9A9A");
    public static Color     COLOR_BORN              = Color.web("#EC407A");
    

}
