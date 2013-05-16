package ru.ifmo.avt.browser.GUI.components;

import java.awt.GridBagLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

import javax.swing.ImageIcon;
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
	JLabel scaleLabel = new JLabel("Scale: ");
	scalePanel.add(scaleLabel, new GBC(0, 0));
	
	BrowserScaleSpinner scaleSpinner = new BrowserScaleSpinner();
	scalePanel.add(scaleSpinner, new GBC(1, 0));
	
	/*JLabel frequencyLabel = new JLabel("  F max [Hz]: ");
	scalePanel.add(frequencyLabel, new GBC(2, 0));
	
	BrowserFrequencySpinner frequencySpinner = new BrowserFrequencySpinner();
	scalePanel.add(frequencySpinner, new GBC(3, 0));
	
	JLabel accelerationLabel = new JLabel("  Ao [m/s^2]: ");
	scalePanel.add(accelerationLabel, new GBC(4, 0));
	
	BrowserAccelerationSpinner accelerationSpinner = new BrowserAccelerationSpinner();
	scalePanel.add(accelerationSpinner, new GBC(5, 0));
	
	JLabel fixingLabel = new JLabel("  Fixing: ");
	scalePanel.add(fixingLabel, new GBC(6, 0));
	
	File file = new File(".\\res");

	Vector<ImageIcon> icons = new Vector<ImageIcon>();

	if (file.exists())
		for (File f : file.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File file, String name) {
				if (name.endsWith(".png"))
					return true;
				return false;
			}
		})) {
			icons.add(new ImageIcon(f.getPath()));
		}
	
	BrowserFixingList fixingList = new BrowserFixingList(icons);
	scalePanel.add(fixingList, new GBC(7, 0));*/
	
	scalePanel.add(new JPanel(), new GBC(2, 0).setFill(GBC.HORIZONTAL).setWeight(100, 100));
	
	add(scalePanel);
	
	addSeparator();
    }
}
