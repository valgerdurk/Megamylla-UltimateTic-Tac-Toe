
package is.hi.mylla.utlit;

import is.hi.mylla.vinnsla.Mylla;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Aðal viðmótsklasinn fyrir Megamylluna 
 * Teiknar megamylluborðið og minni borðin
 * Býr til peð og meðhöndlar aðgerð þegar peð eru sett á borð
 * 
 * @author Valgerður Kristinsdóttir vak9@hi.is
 * Háskóli Íslands
 */
public class MyllaPane extends Pane {
    
    // Tilviksbreytur
    
    private MyllaAdalController mAdal;  // Breyta fyrir aðal controllerinn
    
    private MylluReitur[] reitur = new MylluReitur[81];  // Fylki fyrir reiti á litlu mylluborðum
    public MylluReitur[] bord = new MylluReitur[9]; // Fylki fyrir reiti (borð) á stóra borði
    private MylluReitur[] storabord = new MylluReitur[1]; // Stóra borðið
    
    // Breytur sem halda utan um fjölda peða á mylluborði 
    private int fjoldiPeda = 0; // Heldur utan um fjölda allra peða á borði
    private int liturABordi = 0; // Index fyrir gula lit á mylluborði 
    private int[] pedABordi = new int[9];//Fylki sem heldur utan um fjölda peða á litlu borðunum
    private int fjoldiPedaStora = 0; // Fjöldi peða á stóra borði
    
    // Breytur sem halda utan um vinninga
    private int[] vinningurABordi = new int[9]; // Heldur utan um fjölda vinninga á stóra borði. 1 = Vinningur. 0 = Ekki vinningur.
    private int leikurUnninn = 0; // Heldur utan um hvort Megamyllan er unnin. 1 = Vinningur. 0 = Ekki vinningur
    
    private int[] visunABordi = new int[9]; // Heldur utan um hvort það er vísun á borði. 1 = Vísun er á borði. 0 = Ekki vísun á borði.
    
    
    // Vinnsluklasinn sem heldur utan um mylluborðið og leikmenn 
    // Fylki fyrir hvert tilvik af myllu á litlu mylluborðunum
    private Mylla[] mittBord = {new Mylla(), new Mylla(), new Mylla(), new Mylla(), new Mylla(), new Mylla(), new Mylla(), new Mylla(), new Mylla()};
    private Mylla storaBord = new Mylla(); //Stóra mylluborðið
    
    
    /**
     * Smiður. Upphafsstillir mylluborð með því að setja hnit reita 
     */
    public MyllaPane () { 
        setjaReiti();
    }
    
    /**
     * Frumstillir mylluborðið. Nýr leikur getur hafist.
     */
    public void nyrLeikur() {
        mAdal.birtaVilluskilaboð("");
        mAdal.leikmadur1.setDisable(false);
        mAdal.leikmadur2.setDisable(false);
        
        //Nýtt tilvik af Myllu búið til fyrir hvert mylluborð
        for(int i = 0; i < mittBord.length; i++) {
            mittBord[i] = new Mylla();
        }
        storaBord = new Mylla();
        
        //Borð hreinsað 
        if(fjoldiPeda > 0)
            while(fjoldiPeda != 0) {
                this.getChildren().remove(fjoldiPeda);
                fjoldiPeda--;
            }
        
        //Teljarar endurstilltir
        for(int v = 0; v < vinningurABordi.length; v++) {
            vinningurABordi[v] = 0;
            visunABordi[v] = 0;
            pedABordi[v] = 0;
        }
        
        //Vísað á öll borð í byrjun
        for(int i = 0; i < visunABordi.length; i++) {
            visaABord(i);
        }
        leikurUnninn = 0;
    }
    
    /**
     * Aðferð sem fær inn hvaða leikmaður er að gera og lætur vinnsluklasann vita
     * Lætur öll tilvik vinnsluklasans vita, þ.e. fyrir öll borðin
     * Kallar á nyttPed fyrir leikmann 
     * @param leikmadur sá leikmaður sem hefur verið valinn
     */
     public void leikmadurGerir(int leikmadur) {
       nyttPed(leikmadur);
       for(int i = 0; i < mittBord.length; i++) {
            mittBord[i].setNuverandiLeikmadur(leikmadur);
        }
       storaBord.setNuverandiLeikmadur(leikmadur);
    }
    
