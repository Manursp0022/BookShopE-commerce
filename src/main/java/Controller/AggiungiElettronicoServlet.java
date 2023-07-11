package Controller;

import Model.Bean.LibroCartaceo;
import Model.Bean.LibroElettronico;
import Model.LibroCartaceoDAO;
import Model.LibroElettronicoDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(name = "AggiungiElettronicoServlet", value = "/AggiungiElettronicoServlet")
public class AggiungiElettronicoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "No-error";
        LibroElettronico newL = new LibroElettronico();
        String code = request.getParameter("code");
        newL.setCodice(code);
        String titolo = request.getParameter("titolo");
        newL.setTitolo(titolo);
        String autore = request.getParameter("autore");
        newL.setAutore(autore);
        String descrizione = request.getParameter("descrizione");
        newL.setDescrizione(descrizione);
        String genere = request.getParameter("genere");
        newL.setGenere(genere);
        String formato = request.getParameter("formato");
        newL.setFormato(formato);
        float prezzo = -1;
        try {
            prezzo = Float.parseFloat(request.getParameter("prezzo"));
            newL.setPrezzo(prezzo);

            Part filePart = request.getPart("img");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String destinazione = "LibriIMG" + File.separator + fileName;
            Path pathDestinazione = Paths.get(getServletContext().getRealPath(destinazione));
            InputStream fileInputStream = filePart.getInputStream();
            Files.copy(fileInputStream, pathDestinazione);
        }catch(Exception exception){
            error = "Errore inserimento dati nel form";
            request.setAttribute("errore",error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }

        if(code.isEmpty() || titolo.isEmpty() || autore.isEmpty() || genere.isEmpty() || prezzo < 0){
            error = "Errore inserimento dati nel form";
            request.setAttribute("errore",error);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
        }else {
            LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
            LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
            List<LibroCartaceo> libriC = libroCartaceoDAO.doRetrieveAll();
            if(libroElettronicoDAO.doRetrieveByCode(code) != null){
                error = "Codice già esistente";
            }else{
                for (LibroCartaceo e : libriC) {
                    if (e.getCodice().equals(newL.getCodice())) {
                        error = "Codice già esistente";
                        break;
                    }
                }
            }
            if (error.equals("No-error")) {
                libroElettronicoDAO.doSave(newL);
                request.setAttribute("errore", error);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errore", error);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
