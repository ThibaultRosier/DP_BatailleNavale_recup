/*
 * Decompiled with CFR 0_132.
 */
package vue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import model.service.Case;

public class VueCase
extends JButton {
    private Case c;

    public VueCase(Case c, ImageIcon i) {
        super.setIcon(i);
        this.c = c;
    }
}

