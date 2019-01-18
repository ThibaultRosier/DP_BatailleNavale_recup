package controller;

import model.server.Partie;
import model.service.Case;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

public class ControllerVueCase implements ActionListener {

    private Case c;

    public ControllerVueCase(Case c) {
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Partie.caseSelection = c;
    }
}
