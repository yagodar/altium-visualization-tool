package ru.ifmo.avt.browser.GUI.components;

import javax.swing.JToolBar;

import ru.ifmo.avt.browser.GUI.BrowserAction;

public class BrowserToolBar extends JToolBar {

	private static final long serialVersionUID = -5526646783333000802L;

	public BrowserToolBar() {
		add(BrowserAction.OPEN_FILE.getAction());
		add(BrowserAction.START_ANALYSIS.getAction());
	}
}
