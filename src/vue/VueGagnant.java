/*
 * Decompiled with CFR 0_132.
 */
package vue;

import controller.ControllerVueGagnant;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.server.Joueur;
import model.server.Partie;

public class VueGagnant
extends JPanel {
    public VueGagnant() {
        Partie p = null;
        try {
            p = Partie.getPartieEnCour();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
        this.setSize(300, 150);
        this.setLayout(new BorderLayout());
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, 2));
        JLabel nomLabel = new JLabel();
        if (p.getGagnantNormal() == p.getJoueur1()) {
            nomLabel.setText("Vous avez Gagn\u00e9!");
        } else {
            nomLabel.setText("Vous avez Perdu!");
        }
        center.add(nomLabel);
        JButton quitter = new JButton("quitter");
        quitter.addActionListener(new ControllerVueGagnant(nomLabel, this));
        this.add((Component)center, "North");
        this.add((Component)quitter, "South");
        this.setVisible(true);
    }
}

