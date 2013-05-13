package ru.ifmo.avt.browser.GUI.components;

import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import ru.ifmo.avt.browser.GUI.BrowserAction;
import ru.ifmo.avt.browser.helper.GBC;

public class BrowserToolBar extends JToolBar {

    private static final long serialVersionUID = -5526646783333000802L;

    public BrowserToolBar() {
	add(BrowserAction.OPEN_FILE.getAction());
	add(BrowserAction.START_ANALYSIS.getAction());
	addSeparator();
	JPanel scalePanel = new JPanel();
	scalePanel.setLayout(new GridBagLayout());
	JLabel scaleLabel = new JLabel("Scale:"); 
	scalePanel.add(scaleLabel, new GBC(0, 0));
	BrowserScaleSpinner scaleSpinner = new BrowserScaleSpinner(); 
	scalePanel.add(scaleSpinner, new GBC(1, 0));
	scalePanel.add(new JPanel(), new GBC(2, 0).setFill(GBC.HORIZONTAL).setWeight(100, 100));
	add(scalePanel);
	addSeparator();
    }
}
