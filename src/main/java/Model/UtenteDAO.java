package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    public Utente doRetrieveByUsernamePassword(String email, String password){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT email, username, pswrd, telefono, via, citta, CAP, isAdmin FROM Utente WHERE email = ? AND pswrd =SHA1(?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Utente user = new Utente();
                user.setEmail(rs.getString(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setTelefono(rs.getString(4));
                user.setVia(rs.getString(5));
                user.setCittà(rs.getString(6));
                user.setCAP(rs.getString(7));
                user.setAdmin(rs.getBoolean(8));
                return user;
            }else
                return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Utente utente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Utente (email,username,pswrd,telefono,via,citta,CAP,isAdmin) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, utente.getEmail());
            ps.setString(2, utente.getUsername());
            ps.setString(3, utente.getPassword());
            ps.setString(4,utente.getTelefono());
            ps.setString(5, utente.getVia());
            ps.setString(6, utente.getCittà());
            ps.setString(7, utente.getCAP());
            ps.setBoolean(8, utente.isAdmin());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Utente> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM Utente");
            ResultSet rs = ps.executeQuery();
            List<Utente> utenti = new ArrayList<>();
            while (rs.next()) {
                Utente user = new Utente();
                user.setEmail(rs.getString(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setTelefono(rs.getString(4));
                user.setVia(rs.getString(5));
                user.setCittà(rs.getString(6));
                user.setCAP(rs.getString(7));
                user.setAdmin(rs.getBoolean(8));
                utenti.add(user);
            }
            return utenti;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}