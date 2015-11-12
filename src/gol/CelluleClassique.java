/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class CelluleClassique extends Cellule{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public CelluleClassique(StateLife st){
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
        if(this.state == StateLife.ALIVE){
            if(getNbNeighborsAlive() >= Param.NEIGHBORS_MIN_TO_LIVE && getNbNeighborsAlive() <= Param.NEIGHBORS_MAX_TO_LIVE){
                return StateLife.ALIVE;
            } 
            else{
                return StateLife.DEAD;
            }
        }
        else{
            if(getNbNeighborsAlive() == Param.NEIGHBORS_TO_BORN){
                return StateLife.ALIVE;
            }
        }
        return this.state;
    }
    
    @Override
    public boolean isAlive(){
        return state == StateLife.ALIVE;
    }

    @Override
    public int getNbNeighborsAlive() {
        int sum=0;
        for(Enum direction : neighbors.keySet()){
            if(neighbors.get(direction) != null){
                if(neighbors.get(direction).getState() == StateLife.ALIVE){
                    sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public void kill() {
        state = StateLife.DEAD;
    }

    @Override
    public void born() {
        state = StateLife.ALIVE;
    }

}
