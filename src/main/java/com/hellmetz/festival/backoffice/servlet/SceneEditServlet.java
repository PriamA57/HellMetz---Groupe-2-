package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.SceneDao; // ✅ bon import
import com.hellmetz.festival.backoffice.model.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/backoffice/scenes/edit") // ✅ slash initial ajouté
public class SceneEditServlet extends HttpServlet {

    private SceneDao sceneDao = new SceneDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Scene scene = sceneDao.findById(id);
                req.setAttribute("scene", scene); // ✅ minuscule
                req.setAttribute("pageTitle", "Modifier la scène - HellMetz");
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            req.setAttribute("pageTitle", "Nouvelle scène - HellMetz");
        }

        req.setAttribute("contentPage", "/WEB-INF/backoffice/scenes/edit.jsp"); // ✅
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        int capacite = parseInteger(req.getParameter("capacite"), 0); // ✅ converti en int
        boolean actif = req.getParameter("actif") != null;
        String type_scene = req.getParameter("type_scene");
        int superficie = parseInteger(req.getParameter("superficie"), 0);
        String url_plan_technique = req.getParameter("url_plan_technique");

        Scene scene = new Scene(
                0, nom, description, capacite, actif, type_scene, superficie, url_plan_technique
        );

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                scene.setId(id);
                sceneDao.update(scene);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        } else {
            sceneDao.insert(scene); // ✅ sceneDao.insert, pas scene.insert
        }

        resp.sendRedirect(req.getContextPath() + "/backoffice/scenes"); // ✅ hors du if
    }

    // ✅ méthode déclarée au bon niveau, dans la classe mais hors de doPost
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