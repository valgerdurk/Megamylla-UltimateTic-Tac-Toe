
package is.hi.mylla.utlit;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * Ferningur sem erfir frá peði og er á mylluborði. Getur brugðist við drag
 * og release á músinni. Þegar músinni er sleppt er athugað hvort 
 * peðið sé innan mylluborðsins 
 * @author Ebba Þóra Hvannberg ebba@hi.is
 * @author Valgerður Kristinsdóttir vak9@hi.is - Nokkrar breytingar á aðferðum
 * Háskóli Íslands
 */
public class Ferningur extends Ped {
    
    /**
     * Atburðarhandler fyrir þegar músin er dregin 
     * Peðið er fært 
     */
    private final EventHandler<MouseEvent> mouseDragged =
            new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    faeraHlut( (Rectangle)ped, event);
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
                    bord.hvadaReitur( (int)((Rectangle)ped).getX(),
                               (int)((Rectangle)ped).getY());
                    bord.hvadaBord ( (int)((Rectangle)ped).getX(),
                               (int)((Rectangle)ped).getY());
                }
            };
    
   
    /**
     * Smíðar ferning á mylluborð b í lit c
     * @param b 
     * @param c
     */
    public Ferningur (MyllaPane b, Color c) {
            super(b);
            ped = new Rectangle(630, 550, 22, 22);
            ped.setOnMouseDragged(mouseDragged);
            ped.setOnMouseReleased(mouseReleased);
            ped.setStroke(c);
            ped.setFill(c);
    }
    
     /**
     * Færir  ferning
     * @param s peðið 
     * @param event upplýsingar um released atburðurinn - notum (x,y)
     */
    @Override
    protected void faeraHlut(Shape s, MouseEvent event) {                                  
        ((Rectangle)s).setX(event.getX());
        ((Rectangle)s).setY(event.getY());
    }  
    
}
