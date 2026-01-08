package com.hellmetz.festival.backoffice.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        try {
            // Recupération des paramètres de connexion à la base de données
            Properties props = new Properties();
            props.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties"));
            URL = props.getProperty("db.url");
            USER = props.getProperty("db.user");
            PASSWORD = props.getProperty("db.password");

            // Chargement du driver JDBC postgresql
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver PostgreSQL introuvable", e);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger le fichier db.properties",e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
