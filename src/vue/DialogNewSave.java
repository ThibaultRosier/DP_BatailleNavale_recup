package vue;

import controller.ControllerDialogNewSave;

import javax.swing.*;
import java.awt.*;

public class DialogNewSave extends JDialog {

    private JTextField nom;
    private VueSave jp;

    public DialogNewSave(VueSave jp){
        setTitle("Nouvelle Sauvegarde");
        this.setSize(300, 150);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        this.jp = jp;

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.LINE_AXIS));

        JLabel nomLabel = new JLabel("Nom sauvegarde :");
        nom = new JTextField();

        center.add(nomLabel);
        center.add(nom);

        JButton save = new JButton("Sauvegarder");
        save.addActionListener(new ControllerDialogNewSave(nom,jp));

        this.add(center, BorderLayout.NORTH);
        this.add(save,BorderLayout.SOUTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }
}
