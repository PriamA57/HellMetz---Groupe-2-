package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.GroupeDao;
import com.hellmetz.festival.backoffice.model.Groupe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/backoffice/groupes/edit")
public class GroupeEditServlet extends HttpServlet {

    private GroupeDao groupeDao = new GroupeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Groupe groupe = groupeDao.findById(id);
                req.setAttribute("groupe", groupe);
                req.setAttribute("pageTitle", "Modifier le groupe - HellMetz");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("pageTitle", "Nouveau groupe - HellMetz");
        }

        req.setAttribute("contentPage", "groupes/edit.jsp");

        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        boolean actif = req.getParameter("actif") != null;
        int idConcert = parseInteger(req.getParameter("id_concert"), 0);
        int anneeCreation = parseInteger(req.getParameter("annee_creation"), 0);
        String villeOrigine = req.getParameter("ville_origine");
        String paysOrigine = req.getParameter("pays_origine");
        String urlLogo = req.getParameter("url_logo");
        String siteWeb = req.getParameter("site_web");
        String urlFacebook = req.getParameter("url_facebook");
        String urlInstagram = req.getParameter("url_instagram");
        String urlYoutube = req.getParameter("url_youtube");
        String urlSpotify = req.getParameter("url_spotify");
        String emailContact = req.getParameter("email_contact");
        String telephoneContact = req.getParameter("telephone_contact");
        String urlFicheTechnique = req.getParameter("url_fiche_technique");

        Groupe groupe = new Groupe(
                0,
                nom, description, actif, idConcert, anneeCreation,
                villeOrigine, paysOrigine, urlLogo, siteWeb,
                urlFacebook, urlInstagram, urlYoutube, urlSpotify,
                emailContact, telephoneContact, urlFicheTechnique
        );

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                groupe.setId(id);
                groupeDao.update(groupe);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } // ✅ accolade fermante du catch au bon endroit
        } else {  // ✅ else au bon niveau
            groupeDao.insert(groupe);
        }

        resp.sendRedirect(req.getContextPath() + "/backoffice/groupes");
    }

    private int parseInteger(String value, int defaultValue) {
        if (value != null && !value.trim().isEmpty()) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}