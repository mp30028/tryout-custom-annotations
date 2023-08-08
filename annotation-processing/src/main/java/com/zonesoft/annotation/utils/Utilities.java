package com.zonesoft.annotation.utils;



import java.text.MessageFormat;

public class Utilities {

	public static void writeMsg(String message, Object... params) {
		StackTraceElement element = Thread.currentThread().getStackTrace()[2];
		String caller= element.getClassName() + "." + element.getMethodName();
		int lineNumber = element.getLineNumber();
    	System.out.print("FROM:");
    	System.out.print(caller);
    	System.out.print(" (");System.out.print(lineNumber);System.out.print("):  ");
		System.out.println(MessageFormat.format(message, params));
	}
	
}
