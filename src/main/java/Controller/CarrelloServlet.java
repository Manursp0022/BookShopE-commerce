package Controller;

import Model.Bean.*;
import Model.ContenereCDAO;
import Model.ContenereEDAO;
import Model.LibroCartaceoDAO;
import Model.LibroElettronicoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "CarrelloServlet", value = "/carrelloservlet")
public class CarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession sessione = req.getSession(false);
        PrintWriter scrivi = resp.getWriter();
        if (sessione != null) {
            Utente user = (Utente) sessione.getAttribute("utente");
            if (user == null) {
                resp.setContentType("application/JSON");
                scrivi.println("-1");
            } else {
                Carrello cart = (Carrello) sessione.getAttribute("cart");
                cart.setnLibri(cart.getnLibri() + 1);
                String codice = req.getParameter("codice");
                LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
                LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAll();
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAll();
                ContenereCDAO contenereCDAO = new ContenereCDAO();
                List<ContenereC> contenereC = contenereCDAO.doRetrieveByCart(cart.getUtente());
                List<String> codiciContc = new ArrayList<>();
                for (ContenereC cont : contenereC) {
                    codiciContc.add(cont.getLibroCartaceo());
                }
                ContenereEDAO contenereEDAO = new ContenereEDAO();
                List<ContenereE> contenereE = contenereEDAO.doRetrieveByCarrello(cart.getUtente());
                List<String> codiciConte = new ArrayList<>();
                for (ContenereE cont : contenereE) {
                    codiciConte.add(cont.getLibroElettronico());
                }
                for (LibroCartaceo c : libri) {
                    if (codice.equals(c.getCodice())) {
                        if(codiciContc.contains(c.getCodice())){

                        }
                    }
                }
                for (LibroElettronico e : libriE) {
                    if (codice.equals(e.getCodice())) {
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
