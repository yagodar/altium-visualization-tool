package ru.ifmo.avt.browser.GUI;

import javax.swing.Action;

import ru.ifmo.avt.browser.action.OpenFileAction;
import ru.ifmo.avt.browser.action.StartAnalysisAction;

public enum BrowserAction {
    START_ANALYSIS(new StartAnalysisAction("Start", null, "Start analysis")), OPEN_FILE(
	    new OpenFileAction("Open", null, "Open file"));

    Action action;

    BrowserAction(Action action) {
	this.action = action;
    }

    public Action getAction() {
	return action;
    }
}