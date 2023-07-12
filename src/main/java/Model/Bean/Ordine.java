package Model.Bean;

import java.util.Date;

public class Ordine {
    private int id;
    private String email;
    private String dataOrdine;
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

    public String getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(String dataOrdine) {
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
    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
