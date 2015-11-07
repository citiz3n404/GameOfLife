/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
 */
public class CelluleClassique extends Cellule{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public CelluleClassique(LifeState st){
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    @Override
    public State nextState() {
        if(this.state == LifeState.ALIVE){
            if(getNbNeighborsAlive() >= Param.NEIGHBORS_MIN_TO_LIVE && getNbNeighborsAlive() <= Param.NEIGHBORS_MAX_TO_LIVE){
                return LifeState.ALIVE;
            } 
            else{
                return LifeState.DEAD;
            }
        }
        else{
            if(getNbNeighborsAlive() == Param.NEIGHBORS_TO_BORN){
                return LifeState.ALIVE;
            }
        }
        return this.state;
    }
    
    @Override
    public boolean getAlive(){
        return state == LifeState.ALIVE;
    }

    @Override
    public int getNbNeighborsAlive() {
        int sum=0;
        for(Enum direction : neighbors.keySet()){
            if(neighbors.get(direction) != null){
                if(neighbors.get(direction).getState() == LifeState.ALIVE){
                    sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public void kill() {
        state = LifeState.DEAD;
    }

    @Override
    public void born() {
        state = LifeState.ALIVE;
    }

}
