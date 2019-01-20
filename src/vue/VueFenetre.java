/*
 * Decompiled with CFR 0_132.
 */
package vue;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.rmi.RemoteException;
import javax.swing.*;

import vue.VueGagnant;
import vue.VueJeu;
import vue.VueLoad;
import vue.VueMenuPrincipal;
import vue.VueNewPartie;
import vue.VueOption;
import vue.VueSave;

public class VueFenetre
extends JFrame {
    private JPanel vueActu;
    private static int LARGEUR = 1000;
    private static int HAUTEUR = 1000;
    private GridBagConstraints gbc;

    public VueFenetre() {
        super("Master and Commander");
        this.setSize(1500, 1000);

        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setExtendedState(6);
        this.setLayout(new GridBagLayout());
        this.gbc = new GridBagConstraints(0, 0, 2, 1, 0.25, 0.0, 19, 3, new Insets(1, 1, 1, 1), 0, 0);
        this.vueActu = new VueMenuPrincipal();
        JLabel titre = new JLabel("Master and Commander");
        titre.setFont(new Font("Sans Serif", 0, 30));
        this.add((Component)titre, this.gbc);
        this.gbc.anchor = 10;
        this.gbc.gridy = 2;
        this.gbc.gridwidth = 1;
        this.add((Component)this.vueActu, this.gbc);
        this.setVisible(true);
        this.setDefaultCloseOperation(2);
    }

    public void changerPanel(String vue) throws RemoteException {
        this.remove(this.vueActu);
        switch (vue) {
            case "vueMenuPrincipal": {
                this.vueActu = new VueMenuPrincipal();
                break;
            }
            case "vueLoad": {
                this.vueActu = new VueLoad();
                break;
            }
            case "vueNewPartie": {
                this.vueActu = new VueNewPartie();
                break;
            }
            case "vueSave": {
                this.vueActu = new VueSave();
                break;
            }
            case "vueOption": {
                this.vueActu = new VueOption();
                break;
            }
            case "vueJeu": {
                this.vueActu = new VueJeu();
                break;
            }
            case "vueGagnant": {
                this.vueActu = new VueGagnant();
                break;
            }
        }
        this.add((Component)this.vueActu, this.gbc);
        this.repaint();
        this.revalidate();
    }

    public static void main(String[] args) throws RemoteException {
        new VueFenetre();
    }
}

