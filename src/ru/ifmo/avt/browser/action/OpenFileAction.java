package ru.ifmo.avt.browser.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import ru.ifmo.avt.browser.EntryPoint;
import ru.ifmo.avt.browser.dialog.OpenFileDialog;
import ru.ifmo.avt.browser.interfaces.Browserable;
import ru.ifmo.avt.parser.AltiumPcbDocParser;

public class OpenFileAction extends AbstractAction {

    private static final long serialVersionUID = 2775358860362327685L;

    public OpenFileAction(String name, Icon smallIcon, String shortDescription) {
	putValue(Action.NAME, name);
	putValue(Action.SMALL_ICON, smallIcon);
	putValue(Action.SHORT_DESCRIPTION, shortDescription);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
	int answer = openFileDialog.showOpenDialog(EntryPoint.browser);

	if (answer == OpenFileDialog.APPROVE_OPTION) {

	    AltiumPcbDocParser.getInstance().createNewPcbModel(openFileDialog.getSelectedFile());
	    Browserable browserable = AltiumPcbDocParser.getInstance().getPcbModelBrowserable();

	    if (browserable == null)
		JOptionPane.showConfirmDialog(EntryPoint.browser, "Ошибка чтения файла", "Ошибка", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null);
	    else
		EntryPoint.browser.getBrowserWorkPanel().setBrowserableObject(browserable);
	}
    }

    private OpenFileDialog openFileDialog = new OpenFileDialog();

}
