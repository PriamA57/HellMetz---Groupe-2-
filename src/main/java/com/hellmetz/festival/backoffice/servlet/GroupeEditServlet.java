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

        // Si un ID est fourni, on charge les données (Mode Modification)
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
            // Mode Création
            req.setAttribute("pageTitle", "Nouveau groupe - HellMetz");
        }

        // On indique au layout quelle page charger au centre
        req.setAttribute("contentPage", "groupes/edit.jsp");

        // On transfère la requête au layout principal (qui s'occupera d'inclure le header, le menu et la page d'édition)
        this.getServletContext()
                .getRequestDispatcher("/WEB-INF/backoffice/layout.jsp")
                .forward(req, resp);
    }

}