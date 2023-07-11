package Model.Bean;

public class LibroCartaceo {

    private String codice;
    private String titolo;
    private String autore;
    private float prezzo;
    private String genere;
    private int quantitaDisp;

    private String descrizione;

    public LibroCartaceo() {

    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getQuantitaDisp() {
        return quantitaDisp;
    }

    public void setQuantitaDisp(int quantitaDisp) {
        this.quantitaDisp = quantitaDisp;
    }

    @Override
    public String toString() {
        return "LibroCartaceo{" +
                "codice='" + codice + '\'' +
                ", titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", prezzo=" + prezzo +
                ", genere='" + genere + '\'' +
                ", quantitaDisp=" + quantitaDisp +
                '}';
    }
}