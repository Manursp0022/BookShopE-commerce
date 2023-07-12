package Controller;

import Model.Bean.Carrello;
import Model.Bean.ContenereC;
import Model.Bean.ContenereE;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IndexServlet", value = "/indexServlet", loadOnStartup = 0)
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        synchronized (this){
            Integer mode = (Integer) session.getAttribute("mode");
            if(mode == null) {
                session.setAttribute("mode", 3);
                Carrello cart = new Carrello();
                List<ContenereC> contenereC = new ArrayList<>();
                List<ContenereE> contenereE = new ArrayList<>();
                session.setAttribute("contenereC", contenereC);
                session.setAttribute("contenereE", contenereE);
                cart.setUtente("NULL");
                session.setAttribute("cart", cart);
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
        rd.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