    /**
     * Setur út nýtt peð fyrir leikmann l eða 2 (ferning eða hring) 
     * ef fjöldi peða hefur ekki náð hámarki
     * @param l LEIKMADUR1 eða LEIKMADUR2
     */
    private void nyttPed(int l) {
       Ped s;
        if (l == 1) {
            s = new Ferningur(this, Color.CRIMSON);
        } else {
            s = new Hringur(this, Color.DARKCYAN);
        }

        this.getChildren().add(s.getPed());
        fjoldiPeda++;
    }
    
    /**
     * Aðferð sem teiknar megamylluborðið og öll minni borðin
     * 
     * @param g 
     */
    public void teiknaGrunnbord(GraphicsContext g) {
        g.setStroke(Color.BLACK);
        //Aðal borð
        g.strokeLine(20, 206, 578, 206);
        g.strokeLine(20, 392, 578, 392);
        g.strokeLine(206, 20, 206, 578);
        g.strokeLine(392, 20, 392, 578);
        
        //Mylluborð 1
        g.strokeLine(50, 92, 176, 92);
        g.strokeLine(50, 134, 176, 134);
        g.strokeLine(92, 50, 92, 176);
        g.strokeLine(134, 50, 134, 176);
        
        //Mylluborð 2
        g.strokeLine(236, 92, 362, 92);
        g.strokeLine(236, 134, 362, 134);
        g.strokeLine(278, 50, 278, 176);
        g.strokeLine(320, 50, 320, 176); 
        
        //Mylluborð 3
        g.strokeLine(422, 92, 548, 92);
        g.strokeLine(422, 134, 548, 134);
        g.strokeLine(464, 50, 464, 176);
        g.strokeLine(506, 50, 506, 176); 
        
        //Mylluborð 4
        g.strokeLine(50, 278, 176, 278);
        g.strokeLine(50, 320, 176, 320);
        g.strokeLine(92, 236, 92, 362);
        g.strokeLine(134, 236, 134, 362);
        
        //Mylluborð 5
        g.strokeLine(236, 278, 362, 278);
        g.strokeLine(236, 320, 362, 320);
        g.strokeLine(278, 236, 278, 362);
        g.strokeLine(320, 236, 320, 362); 
        
        //Mylluborð 6
        g.strokeLine(422, 278, 548, 278);
        g.strokeLine(422, 320, 548, 320);
        g.strokeLine(464, 236, 464, 362);
        g.strokeLine(506, 236, 506, 362); 
        
        //Mylluborð 7
        g.strokeLine(50, 464, 176, 464);
        g.strokeLine(50, 506, 176, 506);
        g.strokeLine(92, 422, 92, 548);
        g.strokeLine(134, 422, 134, 548);
        
        //Mylluborð 8
        g.strokeLine(236, 464, 362, 464);
        g.strokeLine(236, 506, 362, 506);
        g.strokeLine(278, 422, 278, 548);
        g.strokeLine(320, 422, 320, 548);
        
        //Mylluborð 9
        g.strokeLine(422, 464, 548, 464);
        g.strokeLine(422, 506, 548, 506);
        g.strokeLine(464, 422, 464, 548);
        g.strokeLine(506, 422, 506, 548);
    }
    
