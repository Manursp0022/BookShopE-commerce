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

        String nome = null;
        if (request.getParameter("name") != null) {
            nome = request.getParameter("name");
        }

        String cognome = null;
        if (request.getParameter("surname") != null) {
            cognome = request.getParameter("surname");
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
        utente.setNome(nome);
        utente.setCognome(cognome);
        utente.setPassword(password);
        utente.setVia(via);
        utente.setCitta(citta);
        utente.setCAP(CAP);
        utente.setEmail(email);
        utente.setAdmin(admin);
        utente.setTelefono(telefono);
        if(password.equals("admin")){
            utente.setAdmin(true);
        }
        String errore = "";
        if((username.equals("")) || (nome.equals("")) || (cognome.equals("")) || (passwordrep.equals("")) || (password.equals("")) || (email.equals("")) || (telefono.equals("")) || (citta.equals("")) || (CAP.equals("")) || (via.equals(""))){
            errore = errore + "6,";
        }
        if (!patternMatchCAP || !lengthMatchCAP) {
            errore = errore + "4,";
        }
        regexPattern = Pattern.compile(patternCELL);
        matcher = regexPattern.matcher(telefono);
        boolean patternMatchCELL = matcher.matches();
        boolean lengthMatchCELL = (telefono.length() == lengthCELL);
        if (!patternMatchCELL || !lengthMatchCELL) {
            errore = errore + "5,";
        }
        if (!(passwordrep.equals(password))) {
            errore = errore + "2,";
        }
        if (!(email.contains("@"))) {
            {
                errore = errore + "3,";
            }
        }
        for (Utente u : utenti) {
            if (u.getEmail().equals(utente.getEmail())) {
                errore = errore + "1,";
            }
        }
        if(errore.equals("")) {
            HttpSession session = request.getSession();
            utenteDAO.doSave(utente);
            CarrelloDAO carrelloDAO = new CarrelloDAO();
            ContenereCDAO contenereCDAO = new ContenereCDAO();
            ContenereEDAO contenereEDAO = new ContenereEDAO();
            synchronized (this) {
                Carrello guestCart = (Carrello) session.getAttribute("cart");
                guestCart.setUtente(email);
                carrelloDAO.doSave(guestCart);
                List<ContenereC> contenereC = (List<ContenereC>)session.getAttribute("contenereC");
                for(ContenereC c: contenereC){
                    c.setCarrello(email);
                    contenereCDAO.doSave(c);
                }
                List<ContenereE> contenereE = (List<ContenereE>)session.getAttribute("contenereE");
                for(ContenereE e: contenereE){
                    e.setCarrello(email);
                    contenereEDAO.doSave(e);
                }
                List<PreferitoC> prefC = (List<PreferitoC>) session.getAttribute("prefC");
                PreferitoCDAO preferitoCDAO = new PreferitoCDAO();
                if(prefC != null){
                    for(PreferitoC pref : prefC){
                        pref.setUtente(email);
                        preferitoCDAO.doSave(pref);
                    }
                }
                List<PreferitoE> prefE = (List<PreferitoE>) session.getAttribute("prefE");
                PreferitoEDAO preferitoEDAO = new PreferitoEDAO();
                if(prefE != null){
                    for(PreferitoE pref : prefE){
                        pref.setUtente(email);
                        preferitoEDAO.doSave(pref);
                    }
                }
                session.setAttribute("utente", utente);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
        }
        else{
            request.setAttribute("errore",errore);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Registrazione.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
