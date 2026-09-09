package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ArtisteDao;
import com.hellmetz.festival.backoffice.dao.GroupeDao;
import com.hellmetz.festival.backoffice.model.Artiste;
import com.hellmetz.festival.backoffice.model.Groupe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/backoffice/artiste/edit")
public class ArtisteEditServlet extends HttpServlet {

    private final ArtisteDao artisteDao = new ArtisteDao();
    private final GroupeDao groupeDao = new GroupeDao();

    /**
     * Affiche le formulaire de création ou de modification
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");

        Artiste artiste = null;

        // Si un ID est présent, on est en modification
        if (idParam != null && !idParam.isEmpty()) {

            try {

                int id = Integer.parseInt(idParam);

                artiste = artisteDao.findById(id);

                if (artiste == null) {
                    resp.sendError(
                            HttpServletResponse.SC_NOT_FOUND,
                            "Artiste introuvable"
                    );
                    return;
                }

            } catch (NumberFormatException e) {

                resp.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "Identifiant invalide"
                );
                return;
            }
        }

        // Récupération des groupes pour le select
        List<Groupe> groupes = groupeDao.findAll();

        req.setAttribute("artiste", artiste);
        req.setAttribute("groupes", groupes);

        // Pour le menu
        req.setAttribute("activeMenu", "artiste");

        // Titre de la page
        if (artiste == null) {
            req.setAttribute("pageTitle", "Nouvel artiste");
        } else {
            req.setAttribute("pageTitle", "Modifier un artiste");
        }

        // JSP à afficher dans le layout
        req.setAttribute(
                "contentPage",
                "/WEB-INF/backoffice/artiste/edit.jsp"
        );

        // Affichage du layout
        req.getRequestDispatcher(
                "/WEB-INF/backoffice/layout.jsp"
        ).forward(req, resp);
    }


    /**
     * Enregistre un nouvel artiste ou modifie un artiste existant
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        // Récupération des données du formulaire
        String idParam = req.getParameter("id_artiste");

        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String nomScene = req.getParameter("nom_scene");
        String biographie = req.getParameter("biographie");
        String urlPhoto = req.getParameter("url_photo");
        String nationalite = req.getParameter("nationalite");
        String cachet = req.getParameter("cachet");
        String urlFacebook = req.getParameter("url_facebook");
        String urlInstagram = req.getParameter("url_instagram");
        String urlSpotify = req.getParameter("url_spotify");
        String exigencesCatering = req.getParameter("exigences_catering");

        // ID du style
        String idStyleParam = req.getParameter("id_style");

        int idStyle = 0;

        if (idStyleParam != null && !idStyleParam.trim().isEmpty()) {
            try {
                idStyle = Integer.parseInt(idStyleParam);
            } catch (NumberFormatException e) {
                resp.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "ID du style invalide"
                );
                return;
            }
        }

        // ID du groupe
        String idGroupeParam = req.getParameter("id_groupe");

        int idGroupe = 0;

        if (idGroupeParam != null && !idGroupeParam.trim().isEmpty()) {
            try {
                idGroupe = Integer.parseInt(idGroupeParam);
            } catch (NumberFormatException e) {
                resp.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "ID du groupe invalide"
                );
                return;
            }
        }

        Artiste artiste;

        /*
         * =========================
         * MODIFICATION
         * =========================
         */
        if (idParam != null && !idParam.trim().isEmpty()) {

            int id;

            try {
                id = Integer.parseInt(idParam);
            } catch (NumberFormatException e) {
                resp.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "ID artiste invalide"
                );
                return;
            }

            artiste = new Artiste(
                    id,
                    nom,
                    prenom,
                    nomScene,
                    biographie,
                    urlPhoto,
                    idStyle,
                    nationalite,
                    cachet,
                    urlInstagram,
                    urlFacebook,
                    urlSpotify,
                    exigencesCatering,
                    idGroupe
            );

            // DEBUG
            System.out.println("=================================");
            System.out.println("MODIFICATION ARTISTE");
            System.out.println("ID : " + artiste.getId_artiste());
            System.out.println("Nom : " + artiste.getNom());
            System.out.println("Prénom : " + artiste.getprenom());
            System.out.println("Nom scène : " + artiste.getnom_scene());
            System.out.println("=================================");

            artisteDao.update(artiste);

        }

        /*
         * =========================
         * NOUVEL ARTISTE
         * =========================
         */
        else {

            artiste = new Artiste(
                    0,
                    nom,
                    prenom,
                    nomScene,
                    biographie,
                    urlPhoto,
                    idStyle,
                    nationalite,
                    cachet,
                    urlInstagram,
                    urlFacebook,
                    urlSpotify,
                    exigencesCatering,
                    idGroupe
            );

            artisteDao.insert(artiste);
        }

        /*
         * Retour à la liste des artistes
         */
        resp.sendRedirect(
                req.getContextPath() + "/backoffice/artiste"
        );
    }
}