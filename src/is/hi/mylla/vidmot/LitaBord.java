
package is.hi.mylla.utlit;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


/**
 * Klasi sem litar mylluborð með því að búa til nýtt ferningslaga peð sem þekur borðið
 * @author Valgerður Kristinsdóttir vak9@hi.is
 * @date 
 * Háskóli Íslands
 */
public class LitaBord extends Ped {
   
    /**
     * Smíðar ferning á mylluborð nr. i í lit c
     * @param b 
     * @param c Litur
     * @param i Nr. borðs
     */
    public LitaBord (MyllaPane b, Color c, int i) {
            super(b);
            
            int[] hnit = b.bord[i].getHnit();
            ped = new Rectangle(hnit[0], hnit[1], hnit[2], hnit[3]);
            ped.setStroke(c);
            ped.setFill(c);
    }

    @Override
    protected void faeraHlut(Shape s, MouseEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}