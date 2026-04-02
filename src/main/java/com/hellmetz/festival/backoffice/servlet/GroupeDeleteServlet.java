package com.hellmetz.festival.backoffice.servlet;

import com.hellmetz.festival.backoffice.dao.GroupeDao;
import com.hellmetz.festival.backoffice.model.Groupe;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/backoffice/groupes/delete")
public class GroupeDeleteServlet extends HttpServlet {

    private GroupeDao groupeDao = new GroupeDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        // Si un ID est fourni, on charge les données (Mode Modification)
        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                groupeDao.delete(id);

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // 5. Redirection vers la liste des groupes (Pattern POST-Redirect-GET) pour éviter la double soumission du formulaire
        // Note : Il faudra s'assurer que GroupeListServlet écoute bien sur "/backoffice/groupes/list" ou "/backoffice/groupes"
        resp.sendRedirect(req.getContextPath() + "/backoffice/groupes");

    }

}
