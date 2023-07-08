package Controller;

import Model.Bean.*;
import Model.ContenereEDAO;
import Model.LibroCartaceoDAO;
import Model.LibroElettronicoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraCarrelloServlet", value = "/MostraCarrelloServlet")
public class MostraCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        synchronized (this) {
            HttpSession session = request.getSession();
            List<ContenereC> contenereC = (List<ContenereC>) session.getAttribute("contenereC");
            List<ContenereE> contenereE = (List<ContenereE>) session.getAttribute("contenereE");
            Carrello carrello = (Carrello) session.getAttribute("cart");
            LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
            LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
            List<LibroCartaceo> libroCartaceos = new ArrayList<>();
            List<LibroElettronico> libroElettronicos = new ArrayList<>();
            if(!contenereC.isEmpty()){
                for(ContenereC c : contenereC){
                    LibroCartaceo l = libroCartaceoDAO.doRetrieveByCode(c.getLibroCartaceo());
                    libroCartaceos.add(l);
                }
            }
            if(!contenereE.isEmpty()){
                for(ContenereE c : contenereE){
                    LibroElettronico l = libroElettronicoDAO.doRetrieveByCode(c.getLibroElettronico());
                    libroElettronicos.add(l);
                }
            }
            request.setAttribute("libri", libroCartaceos);
            request.setAttribute("libriE", libroElettronicos);

            RequestDispatcher rd = request.getRequestDispatcher("Carrello.jsp");
            rd.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request,response);
    }
}
