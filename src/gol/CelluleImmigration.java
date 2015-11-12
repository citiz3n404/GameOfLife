/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
 * @author  Jessica FAVIN
 */
public class CelluleImmigration extends Cellule{
    int tmpNbNeighbors =0;
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public CelluleImmigration(StateImmigration st){
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public State nextState() {
        if(this.state == StateImmigration.ZOMBIE||this.state == StateImmigration.ALIVE){
            //La cellule prend l'état majoritaire parmis ses voisins
            if(getNbNeighborsAlive() >= Param.NEIGHBORS_MIN_TO_LIVE && getNbNeighborsAlive() <= Param.NEIGHBORS_MAX_TO_LIVE){
                return this.state;
            }
            else{
                return StateImmigration.DEAD;
            }
        }
        else{
            if(getNbNeighborsAlive() == Param.NEIGHBORS_TO_BORN){
                if(moreZombies()) return StateImmigration.ZOMBIE;
                else return StateImmigration.ALIVE;
            }
        }
        return state;
    }

    @Override
    public void kill() {
        if(state==StateImmigration.ALIVE) state = StateImmigration.ZOMBIE;
        else state = StateImmigration.DEAD;
    }

    @Override
    public void born() {
        if(state==StateImmigration.ALIVE) state = StateImmigration.ZOMBIE;
        else state = StateImmigration.ALIVE;
    }
    
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    @Override
    public int getNbNeighborsAlive() {
        int sum=0;
        tmpNbNeighbors =0;
        for(Enum direction : neighbors.keySet()){
            if(neighbors.get(direction) != null){
                tmpNbNeighbors ++;
                if(neighbors.get(direction).getState() == StateImmigration.ALIVE
                        ||neighbors.get(direction).getState() == StateImmigration.ZOMBIE){
                    sum++;
                }
            }
        }
        return sum;
    }
    
    public boolean moreZombies(){
        int alive=0, zombie = 0;
        tmpNbNeighbors =0;
        for(Enum direction : neighbors.keySet()){
            if(neighbors.get(direction) != null){
                tmpNbNeighbors ++;
                if(neighbors.get(direction).getState() == StateImmigration.ALIVE){
                    alive++;
                }
                if(neighbors.get(direction).getState() == StateImmigration.ZOMBIE){
                    zombie++;
                }
            }
        }
        return (zombie>alive);
    }

    @Override
    public boolean isAlive() {
        return (state == StateImmigration.ALIVE || state == StateImmigration.ZOMBIE);
    }

}
