package ru.ifmo.avt.browser;

import java.awt.EventQueue;

import javax.swing.JFrame;

import ru.ifmo.avt.browser.GUI.components.BrowserFrame;

public class EntryPoint {

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {

	    @Override
	    public void run() {
		browser = new BrowserFrame();
		browser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		browser.setVisible(true);
	    }
	});
    }

    public static int frequency;
    public static double acceleration;
    public static BrowserFrame browser;
    public static double relativScale = 1;
    public static double scale = 1;
}
