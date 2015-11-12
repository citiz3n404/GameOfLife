/* 
 * Creation : 11 nov. 2015
 */
package gol;

/**
 * @date 11 nov. 2015
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class CelluleMaths extends Cellule {
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************

    public CelluleMaths(StateDouble st) {
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public State nextState() {
        double res;
        double tmp = getNeighborMoyenne() * ((StateDouble)state).val;
        if(tmp<0.25) {
            res = tmp + 0.5;
        } else if(tmp>0.75){
            res = tmp - 0.5;
        } else {
            res = tmp;
        }
        return new StateDouble(res);
    }

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
    
    public double getNeighborMoyenne(){
        double sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) {
                sum+=((StateDouble)neighbors.get(direction).getState()).val;
            }
        }
        return (sum/getNbNeighborsAlive());
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
