/*
 * Decompiled with CFR 0_132.
 */
package controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import model.server.Partie;
import vue.VueSave;

public class ControllerDialogNewSave
implements ActionListener {
    private JTextField jt;
    private VueSave jp;

    public ControllerDialogNewSave(JTextField nom, VueSave jp) {
        this.jt = nom;
        this.jp = jp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!this.jt.getText().equals("")) {
            try {
                Partie p = Partie.getPartieEnCour();
                p.setNom(this.jt.getText());
                p.setDate(Calendar.getInstance().getTime());
                p.serialize(this.jt.getText());
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            Window window = SwingUtilities.windowForComponent((Component)e.getSource());
            window.dispose();
        } else {
            JOptionPane.showMessageDialog(this.jt.getParent(), "saisissez un nom");
        }
    }
}

