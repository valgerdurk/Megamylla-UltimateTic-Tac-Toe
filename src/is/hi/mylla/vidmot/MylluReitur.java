
package is.hi.mylla.utlit;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * @author Valgerður Kristinsdóttir vak9@hi.is - Bætti við aðferð getHnit
 *
 * Klasi sem heldur utan um staðsetningu og stærð reits myllunnar
 */
public class MylluReitur {

    private final int xPos;
    private final int yPos;
    private final int breidd;
    private final int haed;

    /**
     * Smiður sem býr til reit á myllu í staðsetningu (x,y) með breidd og hæð
     *
     * @param x
     * @param y
     * @param b breidd
     * @param h hæð
     */
    public MylluReitur(int x, int y, int b, int h) {
        xPos = x;
        yPos = y;
        breidd = b;
        haed = h;
    }

    /**
     * Athugar hvort hnit (x,y) er innan reitsins
     *
     * @param x x-hnit sem er skoðað
     * @param y y-hnit sem er skoðað
     * @return satt ef (x,y) er innan reitsins, annars ósatt
     */
    public boolean erInnan(int x, int y) {
        return (x >= xPos && x <= (xPos + breidd)
                && y >= yPos && y <= (yPos + haed));
    }
    
    /**
     * Nær í og skilar hnitum reits
     * @return hnit reits í fylki
     */
    public int[] getHnit() {
        int[] hnit = {xPos, yPos, breidd, haed};
        return hnit;
    }
}
