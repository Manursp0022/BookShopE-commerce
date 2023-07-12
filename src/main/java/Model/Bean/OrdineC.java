package Model.Bean;

import java.util.Date;

public class OrdineC {
    private int id;
    private String ordine;
    private String libroCartaceo;
    private int numCopie;
    public OrdineC(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrdine() {
        return ordine;
    }

    public void setOrdine(String ordine) {
        this.ordine = ordine;
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
