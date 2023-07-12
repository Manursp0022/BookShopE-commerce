package Model;

import Model.Bean.LibroCartaceo;
import Model.Bean.LibroElettronico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroElettronicoDAO {
    public LibroElettronico doRetrieveByCode(String codice){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT codice,titolo,autore,prezzo,genere,formato,descrizione FROM Libro_Elettronico WHERE codice = ?");
            ps.setString(1, codice);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                LibroElettronico libro = new LibroElettronico();
                libro.setCodice(rs.getString(1));
                libro.setTitolo(rs.getString(2));
                libro.setAutore(rs.getString(3));
                libro.setPrezzo(rs.getFloat(4));
                libro.setGenere(rs.getString(5));
                libro.setFormato(rs.getString(6));
                libro.setDescrizione(rs.getString(7));
                return libro;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public LibroElettronico doRetrieveByTitle(String titolo){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT codice,titolo,autore,prezzo,genere,formato,descrizione FROM Libro_Elettronico WHERE titolo = ?");
            ps.setString(1, titolo);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                LibroElettronico libro = new LibroElettronico();
                libro.setCodice(rs.getString(1));
                libro.setTitolo(rs.getString(2));
                libro.setAutore(rs.getString(3));
                libro.setPrezzo(rs.getFloat(4));
                libro.setGenere(rs.getString(5));
                libro.setFormato(rs.getString(6));
                libro.setDescrizione(rs.getString(7));
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
                    "INSERT INTO Libro_Elettronico(codice,titolo,autore,prezzo,genere,formato,descrizione) VALUES(?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, libro.getCodice() );
            ps.setString(2, libro.getTitolo());
            ps.setString(3, libro.getAutore());
            ps.setDouble(4, libro.getPrezzo());
            ps.setString(5, libro.getGenere());
            ps.setString(6, libro.getFormato());
            ps.setString(7,libro.getDescrizione());
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
                libro.setPrezzo(rs.getFloat(4));
                libro.setGenere(rs.getString(5));
                libro.setFormato(rs.getString(6));
                libro.setDescrizione(rs.getString(7));
                libri.add(libro);
            }
            return libri;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public  List<LibroElettronico> doRetrieveAllByGender(String genere){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Libro_Elettronico WHERE genere = ?");
            ps.setString(1,genere);
            ResultSet rs = ps.executeQuery();
            List<LibroElettronico> libri = new ArrayList<>();
            while (rs.next()) {
                LibroElettronico libro = new LibroElettronico();
                libro.setCodice(rs.getString(1));
                libro.setTitolo(rs.getString(2));
                libro.setAutore(rs.getString(3));
                libro.setPrezzo(rs.getFloat(4));
                libro.setGenere(rs.getString(5));
                libro.setFormato(rs.getString(6));
                libro.setDescrizione(rs.getString(7));
                libri.add(libro);
            }
            return libri;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doUpdatePrezzo(double prezzo, String codice){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Libro_Elettronico SET prezzo = ? WHERE codice = ?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1,prezzo);
            ps.setString(2,codice);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Update error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void doDelete(String codice){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM Libro_Elettronico WHERE codice = ?",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,codice);

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("Delete error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
