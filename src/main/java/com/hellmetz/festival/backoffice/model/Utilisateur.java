package com.hellmetz.festival.backoffice.model;

public class Utilisateur {
    private Long idUtilisateur;
    private String identifiant;
    private String nom;
    private String prenom;
    private String email;
    private boolean actif;
    private Long date_creation;
    private Long derniere_connexion;

    // Constructeur Utilisateur !!
    public Utilisateur(Long idUtilisateur, String identifiant, String nom, String prenom, String email, boolean actif, Long date_creation, Long derniere_connexion) {
        this.idUtilisateur = idUtilisateur;
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.actif = actif;
        this.date_creation = date_creation;
        this.derniere_connexion = derniere_connexion;
    }

    // Getters et Setters
    public Long getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(Long idUtilisateur) { this.idUtilisateur = idUtilisateur; }

    public String getIdentifiant() { return identifiant; }
    public void setIdentifiant(String identifiant) { this.identifiant = identifiant; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean getActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }

    public Long getDate_creation() { return date_creation; }
    public void setDate_creation(Long date_creation) { this.date_creation = date_creation; }

    public Long getDerniere_connexion() { return derniere_connexion; }
    public void setDerniere_connexion(Long derniere_connexion) { this.derniere_connexion = derniere_connexion; }
}