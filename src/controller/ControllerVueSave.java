/*
 * Decompiled with CFR 0_132.
 */
package controller;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.server.Partie;
import vue.DialogNewSave;
import vue.VueFenetre;
import vue.VueSave;

public class ControllerVueSave
implements ActionListener {
    private String ope;
    private VueSave jp;
    private JList liste;

    public ControllerVueSave(String ope, VueSave jp, JList liste) {
        this.ope = ope;
        this.jp = jp;
        this.liste = liste;
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
                if (!this.liste.isSelectionEmpty()) {
                    Partie p = (Partie)this.liste.getSelectedValue();
                    try {
                        p.serialize(((Partie)this.liste.getSelectedValue()).getNom());
                    }
                    catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
                JOptionPane.showMessageDialog(frame, "Aucune sauvegarde selectionn\u00e9e");
                break;
            }
            case "retour": {
                try {
                    frame.changerPanel("vueOption");
                }
                catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "newSave": {
                new DialogNewSave(this.jp);
                break;
            }
        }
    }
}

