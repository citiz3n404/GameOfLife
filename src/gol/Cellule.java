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
    private int griffeatState;
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public Cellule(LifeState st){
        this.neighbors = new HashMap<>();
        this.state = st;
        griffeatState = 0; //Griffeath
        
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * Si  x vivante inclus entre NEIGHBORS_MIN_TO_LIVE et NEIGHBORS_MAX_TO_LIVE -> VIE
     * Sinon -> Mort
     * Si x morte inclus entre NEIGHBORS_MIN_TO_BORN et NEIGHBORS_MAX_TO_BORN -> VIE
     * Sinon -> Mort
     * @return 
     */
    @Override 
    public State nextState() {
        if(this.state == LifeState.ALIVE){
            if(getNbNeighbors() >= Param.NEIGHBORS_MIN_TO_LIVE && getNbNeighbors() <= Param.NEIGHBORS_MAX_TO_LIVE){
                return LifeState.ALIVE;
            } 
            else{
                return LifeState.DEAD;
            }
        }
        else{
            if(getNbNeighbors() >= Param.NEIGHBORS_MIN_TO_BORN && getNbNeighbors()<=Param.NEIGHBORS_MAX_TO_BORN){
                return LifeState.ALIVE;
            }
        }
        return this.state;
    }
    
    public int nextGriffeatState(){
        if(griffeatState+1 == Param.ETAT_MAX_GRIFFEAT){
            return 0;
        }
        else return griffeatState +1;
    }
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
    
    public void setGriffeatState(int state){
        this.griffeatState = state;
    }
    
    public int getGriffeatState(){
        return this.griffeatState;
    }

}
