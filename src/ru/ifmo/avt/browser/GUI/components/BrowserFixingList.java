package ru.ifmo.avt.browser.GUI.components;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import ru.ifmo.avt.browser.EntryPoint;

public class BrowserFixingList extends JComboBox<ImageIcon> {

	private static final long serialVersionUID = -5596457991364164650L;

	public BrowserFixingList(Vector<ImageIcon> icons) {
		super(icons);
	}
	
	@Override
	public void setSelectedIndex(int anIndex) {
		EntryPoint.fixing = anIndex;
		super.setSelectedIndex(anIndex);
	}
}
