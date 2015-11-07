
package gol;

/**
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 * @date 30/10/2015
 */
public interface Grid <S extends State, N extends Enum<N>, C extends Cell<?, N>> {
    //**************************************************************************
    // METHODS
    //**************************************************************************

    /**
     * Execute une transition de l'automate
     */
    void update();


    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     *
     * @return String qui représente la grille en vue de l'affichage
     */
    String stateAsString();
}