    /**
     * Aðferð sem setur upp reiti á á öllum mylluborðum ásamt megamylluborðinu sjálfu
     * Býr til reit með því að búa til nýjan hlut af klasanum MylluReitur
     */
    public void setjaReiti() {
        //Mylluborð 1 
        reitur[0] = new MylluReitur(50, 50, 42, 42); 
        reitur[1] = new MylluReitur(92, 50, 42, 42);
        reitur[2] = new MylluReitur(134, 50, 42, 42);
        reitur[3] = new MylluReitur(50, 92, 42, 42);
        reitur[4] = new MylluReitur(92, 92, 42, 42);
        reitur[5] = new MylluReitur(134, 92, 42, 42); 
        reitur[6] = new MylluReitur(50, 134, 42, 42);
        reitur[7] = new MylluReitur(92, 134, 42, 42);
        reitur[8] = new MylluReitur(134, 134, 42, 42);
        
        bord[0] = new MylluReitur(50, 50, 125, 125);
        
        //Mylluborð 2
        reitur[9] = new MylluReitur(236, 50, 42, 42); 
        reitur[10] = new MylluReitur(278, 50, 42, 42);
        reitur[11] = new MylluReitur(320, 50, 42, 42);
        reitur[12] = new MylluReitur(236, 92, 42, 42);
        reitur[13] = new MylluReitur(278, 92, 42, 42);
        reitur[14] = new MylluReitur(320, 92, 42, 42); 
        reitur[15] = new MylluReitur(236, 134, 42, 42);
        reitur[16] = new MylluReitur(278, 134, 42, 42);
        reitur[17] = new MylluReitur(320, 134, 42, 42);
        
        bord[1] = new MylluReitur(236, 50, 125, 125);
        
        //Myllubord 3
        reitur[18] = new MylluReitur(422, 50, 42, 42); 
        reitur[19] = new MylluReitur(464, 50, 42, 42);
        reitur[20] = new MylluReitur(506, 50, 42, 42);
        reitur[21] = new MylluReitur(422, 92, 42, 42);
        reitur[22] = new MylluReitur(464, 92, 42, 42);
        reitur[23] = new MylluReitur(506, 92, 42, 42); 
        reitur[24] = new MylluReitur(422, 134, 42, 42);
        reitur[25] = new MylluReitur(464, 134, 42, 42);
        reitur[26] = new MylluReitur(506, 134, 42, 42);
        
        bord[2] = new MylluReitur(422, 50, 125, 125);
        
        //Mylluborð 4
        reitur[27] = new MylluReitur(50, 236, 42, 42); 
        reitur[28] = new MylluReitur(92, 236, 42, 42);
        reitur[29] = new MylluReitur(134, 236, 42, 42);
        reitur[30] = new MylluReitur(50, 278, 42, 42);
        reitur[31] = new MylluReitur(92, 278, 42, 42);
        reitur[32] = new MylluReitur(134, 278, 42, 42); 
        reitur[33] = new MylluReitur(50, 320, 42, 42);
        reitur[34] = new MylluReitur(92, 320, 42, 42);
        reitur[35] = new MylluReitur(134, 320, 42, 42);
        
        bord[3] = new MylluReitur(50, 236, 125, 125);
        
        //Mylluborð 5
        reitur[36] = new MylluReitur(236, 236, 42, 42);
        reitur[37] = new MylluReitur(278, 236, 42, 42);
        reitur[38] = new MylluReitur(320, 236, 42, 42);
        reitur[39] = new MylluReitur(236, 278, 42, 42);
        reitur[40] = new MylluReitur(278, 278, 42, 42);
        reitur[41] = new MylluReitur(320, 278, 42, 42);
        reitur[42] = new MylluReitur(236, 320, 42, 42);
        reitur[43] = new MylluReitur(278, 320, 42, 42);
        reitur[44] = new MylluReitur(320, 320, 42, 42);
        
        bord[4] = new MylluReitur(236, 236, 125, 125);
        
        //Mylluborð 6
        reitur[45] = new MylluReitur(422, 236, 42, 42);
        reitur[46] = new MylluReitur(464, 236, 42, 42);
        reitur[47] = new MylluReitur(506, 236, 42, 42);
        reitur[48] = new MylluReitur(422, 278, 42, 42);
        reitur[49] = new MylluReitur(464, 278, 42, 42);
        reitur[50] = new MylluReitur(506, 278, 42, 42);
        reitur[51] = new MylluReitur(422, 320, 42, 42);
        reitur[52] = new MylluReitur(464, 320, 42, 42);
        reitur[53] = new MylluReitur(506, 320, 42, 42);
        
        bord[5] = new MylluReitur(422, 236, 125, 125);
        
        //Mylluborð 7
        reitur[54] = new MylluReitur(50, 422, 42, 42); 
        reitur[55] = new MylluReitur(92, 422, 42, 42);
        reitur[56] = new MylluReitur(134, 422, 42, 42);
        reitur[57] = new MylluReitur(50, 464, 42, 42);
        reitur[58] = new MylluReitur(92, 464, 42, 42);
        reitur[59] = new MylluReitur(134, 464, 42, 42); 
        reitur[60] = new MylluReitur(50, 506, 42, 42);
        reitur[61] = new MylluReitur(92, 506, 42, 42);
        reitur[62] = new MylluReitur(134, 506, 42, 42);
        
        bord[6] = new MylluReitur(50, 422, 125, 125);
        
        //Mylluborð 8
        reitur[63] = new MylluReitur(236, 422, 42, 42); 
        reitur[64] = new MylluReitur(278, 422, 42, 42);
        reitur[65] = new MylluReitur(320, 422, 42, 42);
        reitur[66] = new MylluReitur(236, 464, 42, 42);
        reitur[67] = new MylluReitur(278, 464, 42, 42);
        reitur[68] = new MylluReitur(320, 464, 42, 42); 
        reitur[69] = new MylluReitur(236, 506, 42, 42);
        reitur[70] = new MylluReitur(278, 506, 42, 42);
        reitur[71] = new MylluReitur(320, 506, 42, 42);
        
        bord[7] = new MylluReitur(236, 422, 125, 125);
        
        //Mylluborð 9
        reitur[72] = new MylluReitur(422, 422, 42, 42); 
        reitur[73] = new MylluReitur(464, 422, 42, 42);
        reitur[74] = new MylluReitur(506, 422, 42, 42);
        reitur[75] = new MylluReitur(422, 464, 42, 42);
        reitur[76] = new MylluReitur(464, 464, 42, 42);
        reitur[77] = new MylluReitur(506, 464, 42, 42); 
        reitur[78] = new MylluReitur(422, 506, 42, 42);
        reitur[79] = new MylluReitur(464, 506, 42, 42);
        reitur[80] = new MylluReitur(506, 506, 42, 42);
        
        bord[8] = new MylluReitur(422, 422, 125, 125);
        
        //Reitur fyrir stóra borð
        storabord[0] = new MylluReitur(50, 50, 498, 498);
    }
    
