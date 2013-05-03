package ru.ifmo.avt.browser.GUI.components;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import ru.ifmo.avt.browser.GUI.BrowserAction;

public class BrowserMenuBar extends JMenuBar {

	private static final long serialVersionUID = 7498138411621319324L;

	public BrowserMenuBar() {
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(BrowserAction.OPEN_FILE.getAction());
		add(fileMenu);

		JMenu actionMenu = new JMenu("Action");
		actionMenu.add(BrowserAction.START_ANALYSIS.getAction());
		add(actionMenu);
	}
}
