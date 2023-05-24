package Controller;

import Model.Utente;
import Model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet (value = "/regitrazione-servlet")
public class RegistrazioneServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String CAP = request.getParameter("CAP");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        boolean admin = Boolean.parseBoolean(request.getParameter("amministratore"));
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setVia(via);
        utente.setCitta(citta);
        utente.setCAP(CAP);
        utente.setEmail(email);
        utente.setAdmin(admin);
        utente.setTelefono(telefono);
        utenteDAO.doSave(utente);
        request.getSession().setAttribute("utente", utente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/headers.jsp");
        dispatcher.forward(request, response);
    }
}