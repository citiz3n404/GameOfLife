package gol;


import javax.swing.*;

/**
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 * @date 30/10/2015
 */
public interface State {
    //**************************************************************************
    // VARIABLES
    //**************************************************************************

    Icon DEFAULT_ICON = new ImageIcon();

    //**************************************************************************
    // METHODS
    //**************************************************************************

    /**
     *
     * @return Un caractère représentant l'état
     */
    default char toChar() { return ',';}

    /**
     *
     * @return icon représentant l'état
     */
    default Icon toIcon(){ return DEFAULT_ICON;}
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

}