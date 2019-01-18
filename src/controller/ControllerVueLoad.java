package controller;

import model.server.Partie;
import vue.VueFenetre;
import vue.VueLoad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

public class ControllerVueLoad implements ActionListener {

    private String ope;
    private JPanel jp;
    private JList s;

    public ControllerVueLoad(String ope, JPanel jp, JList s){
        this.ope = ope;
        this.jp = jp;
        this.s =s;
    }

    public ControllerVueLoad(String ope, JPanel jp) {
        this.ope = ope;
        this.jp = jp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre) window;
        }

        switch(ope){
            case "charger":
                File repertoire = new File("./src/fichier_sauvegarde");
                String [] listefichiers;
                listefichiers=repertoire.list();
                try {
                    Partie.setPartieEnCour(Partie.deSerialize(listefichiers[s.getSelectedIndex()].substring(0, listefichiers[s.getSelectedIndex()].length() - 5)));
                    frame.changerPanel("vueJeu");
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                break;

            case "retour":
                try {
                    frame.changerPanel("vueMenuPrincipal");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            default:
        }


    }
}