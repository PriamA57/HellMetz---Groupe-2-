package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.ArtisteDao;
import com.hellmetz.festival.backoffice.model.Artiste;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/backoffice/artiste")
public class ArtisteListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ArtisteDao artisteDao = new ArtisteDao();

        List<Artiste> artistes = artisteDao.findAll();

        req.setAttribute("artistes", artistes);

        // Menu gauche
        req.setAttribute("activeMenu", "artiste");

        // Titre de la page
        req.setAttribute("pageTitle", "Artistes");

        // JSP qui sera inséré dans layout.jsp
        req.setAttribute("contentPage", "/WEB-INF/backoffice/artiste/list.jsp");

        // Affichage du layout complet
        req.getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }
}