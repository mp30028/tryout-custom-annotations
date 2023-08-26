package com.zonesoft.annotations.e2e_testing;

import java.io.IOException;
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
import static com.zonesoft.annotations.e2e_testing.Constants.*;

@SupportedAnnotationTypes("com.zonesoft.annotations.e2e_testing.PageModel")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class PageModelProcessor extends AbstractProcessor {

	@Override
    public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {		
		PageModelHelper helper = new PageModelHelper(supportedAnnotations, roundEnv);
		Set<Element> annotatedClasses = helper.fetchAllAnnotatedClasses();
		
		for (Element annotatedClass: annotatedClasses) {
			writeImplementationClass(annotatedClass, helper);
		}
    	return true;
    }
		
	private void writeImplementationClass(Element annotatedClass, PageModelHelper helper) {
		Set<Element> containedElements = helper.fetchAllClassElementsAnnotatedBy(annotatedClass, PAGE_MODEL_ELEMENT);		
		Map<NameTypes, String> names = helper.getNames(annotatedClass);
		String simpleClassName = names.get(NameTypes.simpleClassName);
		String packageName = names.get(NameTypes.packageName);
		String fullyQualifiedClassName = names.get(NameTypes.fullyQualifiedClassName);;
		String targetSimpleClassName = simpleClassName + IMPLEMENTATION_SUFFIX;
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
              	out.write("public class "); out.write(targetSimpleClassName); out.write(" implements "); out.write(simpleClassName); out.write("{\n");
              	
	            // Write class methods
	      		for(Element annotatedClassElement: containedElements) {
	            	  String annotatedClassElementName = annotatedClassElement.getSimpleName().toString();
	            	  out.write("\n");
	            	  out.write("\t@Override\n");
	            	  out.write("\tpublic String "); out.write(annotatedClassElementName); out.write("() {\n ");
	            	  out.write("\t\treturn \"");out.write(annotatedClassElementName); out.write("\";\n");
	            	  out.write("\t}\n");
	        	}
              	
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
