package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContenereEDAO {
    public ContenereE doRetrieveByCarrello(String carrello){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT carrello, libro_elettronico, n_copie FROM Contenere_E WHERE carrello = ? ");
            ps.setString(1, carrello);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ContenereE contenere = new ContenereE();
                contenere.setCarrello(rs.getString(1));
                contenere.setLibroElettronico(rs.getString(2));
                contenere.setNumCopie(rs.getInt(3));
                return contenere;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(ContenereE contenere){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Contenere_E(carrello,libro_elettronico,n_copie) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contenere.getCarrello());
            ps.setString(2, contenere.getLibroElettronico());
            ps.setInt(3, contenere.getNumCopie());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ContenereE> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Contenere_E");
            ResultSet rs = ps.executeQuery();
            List<ContenereE> contenereES = new ArrayList<>();
            while (rs.next()) {
                ContenereE contenereE = new ContenereE();
                contenereE.setCarrello(rs.getString(1));
                contenereE.setLibroElettronico(rs.getString(2));
                contenereE.setNumCopie(rs.getInt(3));
                contenereES.add(contenereE);
            }
            return contenereES;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
