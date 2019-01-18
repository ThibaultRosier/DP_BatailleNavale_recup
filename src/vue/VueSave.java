package vue;

import controller.ControllerVueSave;
import model.server.Partie;
import model.server.Sauvegarde;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VueSave extends JPanel {

    private ArrayList<Partie> lesSauvegardes;

    public VueSave() {

        lesSauvegardes = new ArrayList<>();

        chargerSave(new File("./src/fichier_sauvegarde"));

        setLayout(new BorderLayout());

        JPanel nord = new JPanel();

        JLabel saveLabel = new JLabel("Charger une partie");
        saveLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));

        JButton newSave = new JButton("Nouvelle Sauvegarde");

        nord.add(saveLabel);
        nord.add(newSave);

        JButton save = new JButton("Sauvegarder");
        JButton retour = new JButton("Retour");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
        buttonPanel.add(save);
        buttonPanel.add(retour);

        JList liste = new JList();
        DefaultListModel listModel = new DefaultListModel();

        //Remplir le model
        int size = lesSauvegardes.size();
        for(int index=0; index<size; index++)
        {
            listModel.addElement(lesSauvegardes.get(index));
        }

        //Donné le model à la liste
        liste.setModel(listModel);

        newSave.addActionListener(new ControllerVueSave("newSave",this,liste));
        save.addActionListener(new ControllerVueSave("save",this,liste));
        retour.addActionListener(new ControllerVueSave("retour",this,liste));


        JScrollPane js = new JScrollPane(liste);
        js.createVerticalScrollBar();


        add(nord,BorderLayout.NORTH);
        add(js,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);

        this.setPreferredSize(new Dimension(800,500));

    }

    public void chargerSave(File repertoire) {
        String [] listefichiers;
        int i;
        listefichiers=repertoire.list();
        for(i=0;i<listefichiers.length;i++){
            if(listefichiers[i].endsWith(".save")){
                try {
                    if(Partie.deSerialize(listefichiers[i].substring(0, listefichiers[i].length() - 5))!= null) {
                        lesSauvegardes.add(Partie.deSerialize(listefichiers[i].substring(0, listefichiers[i].length() - 5)));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
