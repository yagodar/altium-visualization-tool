package ru.ifmo.avt.vca;

import java.util.List;

public interface IPcbModelForVca extends IPcbObjectModelForVca {
	public List<IPcbElementModelForVca> getElementsForVca();
}
