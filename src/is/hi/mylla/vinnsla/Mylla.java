/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.mylla.vinnsla;

/**
 *
 * @author Ebba Þóra Hvannberg ebba@hi.is
 *
 * Vinnsluklasi fyrir mylluborð
 *
 */

public class Mylla implements MyllaInterface {

    private static final int LEIKMENN = 2;
    private final int[] Leikmadur = {1, 2};

    int[][] bord = new int[3][3];
    int nuverandiLeikmadur = 1;

    /**
     * Smiður sem býr til vinnsluhlut fyrir myllu. Frumstillir borð þannig að
     * það sé tómt
     */
    public Mylla() {

        // Frumstilla borðið. Ekkert peð er á því 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bord[i][j] = -1;
            }
        }
    }

    /**
     * Get aðferð fyrir tilviksbreytuna nuverandiLeikmadur
     *
     * @return
     */
    public int getNuverandiLeikmadur() {
        return nuverandiLeikmadur;
    }

    /**
     * Leikmaður n gerir
     *
     * @param n leikmaður
     */
    public void setNuverandiLeikmadur(int n) {
        nuverandiLeikmadur = n;
    }

    /**
     * Skilar true ef peð er þegar á reit n
     *
     * @param n reitur peðs
     * @return satt ef peð er á reit n annars ósatt
     */
    public boolean erThegarABordi(int n) {
        for (int i = 0; i < LEIKMENN; i++) {
            if (bord[n / 3][(n % 3)] == Leikmadur[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * Setur peð á reit n
     *
     * @param n nr. reits
     */
    public void setjaABord(int n) {
        bord[n / 3][(n % 3)] = nuverandiLeikmadur;
    }

    /**
     * Skilar tölu á bilinu 0-6 eftir því hvort fannst vinningur á láréttum,
     * lóðréttum eða horn-í-horn
     *
     * @return tala
     */
    public int vinningur() {

        // Athuga hvort vinningur finnst lárétt
        int vinningur;
        for (int i = 0; i < 3; i++) {
            vinningur = i;
            // System.out.println ("Vinningur í lykkju "+vinningur);
            for (int j = 0; j < 3; j++) {
                if (bord[i][j] != nuverandiLeikmadur) {
                    vinningur = -1;
                    break;
                }
            }
            if (vinningur >= 0) {
                return vinningur;
            }
        }

        // Athuga hvort vinningur finnst lóðrétt
        for (int j = 0; j < 3; j++) {
            vinningur = 3 + j;
            for (int i = 0; i < 3; i++) {
                if (bord[i][j] != nuverandiLeikmadur) {
                    vinningur = -1;
                    break;
                }
            }
            if (vinningur >= 0) {
                return vinningur;
            }
        }

        // Athuga hvort vinningur finnst í hornum 
        vinningur = 5;
        for (int i = 0; i < 3; i++) {
            if (bord[i][i] != nuverandiLeikmadur) {
                vinningur = -1;
                break;
            }
        }

        if (vinningur >= 0) {
            return vinningur;
        }

        vinningur = 6;
        for (int i = 0; i < 3; i++) {
            if (bord[i][2 - i] != nuverandiLeikmadur) {
                vinningur = -1;
                break;
            }
        }

        // Skilar -1 ef ekki fannst vinningur, annars tölu á bilinu 0-7
        return vinningur;
    }
}