package Model.Bean;

import java.util.Date;

public class OrdineE {
    private int id;
    private String ordine;
    private String libroElettronico;
    private int numCopie;
    public OrdineE(){

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

    public String getLibroElettronico() {
        return libroElettronico;
    }

    public void setLibroElettronico(String libroElettronico) {
        this.libroElettronico = libroElettronico;
    }

    public int getNumCopie() {
        return numCopie;
    }

    public void setNumCopie(int numCopie) {
        this.numCopie = numCopie;
    }
}
