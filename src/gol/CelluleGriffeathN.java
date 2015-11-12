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
    public CelluleGriffeathN(StateInt st){
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    @Override
    public State nextState() {
        if (((StateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
            if(this.neighbors.size() == 5 && getNbNeighborsStateUp() >= 2) {
                return new StateInt(0);

            }
            if (this.neighbors.size() == 3 && getNbNeighborsStateUp() >= 1) {
                return new StateInt(0);

            }
            if ((getNbNeighborsStateUp() >= 3)) {
                return new StateInt(0);
            }
        } else {
            if(this.neighbors.size() == 5 && getNbNeighborsStateUp() >= 2) {
                return new StateInt(((StateInt) state).val+1);
            }
            if (this.neighbors.size() == 3 && getNbNeighborsStateUp() >= 1) {
                return new StateInt(((StateInt) state).val+1);
            }
            if (getNbNeighborsStateUp() >= 3) {
                return new StateInt(((StateInt) state).val+1);
            }
        }
        
        
        return this.state;  
    }
    
    @Override
    public void kill() {
        if (((StateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
            state = new StateInt(0);
        }
        else{
            state = new StateInt(((StateInt) state).val+1);
        }
    }

    @Override
    public void born() {
        if (((StateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
            state = new StateInt(0);
        }
        else{
            state = new StateInt(((StateInt) state).val+1);
        }
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    public int getNbNeighborsStateUp() {
        int sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) 
                if(((StateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
                    if(((StateInt) (neighbors.get(direction).getState())).val == 0){
                        sum++;
                    }
                }else if(((StateInt) state).val < Param.ETAT_MAX_GRIFFEAT){
                    if(((StateInt) (neighbors.get(direction).getState())).val == ((StateInt) state).val + 1){
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
