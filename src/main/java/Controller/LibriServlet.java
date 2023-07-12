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
        LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
        LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
        if (codice != null) {
            LibroCartaceo l = libroCartaceoDAO.doRetrieveByCode(codice);
            LibroElettronico e = libroElettronicoDAO.doRetrieveByCode(codice);
            if(l != null){
                request.setAttribute("libro",l);
                request.setAttribute("visualize", "cartaceo");
                LibroElettronico e_book = libroElettronicoDAO.doRetrieveByTitle(l.getTitolo());
                if(e_book != null){
                    request.setAttribute("libroE",e_book);
                    request.setAttribute("tipo", "bi-tipo");
                }else{
                    request.setAttribute("tipo","cartaceo");
                }
                RequestDispatcher rd = request.getRequestDispatcher("Libro.jsp");
                rd.forward(request,response);
            }else if(e != null){
                    request.setAttribute("libroE",e);
                    request.setAttribute("visualize", "elettronico");
                    LibroCartaceo book = libroCartaceoDAO.doRetrieveByTitle(e.getTitolo());
                    if(book != null) {
                        request.setAttribute("libro", book);
                        request.setAttribute("tipo", "bi-tipo");
                    }else {
                        request.setAttribute("tipo","elettronico");
                    }
                }
                RequestDispatcher rd = request.getRequestDispatcher("Libro.jsp");
                rd.forward(request,response);
            }else{
            RequestDispatcher rd = request.getRequestDispatcher("MostraLibri.jsp");
            rd.forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
