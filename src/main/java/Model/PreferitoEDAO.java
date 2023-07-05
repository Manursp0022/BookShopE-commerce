package Model;
import Model.Bean.PreferitoE;

import java.sql.*;
import java.util.ArrayList;

public class PreferitoEDAO {


    public ArrayList<PreferitoE> doRetrieveByUtente(String utente) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT utente,libro_elettronico FROM Preferiti_E WHERE utente = ?");
            ps.setString(1, utente);
            ResultSet rs = ps.executeQuery();
            ArrayList<PreferitoE> preferitoE = new ArrayList<>();
            while (rs.next()) {
                PreferitoE pref = new PreferitoE();
                pref.setUtente(rs.getString(1));
                pref.setLibroElettronico(rs.getString(2));
                preferitoE.add(pref);
            }
            return preferitoE;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(PreferitoE pref){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Preferiti_E (utente,libro_elettronico) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pref.getUtente());
            ps.setString(2,pref.getLibroElettronico());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(PreferitoE pref){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Preferiti_E WHERE utente = ? AND libro_elettronico = ?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pref.getUtente());
            ps.setString(2,pref.getLibroElettronico());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}