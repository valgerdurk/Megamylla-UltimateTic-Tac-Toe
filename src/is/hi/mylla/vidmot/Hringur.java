/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package is.hi.mylla.utlit;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Hringur sem erfir frá peði og er á mylluborði. Getur brugðist við drag
 * og release á músinni. Þegar músinni er sleppt er athugað hvort 
 * peðið sé innan mylluborðsins 
 * @author Ebba Þóra Hvannberg ebba@hi.is
 * @author Valgerður Kristinsdóttir vak9@hi.is - Nokkrar breytingar á aðferðum
 * Háskóli Íslands
 */
public class Hringur extends Ped {
    /**
     * Atburðarhandler fyrir þegar músin er dregin 
     * Peðið er fært 
     */
    private final EventHandler<MouseEvent> mouseDragged =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    faeraHlut( (Circle)ped, event);
                }
            };
    
    /***
     * Atburðarhandler fyrir þegar músinni er sleppt 
     * Peðið er sett á mylluborðið 
     */
    private final EventHandler<MouseEvent> mouseReleased =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.out.println (" Mús sleppt ");
                    bord.hvadaReitur((int)((Circle)ped).getCenterX(),
                               (int)((Circle)ped).getCenterY());
                    bord.hvadaBord((int)((Circle)ped).getCenterX(),
                               (int)((Circle)ped).getCenterY());
                }
            };
    
   
    /**
     * Smíðar hring á mylluborð b í lit c
     * @param b 
     * @param c
     */
    public Hringur (MyllaPane b, Color c) {
            super(b);
            ped = new Circle(630, 550, 13);
            ped.setOnMouseDragged(mouseDragged);
            ped.setOnMouseReleased(mouseReleased);
            ped.setStroke(c);
            ped.setFill(c);
    }

    
     /**
     * Færir  hring
     * @param s peðið 
     * @param event upplýsingar um released atburðurinn - notum (x,y)
     */
    @Override
    protected void faeraHlut(Shape s, MouseEvent event) {                                  
        ((Circle)s).setCenterX(event.getX());
        ((Circle)s).setCenterY(event.getY());
    }  

}
