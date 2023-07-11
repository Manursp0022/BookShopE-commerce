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

@WebServlet(name = "SearchServlet", value = "/search-servlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("search");
        LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
        LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
        List<LibroCartaceo> libroCartaceos = libroCartaceoDAO.doRetrieveAll();
        List<LibroElettronico> libroElettronicos =  libroElettronicoDAO.doRetrieveAll();
        List<LibroCartaceo> cartaceosret = new ArrayList<>();
        List<LibroElettronico> elettronicosret = new ArrayList<>();
        if(nome != null) {
            for (LibroCartaceo c : libroCartaceos) {
                if (nome.equalsIgnoreCase(c.getTitolo()) || c.getTitolo().toLowerCase().contains(nome.toLowerCase())) {
                    cartaceosret.add(c);
                } else if (nome.equalsIgnoreCase(c.getAutore()) || c.getAutore().toLowerCase().contains(nome.toLowerCase())) {
                    cartaceosret.add(c);
                } else if (nome.equalsIgnoreCase(c.getGenere()) || c.getGenere().toLowerCase().contains(nome.toLowerCase())) {
                    cartaceosret.add(c);
                } else if(nome.equals(c.getCodice())){
                    cartaceosret.add(c);
                }
            }

            for (LibroElettronico e : libroElettronicos) {
                if (nome.equalsIgnoreCase(e.getTitolo())) {
                    elettronicosret.add(e);
                    break;
                } else if (nome.equalsIgnoreCase(e.getAutore())) {
                    elettronicosret.add(e);
                } else if (nome.equalsIgnoreCase(e.getGenere())) {
                    elettronicosret.add(e);
                } else if (nome.equals(e.getCodice())) {
                    elettronicosret.add((e));
                }
            }
        }
        request.setAttribute("libri", cartaceosret);
        request.setAttribute("libriE", elettronicosret);
        RequestDispatcher rd = request.getRequestDispatcher("MostraLibri.jsp");
        rd.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doGet(request,response);
    }
}
