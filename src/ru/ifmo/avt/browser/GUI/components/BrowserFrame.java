package ru.ifmo.avt.browser.GUI.components;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class BrowserFrame extends JFrame {

    private static final long serialVersionUID = 3179526785013988112L;

    public BrowserFrame() {
	setTitle(NAME);
	setSize(WIDTH, HEIGHT);
	setLayout(new BorderLayout());
	setJMenuBar(browserMenuBar = new BrowserMenuBar());
	add(browserToolBar = new BrowserToolBar(), BorderLayout.NORTH);
	add(browserWorkPanel = new BrowserWorkPanel(), BorderLayout.CENTER);
    }

    public BrowserMenuBar getBrowserMenuBar() {
	return browserMenuBar;
    }

    public BrowserToolBar getBrowserToolBar() {
	return browserToolBar;
    }

    public BrowserWorkPanel getBrowserWorkPanel() {
	return browserWorkPanel;
    }

    private BrowserMenuBar browserMenuBar;
    private BrowserToolBar browserToolBar;
    private BrowserWorkPanel browserWorkPanel;
    private static String NAME = "ModeBrowser";
    private static int WIDTH = 800;
    private static int HEIGHT = 600;
}
