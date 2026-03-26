package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Groupe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupeDao {

    public List<Groupe> findAll() {
        List<Groupe> result = new ArrayList<>();

        String sql = "select id_groupe, nom_groupe, description, actif, annee_creation, ville_origine, pays_origine, url_logo, site_web, url_facebook, url_instagram, url_youtube, url_spotify, email_contact, telephone_contact, url_fiche_technique from groupe ORDER BY nom_groupe";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Groupe groupe = new Groupe(rs.getInt("id_groupe"), rs.getString("nom_groupe"),
                        rs.getString("description"), rs.getBoolean("actif"), rs.getInt("annee_creation"),
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
                            rs.getInt("annee_creation"),
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

    /**
     * Insère un nouveau groupe dans la base de données.
     */
    public void insert(Groupe groupe) {
        String sql = "INSERT INTO groupe (nom_groupe, description, actif, id_concert, annee_creation, ville_origine, pays_origine, url_logo, site_web, url_facebook, url_instagram, url_youtube, url_spotify, email_contact, telephone_contact, url_fiche_technique) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, groupe);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour les informations d'un groupe existant.
     */
    public void update(Groupe groupe) {
        String sql = "UPDATE groupe SET nom_groupe=?, description=?, actif=?, id_concert=?, annee_creation=?, ville_origine=?, pays_origine=?, url_logo=?, site_web=?, url_facebook=?, url_instagram=?, url_youtube=?, url_spotify=?, email_contact=?, telephone_contact=?, url_fiche_technique=? WHERE id_groupe=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, groupe);
            ps.setInt(17, groupe.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode utilitaire pour remplir les paramètres d'un PreparedStatement.
     */
    private void fillPreparedStatement(PreparedStatement ps, Groupe groupe) throws SQLException {
        ps.setString(1, groupe.getNom());
        ps.setString(2, groupe.getDescription());
        ps.setBoolean(3, groupe.getActif());
        ps.setInt(4, groupe.getAnnee_creation());
        ps.setString(5, groupe.getVille_origine());
        ps.setString(6, groupe.getPays_origine());
        ps.setString(7, groupe.getUrl_logo());
        ps.setString(8, groupe.getSite_web());
        ps.setString(9, groupe.getUrl_facebook());
        ps.setString(10, groupe.getUrl_instagram());
        ps.setString(11, groupe.getUrl_youtube());
        ps.setString(12, groupe.getUrl_spotify());
        ps.setString(13, groupe.getEmail_contact());
        ps.setString(14, groupe.getTelephone_contact());
        ps.setString(15, groupe.getUrl_fiche_technique());
    }

    // On ajoutera ensuite : delete
}
