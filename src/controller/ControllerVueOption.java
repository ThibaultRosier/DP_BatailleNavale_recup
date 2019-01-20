/*
 * Decompiled with CFR 0_132.
 */
package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.server.Partie;
import vue.VueFenetre;
import vue.VueOption;

public class ControllerVueOption
implements ActionListener {
    VueOption jp;
    String ope;

    public ControllerVueOption(String ope, VueOption vueOption) {
        this.jp = vueOption;
        this.ope = ope;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(this.jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre)window;
        }
        switch (this.ope) {
            case "save": {
                try {
                    frame.changerPanel("vueSave");
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "retour": {
                try {
                    Partie.TYPETIR = (String)this.jp.getTirOrdi().getSelectedItem();
                    frame.changerPanel("vueJeu");
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "quitter": {
                try {
                    Partie.setPartieEnCour(null);
                    frame.changerPanel("vueMenuPrincipal");
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

