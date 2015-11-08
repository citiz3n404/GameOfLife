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

    public CelluleGriffeath(GriffeathState st) {
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    @Override
    public State nextState() {
        if (((GriffeathState) state).val == Param.ETAT_MAX_GRIFFEAT) {
            ((GriffeathState) state).val = 1;
        } else {
            if(getNbNeighborsStateUp() >= 3){
                ((GriffeathState) state).val += 1;
            }
        }
        return this.state;
    }

    @Override
    public void kill() {
        //Quand on clique sur une cell en vie on passe le state au prochain
        state = nextState();
    }

    @Override
    public void born() {
        // Useless car ne sera jamais invoquée. La grille n'a pas d'état mort
    }

    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************
    public int getNbNeighborsStateUp() {
        int sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) {
                if (((GriffeathState) (neighbors.get(direction).getState())).val == ((GriffeathState) state).val + 1) {
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
