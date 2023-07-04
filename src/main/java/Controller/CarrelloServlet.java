package Controller;

import Model.*;
import Model.Bean.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
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
                    CarrelloDAO carrelloDAO = new CarrelloDAO();
                    String codice = req.getParameter("codice");
                    LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
                    List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAll();
                    ContenereCDAO contenereCDAO = new ContenereCDAO();
                    List<ContenereC> contenereC = contenereCDAO.doRetrieveByCart(cart.getUtente());
                    boolean trovatoC = false;
                    boolean outOfStock = false;
                    if(contenereC != null) {
                        for (LibroCartaceo l : libri) {
                            if (codice.equals(l.getCodice())) {
                                for(ContenereC c: contenereC){
                                    if(l.getCodice().equals(c.getLibroCartaceo())){
                                        if(l.getQuantitaDisp() > 0){
                                            l.setQuantitaDisp(l.getQuantitaDisp() - 1);
                                            libroCartaceoDAO.doUpdate(l);
                                            c.setNumCopie(c.getNumCopie() + 1);
                                            trovatoC = true;
                                            contenereCDAO.doUpdate(c);
                                            cart.setnLibri(cart.getnLibri() + 1);
                                            cart.setTotale(cart.getTotale() + l.getPrezzo());
                                            sessione.setAttribute("cart",cart);
                                            carrelloDAO.doUpdate(cart);
                                            break;
                                        }else{
                                            outOfStock = true;
                                            break;
                                        }
                                    }
                                }
                                if(!trovatoC){
                                    if(l.getQuantitaDisp() > 0) {
                                        l.setQuantitaDisp(l.getQuantitaDisp() - 1);
                                        libroCartaceoDAO.doUpdate(l);
                                        ContenereC nuovoC = new ContenereC();
                                        nuovoC.setLibroCartaceo(codice);
                                        nuovoC.setNumCopie(1);
                                        nuovoC.setCarrello(cart.getUtente());
                                        contenereCDAO.doSave(nuovoC);
                                        cart.setnLibri(cart.getnLibri() + 1);
                                        cart.setTotale(cart.getTotale() + l.getPrezzo());
                                        sessione.setAttribute("cart",cart);
                                        carrelloDAO.doUpdate(cart);
                                        break;
                                    }else{
                                        outOfStock = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else{
                        for(LibroCartaceo l : libri){
                            if(codice.equals(l.getCodice())){
                                if(l.getQuantitaDisp() > 0) {
                                    l.setQuantitaDisp(l.getQuantitaDisp() - 1);
                                    libroCartaceoDAO.doUpdate(l);
                                    ContenereC nuovoC = new ContenereC();
                                    nuovoC.setLibroCartaceo(codice);
                                    nuovoC.setNumCopie(1);
                                    nuovoC.setCarrello(cart.getUtente());
                                    contenereCDAO.doSave(nuovoC);
                                    cart.setnLibri(cart.getnLibri() + 1);
                                    cart.setTotale(cart.getTotale() + l.getPrezzo());
                                    sessione.setAttribute("cart",cart);
                                    carrelloDAO.doUpdate(cart);
                                    break;
                                }else{
                                    outOfStock = true;
                                    break;
                                }
                            }
                        }
                    }
                    LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
                    List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAll();
                    ContenereEDAO contenereEDAO = new ContenereEDAO();
                    List<ContenereE> contenereE = contenereEDAO.doRetrieveByCarrello(cart.getUtente());
                    boolean trovatoE = false;
                    if (contenereE != null){
                        for (LibroElettronico l : libriE) {
                            if (codice.equals(l.getCodice())) {
                                for (ContenereE e : contenereE) {
                                    if (codice.equals(e.getLibroElettronico())) {
                                        e.setNumCopie(e.getNumCopie() + 1);
                                        trovatoE = true;
                                        contenereEDAO.doUpdate(e);
                                        cart.setnLibri(cart.getnLibri() + 1);
                                        cart.setTotale(cart.getTotale() + l.getPrezzo());
                                        sessione.setAttribute("cart",cart);
                                        carrelloDAO.doUpdate(cart);
                                        break;
                                    }
                                }
                                if (!trovatoE) {
                                    ContenereE nuovoE = new ContenereE();
                                    nuovoE.setLibroElettronico(codice);
                                    nuovoE.setNumCopie(1);
                                    nuovoE.setCarrello(cart.getUtente());
                                    contenereEDAO.doSave(nuovoE);
                                    cart.setnLibri(cart.getnLibri() + 1);
                                    cart.setTotale(cart.getTotale() + l.getPrezzo());
                                    sessione.setAttribute("cart",cart);
                                    carrelloDAO.doUpdate(cart);
                                    break;
                                }
                            }
                        }
                    }
                    else{
                        for(LibroElettronico l : libriE){
                            if(codice.equals(l.getCodice())){
                                ContenereE nuovoE = new ContenereE();
                                nuovoE.setLibroElettronico(codice);
                                nuovoE.setNumCopie(1);
                                nuovoE.setCarrello(cart.getUtente());
                                contenereEDAO.doSave(nuovoE);
                                cart.setnLibri(cart.getnLibri() + 1);
                                cart.setTotale(cart.getTotale() + l.getPrezzo());
                                sessione.setAttribute("cart",cart);
                                carrelloDAO.doUpdate(cart);
                                break;
                            }
                        }
                    }
                    if (outOfStock) {
                        scrivi.print("-2");
                    }else {
                        scrivi.print(cart.getnLibri());
                    }
                }
            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
