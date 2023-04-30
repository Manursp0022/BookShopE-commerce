package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroElettronicoDAO {
    public LibroElettronico doRetrieveByUsernamePassword(String codice){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT codice,titolo,autore,prezzo,genere,formato FROM Libro_Elettronico WHERE codice = ?");
            ps.setString(1, codice);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                LibroElettronico libro = new LibroElettronico();
                libro.setCodice(rs.getString(1));
                libro.setTitolo(rs.getString(2));
                libro.setAutore(rs.getString(3));
                libro.setPrezzo(rs.getDouble(4));
                libro.setGenere(rs.getString(5));
                libro.setFormato(rs.getString(6));

                return libro;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(LibroElettronico libro){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Libro_Elettronico(codice,titolo,autore,prezzo,genere,formato) VALUES(?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, libro.getCodice() );
            ps.setString(2, libro.getTitolo());
            ps.setString(3, libro.getAutore());
            ps.setDouble(4, libro.getPrezzo());
            ps.setString(5, libro.getGenere());
            ps.setString(6, libro.getFormato());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LibroElettronico> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Libro_Elettronico");
            ResultSet rs = ps.executeQuery();
            List<LibroElettronico> libri = new ArrayList<>();
            while (rs.next()) {
               LibroElettronico libro = new LibroElettronico();
                libro.setCodice(rs.getString(1));
                libro.setTitolo(rs.getString(2));
                libro.setAutore(rs.getString(3));
                libro.setPrezzo(rs.getDouble(4));
                libro.setGenere(rs.getString(5));
                libro.setFormato(rs.getString(6));
                libri.add(libro);
            }
            return libri;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}