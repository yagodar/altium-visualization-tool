package ru.ifmo.avt.browser.action;

import static ru.ifmo.avt.browser.EntryPoint.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;

import ru.ifmo.avt.tca.IPcbModelForTca;
import ru.ifmo.avt.tca.PcbThermalConditionsAnalyzer;
import ru.ifmo.avt.vca.IPcbModelForVca;
import ru.ifmo.avt.vca.PcbVibroConditionsAnalyzer;

public class StartAnalysisAction extends AbstractAction {

    private static final long serialVersionUID = -9120973281725768448L;

    public StartAnalysisAction(String name, Icon smallIcon, String shortDescription) {
	putValue(Action.NAME, name);
	putValue(Action.SMALL_ICON, smallIcon);
	putValue(Action.SHORT_DESCRIPTION, shortDescription);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
	//PcbVibroConditionsAnalyzer.getInstance().analyzePcbModel((IPcbModelForVca)(browser.getBrowserWorkPanel().getBrowserable()), frequency, acceleration, fixing);
	PcbThermalConditionsAnalyzer.getInstance().analyzePcbModel((IPcbModelForTca)(browser.getBrowserWorkPanel().getBrowserable()));
	browser.getBrowserWorkPanel().repaint();
    }

}
