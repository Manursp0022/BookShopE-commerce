package Model.Bean;

public class Carrello {
    private String utente;
    private int nLibri = 0;
    private double totale = 0;

    public String getUtente() {
        return utente;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public int getnLibri() {
        return nLibri;
    }

    public void setnLibri(int nLibri) {
        this.nLibri = nLibri;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }
}