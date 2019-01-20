/*
 * Decompiled with CFR 0_132.
 */
package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.server.Joueur;
import model.server.Partie;
import vue.VueFenetre;
import vue.VueJeu;

public class ControllerVueJeu
implements ActionListener {
    private JPanel vueJeu;
    private String ope;
    private Partie p;

    public ControllerVueJeu(String ope, VueJeu vueJeu) {
        this.vueJeu = vueJeu;
        this.ope = ope;
        try {
            this.p = Partie.getPartieEnCour();
        }
        catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(this.vueJeu);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre)window;
        }
        switch (this.ope) {
            case "tirer": {
                this.p.tireNormal();
                if (this.p.getGagnantNormal() == null) break;
                try {
                    frame.changerPanel("vueGagnant");
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "option": {
                try {
                    frame.changerPanel("vueOption");
                    break;
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                    break;
                }
            }
        }
    }
}

