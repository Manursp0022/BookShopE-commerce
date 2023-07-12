package Controller;

import Model.*;
import Model.Bean.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "RimuoviCarrelloServlet", value = "/RimuoviCarrelloServlet")
public class RimuoviCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            HttpSession sessione = request.getSession(false);
            PrintWriter scrivi = response.getWriter();
            int mode = (int) sessione.getAttribute("mode");
            response.setContentType("text/html");
            Carrello cart = (Carrello) sessione.getAttribute("cart");
            String codice = request.getParameter("codice");
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
                                    if(c.getNumCopie() > 0){
                                        if(c.getNumCopie() - 1 == 0) {
                                            outOfStock = true;
                                            contenereC.remove(c);
                                            sessione.setAttribute("contenereC",contenereC);
                                            cart.setnLibri(cart.getnLibri() - 1);
                                            cart.setTotale(cart.getTotale() - l.getPrezzo());
                                            sessione.setAttribute("cart",cart);
                                            break;
                                        }
                                        c.setNumCopie(c.getNumCopie() - 1);
                                        numCopie = c.getNumCopie();
                                        sessione.setAttribute("contenereC",contenereC);
                                        cart.setnLibri(cart.getnLibri() - 1);
                                        cart.setTotale(cart.getTotale() - l.getPrezzo());
                                        sessione.setAttribute("cart",cart);

                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(!contenereE.isEmpty()){
                    for (LibroElettronico l : libriE) {
                        if (codice.equals(l.getCodice())) {
                            for (ContenereE e : contenereE) {
                                if (codice.equals(e.getLibroElettronico())) {
                                    if(e.getNumCopie() - 1 == 0) {
                                        contenereE.remove(e);
                                        outOfStock= true;
                                        sessione.setAttribute("contenereE", contenereE);
                                        cart.setnLibri(cart.getnLibri() - 1);
                                        cart.setTotale(cart.getTotale() - l.getPrezzo());
                                        sessione.setAttribute("cart", cart);
                                        break;
                                    }
                                    e.setNumCopie(e.getNumCopie() - 1);
                                    numCopie = e.getNumCopie();
                                    sessione.setAttribute("contenereE",contenereE);
                                    cart.setnLibri(cart.getnLibri() - 1);
                                    cart.setTotale(cart.getTotale() - l.getPrezzo());
                                    sessione.setAttribute("cart",cart);
                                    break;
                                }
                            }

                        }
                    }
                }
                if (outOfStock) {
                    scrivi.print(cart.getnLibri() + "-" + "0");
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
                            for (ContenereC c : contenereC) {
                                if (l.getCodice().equals(c.getLibroCartaceo())) {
                                    if (c.getNumCopie() > 0) {
                                        if (c.getNumCopie() - 1 == 0) {
                                            outOfStock = true;
                                            contenereC.remove(c);
                                            try{
                                                Connection con = ConPool.getConnection();
                                                contenereCDAO.doRemove(c,con);
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            sessione.setAttribute("contenereC", contenereC);
                                            cart.setnLibri(cart.getnLibri() - 1);
                                            cart.setTotale(cart.getTotale() - l.getPrezzo());
                                            carrelloDAO.doUpdate(cart);
                                            sessione.setAttribute("cart", cart);
                                            break;
                                        }
                                        c.setNumCopie(c.getNumCopie() - 1);
                                        numCopie = c.getNumCopie();
                                        contenereCDAO.doUpdate(c);
                                        sessione.setAttribute("contenereC", contenereC);
                                        cart.setnLibri(cart.getnLibri() - 1);
                                        cart.setTotale(cart.getTotale() - l.getPrezzo());
                                        carrelloDAO.doUpdate(cart);
                                        sessione.setAttribute("cart", cart);

                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                ContenereEDAO contenereEDAO = new ContenereEDAO();
                if(!contenereE.isEmpty()){
                    for (LibroElettronico l : libriE) {
                        if (codice.equals(l.getCodice())) {
                            for (ContenereE e : contenereE) {
                                if (codice.equals(e.getLibroElettronico())) {
                                    if(e.getNumCopie() - 1 == 0) {
                                        contenereE.remove(e);
                                        contenereEDAO.doRemove(e);
                                        outOfStock = true;
                                        sessione.setAttribute("contenereE", contenereE);
                                        cart.setnLibri(cart.getnLibri() - 1);
                                        cart.setTotale(cart.getTotale() - l.getPrezzo());
                                        carrelloDAO.doUpdate(cart);
                                        sessione.setAttribute("cart", cart);
                                        break;
                                    }
                                    e.setNumCopie(e.getNumCopie() - 1);
                                    numCopie = e.getNumCopie();
                                    contenereEDAO.doRemove(e);
                                    sessione.setAttribute("contenereE",contenereE);
                                    cart.setnLibri(cart.getnLibri() - 1);
                                    cart.setTotale(cart.getTotale() - l.getPrezzo());
                                    carrelloDAO.doUpdate(cart);
                                    sessione.setAttribute("cart",cart);
                                    break;
                                }
                            }
                        }
                    }
                }
                 if (outOfStock) {
                    scrivi.print(cart.getnLibri() + "-" + "0");

                }else {
                    String risposta = cart.getnLibri() + "-" + numCopie + "-" + cart.getTotale();
                    scrivi.print(risposta);
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
