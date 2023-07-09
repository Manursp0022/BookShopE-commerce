package Model.Bean;

import java.util.Date;

public class OrdineC {
    private String ordine;
    private Date dataOrdine;
    private String libroCartaceo;
    private int numCopie;
    public OrdineC(){

    }

    public String getOrdine() {
        return ordine;
    }

    public void setOrdine(String ordine) {
        this.ordine = ordine;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public String getLibroCartaceo() {
        return libroCartaceo;
    }

    public void setLibroCartaceo(String libroCartaceo) {
        this.libroCartaceo = libroCartaceo;
    }

    public int getNumCopie() {
        return numCopie;
    }

    public void setNumCopie(int numCopie) {
        this.numCopie = numCopie;
    }
}
