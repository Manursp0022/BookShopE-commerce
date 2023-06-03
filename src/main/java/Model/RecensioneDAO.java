package Model;
import Model.Bean.Recensione;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RecensioneDAO {
    public Recensione doRetrieveByCodiceLibro(String codiceLibro){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT Codice_Libro_cartaceo, email_utente, descrizione FROM Recensione WHERE Codice_Libro_cartaceo = ? ");
            ps.setString(1, codiceLibro);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setCodiceLibroCartaceo(rs.getString(1));
                recensione.setEmailUtente(rs.getString(2));
                recensione.setDescrizione(rs.getString(3));
                return recensione;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Recensione recensione){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Recensione (Codice_Libro_cartaceo,email_utente,descrizione) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, recensione.getCodiceLibroCartaceo());
            ps.setString(2, recensione.getEmailUtente());
            ps.setString(3, recensione.getDescrizione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recensione> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Recensione");
            ResultSet rs = ps.executeQuery();
            List<Recensione> recensiones = new ArrayList<>();
            while (rs.next()) {
                Recensione recensione = new Recensione();
                recensione.setCodiceLibroCartaceo(rs.getString(1));
                recensione.setEmailUtente(rs.getString(2));
                recensione.setDescrizione(rs.getString(3));
                recensiones.add(recensione);
            }
            return recensiones;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}