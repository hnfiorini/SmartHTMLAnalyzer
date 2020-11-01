package com.testtask.smartanalyzer.services.comparator;

import org.jsoup.nodes.Element;

public interface ComparatorElement {
	
	public Boolean compare(Element originalElement, Element sampleElement);
	
	public void setAllowedMatches(int matches);

}
