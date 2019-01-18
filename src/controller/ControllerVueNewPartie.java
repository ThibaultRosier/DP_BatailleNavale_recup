package controller;

import model.server.Partie;
import vue.VueFenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ControllerVueNewPartie implements ActionListener {

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
        Window window = SwingUtilities.windowForComponent(jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre) window;
        }

        switch (ope) {
            case "retour":
                try {
                    frame.changerPanel("vueMenuPrincipal");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            case "lancer":
                try {
                    Partie.setPartieEnCour(null);
                    Partie.EPOQUE = (String)epoque.getSelectedItem();
                    Partie.TYPEPARTIE = (String)typePartie.getSelectedItem();
                    Partie p = Partie.getPartieEnCour();
                    frame.changerPanel("vueJeu");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
        }
    }
}