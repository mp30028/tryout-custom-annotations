package com.zonesoft.annotations.e2e_testing;

import java.io.IOException;
//import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import javax.tools.Diagnostic;

import com.google.auto.service.AutoService;
import com.zonesoft.annotations.e2e_testing.helpers.PageModelHelper;
import com.zonesoft.annotations.e2e_testing.helpers.PageModelHelper.NameTypes;


@SupportedAnnotationTypes("com.zonesoft.annotations.e2e_testing.PageModel")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class PageModelProcessor extends AbstractProcessor {
	private static final String PAGE_MODEL_ELEMENT = "com.zonesoft.annotations.e2e_testing.PageModelElement";
	private static final String EXTENDER_SUFFIX = "Extender";

	
	@Override
    public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {		
		PageModelHelper helper = new PageModelHelper(supportedAnnotations, roundEnv);
		Set<Element> annotatedClasses = helper.fetchAllAnnotatedClasses();
		
		for (Element annotatedClass: annotatedClasses) {
			writeExtenderFile(annotatedClass, helper);
		}
    	return true;
    }
		
	private void writeExtenderFile(Element annotatedClass, PageModelHelper helper) {
		Set<Element> containedElements = helper.fetchAllClassElementsAnnotatedBy(annotatedClass, PAGE_MODEL_ELEMENT);		
		Map<NameTypes, String> names = helper.getNames(annotatedClass);
		String simpleClassName = names.get(NameTypes.simpleClassName);
		String packageName = names.get(NameTypes.packageName);
		String fullyQualifiedClassName = names.get(NameTypes.fullyQualifiedClassName);;
		String targetSimpleClassName = simpleClassName + EXTENDER_SUFFIX;
		String fullyQualifiedTargetClassName = packageName + "." + targetSimpleClassName;
		Writer out = null;
		try {
			JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(fullyQualifiedTargetClassName);
			Messager messager = processingEnv.getMessager();
			out = builderFile.openWriter();
			
			// Write Package name
              	out.write("package "); out.write(packageName); out.write(";\n\n");
              
            // Write Imports
              	out.write("import static "); out.write(fullyQualifiedClassName); out.write(".*;\n\n");
              
            // Write Start of class definition
              	out.write("public class "); out.write(targetSimpleClassName); out.write(" {\n");
              
              	// Write Start of Constructor
              		out.write("\tpublic "); out.write(targetSimpleClassName); out.write("() {\n");
              		
              		// Write Constructor Body
              		for(Element annotatedClassElement: containedElements) {
	              	  String annotatedClassElementName = annotatedClassElement.getSimpleName().toString();
	              	  out.write("\t\t"); out.write(annotatedClassElementName); out.write(" = "); out.write("\""); out.write(annotatedClassElementName); out.write("\";\n");
              		}
              		
             
            	// Write End of Constructor
              		out.write("\t}\n");
              		

	         // Write End of class definition
              out.write("}\n");
              out.close();
              messager.printMessage(Diagnostic.Kind.NOTE, "File 'Gen' created");              
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	try {
	    		out.close();
	    	}catch(Exception e2) {
	    		//
	    	}
	    }		
	}	
}
