package ru.ifmo.avt.browser.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

public class StartAnalysisAction extends AbstractAction {

    private static final long serialVersionUID = -9120973281725768448L;

    public StartAnalysisAction(String name, Icon smallIcon, String shortDescription) {
	putValue(Action.NAME, name);
	putValue(Action.SMALL_ICON, smallIcon);
	putValue(Action.SHORT_DESCRIPTION, shortDescription);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub

    }

}
