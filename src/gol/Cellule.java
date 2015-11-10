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

    /**
     * nextState
     * Called to do a turn
     * @return 
     */
    @Override 
    public abstract State nextState();
    
    /**
     * Kill a cell
     * Called when we clic on alive cell on the screen
     */
    public abstract void kill();
    
    /**
     * Born a cell
     * Called when we clic on dead cell on the screen
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
     * @return neighbors hashmap 
     */
    public HashMap<Enum,Cellule> getHashMapNeighbors(){
        return this.neighbors;
    }
    
    /**
     * Getter
     * @param direction
     * @return Cell of neighbor of the direction else return null
     */
    @Override
    public Cell getNeighbor(Enum direction) {
        //Retourne null si il n'y a pas de voisins
        return neighbors.get(direction);
    }

    public abstract int getNbNeighborsAlive();
    
    public abstract boolean isAlive();


    @Override
    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }
    

}
