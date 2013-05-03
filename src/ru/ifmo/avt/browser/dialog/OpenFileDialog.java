package ru.ifmo.avt.browser.dialog;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFileDialog extends JFileChooser {

	private static final long serialVersionUID = 2274514065835506491L;

	public OpenFileDialog() {
		setFileFilter(new FileNameExtensionFilter("PCBfile", ".pcb"));
		setCurrentDirectory(new File("."));
	}
}
