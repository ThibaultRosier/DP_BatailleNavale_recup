package controller;

import vue.VueFenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ControllerVueGagnant implements ActionListener {

    private JLabel jl;
    private JPanel jp;

    public ControllerVueGagnant(JLabel nomLabel, JPanel jp) {
        this.jl =nomLabel;
        this.jp = jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre) window;
        }
        try {
            frame.changerPanel("vueMenuPrincipal");

        } catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }
}
