package Model;

import Model.Bean.Carrello;
import Model.Bean.Ordine;
import Model.Bean.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {
    public OrdineDAO(){
    }

    public List<Ordine> doRetrieveAllByMail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT utente, data_ordine, n_libri, totale FROM Ordine WHERE utente = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            List<Ordine> ordini = new ArrayList<>();
            while(rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setEmail(rs.getString(1));
                ordine.setDataOrdine(rs.getDate(2));
                ordine.setTotale(rs.getFloat(3));
                ordini.add(ordine);
            }return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Ordine ordine){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Ordine (utente,data_ordine,n_libri,totale) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ordine.getEmail());
            ps.setObject(2,ordine.getDataOrdine());
            ps.setInt(3, ordine.getNumLibri());
            ps.setDouble(4, ordine.getTotale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
