/* 
 * Creation : 7 nov. 2015
 */
package gol;



/**
 * @date    7 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class CelluleDayAndNight extends Cellule {
    
    public int[]survivre = {3, 6, 7, 8};
    public int[] naitre = {3, 4, 6, 7, 8};
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    
    public CelluleDayAndNight(StateLife st){
        super(st);
    }
    
    //**************************************************************************
    // METHODS
    //**************************************************************************
    
    @Override
    public State nextState() {
        if(this.state == StateLife.ALIVE){
            for(int i=0; i<survivre.length; i++){
                if(getNbNeighborsAlive() == survivre[i]) return StateLife.ALIVE;
            }
            return StateLife.DEAD;
        }
        else{
            for(int i=0; i<naitre.length; i++){
                if(getNbNeighborsAlive() == naitre[i]) return StateLife.ALIVE;
            }
        }
        return this.state;
    }

    @Override
    public void kill() {
        state = StateLife.DEAD;
    }

    @Override
    public void born() {
        state = StateLife.ALIVE;
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

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
    public boolean isAlive() {
        return state == StateLife.ALIVE;
    }
    
}
