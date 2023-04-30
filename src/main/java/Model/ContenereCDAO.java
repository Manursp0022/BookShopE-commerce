package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContenereCDAO {

    public ContenereC doRetrieveByCart(String carrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT carrello,libro_cartaceo,n_copie FROM Contenere_C WHERE carrello = ?");
            ps.setString(1, carrello);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ContenereC contenere = new ContenereC();
                contenere.setCarrello(rs.getString(1));
                contenere.setLibroCartaceo(rs.getString(2));
                contenere.setNumCopie(rs.getInt(3));
                return contenere;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(ContenereC contenere){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Contenere_C (carrello,libro_cartaceo,n_copie) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contenere.getCarrello());
            ps.setString(2,contenere.getLibroCartaceo());
            ps.setInt(3, contenere.getNumCopie());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContenereC> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Contenere_C");
            ResultSet rs = ps.executeQuery();
            List<ContenereC> contenuti = new ArrayList<>();
            while (rs.next()) {
                ContenereC contenuto = new ContenereC();
                contenuto.setCarrello(rs.getString(1));
                contenuto.setLibroCartaceo(rs.getString(2));
                contenuto.setNumCopie(rs.getInt(3));
                contenuti.add(contenuto);
            }
            return contenuti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
