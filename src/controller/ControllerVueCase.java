/*
 * Decompiled with CFR 0_132.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.server.Partie;
import model.service.Case;

public class ControllerVueCase
implements ActionListener {
    private Case c;

    public ControllerVueCase(Case c) {
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Partie.caseSelection = this.c;
    }
}

