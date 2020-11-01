package com.testtask.smartanalyzer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.testtask.smartanalyzer.services.Analyzer;
import com.testtask.smartanalyzer.services.comparator.ComparatorElementTest;

public class SmartAnalyzer {
	
	private static final Logger LOGGER = Logger.getLogger(SmartAnalyzer.class.toString());
	
	public static void main(String[] args) {
		LOGGER.log(Level.INFO, "Original File: " + args[0]);
		LOGGER.log(Level.INFO, "Sample File: " + args[1]);
		
		String idElement = args.length > 2 ? args[2] : "make-everything-ok-button";
		
		Analyzer analyzer = new Analyzer(args[0], args[1], idElement, new ComparatorElementTest(1));
		List<String> result = analyzer.analyze();
		if (result != null) {
			result.stream().forEach(r -> System.out.println(r));
		}
	
	}
	
	
	
	

}
