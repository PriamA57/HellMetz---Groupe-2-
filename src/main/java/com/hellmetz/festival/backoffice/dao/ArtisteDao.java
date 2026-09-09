package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Artiste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtisteDao {

    /**
     * Récupère tous les artistes.
     */
    public List<Artiste> findAll() {

        List<Artiste> result = new ArrayList<>();

        String sql = "SELECT id_artiste, nom, prenom, nom_scene, biographie, " +
                "url_photo, id_style, nationalite, cachet, url_facebook, " +
                "url_instagram, url_spotify, exigences_catering, id_groupe " +
                "FROM artiste ORDER BY nom";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Artiste artiste = new Artiste(
                        rs.getInt("id_artiste"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("nom_scene"),
                        rs.getString("biographie"),
                        rs.getString("url_photo"),
                        rs.getInt("id_style"),
                        rs.getString("nationalite"),
                        rs.getString("cachet"),
                        rs.getString("url_instagram"),
                        rs.getString("url_facebook"),
                        rs.getString("url_spotify"),
                        rs.getString("exigences_catering"),
                        rs.getInt("id_groupe")
                );

                result.add(artiste);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * Récupère un artiste spécifique par son identifiant.
     */
    public Artiste findById(int id) {

        String sql = "SELECT * FROM artiste WHERE id_artiste = ?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Artiste(
                            rs.getInt("id_artiste"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("nom_scene"),
                            rs.getString("biographie"),
                            rs.getString("url_photo"),
                            rs.getInt("id_style"),
                            rs.getString("nationalite"),
                            rs.getString("cachet"),
                            rs.getString("url_instagram"),
                            rs.getString("url_facebook"),
                            rs.getString("url_spotify"),
                            rs.getString("exigences_catering"),
                            rs.getInt("id_groupe")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * Insère un nouvel artiste dans la base de données.
     */
    public void insert(Artiste artiste) {

        String sql = "INSERT INTO artiste " +
                "(nom, prenom, nom_scene, biographie, url_photo, id_style, nationalite, cachet, " +
                "url_facebook, url_instagram, url_spotify, exigences_catering, id_groupe) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, artiste);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Met à jour les informations d'un artiste existant.
     */
    public void update(Artiste artiste) {

        String sql = "UPDATE artiste SET " +
                "nom=?, " +
                "prenom=?, " +
                "nom_scene=?, " +
                "biographie=?, " +
                "url_photo=?, " +
                "id_style=?, " +
                "nationalite=?, " +
                "cachet=?, " +
                "url_facebook=?, " +
                "url_instagram=?, " +
                "url_spotify=?, " +
                "exigences_catering=?, " +
                "id_groupe=? " +
                "WHERE id_artiste=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            fillPreparedStatement(ps, artiste);

            ps.setInt(14, artiste.getId_artiste());

            int resultat = ps.executeUpdate();

            System.out.println("Nombre de lignes modifiées : " + resultat);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Supprime un artiste.
     */
    public boolean delete(int id) {

        String sql = "DELETE FROM artiste WHERE id_artiste = ?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Remplit les paramètres communs pour INSERT et UPDATE.
     */
    private void fillPreparedStatement(
            PreparedStatement ps,
            Artiste artiste
    ) throws SQLException {

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
}