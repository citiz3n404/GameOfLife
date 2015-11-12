/* 
 * Creation : 7 nov. 2015
 */
package gol;

import java.io.Serializable;



/**
 * @date    7 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class StateInt implements State, Serializable {
    public int val = 1;
    
    public StateInt(int value){
        this.val = value;
    }
    
    @Override
    public String toString() {
            return ((Integer)val).toString();
        }
    
}
