/*
 * Decompiled with CFR 0_132.
 */
package vue;

import controller.ControllerVueMenu;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VueMenuPrincipal
extends JPanel {
    public VueMenuPrincipal() {
        JButton newPartie = new JButton("Nouvelle Partie");
        JButton loadPartie = new JButton("Charger Partie");
        JButton quit = new JButton("Quitter");
        newPartie.addActionListener(new ControllerVueMenu("newPartie", this));
        loadPartie.addActionListener(new ControllerVueMenu("load", this));
        quit.addActionListener(new ControllerVueMenu("quit", this));
        newPartie.setPreferredSize(new Dimension(200, 50));
        newPartie.setSize(new Dimension(200, 50));
        this.setLayout(new GridLayout(3, 1, 50, 50));
        this.add(newPartie);
        this.add(loadPartie);
        this.add(quit);
        this.setVisible(true);
    }
}

