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
        return new MoyenneState(getNeighborMoyenne());
    }

    @Override
    public void kill() {
        if(((MoyenneState)state).val==0.0) this.state = new MoyenneState(1.0);
        else this.state = new MoyenneState(0.0);
    }

    @Override
    public void born() {
        if(((MoyenneState)state).val==0.0) this.state = new MoyenneState(1.0);
        else this.state = new MoyenneState(0.0);
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
                sum+=((MoyenneState)neighbors.get(direction).getState()).val;
            }
        }
        return (sum/getNbNeighborsAlive());
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
