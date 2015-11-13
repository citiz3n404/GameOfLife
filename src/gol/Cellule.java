/* 
 * Creation : 1 nov. 2015
 */
package gol;

import java.io.Serializable;
import java.util.HashMap;



/**
 * @date    1 nov. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public abstract class Cellule implements Cell, Serializable {
    protected State state;
    protected HashMap<Enum, Cellule> neighbors;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Cellule(State st){
        state = st;
        this.neighbors = new HashMap<>();
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    @Override 
    public abstract State nextState();
    
    /**
     * Kill a cell
     * Called when we clic on alive cell on the screen
     * More generally gets cell to next state 
     */
    public abstract void kill();
    
    /**
     * Born a cell
     * Called when we clic on dead cell on the screen
     * More generally gets cell to next state 
     */
    public abstract void born();
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     * Add a neighbor the hashmap of neighbors
     * @param direction
     * @param c 
     */
    public void setNeighbor(Enum direction, Cellule c){
        neighbors.put(direction, c);
    }
    
    /**
     * Getter of the neighbors hashmap
     * @return neighbors hashmap of the cell's neighbors
     */
    public HashMap<Enum,Cellule> getHashMapNeighbors(){
        return this.neighbors;
    }
    
    /**
     * Getter
     * @param direction
     * @return Cell the neighbor in the direction specified else return null
     */
    @Override
    public Cell getNeighbor(Enum direction) {
        //Retourne null si il n'y a pas de voisins
        return neighbors.get(direction);
    }
    
    /**
     * 
     * @return number of neighbors considered alive
     */
    public abstract int getNbNeighborsAlive();
    
    /**
     * 
     * @return true if the cell is alive
     */
    public abstract boolean isAlive();


    /**
     * 
     * @return the state of the cell
     */
    @Override
    public State getState() {
        return this.state;
    }

    /**
     * Set the state of the cell by a new state
     * @param state 
     */
    @Override
    public void setState(State state) {
        this.state = state;
    }
    

}
