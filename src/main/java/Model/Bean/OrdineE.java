package Model.Bean;

import java.util.Date;

public class OrdineE {
    private String ordine;
    private Date dataOrdine;
    private String libroElettronico;
    private int numCopie;
    public OrdineE(){

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
