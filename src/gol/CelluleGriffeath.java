/* 
 * Creation : 7 nov. 2015
 */
package gol;

/**
 * @date 7 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class CelluleGriffeath extends Cellule {
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************

    public CelluleGriffeath(StateInt st) {
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
        if (((StateInt) state).val == 3 && getNbNeighborsStateUp() >= 3) {
            state = new StateInt(0);
        } else {
            if (getNbNeighborsStateUp() >= 3) {
                //((StateInt) state).val += 1;
                state = new StateInt(((StateInt) state).val+1);
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
        if (((StateInt) state).val == 3){
            state = new StateInt(0);
        }
        else{
            state = new StateInt(((StateInt) state).val+1);
        }
    }

    @Override
    public void born() {
        if (((StateInt) state).val == 3){
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
                if(((StateInt) state).val == 3){
                    if(((StateInt) (neighbors.get(direction).getState())).val == 0){
                        sum++;
                    }
                }else if(((StateInt) state).val < 3){
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

    /**
     * 
     * @return In this mode cell's are never really dead, always return true
     */
    @Override
    public boolean isAlive() {
        return true;
    }
}
