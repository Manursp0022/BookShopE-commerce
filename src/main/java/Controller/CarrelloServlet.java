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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) {
            HttpSession sessione = req.getSession(false);
            PrintWriter scrivi = resp.getWriter();
            resp.setContentType("text/html");
            if (sessione != null) {
                Utente user = (Utente) sessione.getAttribute("utente");
                if (user == null) {
                    scrivi.print("-1");
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
                    boolean trovatoC = false;
                    boolean trovatoE = false;
                    for (ContenereC cont : contenereC) {
                        codiciContc.add(cont.getLibroCartaceo());
                    }
                    ContenereEDAO contenereEDAO = new ContenereEDAO();
                    List<ContenereE> contenereE = contenereEDAO.doRetrieveByCarrello(cart.getUtente());
                    List<String> codiciConte = new ArrayList<>();
                    for (ContenereE cont : contenereE) {
                        codiciConte.add(cont.getLibroElettronico());
                    }
                    for (LibroCartaceo l : libri) {
                        if (codice.equals(l.getCodice())) {
                            for(ContenereC c: contenereC){
                                if(l.getCodice().equals(c.getLibroCartaceo())){
                                    c.setNumCopie(c.getNumCopie() + 1);
                                    trovatoC = true;
                                    contenereCDAO.doUpdate(c);
                                    break;
                                }
                            }
                            if(!trovatoC){
                                ContenereC nuovoC = new ContenereC();
                                nuovoC.setLibroCartaceo(codice);
                                nuovoC.setNumCopie(1);
                                nuovoC.setCarrello(cart.getUtente());
                                contenereCDAO.doSave(nuovoC);
                            }
                        }
                    }
                    for (LibroElettronico l : libriE) {
                        if (codice.equals(l.getCodice())) {
                            for(ContenereE e: contenereE){
                                if(l.getCodice().equals(e.getLibroElettronico())){
                                    e.setNumCopie(e.getNumCopie() + 1);
                                    trovatoE = true;
                                    contenereEDAO.doUpdate(e);
                                    break;
                                }
                            }
                            if(!trovatoE){
                                ContenereE nuovoE = new ContenereE();
                                nuovoE.setLibroElettronico(codice);
                                nuovoE.setNumCopie(1);
                                nuovoE.setCarrello(cart.getUtente());
                                contenereEDAO.doSave(nuovoE);
                            }
                        }
                    }
                    scrivi.print(cart.getnLibri());
                }
            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
