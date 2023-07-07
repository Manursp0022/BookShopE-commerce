package Controller;

import Model.Bean.*;
import Model.LibroCartaceoDAO;
import Model.LibroElettronicoDAO;
import Model.PreferitoCDAO;
import Model.PreferitoEDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PreferitoServlet", value = "/preferito-servlet")
public class PreferitoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            int mode = (int) session.getAttribute("mode");
            String codice = request.getParameter("codice");
            response.setContentType("text/html");
            PrintWriter stampa = response.getWriter();
            List<PreferitoC> prefC = (List<PreferitoC>) session.getAttribute("prefC");
            List<PreferitoE> prefE = (List<PreferitoE>) session.getAttribute("prefE");
            LibroCartaceoDAO libroCartaceoDAO = new LibroCartaceoDAO();
            LibroElettronicoDAO libroElettronicoDAO = new LibroElettronicoDAO();
            List<LibroCartaceo> libriC = libroCartaceoDAO.doRetrieveAll();
            List<LibroElettronico> libriE = libroElettronicoDAO.doRetrieveAll();
            boolean rimosso = false;
            if(mode == 3){
                if(prefC == null){
                for(LibroCartaceo l : libriC){
                    if(codice.equals(l.getCodice())){
                        PreferitoC pref = new PreferitoC();
                        prefC = new ArrayList<>();
                        pref.setUtente("NULL");
                        pref.setLibroCartaceo(codice);
                        prefC.add(pref);
                        session.setAttribute("prefC", prefC);
                        stampa.print("1");
                        break;
                        }
                    }
                }
                else {
                    PreferitoC pref = new PreferitoC();
                    pref.setUtente("NULL");
                    pref.setLibroCartaceo(codice);
                    for (PreferitoC prefs : prefC) {
                        if (prefs.getLibroCartaceo().equals(codice)) {
                            prefC.remove(prefs);
                            session.setAttribute("prefC", prefC);
                            stampa.print("-1");
                            if(prefC.isEmpty() && prefE == null){
                                stampa.print("-2");
                            } else if (prefC.isEmpty() && prefE.isEmpty()) {
                                stampa.print("-2");
                            }
                            rimosso = true;
                            break;
                        }
                    }
                    if (!rimosso) {
                        for (LibroCartaceo l : libriC) {
                            if (codice.equals(l.getCodice())) {
                                prefC.add(pref);
                                session.setAttribute("prefC", prefC);
                                stampa.print("1");
                                break;
                            }
                        }
                    }
                }
                if(prefE == null) {
                    for (LibroElettronico l : libriE) {
                        if (codice.equals(l.getCodice())) {
                            PreferitoE pref = new PreferitoE();
                            prefE = new ArrayList<>();
                            pref.setUtente("NULL");
                            pref.setLibroElettronico(codice);
                            prefE.add(pref);
                            session.setAttribute("prefE", prefE);
                            stampa.print("1");
                            break;
                        }
                    }
                }
                else{
                    PreferitoE pref = new PreferitoE();
                    pref.setUtente("NULL");
                    pref.setLibroElettronico(codice);
                    for (PreferitoE prefs : prefE) {
                        if (prefs.getLibroElettronico().equals(codice)) {
                            prefE.remove(prefs);
                            session.setAttribute("prefE", prefE);
                            stampa.print("-1");
                            if(prefE.isEmpty() && prefC == null){
                                stampa.print("-2");
                            } else if (prefC.isEmpty() && prefE.isEmpty()) {
                                stampa.print("-2");
                            }
                            rimosso = true;
                            break;
                        }
                    }
                    if(!rimosso) {
                        for (LibroElettronico l : libriE) {
                            if (codice.equals(l.getCodice())) {
                                prefE.add(pref);
                                session.setAttribute("prefE", prefE);
                                stampa.print("1");
                                break;
                            }
                        }
                    }
                }
            }else {
                PreferitoCDAO preferitoCDAO = new PreferitoCDAO();
                PreferitoEDAO preferitoEDAO = new PreferitoEDAO();
                Utente utente = (Utente) session.getAttribute("utente");
                if(prefC == null){
                    for(LibroCartaceo l : libriC){
                        if(codice.equals(l.getCodice())){
                            PreferitoC pref = new PreferitoC();
                            prefC = new ArrayList<>();
                            pref.setUtente(utente.getEmail());
                            pref.setLibroCartaceo(codice);
                            prefC.add(pref);
                            preferitoCDAO.doSave(pref);
                            session.setAttribute("prefC", prefC);
                            stampa.print("1");
                            break;
                        }
                    }
                }
                else {
                    PreferitoC pref = new PreferitoC();
                    pref.setUtente(utente.getEmail());
                    pref.setLibroCartaceo(codice);
                    for (PreferitoC prefs : prefC) {
                        if (prefs.getLibroCartaceo().equals(codice)) {
                            prefC.remove(prefs);
                            preferitoCDAO.doDelete(pref);
                            session.setAttribute("prefC", prefC);
                            stampa.print("-1");
                            if(prefC.isEmpty() && prefE == null){
                                stampa.print("-2");
                            } else if (prefC.isEmpty() && prefE.isEmpty()) {
                                stampa.print("-2");
                            }
                            rimosso = true;
                            break;
                        }
                    }
                    if (!rimosso) {
                        for (LibroCartaceo l : libriC) {
                            if (codice.equals(l.getCodice())) {
                                prefC.add(pref);
                                preferitoCDAO.doSave(pref);
                                session.setAttribute("prefC", prefC);
                                stampa.print("1");
                                break;
                            }
                        }
                    }
                }
                if(prefE == null) {
                    for (LibroElettronico l : libriE) {
                        if (codice.equals(l.getCodice())) {
                            PreferitoE pref = new PreferitoE();
                            prefE = new ArrayList<>();
                            pref.setUtente(utente.getEmail());
                            pref.setLibroElettronico(codice);
                            prefE.add(pref);
                            preferitoEDAO.doSave(pref);
                            session.setAttribute("prefE", prefE);
                            stampa.print("1");
                            break;
                        }
                    }
                }
                else{
                    PreferitoE pref = new PreferitoE();
                    pref.setUtente(utente.getEmail());
                    pref.setLibroElettronico(codice);
                    for (PreferitoE prefs : prefE) {
                        if (prefs.getLibroElettronico().equals(codice)) {
                            prefE.remove(prefs);
                            preferitoEDAO.doDelete(pref);
                            session.setAttribute("prefE", prefE);
                            stampa.print("-1");
                            if(prefE.isEmpty() && prefC == null){
                                stampa.print("-2");
                            } else if (prefC.isEmpty() && prefE.isEmpty()) {
                                stampa.print("-2");
                            }
                            rimosso = true;
                            break;
                        }
                    }
                    if(!rimosso) {
                        for (LibroElettronico l : libriE) {
                            if (codice.equals(l.getCodice())) {
                                prefE.add(pref);
                                preferitoEDAO.doSave(pref);
                                session.setAttribute("prefE", prefE);
                                stampa.print("1");
                                break;
                            }
                        }
                    }
                }
            }
        }
}
