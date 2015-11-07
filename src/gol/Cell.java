
package gol;

import java.io.Serializable;

/**
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 * @param <S>
 * @param <N>
 * @date 30/10/2015
 */
public interface Cell <S extends State, N extends Enum<N>> extends Serializable {
    //**************************************************************************
    // METHODS
    //**************************************************************************


    /**
     * Simule une transition sans changer l'état de l'objet
     * @return etat suivant
     */
    S nextState();



    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     *
     * @param direction
     * @return reference vers la cellule voisine en fonction de la direction
     */
    Cell<S, N> getNeighbor(N direction);

    /**
     *
     * @return Etat courrant de la cellule
     */
    S getState();

    /**
     * Set l'état de la cellule par celui donné en arg
     * @param state
     */
    void setState(S state);
}