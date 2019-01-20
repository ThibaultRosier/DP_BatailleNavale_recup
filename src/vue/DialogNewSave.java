/*
 * Decompiled with CFR 0_132.
 */
package vue;

import controller.ControllerDialogNewSave;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vue.VueSave;

public class DialogNewSave
extends JDialog {
    private JTextField nom;
    private VueSave jp;

    public DialogNewSave(VueSave jp) {
        this.setTitle("Nouvelle Sauvegarde");
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.jp = jp;
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, 2));
        JLabel nomLabel = new JLabel("Nom sauvegarde :");
        this.nom = new JTextField();
        center.add(nomLabel);
        center.add(this.nom);
        JButton save = new JButton("Sauvegarder");
        save.addActionListener(new ControllerDialogNewSave(this.nom, jp));
        this.add((Component)center, "North");
        this.add((Component)save, "South");
        this.setVisible(true);
        this.setDefaultCloseOperation(2);
    }
}

