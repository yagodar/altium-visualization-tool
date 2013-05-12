package ru.ifmo.avt.browser.GUI.paintComponents;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import ru.ifmo.avt.browser.interfaces.Browserable;

public class ElementPaintComponent extends PaintComponent {

    private static final long serialVersionUID = 1866441997831990728L;

    public ElementPaintComponent(Browserable browserable) {
	super(browserable);

	for (Point point : getPeak())
	    markerList.add(new MarkerPaintComponent(this, point));

	addMouseListener(new MouseAdapter() {

	    @Override
	    public void mousePressed(MouseEvent event) {
		BoardPaintComponent board = (BoardPaintComponent) ElementPaintComponent.this.getParent();
		for (MarkerPaintComponent marker : markerList)
			board.setComponentZOrder(marker, 0);
		board.setComponentZOrder(ElementPaintComponent.this, 0);
		board.setSelectedElement(ElementPaintComponent.this);
		board.repaint();
	    }
	});
    }

    @Override
    public void setLocation(Point point) {
	super.setLocation(point);

	if (markerList != null)
	    for (MarkerPaintComponent marker : markerList)
		marker.relocation();
    }

    public void setSelected(boolean isSelected) {
	for (MarkerPaintComponent marker : markerList)
	    marker.setVisible(isSelected);
    }

    public List<MarkerPaintComponent> getMarkerList() {
	return markerList;
    }

    private List<MarkerPaintComponent> markerList = new LinkedList<MarkerPaintComponent>();
}
