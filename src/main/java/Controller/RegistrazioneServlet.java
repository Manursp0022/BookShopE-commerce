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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet (value = "/registrazione-servlet")
public class RegistrazioneServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        if (request.getParameter("username") != null) {
            username = request.getParameter("username");
        }
        String password = null;
        if (request.getParameter("password") != null) {
            password = request.getParameter("password");
        }
        String via = null;
        if (request.getParameter("via") != null) {
            via = request.getParameter("via");
        }
        String citta = null;
        if (request.getParameter("citta") != null) {
            citta = request.getParameter("citta");
        }
        String CAP = null;
        if (request.getParameter("CAP") != null) {
            CAP = request.getParameter("CAP");
        }
        String telefono = null;
        if (request.getParameter("telefono") != null) {
            telefono = request.getParameter("telefono");
        }
        String email = null;
        if (request.getParameter("email") != null) {
            email = request.getParameter("email");
        }
        String passwordrep = null;
        if (request.getParameter("passwordrep") != null) {
            passwordrep = request.getParameter("passwordrep");
        }
        String patternCAP = "[0-9]{5}";
        String patternCELL = "[0-9]{10}";
        int lengthCAP = 5;
        int lengthCELL = 10;
        Pattern regexPattern = Pattern.compile(patternCAP);
        Matcher matcher = regexPattern.matcher(CAP);
        boolean patternMatchCAP = matcher.matches();
        boolean lengthMatchCAP = (CAP.length() == lengthCAP);
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
        if((username == null) || (passwordrep == null) || (password == null) || (email == null) || (telefono == null) || (citta == null) || (CAP == null) || (via == null)){
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
            errore = "true";
            request.setAttribute("6", errore);
            dispatcher.forward(request, response);
        }
        if (!patternMatchCAP || !lengthMatchCAP) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
            errore = "true";
            request.setAttribute("4", errore);
            dispatcher.forward(request, response);
        }
        regexPattern = Pattern.compile(patternCELL);
        matcher = regexPattern.matcher(telefono);
        boolean patternMatchCELL = matcher.matches();
        boolean lengthMatchCELL = (telefono.length() == lengthCELL);
        if (!patternMatchCELL || !lengthMatchCELL) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
            errore = "true";
            request.setAttribute("5", errore);
            dispatcher.forward(request, response);
        }
        if (!(passwordrep.equals(password))) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
            errore = "true";
            request.setAttribute("2", errore);
            dispatcher.forward(request, response);
        }
        if (!(email.contains("@"))) {
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
                errore = "true";
                request.setAttribute("3", errore);
                dispatcher.forward(request, response);
            }
        }
        for (Utente u : utenti) {
            if (u.getEmail().equals(utente.getEmail())) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
                errore = "true";
                request.setAttribute("1", errore);
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
