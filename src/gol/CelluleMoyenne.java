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

    public CelluleMoyenne(MoyenneState st) {
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public State nextState() {
        ((MoyenneState)this.state).val = getNeighborMoyenne();
        return this.state;
    }

    @Override
    public void kill() {
        this.state = new MoyenneState(0.0);
    }

    @Override
    public void born() {
        ((MoyenneState)this.state).val = 1.0;
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
        int sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) {
                sum+=((MoyenneState)state).val;
            }
        }
        return (sum/neighbors.size());
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
