package com.agenda.controller;

import com.agenda.model.Evenement;
import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur simple qui gère la liste d'événements en mémoire.
 * Ici on garde tout en mémoire (ArrayList). Pas de persistance sur disque.
 */
public class AgendaController {
    private List<Evenement> evenements;

    public AgendaController() {
        this.evenements = new ArrayList<>();
    }

    /**
     * Ajouter un événement à la liste en mémoire.
     * @param ev l'événement à ajouter
     */
    public void ajouterEvenement(Evenement ev) {
        evenements.add(ev);
    }

    /**
     * Retourne la liste des événements.
     * @return liste d'événements
     */
    public List<Evenement> getEvenements() {
        return evenements;
    }
}
