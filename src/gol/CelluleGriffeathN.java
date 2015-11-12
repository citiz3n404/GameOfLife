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
    public CelluleGriffeathN(SateInt st){
        super(st);
    }

    //**************************************************************************
    // METHODS
    //**************************************************************************

    @Override
    public State nextState() {
        if (((SateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
            if(this.neighbors.size() == 5 && getNbNeighborsStateUp() >= 2) {
                return new SateInt(0);

            }
            if (this.neighbors.size() == 3 && getNbNeighborsStateUp() >= 1) {
                return new SateInt(0);

            }
            if ((getNbNeighborsStateUp() >= 3)) {
                return new SateInt(0);
            }
        } else {
            if(this.neighbors.size() == 5 && getNbNeighborsStateUp() >= 2) {
                return new SateInt(((SateInt) state).val+1);
            }
            if (this.neighbors.size() == 3 && getNbNeighborsStateUp() >= 1) {
                return new SateInt(((SateInt) state).val+1);
            }
            if (getNbNeighborsStateUp() >= 3) {
                return new SateInt(((SateInt) state).val+1);
            }
        }
        
        
        return this.state;  
    }
    
    @Override
    public void kill() {
        if (((SateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
            state = new SateInt(0);
        }
        else{
            state = new SateInt(((SateInt) state).val+1);
        }
    }

    @Override
    public void born() {
        if (((SateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
            state = new SateInt(0);
        }
        else{
            state = new SateInt(((SateInt) state).val+1);
        }
    }
    
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    public int getNbNeighborsStateUp() {
        int sum = 0;
        for (Enum direction : neighbors.keySet()) {
            if (neighbors.get(direction) != null) 
                if(((SateInt) state).val == Param.ETAT_MAX_GRIFFEAT){
                    if(((SateInt) (neighbors.get(direction).getState())).val == 0){
                        sum++;
                    }
                }else if(((SateInt) state).val < Param.ETAT_MAX_GRIFFEAT){
                    if(((SateInt) (neighbors.get(direction).getState())).val == ((SateInt) state).val + 1){
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
