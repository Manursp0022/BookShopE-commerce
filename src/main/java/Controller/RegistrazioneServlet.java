package Controller;

import Model.Bean.Carrello;
import Model.Bean.Utente;
import Model.CarrelloDAO;
import Model.UtenteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet (value = "/registrazione-servlet")
public class RegistrazioneServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String via = request.getParameter("via");
        String citta = request.getParameter("citta");
        String CAP = request.getParameter("CAP");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        boolean admin = false;
        UtenteDAO utenteDAO = new UtenteDAO();
        List<Utente> utenti = utenteDAO.doRetrieveAll();
        Utente utente = new Utente();
        utente.setUsername(username);
        utente.setPassword(password);
        utente.setVia(via);
        utente.setCitta(citta);
        utente.setCAP(CAP);
        utente.setEmail(email);
        utente.setAdmin(admin);
        utente.setTelefono(telefono);
        String errore;
        for(Utente u:utenti) {
            if (u.getEmail().equals(utente.getEmail())) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
                errore = "true";
                request.setAttribute("errore", errore);
                dispatcher.forward(request, response);
            }
        }
        utenteDAO.doSave(utente);
        Carrello cart = new Carrello();
        cart.setUtente(email);
        CarrelloDAO carrelloDAO = new CarrelloDAO();
        carrelloDAO.doSave(cart);
        synchronized (this) {
            request.getSession().setAttribute("utente", utente);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
