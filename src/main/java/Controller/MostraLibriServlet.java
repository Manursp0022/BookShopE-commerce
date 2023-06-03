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
        switch (id){
            case 1:{
                LibroCartaceoDAO libroCartaceoDAO= new LibroCartaceoDAO();
                LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAll();
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAll();
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 2:{
                LibroCartaceoDAO libroCartaceoDAO= new LibroCartaceoDAO();
                LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Bambini e ragazzi");
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Bambini e ragazzi");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 3:{
                LibroCartaceoDAO libroCartaceoDAO= new LibroCartaceoDAO();
                LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Fumetti");
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Fumetti");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 4:{
                LibroCartaceoDAO libroCartaceoDAO= new LibroCartaceoDAO();
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Vintage");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", null);
                break;
            }
            case 5:{
                LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Elettronico");
                request.setAttribute("libri", null);
                request.setAttribute("libriE", libriE);
                break;
            }
            case 6:{
                LibroCartaceoDAO libroCartaceoDAO= new LibroCartaceoDAO();
                LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
                List<LibroCartaceo> libri = libroCartaceoDAO.doRetrieveAllByGender("Inglese");
                List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAllByGender("Inglese");
                request.setAttribute("libri", libri);
                request.setAttribute("libriE", libriE);
                break;
            }
            default:{
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
