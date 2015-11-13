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
    /**
     * In this type of cell there are two types alive state (ALIVE and ZOMBIE)
     * A new born cell takes the more dominant type
     * A surviving cell keeps its type
     * @return next state of the cell
     */
    @Override
    public State nextState() {
        if(this.state == StateImmigration.ZOMBIE||this.state == StateImmigration.ALIVE){
            //La cellule prend l'état majoritaire parmis ses voisins
            if(getNbNeighborsAlive() >= 2 && getNbNeighborsAlive() <= 3){
                return this.state;
            }
            else{
                return StateImmigration.DEAD;
            }
        }
        else{
            if(getNbNeighborsAlive() == 3){
                if(moreZombies()) return StateImmigration.ZOMBIE;
                else return StateImmigration.ALIVE;
            }
        }
        return state;
    }
    
    /**
     * turn an ALIVE cell to a ZOMBIE cell then to a DEAD cell
     */
    @Override
    public void kill() {
        if(state==StateImmigration.ALIVE) state = StateImmigration.ZOMBIE;
        else state = StateImmigration.DEAD;
    }

    /**
     * turn a DEAD cell to an ALIVE cell
     */
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
    
    /**
     * 
     * @return true if ZOMBIEs are more dominant in the neighborhood
     * false if it's not the case
     */
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
