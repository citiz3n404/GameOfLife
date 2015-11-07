
package gol;

/**
 * @author Anthony CHAFFOT
 * @date 30/10/2015
 */
public enum LifeState implements State{
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
