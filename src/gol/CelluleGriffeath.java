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
        //GriffeathState st = new GriffeathState(0);
        if (((GriffeathState) state).val == Param.ETAT_MAX_GRIFFEAT && getNbNeighborsStateUp() == 3) {
            state = new GriffeathState(0);
        } else {
            if (getNbNeighborsStateUp() == 3) {
                //((GriffeathState) state).val += 1;
                state = new GriffeathState(((GriffeathState) state).val+1);
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
            if (neighbors.get(direction) != null) 
                if(((GriffeathState) state).val == Param.ETAT_MAX_GRIFFEAT){
                    if(((GriffeathState) (neighbors.get(direction).getState())).val == 0){
                        sum++;
                    }
                }else if(((GriffeathState) state).val < 3){
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
