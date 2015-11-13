/* 
 * Creation : 11 nov. 2015
 */
package gol;

/**
 * @date 11 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class CelluleMoyenne extends Cellule {
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************

    public CelluleMoyenne(StateDouble st) {
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    /**
     * 
     * @return next state of the cell which is the average value in the neighborhood
     */
    @Override
    public State nextState() {
        return new StateDouble(getNeighborMoyenne());
    }

    /**
     * cells are never really dead
     * if the cell has value 0 puts the double value to 1
     * else puts the double value to 0
     */
    @Override
    public void kill() {
        if(((StateDouble)state).val==0.0) this.state = new StateDouble(1.0);
        else this.state = new StateDouble(0.0);
    }

    @Override
    public void born() {
        if(((StateDouble)state).val==0.0) this.state = new StateDouble(1.0);
        else this.state = new StateDouble(0.0);
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    /**
     * cells are never really dead
     * return all the neighbors of the cell
     * @return 
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
     * @return the average value of the cells in the neighborhood
     */
    public double getNeighborMoyenne(){
        double sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) {
                sum+=((StateDouble)neighbors.get(direction).getState()).val;
            }
        }
        return (sum/getNbNeighborsAlive());
    }

    /**
     * Cells are never really dead
     * @return true always
     */
    @Override
    public boolean isAlive() {
        return true;
    }
}
