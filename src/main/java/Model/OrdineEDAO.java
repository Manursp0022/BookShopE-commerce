package Model;

import Model.Bean.Ordine;
import Model.Bean.OrdineC;
import Model.Bean.OrdineE;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineEDAO {
    public OrdineEDAO(){

    }
    public List<OrdineE> doRetrieveAllByOrdine(Ordine ordine){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT id_ordine,utente_ordine, libro_elettronico,n_copie FROM Ordine_E WHERE id_ordine = ?");
            ps.setInt(1, ordine.getId());
            ResultSet rs = ps.executeQuery();
            ArrayList<OrdineE> ordineE = new ArrayList<>();
            while(rs.next()) {
                OrdineE o = new OrdineE();
                o.setId(rs.getInt(1));
                o.setOrdine(rs.getString(2));
                o.setLibroElettronico(rs.getString(3));
                o.setNumCopie(rs.getInt(4));
                ordineE.add(o);
            }
            return ordineE;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(OrdineE ordineE){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO Ordine_E (id_ordine,utente_ordine,libro_elettronico,n_copie) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,ordineE.getId());
            ps.setString(2, ordineE.getOrdine());
            ps.setString(3,ordineE.getLibroElettronico());
            ps.setInt(4, ordineE.getNumCopie());

            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
