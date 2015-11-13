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
    /**
     * 
     * @return next state of the cell (increments the int value of the cell)
     */
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
    
    /**
     * in this mode kill increments the int value of the cell
     * used when the cell is clicked on in the GUI
     */
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
    /**
     * 
     * @return sum of neighbors that are in the next state (=value of the cell + 1)
     */
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

    /**
     * 
     * @return the number of neighbors alive 
     */
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
    
    /**
     * 
     * @return In this mode cell's are never really dead, always return true
     */
    @Override
    public boolean isAlive() {
        return true;
    }

}
