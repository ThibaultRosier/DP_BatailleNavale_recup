package controller;

import model.server.Partie;
import model.server.Sauvegarde;
import vue.DialogNewSave;
import vue.VueFenetre;
import vue.VueOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

public class ControllerVueOption implements ActionListener {

    JPanel jp;
    String ope;

    public ControllerVueOption(String ope, VueOption vueOption) {
        jp = vueOption;
        this.ope = ope;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre) window;
        }

        switch(ope){
            case "save":
                try {
                    frame.changerPanel("vueSave");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            case "retour":
                try {
                    frame.changerPanel("vueJeu");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            case "quitter":
                try {
                    Partie.setPartieEnCour(null);
                    frame.changerPanel("vueMenuPrincipal");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            default:
        }
    }
}
