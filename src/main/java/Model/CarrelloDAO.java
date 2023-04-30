package Model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CarrelloDAO {
    public Carrello doRetrieveByEmail(String email){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT utente, n_libri, totale FROM Carrello WHERE utente = ? ");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Carrello carrello = new Carrello();
                carrello.setUtente(rs.getString(1));
                carrello.setnLibri(rs.getInt(2));
                carrello.setTotale(rs.getDouble(3));
                return carrello;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Carrello carrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Carrello (utente,n_libri,totale) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, carrello.getUtente());
            ps.setInt(2, carrello.getnLibri());
            ps.setDouble(3, carrello.getTotale());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Carrello> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Carrello");
            ResultSet rs = ps.executeQuery();
            List<Carrello> carrellos = new ArrayList<>();
            while (rs.next()) {
                Carrello carrello = new Carrello();
                carrello.setUtente(rs.getString(1));
                carrello.setnLibri(rs.getInt(2));
                carrello.setTotale(rs.getDouble(3));
                carrellos.add(carrello);
            }
            return carrellos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}