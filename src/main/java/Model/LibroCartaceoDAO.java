package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroCartaceoDAO {

    public LibroCartaceo doRetrieveByCode(String code){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT codice,titolo,autore,prezzo,genere,quantita_disp FROM LibroCartaceo WHERE codice = ?");
            ps.setString(1,code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                LibroCartaceo cartaceo = new LibroCartaceo();
                cartaceo.setCodice(rs.getString(1));
                cartaceo.setTitolo(rs.getString(2));
                cartaceo.setAutore(rs.getString(3));
                cartaceo.setPrezzo(rs.getDouble(4));
                cartaceo.setGenere(rs.getString(5));
                cartaceo.setQuantitàDisp(rs.getInt(6));
                return cartaceo;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(LibroCartaceo cartaceo){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO LibroCartaceo (codice,titolo,autore,prezzo,genere,quantita_disp) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cartaceo.getCodice());
            ps.setString(2,cartaceo.getTitolo());
            ps.setString(3, cartaceo.getAutore());
            ps.setDouble(4, cartaceo.getPrezzo());
            ps.setString(5, cartaceo.getGenere());
            ps.setInt(6, cartaceo.getQuantitàDisp());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LibroCartaceo> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Libro_Cartaceo");
            ResultSet rs = ps.executeQuery();
            List<LibroCartaceo> cartacei = new ArrayList<>();
            while (rs.next()) {
                LibroCartaceo cartaceo = new LibroCartaceo();
                cartaceo.setCodice(rs.getString(1));
                cartaceo.setTitolo(rs.getString(2));
                cartaceo.setAutore(rs.getString(3));
                cartaceo.setPrezzo(rs.getDouble(4));
                cartaceo.setGenere(rs.getString(5));
                cartaceo.setQuantitàDisp(rs.getInt(6));
                cartacei.add(cartaceo);
            }
            return cartacei;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}