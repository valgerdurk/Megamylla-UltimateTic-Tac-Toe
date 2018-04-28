
package is.hi.mylla.utlit;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * Controller fyrir mylluna
 * 
 * @author Valgerður Kristinsdóttir vak9@hi.is
 * Háskóli Íslands
 */
public class MyllaAdalController implements Initializable {
    
    // Tilviksbreytur
    
    @FXML
    private Label skilabod;         // Skilaboð
    @FXML
    private MyllaPane myllaBord;    // Mylluborðið 
    @FXML
    public Canvas mittCanvas;      // Teiknisvæði 
    @FXML
    public RadioButton leikmadur1; // Radio takki fyrr leikmann 1
    @FXML
    public RadioButton leikmadur2; // Radio takki fyrir leikmann 2
    @FXML
    private Pane dialog; // Pane sem inniheldur Dialoginn
    @FXML
    private TextField leikmadur1nafn; // Textareitur fyrir leikmann 1
    @FXML
    private TextField leikmadur2nafn; // Textareitur fyrir leikmann 2
    @FXML
    private Pane reglur; // Pane sem inniheldur leikreglur
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myllaBord.setAdal(this);
        myllaBord.teiknaGrunnbord(mittCanvas.getGraphicsContext2D());
        nofnLeikmanna();
    }   
    
    /**
     * Aðferð sem birtir dialog sem biður um nöfn tveggja leikmanna 
     * Býr til takkann í lagi sem staðfestir nöfn leikmanna
     * Býr til takkann Hætta við
     * Kallar á aðferðina nyrLeikur í MyllaPane
     * 
     */
    public void nofnLeikmanna() {
        DialogPane p = new DialogPane();
        dialog.setVisible(true);
        
        p.setContent(dialog);
        
        Dialog d = new Dialog();
        
        d.setDialogPane(p);
        d.setHeaderText("Skráið nöfn leikmanna");
        d.setTitle("Megamylla");
        
        //í lagi takki búinn til
        ButtonType iLagi = new ButtonType("Í lagi", ButtonBar.ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(iLagi);
        
        final Node stadfestingHnappur = p.lookupButton(iLagi);
        stadfestingHnappur.disableProperty()
                .bind(leikmadur1nafn.textProperty().isEmpty()
                    .or(leikmadur2nafn.textProperty().isEmpty()));
        
        //Hætta takki búinn til
        ButtonType haettaVid = new ButtonType("Hætta við", ButtonBar.ButtonData.CANCEL_CLOSE);
        d.getDialogPane().getButtonTypes().add(haettaVid);
        
        //Dialog birtur, svarið fengið og nýr leikur hafinn
        Optional<ButtonType> utkoma = d.showAndWait();
        if(utkoma.isPresent() && (utkoma.get()
                .getButtonData() == ButtonBar.ButtonData.OK_DONE)) {
            leikmadur1.setText(leikmadur1nafn.getText());
            leikmadur2.setText(leikmadur2nafn.getText());
            myllaBord.nyrLeikur();
        } else {
            System.out.println("Nöfn vantar");
        } 
    }

   /**
     * Birtir villuskilaboð í strengnum s
     *
     * @param s villuskilaboð
     */
    public void birtaVilluskilaboð(String s) {
          skilabod.setText(s);
    }
    
     /** 
     * Atburðarhandler fyrir Nýr leikur valmyndarstakið
     * Ræsir dialog sem biður um nöfn leikmanna og ræsir nýjan leik
     * @param event 
     */
    @FXML
    private void nyrLeikmadur(ActionEvent event) {
        nofnLeikmanna();
    }
    
    /**
     * Atburðarhandler fyrir Radio takka
     * Hefur samband við mylluborð þegar leikmaður er valinn
     * @param event 
     */
    @FXML
    private void leikmadurHandler(ActionEvent event) {
        RadioButton r = (RadioButton)event.getSource();
        System.out.println("Leikmaður: " + r.getText());
         if(leikmadur1.isSelected()==true) {
            myllaBord.leikmadurGerir(1);
            leikmadur2.setDisable(true);
        } else if(leikmadur2.isSelected()==true){
            myllaBord.leikmadurGerir(2);
            leikmadur1.setDisable(true);
        }   
    }
    
    /**
     * Aftengir Radio takka
     */
    public void leikLokid() {
        leikmadur1.setDisable(true);
        leikmadur2.setDisable(true);
    }
    
    /**
     * Atburðarhandler fyrir Leikreglur valmyndarstakið
     * Birtir leikreglur þegar "Leikreglur" er valið
     * @param event 
     */
    @FXML
    private void birtaLeikreglur(ActionEvent event) {
        reglur.setVisible(true);
    }
    
    /**
     * Atburðarhandler fyrir "Loka" takkann á leikreglu-pane
     * Lokar leikreglum þegar smellt er á takkann
     * @param event 
     */
    @FXML
    private void lokaLeikreglum(ActionEvent event) {
        reglur.setVisible(false);
    }
    
    /**
     * Hættir leik og lokar forriti
     * @param event 
     */
    @FXML
    private void haettaLeik(ActionEvent event) {
        Platform.exit();
    }  
}
