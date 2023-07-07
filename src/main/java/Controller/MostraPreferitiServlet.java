package Controller;

import Model.Bean.LibroCartaceo;
import Model.Bean.LibroElettronico;
import Model.Bean.PreferitoC;
import Model.Bean.PreferitoE;
import Model.LibroCartaceoDAO;
import Model.LibroElettronicoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "MostraPreferitiServlet", value = "/MostraPreferitiServlet")
public class MostraPreferitiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PreferitoC> prefC = (List<PreferitoC>) request.getSession().getAttribute("prefC");
        List<PreferitoE> prefE = (List<PreferitoE>) request.getSession().getAttribute("prefE");
        LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
        LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
        List<LibroCartaceo> send1 = new ArrayList<>();
        List<LibroElettronico> send2 = new ArrayList<>();
        if(prefC != null){
            for(PreferitoC pref : prefC){
                send1.add(libroCartaceoDAO.doRetrieveByCode(pref.getLibroCartaceo()));
            }
            request.setAttribute("libri", send1);
        }
        if(prefE != null){
            for(PreferitoE pref : prefE){
                send2.add(libroElettronicoDAO.doRetrieveByCode(pref.getLibroElettronico()));
            }
            request.setAttribute("libriE", send2);
        }

        RequestDispatcher rd = request.getRequestDispatcher("MostraPreferiti.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
