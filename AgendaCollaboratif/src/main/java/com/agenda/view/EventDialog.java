package com.agenda.view;

import com.agenda.controller.AgendaController;
import com.agenda.model.Evenement;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre modale pour ajouter un événement.
 * Contient des champs : Titre, Date, Description.
 * Quand l'utilisateur clique sur "Enregistrer" :
 *  - on crée un Evenement
 *  - on l'ajoute au controller
 *  - on affiche une boîte de dialogue avec les détails (Titre/Date/Description)
 *  - on ferme la fenêtre d'ajout
 *
 * Tous les commentaires sont en français.
 */
public class EventDialog extends JDialog {
    private JTextField titreField;
    private JTextField dateField;
    private JTextArea descArea;
    private AgendaController controller;

    public EventDialog(Frame parent, AgendaController controller) {
        super(parent, "Ajouter un événement", true);
        this.controller = controller;

        // Layout simple en GridBag pour aligner les champs
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Titre
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Titre:"), gbc);
        titreField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        add(titreField, gbc);

        // Date
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Date:"), gbc);
        dateField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        add(dateField, gbc);

        // Description (textarea)
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Description:"), gbc);
        descArea = new JTextArea(5, 20);
        JScrollPane scroll = new JScrollPane(descArea);
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Boutons Enregistrer / Annuler
        JPanel btnPanel = new JPanel();
        JButton save = new JButton("Enregistrer");
        JButton cancel = new JButton("Annuler");
        btnPanel.add(save);
        btnPanel.add(cancel);
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnPanel, gbc);

        // Action du bouton Annuler : fermer la boîte
        cancel.addActionListener(e -> dispose());

        // Action du bouton Enregistrer
        save.addActionListener(e -> {
            String titre = titreField.getText().trim();
            String date = dateField.getText().trim();
            String desc = descArea.getText().trim();

            // Validation simple : titre non vide
            if (titre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Le titre ne peut pas être vide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Création de l'événement et ajout au controller
            Evenement ev = new Evenement(titre, date, desc);
            controller.ajouterEvenement(ev);

            // Affichage d'une boîte contenant les détails de l'événement (Titre, Date, Description)
            String message = "Titre : " + ev.getTitre() + "\n"
                           + "Date : " + ev.getDate() + "\n"
                           + "Description : " + ev.getDescription();
            JOptionPane.showMessageDialog(this, message, "Détails de l'événement", JOptionPane.INFORMATION_MESSAGE);

            // Fermer la fenêtre d'ajout après confirmation
            dispose();
        });

        pack();
        setLocationRelativeTo(parent);
    }
}
