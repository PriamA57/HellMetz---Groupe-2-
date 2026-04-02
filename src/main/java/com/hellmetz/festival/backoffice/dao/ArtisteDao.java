package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Artiste;
import com.hellmetz.festival.backoffice.model.Groupe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtisteDao {
    public List<Artiste> findAll() {
        List<Artiste> result = new ArrayList<>();

        String sql = "select id_artiste, nom, prenom, nom_scene, biographie, url_photo, id_style, nationalite, cachet, url_facebook, url_instagram, url_spotify, exigences_catering, id_groupe from artiste ORDER BY nom_artiste";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Artiste artiste = new Artiste(rs.getInt("id_artiste"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("nom_scene"),
                        rs.getString("biographie"), rs.getString("url_photo"),
                        rs.getInt("id_style"), rs.getString("nationalite"),
                        rs.getString("cachet"), rs.getString("url_instagram"),
                        rs.getString("url_facebook"), rs.getString("url_spotify"),
                        rs.getString("exigences_catering"), rs.getInt("id_groupe"));
                        result.add(artiste);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // pour les SIO1, on se contente de ça
        }

        return result;
    }

    /**
     * Récupère un groupe spécifique par son identifiant.
     */
    public Artiste findById(int id) {
        String sql = "SELECT * FROM artiste WHERE id_artiste = ?";
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Artiste artiste = new Artiste (rs.getInt("id_artiste"), rs.getString("nom"),
                            rs.getString("prenom"), rs.getString("nom_scene"),
                            rs.getString("biographie"), rs.getString("url_photo"),
                            rs.getInt("id_style"), rs.getString("nationalite"),
                            rs.getString("cachet"), rs.getString("url_instagram"),
                            rs.getString("url_facebook"), rs.getString("url_spotify"),
                            rs.getString("exigences_catering"), rs.getInt("id_groupe"));
                    return artiste;
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
    public void insert(Artiste artiste) {
        String sql = "INSERT INTO artiste (id_artiste, nom, prenom, nom_scene, biographie, url_photo, id_style, nationalite, cachet, url_facebook, url_instagram, url_spotify, exigences_catering, id_groupe) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, artiste);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour les informations d'un groupe existant.
     */
    public void update(Artiste artiste) {
        String sql = "UPDATE artiste SET id_artiste=?, nom=?, prenom=?, nom_scene=?, biographie=?, url_photo=?, id_style=?, nationalite=?, cachet=?, url_facebook=?, url_instagram=?, url_spotify=?, exigences_catering=?, id_groupe=? WHERE id_artiste=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, artiste);
            ps.setInt(14, artiste.getId_artiste());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode utilitaire pour remplir les paramètres d'un PreparedStatement.
     */
    private void fillPreparedStatement(PreparedStatement ps, Artiste artiste) throws SQLException {
        ps.setString(1, artiste.getNom());
        ps.setString(2, artiste.getprenom());
        ps.setString(3, artiste.getnom_scene());
        ps.setString(4, artiste.getbiographie());
        ps.setString(5, artiste.geturl_photo());
        ps.setInt(6, artiste.getid_style());
        ps.setString(7, artiste.getnationalite());
        ps.setString(8, artiste.getcachet());
        ps.setString(9, artiste.geturl_facebook());
        ps.setString(10, artiste.getUrl_instagram());
        ps.setString(11, artiste.getUrl_spotify());
        ps.setString(12, artiste.getexigences_catering());
        ps.setInt(13, artiste.getid_groupe());
    }

    public Boolean delete(int id) {
        String sql = "DELETE FROM artiste WHERE id_artiste = ?";
        int resultat = 0;

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            resultat = ps.executeUpdate();
            if (resultat == 1){
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
