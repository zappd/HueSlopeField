package com.colors.parser;

public class ParserException extends Exception {
	
	public static final String MISMATCHED_PARENS_ERROR = "Mismatched Parentheses";
	public static final String UNKNOWN_FUNCTION_ERROR  = "Unkown Function";
	
	public ParserException(String message) {
		super(message);
	}
}
