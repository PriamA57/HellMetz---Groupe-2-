package com.hellmetz.festival.backoffice.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String URL_BDD;
    private static final String UTILISATEUR_BDD;
    private static final String MOT_DE_PASSE_BDD;
    private static final String DRIVER;

    static {
        try {
            // Recupération des paramètres de connexion à la base de données
            Properties proprietes = new Properties();
            proprietes.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties"));
            URL_BDD          = proprietes.getProperty("db.url");
            UTILISATEUR_BDD  = proprietes.getProperty("db.user");
            MOT_DE_PASSE_BDD = proprietes.getProperty("db.password");
            DRIVER           = proprietes.getProperty("db.driver");

            // Chargement du driver JDBC postgresql
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver PostgreSQL introuvable", e);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger le fichier db.properties",e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_BDD, UTILISATEUR_BDD, MOT_DE_PASSE_BDD);
    }
}