     /**
     * Athugar á hvaða borði peð er og framkvæmir eftirfarandi aðgerðir fyrir hvert borð
     * Athugar á hvaða reit peð er, hvort peð er þegar á þeim reit setur peðið á
     * þann reit og athugar hvort það er vinningur.
     * Vísar á það borð sem næsti leikmaður á að gera á, og hreinsar gula litinn af síðasta borði.
     * Birtir villuskilaboð ef peð er ekki á löglegum reit
     * Birtir skilaboð og kallar á viðeigandi aðferðir ef borð er fullt eða unnið.
     * 
     * @param x x-gildi hnits
     * @param y y-gildi hnits
     */
    
    public void hvadaReitur (int x, int y){
        for (int j = 0; j < bord.length; j++) {
            if(bord[j].erInnan(x, y)) {
                if(erVinningurABordi(j) || visunABordi[j] == 0) { 
                    System.out.println("Borð er unnið, fyllt eða ekki vísað á");
                    mAdal.birtaVilluskilaboð("Settu peðið á upplýst borð");
                } else {
                    for(int b = 0; b < 9; b++) {
                        for(int i = 1+(9*b-1); i < 9*(b+1); i++) {
                            if(reitur[i].erInnan(x,y)) { 
                                System.out.println("Peð lendir á reit " + i);
                                if(mittBord[b].erThegarABordi(i-(1+(9*b-1))) == true) {
                                    mAdal.birtaVilluskilaboð("Það er peð í þessum reit");
                                } else {
                                    mittBord[b].setjaABord(i-(1+(9*b-1)));
                                    mAdal.birtaVilluskilaboð("");
                                    pedABordi[b]++;
                                    int l = mittBord[0].getNuverandiLeikmadur();
                                    if (l == 1) {
                                        mAdal.leikmadur2.setDisable(false);
                                    } else if (l == 2) {
                                        mAdal.leikmadur1.setDisable(false);
                                    }
                                    hreinsaLitBords();
                                    visaABord(i-(1+(9*b-1)));
                                }
                                if(mittBord[b].vinningur() >= 0) {
                                    int l = mittBord[b].getNuverandiLeikmadur();
                                    if(mAdal.leikmadur1.isSelected()) {
                                        mAdal.birtaVilluskilaboð(mAdal.leikmadur1.getText() + " vann borð nr. " + (j+1));
                                    } else {
                                        mAdal.birtaVilluskilaboð(mAdal.leikmadur2.getText() + " vann borð nr. " + (j+1));
                                    }
                                    breytaLit(l, j);
                                    hreinsaLitBords();
                                    vinningurKominn(j);
                                    if(leikurUnninn == 0)
                                        visaABord(i-(1+(9*b-1)));
                                }
                                if(pedABordi[b] == 9 && mittBord[b].vinningur() < 0) {
                                    mAdal.birtaVilluskilaboð("Allir reitir hafa verið fylltir");
                                }
                            }
                        }
                    }   
                }
            }
        }
        
        if(storabord[0].erInnan(x,y)) {
            System.out.println("Peð lendir á leikborði");
        } else {
            System.out.println("Peð er utan leikborðs");
            mAdal.birtaVilluskilaboð("Peð er utan leikborðs");
        }
    }
    
