/*
 * Decompiled with CFR 0_132.
 */
package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import vue.VueFenetre;

public class ControllerVueGagnant
implements ActionListener {
    private JLabel jl;
    private JPanel jp;

    public ControllerVueGagnant(JLabel nomLabel, JPanel jp) {
        this.jl = nomLabel;
        this.jp = jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(this.jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre)window;
        }
        try {
            frame.changerPanel("vueMenuPrincipal");
        }
        catch (RemoteException e1) {
            e1.printStackTrace();
        }
    }
}

