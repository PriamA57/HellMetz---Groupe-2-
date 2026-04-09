package com.hellmetz.festival.backoffice.model;

public class Artiste {


    /*
        Propriété de la classe Groupe
     */
    private int id_artiste;
    private String nom;
    private String prenom;
    private String nom_scene;
    private String biographie;
    private String url_photo;
    private int id_style;
    private String nationalite;
    private String cachet;
    private String url_facebook;
    private String url_instagram;
    private String url_spotify;
    private String exigences_catering;
    private int id_groupe;

    /*
        ENCAPSULATION - Permet de sécuriser les données
        Des contraintes d'intégrités peuvent être ajouter

        Les méthodes permettant d'accéder et modifier les données utiles
     */
    public int getId_artiste() {
        return id_artiste;
    }

    public String getNom() {
        return nom;
    }

    public String getprenom() {
        return prenom;
    }

    public String getnom_scene() {
        return nom_scene;
    }

    public String getbiographie() {
        return biographie;
    }

    public String geturl_photo() {
        return url_photo;
    }

    public int getid_style() {
        return id_style;
    }

    public String getnationalite() {
        return nationalite;
    }

    public String getcachet() {
        return cachet;
    }

    public String getUrl_instagram() {
        return url_instagram;
    }

    public String geturl_facebook() {
        return url_facebook;
    }

    public String getUrl_spotify() {
        return url_spotify;
    }

    public String getexigences_catering() {
        return exigences_catering;
    }

    public int getid_groupe() {
        return id_groupe;
    }

    public void setId_artiste(int id_artiste) {
        this.id_artiste = id_artiste;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setprenom(String prenom) {
        this.prenom = prenom;
    }

    public void setnom_scene(String nom_scene) {
        this.nom_scene = nom_scene;
    }

    public void setbiographie(String biographie) { this.biographie = biographie; }

    public void seturl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public void setid_style(int id_style) {
        this.id_style = id_style;
    }

    public void setnationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public void setcachet(String cachet) {
        this.cachet = cachet;
    }

    public void setUrl_instagram(String url_instagram) {
        this.url_instagram = url_instagram;
    }

    public void setUrl_facebook(String url_youtube) {
        this.url_facebook = url_facebook;
    }

    public void setUrl_spotify(String url_spotify) {
        this.url_spotify = url_spotify;
    }

    public void setexigences_catering(String exigences_catering) {
        this.exigences_catering = exigences_catering;
    }

    public void setid_groupe(int id_groupe) {
        this.id_groupe = id_groupe;
    }


    public Artiste() {
        this.id_artiste = 0;
        this.nom = null;
        this.prenom = null;
        this.nom_scene = null;
        this.biographie = null;
        this.url_photo = null;
        this.id_style = 0;
        this.nationalite = null;
        this.cachet = null;
        this.url_instagram = null;
        this.url_facebook = null;
        this.url_spotify = null;
        this.exigences_catering = null;
        this.id_groupe = 0;
    }

    public Artiste(int id_artiste, String nom, String prenom, String nom_scene, String biographie, String url_photo, int id_style, String nationalite, String cachet, String url_instagram, String url_facebook, String url_spotify, String exigences_catering, int id_groupe) {
        this.id_artiste = id_artiste;
        this.nom = nom;
        this.prenom = prenom;
        this.nom_scene = nom_scene;
        this.biographie = biographie;
        this.url_photo = url_photo;
        this.id_style = id_style;
        this.nationalite = nationalite;
        this.cachet = cachet;
        this.url_instagram = url_instagram;
        this.url_facebook = url_facebook;
        this.url_spotify = url_spotify;
        this.exigences_catering = exigences_catering;
        this.id_groupe = id_groupe;
    }
}