/* 
 * Creation : 1 nov. 2015
 */
package gol;

import java.util.HashMap;



/**
 * @date    1 nov. 2015
 * @author  Anthony CHAFFOT
 */
public class Cellule implements Cell{
    private State state;
    private HashMap<Enum, Cellule> neighbors;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Cellule(LifeState st){
        this.neighbors = new HashMap<>();
        this.state = st;
        
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override // A REFAIRE !
    public State nextState() {
        if(this.state == LifeState.ALIVE){
            if(getNbNeighbors() < Param.NEIGHBORS_MIN_TO_LIVE){
                return LifeState.DEAD;
            } 
            else if(getNbNeighbors() > Param.NEIGHBORS_MAX_TO_LIVE){
                return LifeState.DEAD;
            }
            else{
                return LifeState.ALIVE;
            }
        }
        else{
            if(getNbNeighbors() == Param.NEIGHBORS_MIN_TO_BORN){
                return LifeState.ALIVE;
            }
        }
        return this.state;
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public void setNeighbor(Enum direction, Cellule c){
        neighbors.put(direction, c);
    }
    
    @Override
    public Cell getNeighbor(Enum direction) {
        //Retourne null si il n'y a pas de voisins
        return neighbors.get(direction);
    }

    public int getNbNeighbors(){
        int sum=0;
        for(Enum direction : neighbors.keySet()){
            if(neighbors.get(direction) != null){
                if(neighbors.get(direction).getAlive()){
                    sum++;
                }
            }
        }
        return sum;
    }
    
    public boolean getAlive(){
        return state == LifeState.ALIVE;
    }

    @Override
    public State getState() {
        return this.state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
