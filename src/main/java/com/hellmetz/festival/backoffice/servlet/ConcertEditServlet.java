package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ConcertDao;
import com.hellmetz.festival.backoffice.dao.SceneDao;
import com.hellmetz.festival.backoffice.model.Concert;
import com.hellmetz.festival.backoffice.model.Concert.Statut;
import com.hellmetz.festival.backoffice.model.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/backoffice/concert/edit")
public class ConcertEditServlet extends HttpServlet {

    private ConcertDao concertDao = new ConcertDao();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); // pour input datetime-local

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Concert concert = concertDao.findById(id);
                req.setAttribute("concert", concert);
                req.setAttribute("pageTitle", "Modifier le concert - HellMetz");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("pageTitle", "Nouveau concert - HellMetz");
        }

        req.setAttribute("contentPage", "/WEB-INF/backoffice/concert/edit.jsp");
        req.getRequestDispatcher("/WEB-INF/backoffice/layout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = parseInteger(req.getParameter("id"), 0);

        // ✅ Parsing sécurisé des LocalDateTime
        LocalDateTime dateDebut = parseDateTime(req.getParameter("dateDebut"));
        LocalDateTime dateFin = parseDateTime(req.getParameter("dateFin"));
        LocalDateTime balanceDebut = parseDateTime(req.getParameter("balanceDebut"));
        LocalDateTime balanceFin = parseDateTime(req.getParameter("balanceFin"));

        // ✅ Parsing des autres champs
        String statut = req.getParameter("statut");
        int scene = parseInteger(req.getParameter("scene"), 0);
        int edition = parseInteger(req.getParameter("edition"), 0);
        int decibels = parseInteger(req.getParameter("decibels"), 0);

        // Recherche de la scene en fonction de son identifiant
        SceneDao sceneDao = new SceneDao();
        Scene scene1 = sceneDao.findById(scene);

        Concert concert = new Concert(id, dateDebut, dateFin, statut, scene1, edition, balanceDebut, balanceFin, decibels);

        try {
            if (id > 0) {
                concertDao.update(concert);
            } else {
                concertDao.insert(concert);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erreur lors de la sauvegarde du concert", e);
        }

        resp.sendRedirect(req.getContextPath() + "/backoffice/concert");
    }

    // Méthode utilitaire : parsing int avec valeur par défaut
    private int parseInteger(String value, int defaultValue) {
        try { return Integer.parseInt(value); }
        catch (Exception e) { return defaultValue; }
    }

    // Méthode utilitaire : parsing LocalDateTime avec gestion des champs vides
    private LocalDateTime parseDateTime(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(value, formatter);
    }
}