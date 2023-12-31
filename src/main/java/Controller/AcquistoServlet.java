package Controller;

import Model.*;
import Model.Bean.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Savepoint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AcquistoServlet", value = "/AcquistoServlet")
public class AcquistoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this){
            HttpSession session = request.getSession();
            int mode = (int) session.getAttribute("mode");
            if(mode == 3){
                RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
                dispatcher.forward(request, response);
            }else {
                Utente utente = (Utente) session.getAttribute("utente");
                Carrello cart = (Carrello) session.getAttribute("cart");
                List<ContenereC> contenereCList;
                List<ContenereE> contenereEList;
                ContenereCDAO contenereCDAO = new ContenereCDAO();
                contenereCList = contenereCDAO.doRetrieveByCart(utente.getEmail());
                ContenereEDAO contenereEDAO = new ContenereEDAO();
                contenereEList = contenereEDAO.doRetrieveByCarrello(utente.getEmail());
                List<ContenereC> emptyC = new ArrayList<>();
                List<ContenereE> emptyE = new ArrayList<>();
                if (contenereEList.isEmpty() && contenereCList.isEmpty()) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Carrello.jsp");
                    dispatcher.forward(request, response);
                } else {
                    Ordine ordine = new Ordine();
                    ordine.setEmail(cart.getUtente());
                    ordine.setNumLibri(cart.getnLibri());
                    ordine.setTotale(cart.getTotale());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    ordine.setDataOrdine(sdf.format(new Date()));
                    try {
                        Connection connection = ConPool.getConnection();//creo una connessione unica
                        Savepoint savepoint = null;//dichiaro il punto a cui devo tornare indietro
                        try {
                            connection.setAutoCommit(false);//impedisco alle query di applicare subito le modifiche
                            savepoint = connection.setSavepoint();//imposto un checkpoint a cui tornare
                            OrdineDAO ordineDAO = new OrdineDAO();
                            int id = ordineDAO.doSave(ordine, connection);//salvo l'ordine e mi ritorno l'id
                            OrdineCDAO ordineCDAO = new OrdineCDAO();
                            OrdineEDAO ordineEDAO = new OrdineEDAO();
                            CarrelloDAO carrelloDAO = new CarrelloDAO();
                            LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
                            LibroCartaceo l;
                            for (ContenereC c : contenereCList) {
                                l = libroCartaceoDAO.doRetrieveByCode(c.getLibroCartaceo());// mi salvo il libro che sta nel carrello
                                if (l.getQuantitaDisp() >= c.getNumCopie()) {  //controllo se ho abbastanza quantità di quel libro
                                    OrdineC ordineC = new OrdineC();
                                    ordineC.setId(id);
                                    ordineC.setOrdine(c.getCarrello());
                                    ordineC.setLibroCartaceo(c.getLibroCartaceo());
                                    ordineC.setNumCopie(c.getNumCopie());
                                    ordineCDAO.doSave(ordineC, connection);
                                    contenereCDAO.doRemove(c, connection);
                                    libroCartaceoDAO.doUpdateQuantitaDisp(l.getQuantitaDisp() - c.getNumCopie(), l.getCodice(), connection);
                                } else {
                                    throw new Exception(c.getLibroCartaceo());
                                }
                            }
                            connection.commit();//aggiorno le tabell ocn i risultati delle query
                            connection.setAutoCommit(true);//applica di nuovo le modifiche appena eseguo la query
                            for (ContenereE e : contenereEList) {
                                OrdineE ordineE = new OrdineE();
                                ordineE.setId(id);
                                ordineE.setOrdine(e.getCarrello());
                                ordineE.setLibroElettronico(e.getLibroElettronico());
                                ordineE.setNumCopie(e.getNumCopie());
                                ordineEDAO.doSave(ordineE);
                                contenereEDAO.doRemove(e);
                            }
                            cart.setnLibri(0);
                            cart.setTotale(0);
                            carrelloDAO.doUpdate(cart);
                            List<Ordine> ordini = (List<Ordine>) session.getAttribute("ordini");
                            ordini.add(ordine);
                            session.setAttribute("ordini", ordini);
                            session.setAttribute("cart", cart);
                            session.setAttribute("contenereC", emptyC);
                            session.setAttribute("contenereE", emptyE);
                            RequestDispatcher dispatcher = request.getRequestDispatcher("profilo.jsp");
                            dispatcher.forward(request, response);
                        } catch (Exception e) {
                            if (savepoint != null) {
                                connection.rollback(savepoint);
                            } else {
                                connection.rollback();
                            }
                            connection.setAutoCommit(true);
                            request.setAttribute("errore", "Quantità non disponibile-" + e.getMessage());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("MostraCarrelloServlet");
                            dispatcher.forward(request, response);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
