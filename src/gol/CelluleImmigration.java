/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
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
        ImmigrationState st = ImmigrationState.DEAD;
        if(this.state == ImmigrationState.BORN){
            //La cellule prend l'état majoritaire parmis ses voisins
            if((double)getNbNeighborsAlive() >= (double)(tmpNbNeighbors/2)){
                st = ImmigrationState.ALIVE;
            }
            else{
                st = ImmigrationState.DEAD;
            }
        }
        else if(this.state == ImmigrationState.ALIVE){
            if(getNbNeighborsAlive() >= Param.NEIGHBORS_MIN_TO_LIVE && getNbNeighborsAlive() <= Param.NEIGHBORS_MAX_TO_LIVE){
                st = ImmigrationState.ALIVE;
            }
            else{
                st = ImmigrationState.DEAD;
            }
        }
        else{
            if(getNbNeighborsAlive() == Param.NEIGHBORS_TO_BORN){
                st = ImmigrationState.BORN;
            }
        }
        return st;
    }

    @Override
    public void kill() {
        state = ImmigrationState.DEAD;
    }

    @Override
    public void born() {
        state = ImmigrationState.BORN;
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
                if(neighbors.get(direction).getState() == ImmigrationState.ALIVE){
                    sum++;
                }
            }
        }
        return sum;
    }

    @Override
    public boolean getAlive() {
        if(state == ImmigrationState.ALIVE || state == ImmigrationState.BORN){
            return true;
        }
        return false;
    }

}
