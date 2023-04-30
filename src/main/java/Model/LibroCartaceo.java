package Model;

public class LibroCartaceo {

    private String codice;
    private String titolo;
    private String autore;
    private Double prezzo;
    private String genere;
    private int quantitàDisp;

    public LibroCartaceo() {

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

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public int getQuantitàDisp() {
        return quantitàDisp;
    }

    public void setQuantitàDisp(int quantitàDisp) {
        this.quantitàDisp = quantitàDisp;
    }
}