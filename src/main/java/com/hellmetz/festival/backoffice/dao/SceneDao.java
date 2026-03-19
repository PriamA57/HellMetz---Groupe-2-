package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Scene;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public static class SceneDao {

    public List<Scene> findAll(){
        List<Scene> result = new ArrayList<>();

        String sql = "select id_scene, nom_scene, description, capacite, actif, type_scene, superficie_m2, url_plan_technique from scene ORDER BY nom_scene";

        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()){
                Scene scene = new Scene(rs.getInt("id_scene"), rs.getString("nom_scene"), rs.getString("description"), rs.getInt("capacite"), rs.getBoolean("actif"), rs.getString("type_scene"), rs.getInt("superficie_m2"), rs.getString("url_plan_technique"));
                result.add(scene);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public Scene findById(int id) {
        String sql = "SELECT * FROM scene WHERE id_scene = ?";
        try (Connection cn = ConnectionFactory.getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Scene scene = new Scene(rs.getInt("id_scene"), rs.getString("nom_scene"), rs.getString("description"), rs.getInt("capacite"), rs.getBoolean("actif"), rs.getString("type_scene"), rs.getInt("superficie_m2"), rs.getString("url_plan_technique"));
                    return scene;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

public void insert(String scene) {
    String sql = "INSERT INTO groupe (nom_scene, description, capacite, actif, type_scene, superficie_m2, url_plan_technique) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection cn = ConnectionFactory.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        fillPreparedStatement(ps, scene);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void update(Scene scene) {
    String sql = "UPDATE scene SET nom_scene=?, description=?, capacite=?, actif=?, type_scene=?, superficie_m2=?, url_plan_technique=? WHERE id_scene=?";

    try (Connection cn = ConnectionFactory.getConnection();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        fillPreparedStatement(ps, scene);
        ps.setInt(17, scene.getId());
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void fillPreparedStatement(PreparedStatement ps, Scene scene) throws SQLException {
    ps.setString(1, scene.getNom());
    ps.setString(2, scene.getDescription());
    ps.setInt(3, scene.getCapacite());
    ps.setBoolean(4, scene.getActif());
    ps.setString(5, scene.getType_scene());
    ps.setInt(6, scene.getSuperficie());
    ps.setString(7, scene.getUrl_plan_technique());
}

}
