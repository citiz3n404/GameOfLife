/* 
 * Creation : 11 nov. 2015
 */
package gol;



/**
 * @date    11 nov. 2015
 * @author  Anthony CHAFFOT
 */
public class CelluleGriffeathN extends Cellule{
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public CelluleGriffeathN(GriffeathState st){
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    @Override
    public State nextState() {
        if (((GriffeathState) state).val == Param.ETAT_MAX_GRIFFEAT && getNbNeighborsStateUp() == 3) {
            state = new GriffeathState(0);
        } else {
            if (getNbNeighborsStateUp() == 3) {
                state = new GriffeathState(((GriffeathState) state).val+1);
            }
        }
        return this.state;
    }

    @Override
    public void kill() {
        if (((GriffeathState) state).val == Param.ETAT_MAX_GRIFFEAT){
            state = new GriffeathState(0);
        }
        else{
            state = new GriffeathState(((GriffeathState) state).val+1);
        }
    }

    @Override
    public void born() {
        if (((GriffeathState) state).val == Param.ETAT_MAX_GRIFFEAT){
            state = new GriffeathState(0);
        }
        else{
            state = new GriffeathState(((GriffeathState) state).val+1);
        }
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    public int getNbNeighborsStateUp() {
        int sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) 
                if(((GriffeathState) state).val == Param.ETAT_MAX_GRIFFEAT){
                    if(((GriffeathState) (neighbors.get(direction).getState())).val == 0){
                        sum++;
                    }
                }else if(((GriffeathState) state).val < Param.ETAT_MAX_GRIFFEAT){
                    if(((GriffeathState) (neighbors.get(direction).getState())).val == ((GriffeathState) state).val + 1){
                        sum++;
                    }
                }
            }
        
        return sum;
    }

    @Override
    public int getNbNeighborsAlive() {
        int sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) {
                sum++;
            }
        }
        return sum;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

}
