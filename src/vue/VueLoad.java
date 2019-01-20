/*
 * Decompiled with CFR 0_132.
 */
package vue;

import controller.ControllerVueLoad;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import model.server.Partie;

public class VueLoad
extends JPanel {
    private ArrayList<Partie> lesSauvegardes = new ArrayList();

    public VueLoad() {
        this.chargerSave(new File("./src/fichier_sauvegarde"));
        this.setLayout(new BorderLayout());
        JLabel loadLabel = new JLabel("Charger une partie");
        loadLabel.setFont(new Font("Sans Serif", 0, 20));
        JButton charger = new JButton("Charger");
        JButton retour = new JButton("Retour");
        retour.addActionListener(new ControllerVueLoad("retour", this));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, 2));
        buttonPanel.add(charger);
        buttonPanel.add(retour);
        JList liste = new JList();
        DefaultListModel<Partie> listModel = new DefaultListModel<Partie>();
        int size = this.lesSauvegardes.size();
        for (int index = 0; index < size; ++index) {
            listModel.addElement(this.lesSauvegardes.get(index));
        }
        liste.setModel(listModel);
        charger.addActionListener(new ControllerVueLoad("charger", this, liste));
        JScrollPane js = new JScrollPane(liste);
        js.createVerticalScrollBar();
        this.add((Component)loadLabel, "North");
        this.add((Component)js, "Center");
        this.add((Component)buttonPanel, "South");
        this.setPreferredSize(new Dimension(800, 500));
    }

    private void chargerSave(File repertoire) {
        String[] listefichiers = repertoire.list();
        for (int i = 0; i < listefichiers.length; ++i) {
            if (!listefichiers[i].endsWith(".save")) continue;
            try {
                if (Partie.deSerialize(listefichiers[i].substring(0, listefichiers[i].length() - 5)) == null) continue;
                this.lesSauvegardes.add(Partie.deSerialize(listefichiers[i].substring(0, listefichiers[i].length() - 5)));
                continue;
            }
            catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

