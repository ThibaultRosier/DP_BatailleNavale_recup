package vue;

import model.service.Case;

import javax.swing.*;

public class VueCase extends JButton{

    private Case c;


    public VueCase(Case c, ImageIcon i) {
        super();
        super.setIcon(i);
        this.c = c;
    }
}
