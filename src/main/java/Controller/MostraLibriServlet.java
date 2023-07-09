package Controller;

import Model.Bean.LibroCartaceo;
import Model.Bean.LibroElettronico;
import Model.LibroCartaceoDAO;
import Model.LibroElettronicoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MostraLibriServlet", value = "/MostraLibriServlet")
public class MostraLibriServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        LibroCartaceoDAO libroCartaceoDAO= new LibroCartaceoDAO();
        LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
        switch (id){
            case 1:{
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAll();
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAll();
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 2:{
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Bambini e ragazzi");
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Bambini e ragazzi");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 3:{
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Fumetti");
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Fumetti");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 4:{
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Vintage");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", null);
                break;
            }
            case 5:{
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Elettronico");
                request.setAttribute("libri", null);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 6:{
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Inglese");
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Inglese");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 7:{
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Arte");
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Arte");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            default:{
                break;
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("MostraLibri.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
