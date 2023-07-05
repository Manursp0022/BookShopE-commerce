package Controller;

import Model.Bean.Carrello;
import Model.Bean.PreferitoC;
import Model.Bean.PreferitoE;
import Model.CarrelloDAO;
import Model.PreferitoCDAO;
import Model.PreferitoEDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Bean.Utente;
import Model.UtenteDAO;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet (value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveByEmailPassword(email, password);
        if (utente == null){
            request.setAttribute("error","utente not found");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request,response);
        }
        else {
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            Carrello cart = carrelloDAO.doRetrieveByEmail(utente.getEmail());
            PreferitoEDAO preferitoEDAO = new PreferitoEDAO();
            List<PreferitoE> preferitiE = preferitoEDAO.doRetrieveByUtente(utente.getEmail());
            PreferitoCDAO preferitoCDAO = new PreferitoCDAO();
            List<PreferitoC> preferitiC = preferitoCDAO.doRetrieveByUtente(utente.getEmail());
            synchronized (this) {
                request.getSession().setAttribute("utente", utente);
                request.getSession().setAttribute("cart", cart);
                request.getSession().setAttribute("prefC", preferitiC);
                request.getSession().setAttribute("prefE", preferitiE);
                if (utente.isAdmin())
                    request.getSession().setAttribute("mode", 1);
                else
                    request.getSession().setAttribute("mode", 2);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
