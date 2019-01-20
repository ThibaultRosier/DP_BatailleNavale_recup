/*
 * Decompiled with CFR 0_132.
 */
package vue;

import controller.ControllerVueCase;
import controller.ControllerVueJeu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.*;
import javax.swing.border.Border;
import model.server.Camp;
import model.server.Partie;
import model.server.batiment.Batiment;
import model.server.batiment.GrandBatiment;
import model.server.batiment.MoyenBatiment;
import model.server.batiment.PetitBatiment;
import model.service.Case;
import vue.Vue;
import vue.VueCase;

public class VueJeu
extends JPanel
implements Vue {
    private JButton[][] tabPlateauDroite;
    private JButton[][] tabPlateauGauche;
    private Partie partie;
    private Case[][] plateauBateaux;
    private Case[][] plateauTires;
    private JPanel plateauDroite;
    private JPanel plateauGauche;

    public VueJeu() throws RemoteException {

        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());

        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        this.setLayout(new BorderLayout());
        this.partie = Partie.getPartieEnCour();
        this.partie.ajouterVue(this);
        Camp c = this.partie.getCampJoueur1();
        this.plateauBateaux = c.getCamp();
        Camp c2 = this.partie.getCampJoueur2();
        this.plateauTires = c2.getCamp();
        this.tabPlateauDroite = new JButton[11][11];
        this.tabPlateauGauche = new JButton[11][11];
        this.plateauDroite = new JPanel();
        this.plateauGauche = new JPanel();
        JPanel nord = new JPanel();
        JPanel sud = new JPanel();
        JButton option = new JButton("Option");
        option.addActionListener(new ControllerVueJeu("option", this));
        nord.add(option);
        JLabel labelDroite = new JLabel("Vos Batiments :");
        labelDroite.setHorizontalAlignment(0);
        JLabel labelGauche = new JLabel("Vos Tirs :");
        labelGauche.setHorizontalAlignment(0);
        JPanel JPdroite = new JPanel();
        JPdroite.setLayout(new BorderLayout());
        JPdroite.add((Component)labelDroite, "North");
        JPdroite.add((Component)this.plateauGauche, "Center");
        JPanel JPgauche = new JPanel();
        JPgauche.setLayout(new BorderLayout());
        JPgauche.add((Component)labelGauche, "North");
        JPgauche.add((Component)this.plateauDroite, "Center");
        JButton tirer = new JButton("Tirer");
        tirer.addActionListener(new ControllerVueJeu("tirer", this));
        sud.add(tirer);
        this.plateauDroite.setLayout(null);
        this.plateauDroite.setPreferredSize(new Dimension(550, 550));
        this.plateauGauche.setLayout(null);
        this.plateauGauche.setPreferredSize(new Dimension(550, 550));
        this.creerBord(this.plateauDroite, this.tabPlateauDroite);
        this.creerBord(this.plateauGauche, this.tabPlateauGauche);
        this.creerPlateauTir(this.plateauDroite, this.tabPlateauDroite);
        this.creerPlateauBateaux(this.plateauGauche, this.tabPlateauGauche);
        JSplitPane js = new JSplitPane(1, JPgauche, JPdroite);
        js.setEnabled(false);
        this.add((Component)js, "Center");
        this.add((Component)nord, "North");
        this.add((Component)sud, "South");
        this.setVisible(true);
    }

    public void creerBord(JPanel jp, JButton[][] tab) {
        tab[0][0] = new JButton();
        tab[0][0].setBorder(BorderFactory.createLineBorder(Color.black));
        tab[0][0].setBounds(0, 0, 50, 50);
        tab[0][0].setEnabled(false);
        jp.add(tab[0][0]);
        for (int j = 1; j < 11; ++j) {
            tab[0][j] = new JButton("" + j + "");
            tab[0][j].setHorizontalAlignment(0);
            tab[0][j].setBorder(BorderFactory.createLineBorder(Color.black));
            tab[0][j].setBounds(0, j * 50, 50, 50);
            tab[0][j].setEnabled(false);
            jp.add(tab[0][j]);
        }
        for (int i = 1; i < 11; ++i) {
            tab[i][0] = new JButton(Character.toString((char)(64 + i)));
            tab[i][0].setHorizontalAlignment(0);
            tab[i][0].setBorder(BorderFactory.createLineBorder(Color.black));
            tab[i][0].setBounds(i * 50, 0, 50, 50);
            tab[i][0].setEnabled(false);
            jp.add(tab[i][0]);
        }
    }

    public void creerPlateauBateaux(JPanel jp, JButton[][] tab) {
        for (int i = 1; i < 11; ++i) {
            for (int j = 1; j < 11; ++j) {
                tab[i][j] = new VueCase(this.plateauBateaux[i - 1][j - 1], null);
                tab[i][j].setBackground(new Color(0, 206, 209));
                if (this.plateauBateaux[i - 1][j - 1].getToucher()) {
                    if (this.plateauBateaux[i - 1][j - 1].getBatiment() != null) {
                        tab[i][j].setBackground(new Color(133, 6, 6));
                    }
                } else if (this.plateauBateaux[i - 1][j - 1].getBatiment() instanceof PetitBatiment) {
                    tab[i][j].setBackground(Color.GRAY);
                } else if (this.plateauBateaux[i - 1][j - 1].getBatiment() instanceof MoyenBatiment) {
                    tab[i][j].setBackground(Color.DARK_GRAY);
                } else if (this.plateauBateaux[i - 1][j - 1].getBatiment() instanceof GrandBatiment) {
                    tab[i][j].setBackground(Color.BLACK);
                }
                tab[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tab[i][j].setBounds(i * 50, j * 50, 50, 50);
                jp.add(tab[i][j]);
            }
        }
    }

    public void creerPlateauTir(JPanel jp, JButton[][] tab) {
        for (int i = 1; i < 11; ++i) {
            for (int j = 1; j < 11; ++j) {
                tab[i][j] = new VueCase(this.plateauTires[i - 1][j - 1], null);
                tab[i][j].addActionListener(new ControllerVueCase(this.plateauTires[i - 1][j - 1]));
                tab[i][j].setBackground(new Color(0, 206, 209));
                if (this.plateauTires[i - 1][j - 1].getToucher()) {
                    if (this.plateauTires[i - 1][j - 1].getBatiment() == null) {
                        tab[i][j].setBackground(new Color(0, 0, 100));
                    } else {
                        tab[i][j].setBackground(new Color(133, 6, 6));
                    }
                }
                tab[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                tab[i][j].setBounds(i * 50, j * 50, 50, 50);
                jp.add(tab[i][j]);
            }
        }
    }

    @Override
    public void update() {
        this.plateauDroite.removeAll();
        this.plateauGauche.removeAll();
        this.creerBord(this.plateauDroite, this.tabPlateauDroite);
        this.creerBord(this.plateauGauche, this.tabPlateauGauche);
        this.creerPlateauTir(this.plateauDroite, this.tabPlateauDroite);
        this.creerPlateauBateaux(this.plateauGauche, this.tabPlateauGauche);
        this.repaint();
    }
}

