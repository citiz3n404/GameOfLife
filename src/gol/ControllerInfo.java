/* 
 * Creation : 10 nov. 2015
 */
package gol;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;



/**
 * @date    10 nov. 2015
 * @author  Anthony CHAFFOT
 */
public class ControllerInfo implements Initializable{
    
    //**************************************************************************
    // CONSTRUCTOR
    //**************************************************************************
    public ControllerInfo(){

    }

    //**************************************************************************
    // METHODS
    //**************************************************************************
    
    /**
     * closes the stage
     */
    @FXML
    private void handleClose(){
        Controller.stage.close();
    }
    //**************************************************************************
    // SETTERS / GETTERS
    //**************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
