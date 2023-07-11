package Controller;

import Model.*;
import Model.Bean.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ModificaElettronicoServlet", value = "/ModificaElettronicoServlet")
public class ModificaElettronicoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        String code = request.getParameter("code");
        LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        ContenereEDAO contenereEDAO = new ContenereEDAO();
        List<Carrello> carrelloList = carrelloDAO.doRetrieveAll();
        List<ContenereE> contenereEList = contenereEDAO.doRetrieveAll();
        List<PreferitoE> preferitiE = (List<PreferitoE>) request.getSession().getAttribute("prefE");
        List<ContenereE> contenereE = (List<ContenereE>) request.getSession().getAttribute("contenereE");
        float newPrice;
        if(operation.equals("Edit") && !code.isEmpty()){
            String newPriceS = request.getParameter("newPrice");
            try {
                if (!newPriceS.isEmpty()){
                    newPrice = Float.parseFloat(newPriceS);
                    if (newPrice > 0) {
                        if(!carrelloList.isEmpty()){
                            for(Carrello c: carrelloList){
                                if(!contenereEList.isEmpty()){
                                    for(ContenereE con: contenereEList){
                                        if(con.getCarrello().equals(c.getUtente())){
                                            if(con.getLibroElettronico().equals(code)){
                                                float oldPrice = libroElettronicoDAO.doRetrieveByCode(code).getPrezzo();
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
                        libroElettronicoDAO.doUpdatePrezzo(newPrice, code);
                    } else {
                        request.setAttribute("errore", "Valori negativi");
                        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                        dispatcher.forward(request, response);
                    }
                }else{
                    request.setAttribute("errore", "Nessun valore scritto");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                    dispatcher.forward(request, response);
                }
            }catch (NumberFormatException exception){
                exception.printStackTrace();
                request.setAttribute("errore", "Hai inserito lettere, ci vogliono i numeri");
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            }
            request.setAttribute("errore","No-error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request,response);
        } else if (operation.equals("Delete")){
            try {
                if(!carrelloList.isEmpty()){
                    for(Carrello c: carrelloList){
                        if(!contenereEList.isEmpty()){
                            for(ContenereE con: contenereEList){
                                if(con.getCarrello().equals(c.getUtente())){
                                    if(con.getLibroElettronico().equals(code)){
                                        c.setTotale(c.getTotale() - (libroElettronicoDAO.doRetrieveByCode(code).getPrezzo() * con.getNumCopie()));
                                        c.setnLibri(c.getnLibri() - con.getNumCopie());
                                        request.getSession().setAttribute("cart",c);
                                        carrelloDAO.doUpdate(c);
                                        contenereE.remove(con);
                                        for(PreferitoE p: preferitiE){
                                            if(p.getLibroElettronico().equals(con.getLibroElettronico())){
                                                preferitiE.remove(p);
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
                libroElettronicoDAO.doDelete(code);
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
