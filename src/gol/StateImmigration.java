/* 
 * Creation : 6 nov. 2015
 */
package gol;



/**
 * State of the cell as ALIVE, ZOMBIE or DEAD
 * @date    6 nov. 2015
 * @author  Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public enum StateImmigration implements State{
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
    ZOMBIE{
        @Override
        public char toChar(){
            return '*';
        }
    }
}
