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
    private String code_role;
    private String code_permission;

    // Constructeur Utilisateur !!
    public Utilisateur() {
        this.idUtilisateur = idUtilisateur;
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.actif = actif;
        this.date_creation = date_creation;
        this.derniere_connexion = derniere_connexion;
        this.code_role = code_role;
        this.code_permission = code_permission;
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

    public String getCode_role() { return code_role; }
    public void setCode_role(String  code_role) { this.code_role = code_role; }

    public String getCode_permission() { return code_permission; }
    public void setCode_permission(String  code_permission) { this.code_permission = code_permission; }

    public void addRole(String code_role) {
    }

    public void addPermission(String code_permission) {
    }
}