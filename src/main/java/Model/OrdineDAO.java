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
                    con.prepareStatement("SELECT id,utente, data_ordine, n_libri, totale FROM Ordine WHERE utente = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            List<Ordine> ordini = new ArrayList<>();
            while(rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setId(rs.getInt(1));
                ordine.setEmail(rs.getString(2));
                ordine.setDataOrdine(rs.getString(3));
                ordine.setNumLibri(rs.getInt(4));
                ordine.setTotale(rs.getFloat(5));
                ordini.add(ordine);
            }return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int doSave(Ordine ordine, Connection connection){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO Ordine (utente,data_ordine,n_libri,totale) VALUES(?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ordine.getEmail());
            ps.setObject(2,ordine.getDataOrdine());
            ps.setInt(3, ordine.getNumLibri());
            ps.setDouble(4, ordine.getTotale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()){
                return keys.getInt(1);
            }
            else
                return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
