package vue;

import model.server.Partie;
import model.server.Sauvegarde;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.rmi.RemoteException;

public class VueFenetre extends JFrame {

    private  JPanel vueActu;
    private static int LARGEUR = 1000;
    private static int HAUTEUR = 1000;
    private GridBagConstraints gbc;

    public VueFenetre() {
        super("Master and Commander");

        this.setSize(1500   , 1000);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        /*//test save
        Sauvegarde s = new Sauvegarde("test","X siecle","master");
        try {
            s.serialize("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //fin test save*/


        setLayout(new GridBagLayout());
        gbc=new GridBagConstraints(0,0,2,1,0.25,0,GridBagConstraints.PAGE_START,GridBagConstraints.VERTICAL,new Insets(1,1,1,1), 0,0);

        vueActu = new VueMenuPrincipal();

        JLabel titre = new JLabel("Master and Commander");
        titre.setFont(new Font("Sans Serif", Font.PLAIN, 30));

        add(titre,gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy=2;
        gbc.gridwidth=1;
        add(vueActu,gbc);

        setVisible(true);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

    public void changerPanel(String vue) throws RemoteException {

        remove(vueActu);

        switch(vue){
            case "vueMenuPrincipal":
                vueActu = new VueMenuPrincipal();
                break;

            case "vueLoad":
                vueActu = new VueLoad();
                break;

            case "vueNewPartie":
                vueActu = new VueNewPartie();
                break;

            case "vueSave":
                vueActu = new VueSave();
                break;

            case "vueOption":
                vueActu = new VueOption();
                break;

            case "vueJeu":
                vueActu = new VueJeu();
                break;

            case "vueGagnant":
                vueActu = new VueGagnant();
                break;


            default:
        }

        add(vueActu,gbc);
        repaint();
        revalidate();
    }

    public static void main(String[] args) throws RemoteException {
        new VueFenetre();
    }
}
