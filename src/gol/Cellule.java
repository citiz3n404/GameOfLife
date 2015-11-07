/* 
 * Creation : 1 nov. 2015
 */
package gol;

import java.io.Serializable;
import java.util.HashMap;



/**
 * @date    1 nov. 2015
 * @author  Anthony CHAFFOT
 */
public abstract class Cellule implements Cell, Serializable {
    protected State state;
    protected HashMap<Enum, Cellule> neighbors;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Cellule(State st){
        this.neighbors = new HashMap<>();
        
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    @Override 
    public abstract State nextState();
    
    public abstract void kill();
    
    public abstract void born();
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public void setNeighbor(Enum direction, Cellule c){
        neighbors.put(direction, c);
    }
    
    public HashMap<Enum,Cellule> getHashMapNeighbors(){
        return this.neighbors;
    }
    
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
