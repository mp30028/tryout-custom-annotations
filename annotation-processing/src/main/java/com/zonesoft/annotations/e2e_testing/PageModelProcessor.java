package com.zonesoft.annotations.e2e_testing;

import static com.zonesoft.modelling.framework.Constants.*;

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
			writeExpectationsClass(annotatedClass, helper);
		}
    	return true;
    }
		
	private void writeImplementationClass(Element annotatedClass, PageModelHelper helper) {
		Set<Element> containedElements = helper.fetchAllClassElementsAnnotatedBy(annotatedClass, PAGE_MODEL_ELEMENT);		
		Map<NameTypes, String> names = helper.getNames(annotatedClass);
		String simpleClassName = names.get(NameTypes.simpleClassName);
		String packageName = names.get(NameTypes.packageName);
//		String fullyQualifiedClassName = names.get(NameTypes.fullyQualifiedClassName);
		String targetSimpleClassName = simpleClassName + IMPLEMENTATION_SUFFIX;
		String targetFullyQualifiedClassName = packageName + "." + targetSimpleClassName;
		String pagePathAttributeValue = helper.getPagePathAttribute(annotatedClass);
		
		Writer out = null;
		try {
			JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(targetFullyQualifiedClassName);
			Messager messager = processingEnv.getMessager();
			out = builderFile.openWriter();
			
			//Package name
              	out.write("package "); out.write(packageName); out.write(";\n\n");
              
            //Static Imports
              	out.write("import static com.zonesoft.modelling.framework.ModelParameter.modelParameter;\n\n");
              	
            //Imports              	
              	out.write("import com.zonesoft.modelling.framework.AbstractPageModel;\n");
              	out.write("import com.zonesoft.modelling.framework.PageElementType;\n");
              	out.write("import com.zonesoft.modelling.framework.SelectBy;\n\n");
              
            //Start of class definition
              	out.write("public class "); out.write(targetSimpleClassName); out.write(" extends AbstractPageModel implements "); out.write(simpleClassName); out.write("{\n");

              		//Field declarations
              		out.write("\n\tprivate static final String PAGE_PATH = \"");out.write(pagePathAttributeValue); out.write("\";\n");
              	
	              	//Start of Constructor
		          		out.write("\n\tpublic "); out.write(targetSimpleClassName); out.write("() {\n");          		
			          		//Constructor Body
			          		out.write("\t\t"); out.write("super(PAGE_PATH);\n");
			          		out.write("\t\tinitialise();\n");
			          	out.write("\t}\n");
		        	//End of Constructor
	              	
		        	//Start of initialise method
			    		out.write("\n\tprivate void initialise() {\n");

			    			//Start of parameter updates: Iterate for each annotation found
			    				for(Element annotatedClassElement: containedElements) {
			    					String annotatedClassElementName = annotatedClassElement.getSimpleName().toString();
			    					String elementTypeAsString = helper.getElementTypeAttribute(annotatedClassElement).toString();
			    					String elementByAsString = helper.getElementByAttribute(annotatedClassElement).toString();
			    					String elementHavingValue = helper.getElementHavingAttribute(annotatedClassElement);
			    					String promptByAsString = helper.getPromptByAttribute(annotatedClassElement).toString();
			    					String promptHavingValue = helper.getPromptHavingAttribute(annotatedClassElement);
			    					String defaultValue = helper.getDefaultValueAttribute(annotatedClassElement);
			    					String promptText = helper.getPromptTextAttribute(annotatedClassElement);
			    					out.write("\t\tparameters().put(\n"); 
			    					out.write("\t\t\t"); out.write(annotatedClassElementName); out.write("(),\n"); 
			    						out.write("\t\t\tmodelParameter()\n");
			    						out.write("\t\t\t\t.elementType(PageElementType."); out.write(elementTypeAsString);out.write(")\n");
			    						out.write("\t\t\t\t.selectElementBy(SelectBy."); out.write(elementByAsString);out.write(")\n");
			    						out.write("\t\t\t\t.selectElementWithValue(\""); out.write(elementHavingValue);out.write("\")\n");
			    						out.write("\t\t\t\t.selectPromptBy(SelectBy."); out.write(promptByAsString);out.write(")\n");
			    						out.write("\t\t\t\t.selectPromptWithValue(\""); out.write(promptHavingValue);out.write("\")\n");			    						
			    						out.write("\t\t\t\t.value(\""); out.write(defaultValue);out.write("\")\n");
			    						out.write("\t\t\t\t.promptText(\""); out.write(promptText);out.write("\")\n");			    									    						
			    					out.write("\t\t);\n\n");
			    				}
			    			//End of parameter updates
			    				
			    		out.write("\t}\n");
		    		//End of initilisation method		          	
		          	
		            //Start Interface Methods that need to be implemented
		      		for(Element annotatedClassElement: containedElements) {
		            	  String annotatedClassElementName = annotatedClassElement.getSimpleName().toString();
		            	  out.write("\n");
		            	  out.write("\t@Override\n");
		            	  out.write("\tpublic String "); out.write(annotatedClassElementName); out.write("() {\n ");
		            	  out.write("\t\treturn \"");out.write(annotatedClassElementName); out.write("\";\n");
		            	  out.write("\t}\n");
		        	}
		            //End Interface Methods
		      		
		      	out.write("}\n");
 	         //End of class definition
              
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
	
	private void writeExpectationsClass(Element annotatedClass, PageModelHelper helper) {
//		Set<Element> containedElements = helper.fetchAllClassElementsAnnotatedBy(annotatedClass, PAGE_MODEL_ELEMENT);		
		Map<NameTypes, String> names = helper.getNames(annotatedClass);
		String simpleClassName = names.get(NameTypes.simpleClassName);
		String packageName = names.get(NameTypes.packageName);
		String fullyQualifiedClassName = names.get(NameTypes.fullyQualifiedClassName);
		String targetSimpleClassName = simpleClassName + EXPECTATION_SUFFIX;
		String targetFullyQualifiedClassName = packageName + "." + targetSimpleClassName;
		
		Writer out = null;
		try {
			JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(targetFullyQualifiedClassName);
			out = builderFile.openWriter();
				//Package name
          			out.write("package "); out.write(packageName); out.write(";\n\n");

                //Imports
          			out.write("import ");out.write(fullyQualifiedClassName);out.write(";\n");
                  	out.write("import com.zonesoft.modelling.framework.AbstractExpectation;\n");
                  	out.write("import com.zonesoft.modelling.framework.IExpectation;\n");
                  	out.write("import com.zonesoft.modelling.framework.IPageModel;\n\n");          			
          			
          		//Start of class definition
                  	out.write("public class "); out.write(targetSimpleClassName);  out.write(" extends AbstractExpectation<");out.write(simpleClassName);out.write(">{\n");
    				
	              	//Start of Constructor
		          		out.write("\n\tpublic "); out.write(targetSimpleClassName); out.write("("); out.write(simpleClassName);  out.write(" model){\n");          		
			          		//Constructor Body
			          		out.write("\t\t"); out.write("super(model);\n");
			          	out.write("\t}\n");
		        	//End of Constructor
                  	
                  	
//                  	// generated code output goes here
//                  		out.write("\n//generated code output goes here\n");
                  	
    		      	out.write("}\n");
    	 	         //End of class definition

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				out.close();
			} catch (Exception e2) {
				//
			}
		}
	}
}
