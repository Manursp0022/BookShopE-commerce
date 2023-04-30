package Model;

public class Recensione {
    private String emailUtente;
    private String codiceLibroCartaceo;
    private String descrizione;

    public String getEmailUtente() {
        return emailUtente;
    }

    public void setEmailUtente(String emailUtente) {
        this.emailUtente = emailUtente;
    }

    public String getCodiceLibroCartaceo() {
        return codiceLibroCartaceo;
    }

    public void setCodiceLibroCartaceo(String codiceLibroCartaceo) {
        this.codiceLibroCartaceo = codiceLibroCartaceo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}