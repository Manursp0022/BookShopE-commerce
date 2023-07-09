package Model;

import Model.Bean.ContenereC;
import Model.Bean.Ordine;
import Model.Bean.OrdineC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineCDAO {
    public OrdineCDAO(){
    }
    public List<OrdineC> doRetrieveAllByOrdine(Ordine ordine){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT utente_ordine, data_ordine, libro_cartaceo,n_copie FROM Ordine_C WHERE utente_ordine = ? AND data_ordine = ?");
            ps.setString(1, ordine.getEmail());
            ps.setObject(2,ordine.getDataOrdine());
            ResultSet rs = ps.executeQuery();
            ArrayList<OrdineC> ordineC = new ArrayList<>();
            while(rs.next()) {
                OrdineC o = new OrdineC();
                o.setOrdine(rs.getString(1));
                o.setDataOrdine(rs.getDate(2));
                o.setLibroCartaceo(rs.getString(3));
                o.setNumCopie(rs.getInt(4));
                ordineC.add(o);
            }
            return ordineC;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(OrdineC ordineC){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Ordine_C (utente_ordine,data_ordine,libro_cartaceo,n_copie) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, ordineC.getOrdine());
            ps.setObject(2,ordineC.getDataOrdine());
            ps.setString(3,ordineC.getLibroCartaceo());
            ps.setInt(4, ordineC.getNumCopie());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
