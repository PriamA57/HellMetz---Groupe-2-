package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.SceneDao; // ✅ bon import
import com.hellmetz.festival.backoffice.model.Scene;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/backoffice/scenes")
public class SceneListServlet extends HttpServlet {

    private SceneDao sceneDao = new SceneDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Scene> scenes = sceneDao.findAll(); // ✅ instance, pas classe
        req.setAttribute("scenes", scenes);

        req.setAttribute("pageTitle", "HellMetz - Scènes"); // ✅ minuscule
        req.setAttribute("activeMenu", "scenes");
        req.setAttribute("contentPage", "/WEB-INF/backoffice/scenes/list.jsp");

        req.getRequestDispatcher("/WEB-INF/backoffice/layout.jsp").forward(req, resp);
    }
}