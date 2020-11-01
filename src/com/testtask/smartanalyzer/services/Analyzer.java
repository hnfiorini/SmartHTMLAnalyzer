package com.testtask.smartanalyzer.services;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.testtask.smartanalyzer.services.comparator.ComparatorElement;

public class Analyzer {
	
	private static final Logger LOGGER = Logger.getLogger(Analyzer.class.toString());
	
	private static String CHARSET_NAME = "utf8";
	
	private String originalFile;
	private String sampleFile;
	private String idElement;
	private ComparatorElement comparator;

	public Analyzer(String originalFile, String sampleFile, String idElement, ComparatorElement comparator) {
		this.originalFile = originalFile;
		this.sampleFile = sampleFile;
		this.idElement = idElement;
		this.comparator = comparator;
	}
	
	public List<String> analyze() {
		List<String> result = null;
		try {
			Document originalFile = loadFile(new File(this.originalFile));
			Document sampleFile = loadFile(new File(this.sampleFile));
			Element element = originalFile.getElementById(this.idElement);
			if (element != null) {
				
				result = findElementInSample(element, sampleFile);
                        
			} else {
				LOGGER.log(Level.SEVERE, "Element: " + this.idElement + " doesn't exist");
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Problems with the file path");
		}
		return result;
	}
	
	private Document loadFile(File file) throws IOException {
		Document doc = Jsoup.parse(
                file,
                CHARSET_NAME,
                file.getAbsolutePath());
		return doc;
	}
	
	private List<String> findElementInSample(Element element, Document sampleFile) {
		return sampleFile.getAllElements().stream().filter(el -> compare(el, element)).map(el -> el.toString()).collect(Collectors.toList());
	}
	
	private Boolean compare(Element origElement, Element sampleElement ) {
		return comparator.compare(origElement, sampleElement);
	}

	public String getOriginalFile() {
		return originalFile;
	}

	public void setOriginalFile(String originalFile) {
		this.originalFile = originalFile;
	}

	public String getSampleFile() {
		return sampleFile;
	}

	public void setSampleFile(String sampleFile) {
		this.sampleFile = sampleFile;
	}

	public String getIdElement() {
		return idElement;
	}

	public void setIdElement(String idElement) {
		this.idElement = idElement;
	}

}
