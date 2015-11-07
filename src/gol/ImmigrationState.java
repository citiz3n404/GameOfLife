/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
 */
public enum ImmigrationState implements State{
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
    },
    BORN{
        @Override
        public char toChar(){
            return '*';
        }
    }
}
