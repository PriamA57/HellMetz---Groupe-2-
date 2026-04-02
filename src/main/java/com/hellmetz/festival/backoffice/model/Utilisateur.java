package com.hellmetz.festival.backoffice.model;

public class Utilisateur {
    private Long idUtilisateur;
    private String identifiant;
    private String nom;
    private String prenom;
    private String email;

    // Constructeur
    public Utilisateur(Long idUtilisateur, String identifiant, String nom, String prenom, String email) {
        this.idUtilisateur = idUtilisateur;
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
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

}