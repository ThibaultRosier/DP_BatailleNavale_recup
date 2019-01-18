package controller;

import model.server.Partie;
import vue.VueFenetre;
import vue.VueJeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ControllerVueJeu implements ActionListener {

    private JPanel vueJeu;
    private String ope;
    private Partie p;

    public ControllerVueJeu(String ope, VueJeu vueJeu) {
        this.vueJeu = vueJeu;
        this.ope = ope;
        try {
            p = Partie.getPartieEnCour();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(vueJeu);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre) window;
        }
        switch(ope){
            case "tirer":
                p.tireNormal();
                if(p.getGagnantNormal() != null){
                    try {
                        frame.changerPanel("vueGagnant");
                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }
                }
                break;

            case "option":
                try {
                    frame.changerPanel("vueOption");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            default:
        }
    }
}