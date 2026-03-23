package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Concert;
import com.hellmetz.festival.backoffice.model.Scene;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConcertDao {

    public List<Concert> findAll() {
        List<Concert> result = new ArrayList<>();
        String sql = "SELECT id_concert, date_heure_debut, date_heure_fin, statut, id_scene, id_edition, " +
                "heure_balance_debut, heure_balance_fin, decibels_max FROM concert ORDER BY statut";



        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Concert concert = mapResultSet(rs);
                result.add(concert);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public Concert findById(int id) {
        String sql = "SELECT id_concert, date_heure_debut, date_heure_fin, statut, id_scene, id_edition, " +
                "heure_balance_debut, heure_balance_fin, decibels_max FROM concert WHERE id_concert = ?";


        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Concert concert) throws SQLException {
        String sql = "INSERT INTO concert (date_heure_debut, date_heure_fin, statut, id_scene, id_edition, " +
                "heure_balance_debut, heure_balance_fin, decibels_max) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setPreparedStatement(ps, concert);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) concert.setId(rs.getInt(1));
            }
        }
    }

    public void update(Concert concert) throws SQLException {
        String sql = "UPDATE concert SET date_heure_debut=?, date_heure_fin=?, statut=?, id_scene=?, " +
                "id_edition=?, heure_balance_debut=?, heure_balance_fin=?, decibels_max=? WHERE id_concert=?";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            setPreparedStatement(ps, concert);
            ps.setInt(9, concert.getId());
            ps.executeUpdate();
        }
    }

    // Helper pour remplir le PreparedStatement
    private void setPreparedStatement(PreparedStatement ps, Concert concert) throws SQLException {
        ps.setTimestamp(1, Timestamp.valueOf(concert.getDateDebut()));
        ps.setTimestamp(2, concert.getDateFin() != null ? Timestamp.valueOf(concert.getDateFin()) : null);
        ps.setString(3, concert.getStatut());
        ps.setObject(4, concert.getScene());
        ps.setInt(5, concert.getEdition());
        ps.setTimestamp(6, concert.getBalanceDebut() != null ? Timestamp.valueOf(concert.getBalanceDebut()) : null);
        ps.setTimestamp(7, concert.getBalanceFin() != null ? Timestamp.valueOf(concert.getBalanceFin()) : null);
        ps.setInt(8, concert.getDecibels());
    }

    // Helper pour mapper ResultSet → Concert
    private Concert mapResultSet(ResultSet rs) throws SQLException {

        SceneDao sceneDao = new SceneDao();
        Scene scene = new Scene();

        // Recherche de la scene lié au concert via son identifiant (id)
        scene = sceneDao.findById(rs.getInt("id_scene"));

        return new Concert(
                rs.getInt("id_concert"),
                rs.getTimestamp("date_heure_debut").toLocalDateTime(),
                rs.getTimestamp("date_heure_fin") != null ? rs.getTimestamp("date_heure_fin").toLocalDateTime() : null,
                rs.getString("statut"), scene,
                rs.getInt("id_edition"),
                rs.getTimestamp("heure_balance_debut") != null ? rs.getTimestamp("heure_balance_debut").toLocalDateTime() : null,
                rs.getTimestamp("heure_balance_fin") != null ? rs.getTimestamp("heure_balance_fin").toLocalDateTime() : null,
                rs.getInt("decibels_max")
        );
    }
}