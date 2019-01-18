package vue;

import controller.ControllerVueMenu;

import javax.swing.*;
import java.awt.*;

public class VueMenuPrincipal extends JPanel {

    public VueMenuPrincipal(){


        JButton newPartie = new JButton("Nouvelle Partie");
        JButton loadPartie = new JButton("Charger Partie");
        JButton quit = new JButton("Quitter");

        newPartie.addActionListener(new ControllerVueMenu("newPartie",this));
        loadPartie.addActionListener(new ControllerVueMenu("load",this));
        quit.addActionListener(new ControllerVueMenu("quit",this));

        newPartie.setPreferredSize(new Dimension(200,50));
        newPartie.setSize(new Dimension(200,50));

        setLayout(new GridLayout(3,1,50,50));

        add(newPartie);
        add(loadPartie);
        add(quit);

        setVisible(true);
    }

}
