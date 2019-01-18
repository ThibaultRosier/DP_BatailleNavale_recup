package controller;

import vue.VueFenetre;
import vue.VueGagnant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ControllerVueMenu implements ActionListener {

    private String ope;
    private JPanel jp;

    public ControllerVueMenu(String ope, JPanel jp){
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
            case "newPartie":
                try {
                    frame.changerPanel("vueNewPartie");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            case "load":
                try {
                    frame.changerPanel("vueLoad");
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;

            case "quit":
                frame.dispose();
                break;

            default:
        }


    }
}
