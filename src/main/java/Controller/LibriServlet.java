package Controller;

import Model.Bean.LibroCartaceo;
import Model.Bean.LibroElettronico;
import Model.LibroCartaceoDAO;
import Model.LibroElettronicoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LibriServlet", value = "/LibriServlet")
public class LibriServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codice = request.getParameter("codice");
        RecensioneDAO recensioneDAO = new RecensioneDAO();
        List<Recensione> recensioni = new ArrayList<>();
        LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
        LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
        List<LibroCartaceo> libriC = libroCartaceoDAO.doRetrieveAll();
        List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAll();
        if (codice != null) {
            for (LibroCartaceo c : libriC) {
                if (c.getCodice().equals(codice)) {
                    recensioni = recensioneDAO.doRetrieveByTitolo(c.getTitolo());
                    request.setAttribute("libro", c);
                    request.setAttribute("tipo", "cartaceo");
                    break;
                }
            }
            for (LibroElettronico e : libriE) {
                if (e.getCodice().equals(codice)) {
                    recensioni = recensioneDAO.doRetrieveByTitolo(e.getTitolo());
                    request.setAttribute("libro", e);
                    request.setAttribute("tipo", "elettronico");
                    break;
                }
            }
            request.setAttribute("recensioni", recensioni);
            RequestDispatcher rd = request.getRequestDispatcher("Libro.jsp");
            rd.forward(request,response);
        }

        RequestDispatcher rd = request.getRequestDispatcher("MostraLibri.jsp");
        rd.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
