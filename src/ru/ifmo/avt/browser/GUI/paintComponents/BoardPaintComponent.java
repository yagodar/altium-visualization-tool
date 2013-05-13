package ru.ifmo.avt.browser.GUI.paintComponents;

import java.util.LinkedList;
import java.util.List;

import ru.ifmo.avt.browser.GUI.components.BrowserPaintPanel;
import ru.ifmo.avt.browser.interfaces.Browserable;

public class BoardPaintComponent extends PaintComponent {

    private static final long serialVersionUID = -6157564799202608539L;

    public BoardPaintComponent(Browserable browserable) {
	super(browserable);

	if (getBrowserable().getBrowserableObjects() != null)
	    for (Browserable element : browserable.getBrowserableObjects()) {
		BrowserPaintPanel.scale(element);
		ElementPaintComponent elementComponent = new ElementPaintComponent(element);
		elementList.add(elementComponent);
		add(elementComponent);
	    }
    }

    private void add(ElementPaintComponent component) {
	for (MarkerPaintComponent marker : component.getMarkerList())
	    add(marker);

	super.add(component);
    }

    public void setSelectedElement(ElementPaintComponent component) {
	if (selectedElement != null)
	    selectedElement.setSelected(false);

	selectedElement = component;
	selectedElement.setSelected(true);
    }

    private List<ElementPaintComponent> elementList = new LinkedList<ElementPaintComponent>();
    private ElementPaintComponent selectedElement = null;
}
