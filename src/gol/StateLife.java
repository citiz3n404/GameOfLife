
package gol;

import java.io.Serializable;

/**
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 * @date 30/10/2015
 */
public enum StateLife implements State, Serializable{
    DEAD{
        @Override
        public char toChar() {
            return '.';
        }
    },
    ALIVE{
        @Override
        public char toChar() {
            return 'O';
        }
    }
}
