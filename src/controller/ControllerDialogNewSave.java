package controller;

import model.server.Partie;
import model.server.Sauvegarde;
import vue.VueSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class ControllerDialogNewSave implements ActionListener {

    private JTextField jt;
    private VueSave jp;

    public ControllerDialogNewSave(JTextField nom, VueSave jp) {
        this.jt = nom;
        this.jp = jp;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(!jt.getText().equals("")){

            try {
                Partie p = Partie.getPartieEnCour();
                p.setNom(jt.getText());
                p.setDate(Calendar.getInstance().getTime());
                p.serialize(jt.getText());
                /*jp.chargerSave(new File("./src/fichier_sauvegarde"));
                jp.revalidate();
                jp.repaint();*/
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Window window = SwingUtilities.windowForComponent((Component)e.getSource());
            window.dispose();
        }
        else{
            JOptionPane.showMessageDialog(jt.getParent(), "saisissez un nom");
        }
    }
}
