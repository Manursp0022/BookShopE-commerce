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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet (name = "CarrelloServlet", value = "/carrelloservlet")
public class CarrelloServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        synchronized (this) {
            HttpSession sessione = req.getSession(false);
            PrintWriter scrivi = resp.getWriter();
            int mode = (int) sessione.getAttribute("mode");
            resp.setContentType("text/html");
            Carrello cart = (Carrello) sessione.getAttribute("cart");
            String codice = req.getParameter("codice");
            LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
            List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAll();
            LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
            List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAll();
            List<ContenereC> contenereC = (List<ContenereC>) sessione.getAttribute("contenereC");
            List<ContenereE> contenereE = (List<ContenereE>) sessione.getAttribute("contenereE");
            boolean trovatoC = false;
            boolean trovatoE = false;
            boolean outOfStock = false;
            int numCopie = 0;
            if(mode == 3) {
                if(!contenereC.isEmpty()){
                    for (LibroCartaceo l : libri) {
                        if (codice.equals(l.getCodice())) {
                            for(ContenereC c: contenereC){
                                if(l.getCodice().equals(c.getLibroCartaceo())){
                                    if(l.getQuantitaDisp() > 0){
                                        if(c.getNumCopie() + 1 > l.getQuantitaDisp()) {
                                            outOfStock = true;
                                            trovatoC = true;
                                            break;
                                        }
                                        c.setNumCopie(c.getNumCopie() + 1);
                                        numCopie = c.getNumCopie();
                                        sessione.setAttribute("contenereC",contenereC);
                                        trovatoC = true;
                                        cart.setnLibri(cart.getnLibri() + 1);
                                        cart.setTotale(cart.getTotale() + l.getPrezzo());
                                        sessione.setAttribute("cart",cart);
                                        break;
                                    }
                                }
                            }
                            if(!trovatoC){
                                if(l.getQuantitaDisp() > 0) {
                                    ContenereC nuovoC = new ContenereC();
                                    nuovoC.setLibroCartaceo(codice);
                                    nuovoC.setNumCopie(1);
                                    numCopie = 1;
                                    nuovoC.setCarrello(cart.getUtente());
                                    contenereC.add(nuovoC);
                                    sessione.setAttribute("contenereC",contenereC);
                                    cart.setnLibri(cart.getnLibri() + 1);
                                    cart.setTotale(cart.getTotale() + l.getPrezzo());
                                    sessione.setAttribute("cart",cart);
                                    break;
                                }
                            }
                        }
                    }
                }else{
                    for(LibroCartaceo l : libri){
                        if(codice.equals(l.getCodice())){
                            if(l.getQuantitaDisp() > 0) {
                                ContenereC nuovoC = new ContenereC();
                                nuovoC.setLibroCartaceo(codice);
                                nuovoC.setNumCopie(1);
                                numCopie = 1;
                                nuovoC.setCarrello(cart.getUtente());
                                contenereC.add(nuovoC);
                                sessione.setAttribute("contenereC",contenereC);
                                cart.setnLibri(cart.getnLibri() + 1);
                                cart.setTotale(cart.getTotale() + l.getPrezzo());
                                sessione.setAttribute("cart",cart);
                                break;
                            }
                        }
                    }
                }
                if(!contenereE.isEmpty()){
                    for (LibroElettronico l : libriE) {
                        if (codice.equals(l.getCodice())) {
                            for (ContenereE e : contenereE) {
                                if (codice.equals(e.getLibroElettronico())) {
                                    e.setNumCopie(e.getNumCopie() + 1);
                                    numCopie = e.getNumCopie();
                                    trovatoE = true;
                                    sessione.setAttribute("contenereE",contenereE);
                                    cart.setnLibri(cart.getnLibri() + 1);
                                    cart.setTotale(cart.getTotale() + l.getPrezzo());
                                    sessione.setAttribute("cart",cart);
                                    break;
                                }
                            }
                            if (!trovatoE) {
                                ContenereE nuovoE = new ContenereE();
                                nuovoE.setLibroElettronico(codice);
                                nuovoE.setNumCopie(1);
                                numCopie = 1;
                                nuovoE.setCarrello(cart.getUtente());
                                contenereE.add(nuovoE);
                                sessione.setAttribute("contenereE",contenereE);
                                cart.setnLibri(cart.getnLibri() + 1);
                                cart.setTotale(cart.getTotale() + l.getPrezzo());
                                sessione.setAttribute("cart",cart);
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
                            numCopie = 1;
                            nuovoE.setCarrello(cart.getUtente());
                            contenereE.add(nuovoE);
                            sessione.setAttribute("contenereE",contenereE);
                            cart.setnLibri(cart.getnLibri() + 1);
                            cart.setTotale(cart.getTotale() + l.getPrezzo());
                            sessione.setAttribute("cart",cart);
                            break;
                        }
                    }
                }
                if (outOfStock) {
                    scrivi.print("-2");
                }else {
                    String risposta = cart.getnLibri() + "-" + numCopie + "-" + cart.getTotale();
                    scrivi.print(risposta);
                }
            }else {
                CarrelloDAO carrelloDAO = new CarrelloDAO();
                ContenereCDAO contenereCDAO = new ContenereCDAO();
                if(contenereC != null) {
                    for (LibroCartaceo l : libri) {
                        if (codice.equals(l.getCodice())) {
                            for(ContenereC c: contenereC){
                                if(l.getCodice().equals(c.getLibroCartaceo())){
                                    if(l.getQuantitaDisp() > 0){
                                        if(c.getNumCopie() + 1 > l.getQuantitaDisp()) {
                                            outOfStock = true;
                                            trovatoC = true;
                                            break;
                                        }
                                        c.setNumCopie(c.getNumCopie() + 1);
                                        numCopie = c.getNumCopie();
                                        trovatoC = true;
                                        contenereCDAO.doUpdate(c);
                                        sessione.setAttribute("contenereC",contenereC);
                                        cart.setnLibri(cart.getnLibri() + 1);
                                        cart.setTotale(cart.getTotale() + l.getPrezzo());
                                        sessione.setAttribute("cart",cart);
                                        carrelloDAO.doUpdate(cart);
                                        break;
                                    }
                                }
                            }
                            if(!trovatoC){
                                if(l.getQuantitaDisp() > 0) {
                                    ContenereC nuovoC = new ContenereC();
                                    nuovoC.setLibroCartaceo(codice);
                                    nuovoC.setNumCopie(1);
                                    numCopie = 1;
                                    nuovoC.setCarrello(cart.getUtente());
                                    contenereCDAO.doSave(nuovoC);
                                    contenereC.add(nuovoC);
                                    sessione.setAttribute("contenereC",contenereC);
                                    cart.setnLibri(cart.getnLibri() + 1);
                                    cart.setTotale(cart.getTotale() + l.getPrezzo());
                                    sessione.setAttribute("cart",cart);
                                    carrelloDAO.doUpdate(cart);
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
                                ContenereC nuovoC = new ContenereC();
                                nuovoC.setLibroCartaceo(codice);
                                nuovoC.setNumCopie(1);
                                numCopie = 1;
                                nuovoC.setCarrello(cart.getUtente());
                                contenereCDAO.doSave(nuovoC);
                                contenereC.add(nuovoC);
                                sessione.setAttribute("contenereC",contenereC);
                                cart.setnLibri(cart.getnLibri() + 1);
                                cart.setTotale(cart.getTotale() + l.getPrezzo());
                                sessione.setAttribute("cart",cart);
                                carrelloDAO.doUpdate(cart);
                                break;
                            }
                        }
                    }
                }
                ContenereEDAO contenereEDAO = new ContenereEDAO();
                if (contenereE != null){
                    for (LibroElettronico l : libriE) {
                        if (codice.equals(l.getCodice())) {
                            for (ContenereE e : contenereE) {
                                if (codice.equals(e.getLibroElettronico())) {
                                    e.setNumCopie(e.getNumCopie() + 1);
                                    numCopie = e.getNumCopie();
                                    trovatoE = true;
                                    contenereEDAO.doUpdate(e);
                                    sessione.setAttribute("contenereE",contenereE);
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
                                numCopie = 1;
                                nuovoE.setCarrello(cart.getUtente());
                                contenereEDAO.doSave(nuovoE);
                                contenereE.add(nuovoE);
                                sessione.setAttribute("contenereE",contenereE);
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
                            numCopie = 1;
                            nuovoE.setCarrello(cart.getUtente());
                            contenereEDAO.doSave(nuovoE);
                            contenereE.add(nuovoE);
                            sessione.setAttribute("contenereE",contenereE);
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
                    String risposta = cart.getnLibri() + "-" + numCopie + "-" + cart.getTotale();
                    scrivi.print(risposta);
                }
            }
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
