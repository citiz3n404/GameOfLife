/* 
 * Creation : 11 nov. 2015
 */
package gol;

import java.io.Serializable;



/**
 * Cell's state as a double number
 * @date    11 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class StateDouble implements State, Serializable {
    public double val;
    
    public StateDouble(double value){
        this.val = value;
    }
    
    @Override
    public String toString() {
            return ((Double)val).toString();
        }
    
}
