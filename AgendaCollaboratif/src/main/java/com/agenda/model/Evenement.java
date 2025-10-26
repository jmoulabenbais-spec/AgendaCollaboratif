package com.agenda.model;

/**
 * Classe représentant un événement simple.
 * Contient titre, date et description.
 */
public class Evenement {
    private String titre;
    private String date;
    private String description;

    public Evenement(String titre, String date, String description) {
        this.titre = titre;
        this.date = date;
        this.description = description;
    }

    // Getters et setters
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        // Utilisé pour l'affichage dans la liste (titre - date)
        return titre + "  -  " + date;
    }
}
