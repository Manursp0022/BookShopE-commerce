package Controller;

import Model.*;
import Model.Bean.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
            ContenereCDAO contenereCDAO = new ContenereCDAO();
            List<ContenereC> contenereC = contenereCDAO.doRetrieveByCart(utente.getEmail());
            ContenereEDAO contenereEDAO = new ContenereEDAO();
            List<ContenereE> contenereE = contenereEDAO.doRetrieveByCarrello(utente.getEmail());
            synchronized (this) {
                HttpSession session = request.getSession();
                session.setAttribute("utente", utente);
                session.setAttribute("cart", cart);
                session.setAttribute("prefC", preferitiC);
                session.setAttribute("prefE", preferitiE);
                session.setAttribute("contenereC",contenereC);
                session.setAttribute("contenereE",contenereE);
                if (utente.isAdmin())
                    request.getSession().setAttribute("mode", 1);
                else
                    request.getSession().setAttribute("mode", 2);
                RequestDispatcher dispatcher = request.getRequestDispatcher("homepage.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
