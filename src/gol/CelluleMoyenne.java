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

    public CelluleMoyenne(SateDouble st) {
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public State nextState() {
        return new SateDouble(getNeighborMoyenne());
    }

    @Override
    public void kill() {
        if(((SateDouble)state).val==0.0) this.state = new SateDouble(1.0);
        else this.state = new SateDouble(0.0);
    }

    @Override
    public void born() {
        if(((SateDouble)state).val==0.0) this.state = new SateDouble(1.0);
        else this.state = new SateDouble(0.0);
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
                sum+=((SateDouble)neighbors.get(direction).getState()).val;
            }
        }
        return (sum/getNbNeighborsAlive());
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
