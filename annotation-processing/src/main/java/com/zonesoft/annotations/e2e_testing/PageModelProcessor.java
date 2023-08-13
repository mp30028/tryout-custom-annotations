package com.zonesoft.annotations.e2e_testing;

import static com.zonesoft.annotations.utilities.WriteMessage.writeMsg;

import java.io.IOException;
//import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
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
//	private static final String EXTENDER_SUFFIX = "Extender";
	private static final String ABSTRACT_PREFIX = "Abstract";
	
	@Override
    public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {		
//		writeMsg("supportedAnnotations.size() = {0}", supportedAnnotations.size());
		PageModelHelper helper = new PageModelHelper(supportedAnnotations, roundEnv);
		Set<Element> annotatedClasses = helper.fetchAllAnnotatedClasses();
//		helper.inspectElements("After-Fetch:annotatedClasses", annotatedClasses);
		for (Element annotatedClass: annotatedClasses) {
//			Set<Element> annotatedClassElements = helper.fetchAllClassElementsAnnotatedBy(annotatedClass, PAGE_MODEL_ELEMENT);
//			helper.inspectElements("After-Fetch:annotatedClassElements:" + annotatedClass.getSimpleName() + ": ", annotatedClassElements);
			writeExtenderFile(annotatedClass, helper);
		}
    	return true;
    }
	

	
//	private PrintWriter openSourceFile(String sourceFileName) throws IOException {
//		return new PrintWriter(processingEnv.getFiler().createSourceFile(sourceFileName).openWriter());
//	}
	
	private void writeExtenderFile(Element annotatedClass, PageModelHelper helper) {
		Set<Element> containedElements = helper.fetchAllClassElementsAnnotatedBy(annotatedClass, PAGE_MODEL_ELEMENT);		
		Map<NameTypes, String> names = helper.getNames(annotatedClass);
		String simpleClassName = names.get(NameTypes.simpleClassName);
		String packageName = names.get(NameTypes.packageName);
		String fullyQualifiedClassName = names.get(NameTypes.fullyQualifiedClassName);;
		String targetSimpleClassName = ABSTRACT_PREFIX + simpleClassName;
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
//
//              	// Output private field declaration
//              	out.print("    private ");
//              	out.print(simpleClassName);
//              	out.print(" object = new ");
//              	out.print(simpleClassName);
//              	out.println("();");
//              	out.println();
//
//          out.print("    public ");
//          out.print(simpleClassName);
//          out.println(" build() {");
//          out.println("        return object;");
//          out.println("    }");
//          out.println();
//
//          setterMap.entrySet().forEach(setter -> {
//              String methodName = setter.getKey();
//              String argumentType = setter.getValue();
//
//              out.print("    public ");
//              out.print(builderSimpleClassName);
//              out.print(" ");
//              out.print(methodName);
//
//              out.print("(");
//
//              out.print(argumentType);
//              out.println(" value) {");
//              out.print("        object.");
//              out.print(methodName);
//              out.println("(value);");
//              out.println("        return this;");
//              out.println("    }");
//              out.println();
//          });



	@SuppressWarnings("unused")
	private void writeModifiers(Writer out, Element annotatedClassElement) throws IOException {
  	  Set<Modifier> modifiers = annotatedClassElement.getModifiers();

  	  out.write("\t"); 
  	  if (Objects.nonNull(modifiers) && modifiers.size() > 0) {
      	  for(Modifier modifier: modifiers) {
      		  out.write(modifier.toString()); out.write(" ");
      	  }
  	  }else {
  		  out.write("\\-- No modifiers found --");
  	  }		            	  
  	  out.write("\n");
	}
	
}
