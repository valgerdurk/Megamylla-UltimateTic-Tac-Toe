/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.mylla.vinnsla;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 * @date
 * Háskóli Íslands
 */
public interface MyllaInterface {

    /**
     * Skilar true ef peð er þegar á reit n
     *
     * @param n reitur peðs
     * @return satt ef peð er á reit n annars ósatt
     */
    boolean erThegarABordi(int n);

    /**
     * Get aðferð fyrir tilviksbreytuna nuverandiLeikmadur
     *
     * @return
     */
    int getNuverandiLeikmadur();

    /**
     * Leikmaður n gerir
     *
     * @param n leikmaður
     */
    void setNuverandiLeikmadur(int n);

    /**
     * Setur peð á reit n
     *
     * @param n nr. reits
     */
    void setjaABord(int n);

    /**
     * Skilar tölu á bilinu 0-6 eftir því hvort fannst vinningur á láréttum,
     * lóðréttum eða horn-í-horn
     *
     * @return tala
     */
    int vinningur();
    
}
