package com.hellmetz.festival.backoffice.model;

import org.eclipse.tags.shaded.org.apache.xpath.objects.XString;

import java.time.LocalDateTime;

public class Concert {

    private int id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String statut;
    private Scene scene;
    private int edition;
    private LocalDateTime balanceDebut;
    private LocalDateTime balanceFin;
    private int decibels;

    // Enum pour le statut
    public enum Statut {
        PROGRAMME,
        ANNULE,
        REPORTE
    }

    // Constructeur
    public Concert(int id, LocalDateTime dateDebut, LocalDateTime dateFin, String statut,
                   Scene scene, int edition, LocalDateTime balanceDebut, LocalDateTime balanceFin, int decibels) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.scene = scene;
        this.edition = edition;
        this.balanceDebut = balanceDebut;
        this.balanceFin = balanceFin;
        this.decibels = decibels;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }

    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public Scene getScene() { return scene; }
    public void setScene(Scene scene) { this.scene = scene; }

    public int getEdition() { return edition; }
    public void setEdition(int edition) { this.edition = edition; }

    public LocalDateTime getBalanceDebut() { return balanceDebut; }
    public void setBalanceDebut(LocalDateTime balanceDebut) { this.balanceDebut = balanceDebut; }

    public LocalDateTime getBalanceFin() { return balanceFin; }
    public void setBalanceFin(LocalDateTime balanceFin) { this.balanceFin = balanceFin; }

    public int getDecibels() { return decibels; }
    public void setDecibels(int decibels) { this.decibels = decibels; }
}