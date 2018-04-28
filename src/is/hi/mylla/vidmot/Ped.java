
package is.hi.mylla.utlit;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;

/**
 * Abstract klasi sem útfærir peð á mylluborði. Getur fært peð 
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * @date Febrúar 2018
 * Háskóli Íslands
 */
public abstract class Ped {

    protected Shape ped;
    protected MyllaPane bord;

    public Ped(MyllaPane b) {
        bord = b;
    }

    public Shape getPed() {
        return ped;
    }

    /**
     * Færir  peð s í staðsetningu (x,y) í event 
     *
     * @param s    hluturinn sem á að færa 
     * @param event upplýsingar um released atburðurinn - notum (x,y)
     */
    protected abstract void faeraHlut(Shape s, MouseEvent event);

}
