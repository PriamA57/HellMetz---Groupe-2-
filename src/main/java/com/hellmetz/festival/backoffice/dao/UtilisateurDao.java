package com.hellmetz.festival.backoffice.dao;

import com.hellmetz.festival.backoffice.model.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDao {
    /**
     * Autentifie un utilisateur, vérifie son mot de passe avec jBCrypt,
     * et charge ses rôle et permissions s'il est valide.
     */
    public Utilisateur getUnUtilisateur(String identifiant, String motDePasseClair) {
        Utilisateur utilisateur = null;

        //1.Requête pour récupérer les infos de base et le hash du mot de passe
        String queryUser = "SELECT id_utilisateur, prenom, nom, mot_de_passe" +
                            "FROM utilisateur WHERE identifiant = ? AND actif = true";

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement pstmtUser = conn.prepareStatement(queryUser)){

            pstmtUser.setString(1, identifiant);

            try (ResultSet rsUser = pstmtUser.executeQuery()){
                if (rsUser.next()) {
                    String hashEnBase = rsUser.getString("mot_de_passe");

                    //2. Verification sécurisée avec jBCrypt
                    if (BCrypt.checkpw(motDePasseClair, hashEnBase)) {

                        //Le mot de passe est correct, on hydrate l'objet (SANS le mot de passe)
                        utilisateur = new Utilisateur();
                        utilisateur.setIdUtilisateur(rsUser.getLong("id_utilisateur"));
                        utilisateur.setPrenom(rsUser.getString("prenom"));
                        utilisateur.setNom(rsUser.getString("nom"));
                        utilisateur.setIdentifiant(identifiant);

                        //3. Récupération des Rôles
                        chargerRoles(conn, utilisateur);

                        //4. Récupération des Permissions
                        chargerPermissions(conn,utilisateur);

                    }
                }
            }
        } catch (SQLException e){
            System.err.println("Erreur SQL lors de l'authentification :" +e.getMessage());
            e.printStackTrace();
        }
        return utilisateur;//Retourne l'utilisateur complet ou null en cas d'échec
    }
    /**
     * méthode privée pour charger les rôles de l'utilisateur
     */
    private  void chargerRoles(Connection conn, Utilisateur utilisateur) throws SQLException {
        String queryRoles = "SELECT R.code_role FROM role r" +
                            "JOIN role_utilisateur ru ON r.id_role = ru.id_role" +
                            "WHERE ru.id_utilisateur = ?";
        try (PreparedStatement pstmtRoles = conn.prepareStatement(queryRoles)) {
            pstmtRoles.setLong (1,utilisateur.getIdUtilisateur());
            try (ResultSet rsRoles = pstmtRoles.executeQuery()){
                while (rsRoles.next()){
                    utilisateur.addRole(rsRoles.getString("code_role"));
                }
            }
        }
    }
    /**
     * Méthode privée pour charger toutes les permissions liées aux rôles de l'utilisateur
     */
    private void chargerPermissions(Connection conn, Utilisateur utilisateur) throws SQLException{
        //Cette requête joint les permissions aux rôles, eux-mêmes joints à l'utilisateur
        String queryPerms = "SELECT DISTINCT p.code_permission FROM permission p"+
                            "JOIN role_permission rp ON p.id_permission = rp.id_permission" +
                            "JOIN role_utilisateur ru ON rp.id_role = ru.id_role" +
                            "WHERE ru.id_utilisateur = ?";

        try (PreparedStatement pstmtPerms = conn.prepareStatement(queryPerms)){
            pstmtPerms.setLong(1, utilisateur.getIdUtilisateur());
            try (ResultSet rsPerms = pstmtPerms.executeQuery()) {
                while (rsPerms.next()) {
                    utilisateur.addPermission(rsPerms.getString("code_permission"));
                }
            }
        }
    }
}