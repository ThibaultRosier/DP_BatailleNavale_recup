/*
 * Decompiled with CFR 0_132.
 */
package vue;

import controller.ControllerVueNewPartie;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import model.server.Partie;

public class VueNewPartie
extends JPanel {
    private JComboBox typePartie;
    private JComboBox epoque;
    private JComboBox tirOrdi;

    public VueNewPartie() {
        JPanel centre = new JPanel();
        centre.setLayout(new GridLayout(4, 1, 50, 50));
        this.typePartie = new JComboBox<String>(Partie.tabTypePartie);
        this.epoque = new JComboBox<String>(Partie.tabEpoque);
        this.tirOrdi = new JComboBox<String>(Partie.tabTireOrdi);
        this.typePartie.setPreferredSize(new Dimension(100, 50));
        this.typePartie.setSize(new Dimension(100, 50));
        centre.add(this.typePartie);
        centre.add(this.epoque);
        centre.add(this.tirOrdi);
        JPanel sud = new JPanel();
        JButton launch = new JButton("Lancer la partie");
        JButton retour = new JButton("Retour");
        launch.addActionListener(new ControllerVueNewPartie("lancer", this.typePartie, this.epoque, this.tirOrdi, this));
        retour.addActionListener(new ControllerVueNewPartie("retour", this));
        sud.setLayout(new GridLayout(1, 2, 100, 100));
        sud.add(launch);
        sud.add(retour);
        this.setLayout(new BorderLayout());
        this.add((Component)centre, "Center");
        this.add((Component)sud, "South");
        this.setVisible(true);
    }
}

