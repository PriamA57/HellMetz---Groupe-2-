package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Groupe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupeDao {

    public List<Groupe> findAll() {
        List<Groupe> result = new ArrayList<>();

        String sql = "select id_groupe, nom_groupe, description, actif, id_concert, annee_creation, ville_origine, pays_origine, url_logo, site_web, url_facebook, url_instagram, url_youtube, url_spotify, email_contact, telephone_contact, url_fiche_technique from groupe ORDER BY nom_groupe";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                /*
                    OPTION 1 - Utilisation du constructeur par défaut
                    Utilisation des méthodes de accesseurs et mutateurs
                 */
                /*
                    Groupe groupe = new Groupe();
                    groupe.setId(rs.getInt("id_groupe"));
                    groupe.setNom(rs.getString("nom_groupe"));
                    groupe.setAnnee_creation(rs.getInt("annee_creation"));
                    groupe.setPays_origine(rs.getString("pays_origine"));
                    groupe.setDescription(rs.getString("description"));
                    groupe.setSite_web(rs.getString("site_web"));
                    groupe.setUrl_logo(rs.getString("url_logo"));
                    ...

                    result.add(groupe);

                 */

                /*
                    OPTION 2 - Utilisation du constructeur uniquement
                 */
                Groupe groupe = new Groupe(rs.getInt("id_groupe"), rs.getString("nom_groupe"),
                        rs.getString("description"), rs.getBoolean("actif"),
                        rs.getInt("id_concert"), rs.getInt("annee_creation"),
                        rs.getString("ville_origine"), rs.getString("pays_origine"),
                        rs.getString("url_logo"), rs.getString("site_web"),
                        rs.getString("url_facebook"), rs.getString("url_instagram"),
                        rs.getString("url_youtube"), rs.getString("url_spotify"),
                        rs.getString("email_contact"), rs.getString("telephone_contact"),
                        rs.getString("url_fiche_technique"));

                result.add(groupe);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // pour les SIO1, on se contente de ça
        }

        return result;
    }

    /**
     * Récupère un groupe spécifique par son identifiant.
     */
    public Groupe findById(int id) {
        String sql = "SELECT * FROM groupe WHERE id_groupe = ?";
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Groupe groupe = new Groupe(rs.getInt("id_groupe"), rs.getString("nom_groupe"),
                            rs.getString("description"), rs.getBoolean("actif"),
                            rs.getInt("id_concert"), rs.getInt("annee_creation"),
                            rs.getString("ville_origine"), rs.getString("pays_origine"),
                            rs.getString("url_logo"), rs.getString("site_web"),
                            rs.getString("url_facebook"), rs.getString("url_instagram"),
                            rs.getString("url_youtube"), rs.getString("url_spotify"),
                            rs.getString("email_contact"), rs.getString("telephone_contact"),
                            rs.getString("url_fiche_technique"));
                    return groupe;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // On ajoutera ensuite : insert, update, delete
}
