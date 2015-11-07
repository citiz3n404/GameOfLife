package gol;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

/**
 *
 * @author Anthony CHAFFOT
 * @author Jessica FAVIN
 */
public class Controller implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Label l_generation;

    @FXML
    private Label l_population;

    @FXML
    private Label l_percent;

    @FXML
    private StackPane pane;

    @FXML
    private Button b_stop;

    @FXML
    private Button b_start;

    @FXML
    private Button b_step;

    @FXML
    private Button b_clear;

    @FXML
    private Button b_load;

    @FXML
    private Button b_save;

    @FXML
    private Button b_update;
    
    @FXML
    private ChoiceBox cb_mode;
    
    @FXML
    private ToggleButton b_torique;
    
    @FXML
    private ToggleButton b_immigration;
    
    @FXML
    private ToggleButton b_isotrope;
    
    @FXML
    private Button b_square;
    
    @FXML
    private Button b_hexagon;

    @FXML
    private Slider s_random;
    
    @FXML
    private Slider s_nbRows;
    
    @FXML
    private Label l_nbRows;
    
    @FXML
    private Slider s_nbColumns;
    
    @FXML
    private Label l_nbColumns;

    private Board board;
    private GridPaneDriver display;
    private Timeline loop = null;
    private int generation = 0;
    private FileChooser fileChooser = new FileChooser();
    private int exVal =0;
    
    @FXML 
    private void handleTorique(){
        //Changer les voisins
        if(b_torique.isSelected()){
            Param.IS_TORIQUE = true;
            board.updateNeighbors();
        }
        else{
            Param.IS_TORIQUE = false;
            board.updateNeighbors();
        }
        
    }
    
    private void stopAndClear(){
        if(loop != null){
            handleStop();
        }
        handleClear();
    }
    
    @FXML
    private void handleSquare(){
        b_hexagon.setDisable(false);
        Param.GRID = 1;
        b_square.setDisable(true);
        stopAndClear();
    }
    
    @FXML
    private void handleHexa(){
        b_square.setDisable(false);
        Param.GRID = 2;
        b_hexagon.setDisable(true);
        stopAndClear();
    }

    @FXML
    private void handleLoad(ActionEvent event){
        fileChooser.setTitle("Select a file");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("GoL files", "*.gol"));
        File selectedFile = fileChooser.showOpenDialog(null); 
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            System.out.println(path);
            handleUpdate(new ActionEvent());
            SaveManager.loadBoard(path);
            resetGridView();
            display.displayBoard(board);
        }
    }
    
    @FXML
    private void handleSave(ActionEvent event){
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("GoL files", "*.gol"));
        fileChooser.setInitialFileName("save.gol");
        File selectedFile = fileChooser.showSaveDialog(null); 
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            System.out.println(path);
            SaveManager.saveBoard(path, board);
        }
    }

    @FXML
    private void handleRandom(ActionEvent event) {
        generation = 0;
        board.initRandom(s_random.getValue() / 100);
        display.displayBoard(board);
        l_generation.setText(Integer.toString(generation));
        l_population.setText(Integer.toString(board.getPopulation()));
    }

    @FXML
    private void handleUpdate(ActionEvent event) {
        l_generation.setText(Integer.toString(generation));
        l_population.setText(Integer.toString(board.getPopulation()));
    }

    @FXML
    private void handleStop() {
        loop.stop();

        b_start.setDisable(false);
        b_step.setDisable(false);
        b_clear.setDisable(false);
        b_save.setDisable(false);
        b_load.setDisable(false);
        b_stop.setDisable(true);
    }

    @FXML
    private void handleRun(ActionEvent event) {
        loop = new Timeline(new KeyFrame(Duration.millis(300), e -> {
            board.update();
            display.displayBoard(board);
            generation++;
            l_generation.setText(Integer.toString(generation));
            
            if(exVal > board.getPopulation()){
                l_population.setStyle("-fx-background-color: #E57373;-fx-background-radius:5; -fx-padding:3;");
            }
            else if(exVal == board.getPopulation()){
                l_population.setStyle("-fx-background-color: #BDBDBD; -fx-background-radius:5; -fx-padding:3;");
            }
            else{
                l_population.setStyle("-fx-background-color: #AED581;-fx-background-radius:5; -fx-padding:3;");
            }
            l_population.setText(Integer.toString(board.getPopulation()));
            exVal = board.getPopulation();
        }));
        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
        b_stop.setDisable(false);
        b_start.setDisable(true);
        b_step.setDisable(true);
        b_clear.setDisable(true);
        b_save.setDisable(true);
        b_load.setDisable(true);
    }

    @FXML
    private void handleStep(ActionEvent event) {
        board.update();
        display.displayBoard(board);
        generation++;
        l_generation.setText(Integer.toString(generation));
        l_population.setText(Integer.toString(board.getPopulation()));
    }

    @FXML
    private void handleClear() {
        board = Utils.createNewBoard();
        resetGridView();
        generation = 0;
        l_generation.setText("0");
        l_population.setText("0");
        l_population.setStyle("-fx-background-color: #BDBDBD; -fx-background-radius:5; -fx-padding:3;");
    }
    
    private void resetGridView(){
        if(Param.GRID == 1){
            display = new DisplaySquareGridFX(board);
        }
        else if(Param.GRID == 2){
            display = new DisplayHexaGridFX(board);
        }
        else{
            display = new DisplaySquareGridFX(board);
        }
        pane.getChildren().clear();
        pane.getChildren().add(new Group(display.getPane()));
    }

    private void setSliders(){
        s_random.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
                //System.out.println(new_val.doubleValue());
                l_percent.setText(String.format("%.1f", new_val) + "%");
            }
        });
        s_nbRows.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val){
                int val = new_val.intValue();
                l_nbRows.setText(Integer.toString(val));
                Param.NB_ROWS = val;
                stopAndClear();
            }
        });
        s_nbColumns.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val){
                int val = new_val.intValue();
                l_nbColumns.setText(Integer.toString(val));
                Param.NB_COLUMNS = val;
                stopAndClear();
            }
        });
    }
    
    private void setChoiceBox(){
        cb_mode.setItems(FXCollections.observableArrayList(
                "Classique", "Isotrope","Immigration", "NightAndDay", "HighLife", "Fredkin"));
        cb_mode.getSelectionModel().selectFirst();
        cb_mode.getSelectionModel().selectedItemProperty()
            .addListener((ObservableValue observable, 
                    Object oldValue, Object newValue) -> {
                //label1.setText((String)newValue);
                if(loop != null){
                    handleStop();
                }
                if("Classique".equals((String)newValue)){
                    Param.IS_DAY_AND_NIGHT  = false;
                    Param.IS_GRIFFEAT       = false;
                    Param.IS_HIGHLIFE       = false;
                    Param.IS_IMMIGRATION    = false;
                    Param.IS_ISOTROPE       = false;
                    Param.IS_FREDKIN        = false;
                }else if("Isotrope".equals((String)newValue)){
                    Param.IS_DAY_AND_NIGHT  = false;
                    Param.IS_GRIFFEAT       = false;
                    Param.IS_HIGHLIFE       = false;
                    Param.IS_IMMIGRATION    = false;
                    Param.IS_ISOTROPE       = true;
                    Param.IS_FREDKIN        = false;
                }else if("Immigration".equals((String)newValue)){
                    Param.IS_DAY_AND_NIGHT  = false;
                    Param.IS_GRIFFEAT       = false;
                    Param.IS_HIGHLIFE       = false;
                    Param.IS_IMMIGRATION    = true;
                    Param.IS_ISOTROPE       = false;
                    Param.IS_FREDKIN        = false;
                }else if("NightAndDay".equals((String)newValue)){
                    Param.IS_DAY_AND_NIGHT  = true;
                    Param.IS_GRIFFEAT       = false;
                    Param.IS_HIGHLIFE       = false;
                    Param.IS_IMMIGRATION    = false;
                    Param.IS_ISOTROPE       = false;
                    Param.IS_FREDKIN        = false;
                }else if("HighLife".equals((String)newValue)){
                    Param.IS_DAY_AND_NIGHT  = false;
                    Param.IS_GRIFFEAT       = false;
                    Param.IS_HIGHLIFE       = true;
                    Param.IS_IMMIGRATION    = false;
                    Param.IS_ISOTROPE       = false;
                    Param.IS_FREDKIN        = false;
                }else if("HighLife".equals((String)newValue)){
                    Param.IS_DAY_AND_NIGHT  = false;
                    Param.IS_GRIFFEAT       = false;
                    Param.IS_HIGHLIFE       = false;
                    Param.IS_IMMIGRATION    = false;
                    Param.IS_ISOTROPE       = false;
                    Param.IS_FREDKIN        = true;
                }
                handleClear();
        });
        System.out.println(cb_mode.getValue());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        board = Utils.createNewBoard();
        resetGridView();
        b_stop.setDisable(true);
        setSliders();
        setChoiceBox();
    }

}
