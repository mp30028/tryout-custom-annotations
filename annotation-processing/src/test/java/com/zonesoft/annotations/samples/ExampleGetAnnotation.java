package com.zonesoft.annotations.samples;

//Java program to demonstrate
//getAnnotation() method

import java.util.*;
import java.lang.annotation.*;

//create a custom Annotation
@Retention(RetentionPolicy.RUNTIME)
@interface Annotation {

	// This annotation has two attributes.
	public String key();

	public String value();
}

//call Annotation for method
//and pass values for annotation
@Annotation(key = "GFG", value = "GeeksForGeeks")
public class ExampleGetAnnotation {

	public static void main(String[] args)
		throws ClassNotFoundException
	{

		// returns the Class object for this class
		Class myClass = ExampleGetAnnotation.class;

		System.out.println("Class represented by myClass: "
						+ myClass.toString());

		// Get the annotation
		// using getAnnotation() method
		System.out.println("Annotation of myClass: " + myClass.getAnnotation(Annotation.class));
	}
}
