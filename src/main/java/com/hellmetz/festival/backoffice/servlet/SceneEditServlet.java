package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.GroupeDao;
import com.hellmetz.festival.backoffice.model.Groupe;

import com.hellmetz.festival.backoffice.model.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("backoffice/scenes/edit")
public class SceneEditServlet extends HttpServlet{

    private SceneDao sceneDao = new SceneDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Scene scene = sceneDao.findById(id);
                req.setAttribute("Scenes", scene);
                req.setAttribute("pageTitle", "Modifier la scene - HellMetz");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("pageTitle", "Nouvelle scene - HellMetz");
        }

        req.setAttribute("contentPage", "groupe/edit.jsp");

        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String capacite = req.getParameter("capacite");
        boolean actif = req.getParameter("actif") != null;
        String type_scene = req.getParameter("type_scene");
        int superficie = parseInteger(req.getParameter("superficie"),0);
        String url_plan_technique = req.getParameter("url_plan_technique");

        Scene scene = new Scene(
                0,
                nom, description, capacite, actif, type_scene, superficie, url_plan_technique
        );

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                scene.setId(id); // On assigne le véritable ID
                sceneDao.update(scene); // Met à jour en base de données
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } else {
                scene.insert(scene); // Insère un nouveau groupe en base
            }

            resp.sendRedirect(req.getContextPath() + "/backoffice/scenes");

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
