package Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Model.Utente;
import Model.UtenteDAO;

import java.io.IOException;

@WebServlet (value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UtenteDAO utenteDAO = new UtenteDAO();
        Utente utente = utenteDAO.doRetrieveByEmailPassword(email, password);
        if (utente == null){
            request.setAttribute("error","utente not found");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/headers.jsp");
            dispatcher.forward(request,response);
        }
        request.getSession().setAttribute("utente",utente);
        String address = "http://localhost:8080/InitCategorie_war_exploded/";
        response.sendRedirect(address);
    }
}
