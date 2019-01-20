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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.server.Partie;
import vue.VueFenetre;

public class ControllerVueNewPartie
implements ActionListener {
    private String ope;
    private JPanel jp;
    private JComboBox typePartie;
    private JComboBox epoque;
    private JComboBox tirOrdi;

    public ControllerVueNewPartie(String ope, JPanel jp) {
        this.ope = ope;
        this.jp = jp;
    }

    public ControllerVueNewPartie(String ope, JComboBox typePartie, JComboBox epoque, JComboBox tirOrdi, JPanel jp) {
        this.ope = ope;
        this.jp = jp;
        this.tirOrdi = tirOrdi;
        this.epoque = epoque;
        this.typePartie = typePartie;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(this.jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre)window;
        }
        switch (this.ope) {
            case "retour": {
                try {
                    frame.changerPanel("vueMenuPrincipal");
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "lancer": {
                try {
                    Partie.setPartieEnCour(null);
                    Partie.EPOQUE = (String)this.epoque.getSelectedItem();
                    Partie.TYPEPARTIE = (String)this.typePartie.getSelectedItem();
                    Partie.TYPETIR = (String)this.tirOrdi.getSelectedItem();
                    Partie p = Partie.getPartieEnCour();
                    frame.changerPanel("vueJeu");
                    break;
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

