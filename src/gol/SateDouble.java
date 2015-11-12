/* 
 * Creation : 11 nov. 2015
 */
package gol;

import java.io.Serializable;



/**
 * @date    11 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class SateDouble implements State, Serializable {
    public double val;
    
    public SateDouble(double value){
        this.val = value;
    }
    
    @Override
    public String toString() {
            return ((Double)val).toString();
        }
    
}