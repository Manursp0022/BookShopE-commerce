package Model;
import Model.Bean.PreferitoC;

import java.sql.*;
import java.util.ArrayList;

public class PreferitoCDAO {


    public ArrayList<PreferitoC> doRetrieveByUtente(String utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT utente,libro_cartaceo FROM Preferiti_C WHERE utente = ?");
            ps.setString(1, utente);
            ResultSet rs = ps.executeQuery();
            ArrayList<PreferitoC> preferitoC = new ArrayList<>();
            while (rs.next()) {
                PreferitoC pref = new PreferitoC();
                pref.setUtente(rs.getString(1));
                pref.setLibroCartaceo(rs.getString(2));
                preferitoC.add(pref);
            }
            return preferitoC;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(PreferitoC pref){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Preferiti_C (utente,libro_cartaceo) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pref.getUtente());
            ps.setString(2,pref.getLibroCartaceo());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(PreferitoC pref){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Preferiti_C WHERE utente = ? AND libro_cartaceo = ?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pref.getUtente());
            ps.setString(2,pref.getLibroCartaceo());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}