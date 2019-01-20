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
import vue.VueFenetre;

public class ControllerVueMenu
implements ActionListener {
    private String ope;
    private JPanel jp;

    public ControllerVueMenu(String ope, JPanel jp) {
        this.ope = ope;
        this.jp = jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Window window = SwingUtilities.windowForComponent(this.jp);
        VueFenetre frame = null;
        if (window instanceof JFrame) {
            frame = (VueFenetre)window;
        }
        switch (this.ope) {
            case "newPartie": {
                try {
                    frame.changerPanel("vueNewPartie");
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "load": {
                try {
                    frame.changerPanel("vueLoad");
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "quit": {
                frame.dispose();
                break;
            }
        }
    }
}

