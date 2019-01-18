package vue;

import controller.ControllerVueGagnant;
import model.server.Partie;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class VueGagnant extends JPanel {

    public VueGagnant(){

        Partie p = null;
        try {
            p = Partie.getPartieEnCour();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        this.setSize(300, 150);

        this.setLayout(new BorderLayout());

        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center,BoxLayout.LINE_AXIS));
        
        JLabel nomLabel = new JLabel();
        
        if((p.getGagnantNormal() == p.getJoueur1())){
            nomLabel.setText("Vous avez Gagné!");
        }
        else {
            nomLabel.setText("Vous avez Perdu!");
        }

        center.add(nomLabel);

        JButton quitter = new JButton("quitter");
        quitter.addActionListener(new ControllerVueGagnant(nomLabel,this));

        this.add(center, BorderLayout.NORTH);
        this.add(quitter,BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
