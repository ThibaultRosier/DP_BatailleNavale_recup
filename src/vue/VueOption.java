package vue;

import controller.ControllerVueOption;
import model.server.Partie;

import javax.swing.*;
import java.awt.*;

public class VueOption extends JPanel {

    private JComboBox tirOrdi;

    public VueOption() {
        JPanel centre = new JPanel();
        centre.setLayout(new GridLayout(3,1,50,50));

        JLabel option = new JLabel("Option de partie");
        option.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        tirOrdi = new JComboBox(Partie.tabTireOrdi);

        tirOrdi.setPreferredSize(new Dimension(200,50));
        tirOrdi.setSize(new Dimension(200,50));

        centre.add(option);
        centre.add(tirOrdi);


        JPanel sud = new JPanel();

        JButton save = new JButton("Sauvegarder");
        JButton quit = new JButton("Quitter partie");
        JButton retour = new JButton("Retour");
        save.addActionListener(new ControllerVueOption("save",this));
        quit.addActionListener(new ControllerVueOption("quitter",this));
        retour.addActionListener(new ControllerVueOption("retour",this));

        sud.setLayout(new GridLayout(1,2,100,100));
        sud.add(save);
        sud.add(quit);
        sud.add(retour);

        this.setLayout(new BorderLayout());
        this.add(centre,BorderLayout.CENTER);
        this.add(sud,BorderLayout.SOUTH);

        setVisible(true);
    }
}
