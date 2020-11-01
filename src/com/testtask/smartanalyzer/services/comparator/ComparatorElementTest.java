package com.testtask.smartanalyzer.services.comparator;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;

public class ComparatorElementTest implements ComparatorElement {
	
	private int allowedMatches = 0;

	@Override
	public Boolean compare(Element originalElement, Element sampleElement) {
		Map<String, String> mapOriginal = new HashMap<>();
		mapOriginal = originalElement.attributes().asList().stream()
			      .collect(Collectors.toMap(Attribute::getKey, Attribute::getValue));
		
		Map<String, String> mapSample = new HashMap<>();
		mapSample = sampleElement.attributes().asList().stream()
			      .collect(Collectors.toMap(Attribute::getKey, Attribute::getValue));
		
		int matches = 0;
		for (String key : mapOriginal.keySet()) {
			if (mapSample.get(key) != null && mapSample.get(key).equalsIgnoreCase(mapOriginal.get(key))) {
				matches++;
			}
		}
		
		return matches >= this.allowedMatches ? Boolean.TRUE : Boolean.FALSE;
	}

	public ComparatorElementTest(int allowedMatches) {
		super();
		this.allowedMatches = allowedMatches;
	}

	@Override
	public void setAllowedMatches(int matches) {
		this.allowedMatches = matches;
		
	}

}
