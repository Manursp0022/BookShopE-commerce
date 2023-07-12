package Controller;

import Model.*;
import Model.Bean.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ModificaCartaceoServlet", value = "/ModificaCartaceoServlet")
public class ModificaCartaceoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String code = request.getParameter("code");
        LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        ContenereCDAO contenereCDAO = new ContenereCDAO();
        List<Carrello> carrelloList = carrelloDAO.doRetrieveAll();
        List<ContenereC> contenereCList = contenereCDAO.doRetrieveAll();
        List<PreferitoC> preferitiC = (List<PreferitoC>) request.getSession().getAttribute("prefC");
        List<ContenereC> contenereC = (List<ContenereC>) request.getSession().getAttribute("contenereC");
        int newStock;
        float newPrice;
        if(!operation.isEmpty()) {
            if (operation.equals("Edit") && !code.isEmpty()) {
                String newPriceS = request.getParameter("newPrice");
                String newStockS = request.getParameter("newStock");
                try {
                    if (!newPriceS.isEmpty() && !newStockS.isEmpty()) {
                        newPrice = Float.parseFloat(newPriceS);
                        newStock = Integer.parseInt(newStockS);
                        if (newPrice > 0 && newStock >= 0) {
                            if(!carrelloList.isEmpty()){
                                for(Carrello c: carrelloList){
                                    if(!contenereCList.isEmpty()){
                                        for(ContenereC con: contenereCList){
                                            if(con.getCarrello().equals(c.getUtente())){
                                                if(con.getLibroCartaceo().equals(code)){
                                                    float oldPrice = libroCartaceoDAO.doRetrieveByCode(code).getPrezzo();
                                                    if(oldPrice > newPrice) {
                                                        c.setTotale(c.getTotale() - ((oldPrice - newPrice) * con.getNumCopie()));
                                                    }else{
                                                        c.setTotale(c.getTotale() + ((newPrice - oldPrice) * con.getNumCopie()));
                                                    }
                                                    request.getSession().setAttribute("cart",c);
                                                    carrelloDAO.doUpdate(c);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            libroCartaceoDAO.doUpdatePrezzo(newPrice, code);
                            try{
                                Connection connection = ConPool.getConnection();
                                libroCartaceoDAO.doUpdateQuantitaDisp(newStock, code, connection);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        } else {
                            request.setAttribute("errore", "Valori negativi");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                            dispatcher.forward(request, response);
                        }
                    } else if (newPriceS.isEmpty() && !newStockS.isEmpty()) {
                        newStock = Integer.parseInt(newStockS);
                        if (newStock >= 0) {
                            try{
                                Connection connection = ConPool.getConnection();
                                libroCartaceoDAO.doUpdateQuantitaDisp(newStock, code, connection);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        } else {
                            request.setAttribute("errore", "Valori negativi");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                            dispatcher.forward(request, response);
                        }
                    } else if (!newPriceS.isEmpty()) {
                        newPrice = Float.parseFloat(newPriceS);
                        if (newPrice > 0) {
                            if(!carrelloList.isEmpty()){
                                for(Carrello c: carrelloList){
                                    if(!contenereCList.isEmpty()){
                                        for(ContenereC con: contenereCList){
                                            if(con.getCarrello().equals(c.getUtente())){
                                                if(con.getLibroCartaceo().equals(code)){
                                                    float oldPrice = libroCartaceoDAO.doRetrieveByCode(code).getPrezzo();
                                                    if(oldPrice > newPrice) {
                                                        c.setTotale(c.getTotale() - ((oldPrice - newPrice) * con.getNumCopie()));
                                                    }else{
                                                        c.setTotale(c.getTotale() + ((newPrice - oldPrice) * con.getNumCopie()));
                                                    }
                                                    request.getSession().setAttribute("cart",c);
                                                    carrelloDAO.doUpdate(c);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            libroCartaceoDAO.doUpdatePrezzo(newPrice, code);
                        } else {
                            request.setAttribute("errore", "Valori negativi");
                            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                            dispatcher.forward(request, response);
                        }
                    } else {
                        request.setAttribute("errore", "Nessun valore scritto");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                        dispatcher.forward(request, response);
                    }
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    request.setAttribute("errore", "Hai inserito lettere, ci vogliono i numeri");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(request, response);
                }
                request.setAttribute("errore", "No-error");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            } else if (operation.equals("Delete")){
                try {
                    if(!carrelloList.isEmpty()){
                        for(Carrello c: carrelloList){
                            if(!contenereCList.isEmpty()){
                                for(ContenereC con: contenereCList){
                                    if(con.getCarrello().equals(c.getUtente())){
                                        if(con.getLibroCartaceo().equals(code)){
                                            c.setTotale(c.getTotale() - (libroCartaceoDAO.doRetrieveByCode(code).getPrezzo() * con.getNumCopie()));
                                            c.setnLibri(c.getnLibri() - con.getNumCopie());
                                            request.getSession().setAttribute("cart",c);
                                            carrelloDAO.doUpdate(c);
                                            contenereC.remove(con);
                                            for(PreferitoC p: preferitiC){
                                                if(p.getLibroCartaceo().equals(con.getLibroCartaceo())){
                                                    preferitiC.remove(p);
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    libroCartaceoDAO.doDelete(code);
                }catch (Exception exception){
                    exception.printStackTrace();
                    request.setAttribute("errore", "Codice non presente");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(request, response);
                }
                request.setAttribute("errore", "No-error");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            }else{
                request.setAttribute("errore", "Nessun codice inserito");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            }
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
