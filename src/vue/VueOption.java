/*
 * Decompiled with CFR 0_132.
 */
package vue;

import controller.ControllerVueOption;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.server.Partie;

public class VueOption
extends JPanel {
    private JComboBox tirOrdi;

    public VueOption() {
        JPanel centre = new JPanel();
        centre.setLayout(new GridLayout(3, 1, 50, 50));
        JLabel option = new JLabel("Option de partie");
        option.setFont(new Font("Sans Serif", 0, 20));
        this.tirOrdi = new JComboBox<String>(Partie.tabTireOrdi);
        this.tirOrdi.setPreferredSize(new Dimension(200, 50));
        this.tirOrdi.setSize(new Dimension(200, 50));
        centre.add(option);
        centre.add(this.tirOrdi);
        JPanel sud = new JPanel();
        JButton save = new JButton("Sauvegarder");
        JButton quit = new JButton("Quitter partie");
        JButton retour = new JButton("Valider et Retour");
        save.addActionListener(new ControllerVueOption("save", this));
        quit.addActionListener(new ControllerVueOption("quitter", this));
        retour.addActionListener(new ControllerVueOption("retour", this));
        sud.setLayout(new GridLayout(1, 2, 100, 100));
        sud.add(save);
        sud.add(quit);
        sud.add(retour);
        this.setLayout(new BorderLayout());
        this.add((Component)centre, "Center");
        this.add((Component)sud, "South");
        this.setVisible(true);
    }

    public JComboBox getTirOrdi() {
        return this.tirOrdi;
    }
}

