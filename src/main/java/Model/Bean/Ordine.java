package Model.Bean;

import java.util.Date;

public class Ordine {
    private String email;
    private Date dataOrdine;
    private int numLibri;
    private float totale;

    public Ordine(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public int getNumLibri() {
        return numLibri;
    }

    public void setNumLibri(int numLibri) {
        this.numLibri = numLibri;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