    /**
     * Aðferð fyrir litlu mylluborðin. Athugar á hvaða borði peð er.
     * @param x x-gildi hnits
     * @param y y-gildi hnits
     */
    public void hvadaBord (int x, int y) {
        for(int i = 0; i < bord.length; i++) {
            if(bord[i].erInnan(x,y)) {
                System.out.println("Peð lendir á borði nr " + i);         
            }
        }
    }
    
    /**
     * Aðferð sem vísar á það borð sem næsti leikmaður má setja peð sitt
     * Vísar á borðið með því að lýsa það upp í gulum lit
     * Ef það er vinningur á borði eða borðið fullt er vísað á öll hin borðin
     * @param i reitur sem peð lenti á = borð sem vísa skal á fyrir næsta leikmann
     */
    public void visaABord(int i) {
        Ped v;
        Ped b;
        
        if(erVinningurABordi(i)) {
            //Lýsir upp öll borð nema það sem vísað er á og þau sem hafa vinning / eru full
            for(int j = 0; j < vinningurABordi.length; j++) {
                if(j == i || vinningurABordi[j] == 1) {
                    continue;
                }
                b = new LitaBord(this, Color.YELLOW.deriveColor(0, 50, 100, 0.3), j);
                this.getChildren().add(b.getPed());
                liturABordi++;
                visunABordi[j] = 1;
            }
                
        } else if(visunABordi[i] == 0) {
            //Lýsir upp borðið sem vísað er á ef það er ekki vísun á því nú þegar
            v = new LitaBord(this, Color.YELLOW.deriveColor(0, 50, 100, 0.3), i);

            this.getChildren().add(v.getPed());
            liturABordi++;
            visunABordi[i] = 1;
        }        
    }
    
    /**
     * Aðferð sem hreinsar gula litinn af mylluborði
     */
    public void hreinsaLitBords() {
        if(liturABordi > 0) {
            while(liturABordi != 0) {
                this.getChildren().remove(liturABordi+(fjoldiPeda-1));
                liturABordi--;
            }
        }
        for(int v = 0; v< visunABordi.length; v++) {
           visunABordi[v] = 0;
        }
    }
    
     /**
     * Aðferð sem breytir lit á mylluborði þegar þau hafa verið unnin
     * @param l nr. leikmanns
     * @param i nr. borðs
     */
    public void breytaLit(int l, int i) {
        //Útfæra fyrir hvert borð
        Ped p;
        if (l == 1) {
            p = new LitaBord(this, Color.CRIMSON.deriveColor(0, 50, 100, 0.3), i);
        } else {
            p = new LitaBord(this, Color.DARKCYAN.deriveColor(0, 50, 100, 0.3), i);
        }

        this.getChildren().add(p.getPed());
        fjoldiPeda++;
    }
    
    /**
     * Heldur utan um vinninga á minni borðunum.
     * Lýkur leik ef vinningur er kominn á stóra borðið.
     * @param i Númer borðs sem vinningurinn kom á 
     */
    public void vinningurKominn(int i) {
        storaBord.setjaABord(i);
        vinningurABordi[i] = 1;
        
        if(storaBord.vinningur() >= 0)  {
        int l = storaBord.getNuverandiLeikmadur();
            if(mAdal.leikmadur1.isSelected()) {
                mAdal.birtaVilluskilaboð(mAdal.leikmadur1.getText() + " vann Megamyllu!");
            } else {
                mAdal.birtaVilluskilaboð(mAdal.leikmadur2.getText() + " vann Megamyllu!");
            }
            mAdal.leikLokid();
            leikurUnninn = 1;
        }
    }
    
    /**
     * Aðferð sem athugar hvort það sé vinningur á borði b
     * Athugar einnig hvort borð b sé fullt
     * @param b borð
     * @return true ef það er vinningur kominn eða borð fullt 
     */
    public boolean erVinningurABordi(int b) {
        return vinningurABordi[b] == 1 || pedABordi[b] == 9;
    }
    
      /***
     * Frumstillir tengingu í aðalcontroller 
     * @param aThis 
     */
    void setAdal(MyllaAdalController aThis) {
        mAdal = aThis;
    }
}
