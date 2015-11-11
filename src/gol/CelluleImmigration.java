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
    public CelluleImmigration(ImmigrationState st){
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public State nextState() {
        if(this.state == ImmigrationState.ZOMBIE||this.state == ImmigrationState.ALIVE){
            //La cellule prend l'état majoritaire parmis ses voisins
            if(getNbNeighborsAlive() >= Param.NEIGHBORS_MIN_TO_LIVE && getNbNeighborsAlive() <= Param.NEIGHBORS_MAX_TO_LIVE){
                return this.state;
            }
            else{
                return ImmigrationState.DEAD;
            }
        }
        else{
            if(getNbNeighborsAlive() == Param.NEIGHBORS_TO_BORN){
                if(moreZombies()) return ImmigrationState.ZOMBIE;
                else return ImmigrationState.ALIVE;
            }
        }
        return state;
    }

    @Override
    public void kill() {
        if(state==ImmigrationState.ALIVE) state = ImmigrationState.ZOMBIE;
        else state = ImmigrationState.DEAD;
    }

    @Override
    public void born() {
        if(state==ImmigrationState.ALIVE) state = ImmigrationState.ZOMBIE;
        else state = ImmigrationState.ALIVE;
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
                if(neighbors.get(direction).getState() == ImmigrationState.ALIVE
                        ||neighbors.get(direction).getState() == ImmigrationState.ZOMBIE){
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
                if(neighbors.get(direction).getState() == ImmigrationState.ALIVE){
                    alive++;
                }
                if(neighbors.get(direction).getState() == ImmigrationState.ZOMBIE){
                    zombie++;
                }
            }
        }
        return (zombie>alive);
    }

    @Override
    public boolean isAlive() {
        return (state == ImmigrationState.ALIVE || state == ImmigrationState.ZOMBIE);
    }

}
