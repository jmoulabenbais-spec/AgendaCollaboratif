package com.agenda.view;

import com.agenda.controller.AgendaController;
import com.agenda.model.Evenement;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Fenêtre principale (MainFrame).
 * Contient un JTabbedPane avec 3 onglets : Liste / Hebdo / Mensuel,
 * et un bouton pour ajouter un événement.
 *
 * Les commentaires sont en français pour aider la compréhension.
 */
public class MainFrame extends JFrame {
    private AgendaController controller;
    private DefaultListModel<Evenement> listModel; // modèle pour JList dans l'onglet "Liste"

    public MainFrame() {
        // Initialisation du contrôleur
        controller = new AgendaController();

        setTitle("Agenda Collaboratif");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Création des onglets
        JTabbedPane tabs = new JTabbedPane();

        // Onglet "Liste" : contiendra une JList montrant les événements
        listModel = new DefaultListModel<>();
        JList<Evenement> jList = new JList<>(listModel);
        JScrollPane listPane = new JScrollPane(jList);
        JPanel listePanel = new JPanel(new BorderLayout());
        listePanel.add(listPane, BorderLayout.CENTER);
        tabs.add("Liste", listePanel);

        // Onglet "Hebdo" et "Mensuel" : pour l'instant vides (squelette)
        tabs.add("Hebdo", new JPanel());
        tabs.add("Mensuel", new JPanel());

        // Bouton pour ajouter un événement
        JButton addButton = new JButton("Ajouter un événement");
        addButton.addActionListener(e -> {
            // Ouvre la fenêtre de dialogue en passant le contrôleur et une référence à MainFrame
            EventDialog dialog = new EventDialog(this, controller);
            dialog.setVisible(true);

            // Après fermeture du dialogue, on met à jour la liste (affichage)
            mettreAJourListe();
        });

        add(tabs, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }

    /**
     * Met à jour le modèle de la JList à partir des événements du contrôleur.
     */
    private void mettreAJourListe() {
        listModel.clear();
        List<Evenement> evs = controller.getEvenements();
        for (Evenement ev : evs) {
            listModel.addElement(ev);
        }
    }

    public static void main(String[] args) {
        // Lancement de l'interface Swing dans l'Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
