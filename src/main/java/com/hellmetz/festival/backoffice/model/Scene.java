package com.hellmetz.festival.backoffice.model;

public class Scene {
    /*
        Propriété de la classe Groupe
     */

    private int id;
    private String nom;
    private String description;
    private int capacite;
    private Boolean actif;
    private String type_scene;
    private int superficie;
    private String url_plan_technique;

    /*
        ENCAPSULATION - Permet de sécuriser les données
        Des contraintes d'intégrités peuvent être ajouter

        Les méthodes permettant d'accéder et modifier les données utiles
     */

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacite() {
        return capacite;
    }

    public Boolean getActif() {
        return actif;
    }

    public String getType_scene() {
        return type_scene;
    }

    public int getSuperficie() {
        return superficie;
    }

    public String getUrl_plan_technique() {
        return url_plan_technique;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public void setType_scene(String type_scene) {
        this.type_scene = type_scene;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public void setUrl_plan_technique(String urlPlanTechnique) {
        this.url_plan_technique = urlPlanTechnique;
    }

    public Scene() {
        this.id = 0;
        this.nom = "Undefined";
        this.description = "Undefined";
        this.capacite = 0;
        this.actif = false;
        this.type_scene = "";
        this.superficie = 0;
        this.url_plan_technique = "";
    }

    public Scene(int id, String nom_scene, String description, int capacite, Boolean actif, String type_scene, int superficie, String url_plan_technique){
        this.id = id;
        this.nom = nom_scene;
        this.description = description;
        this.capacite = capacite;
        this.actif = actif;
        this.type_scene = type_scene;
        this.superficie = superficie;
        this.url_plan_technique = url_plan_technique;
    }

}
