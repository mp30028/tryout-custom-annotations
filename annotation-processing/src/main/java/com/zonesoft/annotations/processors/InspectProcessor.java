package com.zonesoft.annotations.processors;

import static com.zonesoft.annotations.utilities.WriteMessage.writeMsg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
//import javax.tools.JavaFileObject;

import com.google.auto.service.AutoService;

@SupportedAnnotationTypes("com.zonesoft.annotations.processors.Inspect")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class InspectProcessor extends AbstractProcessor {
	
	@Override
    public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {		
//		writeMsg("supportedAnnotations.size() = {0}", supportedAnnotations.size());
		if (supportedAnnotations.size() > 0) {
			List<Element> annotatedSetters = getAnnotatedSetters(supportedAnnotations, roundEnv);
//			writeMsg("annotatedSetters.size() = {0}", annotatedSetters.size());
//			writeInspectionFile(annotatedSetters);	     	    	    				
		}		
    	return true;
    }
	
	private PrintWriter openInspectionFile(String inspectionFileName) throws IOException {
		return new PrintWriter(processingEnv.getFiler().createSourceFile(inspectionFileName).openWriter());
	}
	
	private void writeInspectionFile(List<Element> annotatedSetters) {
		Map<String, List<Element>> groupedAnnotatedSetters = groupByClass(annotatedSetters);
		for (Entry<String, List<Element>> groupedSetters : groupedAnnotatedSetters.entrySet()) {
		    try (PrintWriter inspectionFile = openInspectionFile(groupedSetters.getKey() + "Inspection")){			  
				  writeInspectionFile(inspectionFile, groupedSetters.getValue());
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }	
		}
	}

	private void writeInspectionFile(PrintWriter inspectionFile, List<Element> annotatedSetters) {
		inspectionFile.println("//------------- Inspection Result ---------------- ");
		for (Element annotatedSetter : annotatedSetters) {						
			String className = null;
			String packageName = null;
			String fullyQualifiedClassName = ((TypeElement) annotatedSetter.getEnclosingElement()).getQualifiedName().toString();
			int lastDot = fullyQualifiedClassName.lastIndexOf('.');
			if (lastDot > 0) {
				packageName = fullyQualifiedClassName.substring(0, lastDot);
				className = fullyQualifiedClassName.substring(lastDot + 1);
			}else {
				packageName = "";
				className = fullyQualifiedClassName.substring(lastDot);
			}
			inspectionFile.print("//PACKAGE-NAME: ");
			inspectionFile.print(packageName);
			inspectionFile.print(", CLASS-NAME: ");
			inspectionFile.print(className);
			inspectionFile.print(", METHOD-NAME: ");
			inspectionFile.print(annotatedSetter.getSimpleName());
			inspectionFile.println();
		}
		inspectionFile.println("//------------- ****** ---------------- ");
	}	
	
	private Map<String, List<Element>> groupByClass(List<Element> annotatedSetters) {
		Map<String, List<Element>> groupedAnnotatedSetters = annotatedSetters.stream().collect(
				Collectors.groupingBy(e -> ((TypeElement) e.getEnclosingElement()).getQualifiedName().toString())
		);
		return groupedAnnotatedSetters;
	}

	private List<Element> getAnnotatedSetters(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {
    	List<Element> setterMethods = null;
    	List<Element> nonSetterMethods = null;
    	for (TypeElement supportedAnnotation : supportedAnnotations) {    		
            Set<? extends Element> allElementsAnnotatedWithSupportedAnnotation = roundEnv.getElementsAnnotatedWith(supportedAnnotation);
            Map<Boolean, List<Element>> allElementsAnnotatedWithSupportedAnnotationPartioned = allElementsAnnotatedWithSupportedAnnotation.stream().collect(
            		Collectors.partitioningBy(element -> (
            				(ExecutableType) element.asType()).getParameterTypes().size() == 1 && element.getSimpleName().toString().startsWith("set")
            		)
            );            
            nonSetterMethods = allElementsAnnotatedWithSupportedAnnotationPartioned.get(false);
            setterMethods = allElementsAnnotatedWithSupportedAnnotationPartioned.get(true);
            markWithErrors(nonSetterMethods);            		
    	}
		return setterMethods;
	}
	
	private void markWithErrors(List<Element> invalidMethods) {
		invalidMethods.forEach(element -> processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@PageModel must be applied to a setXxx method with a single argument", element));		
	}

    
//    private void writeBuilderFile(String className, Map<String, String> setterMap) throws IOException {
//
//        String packageName = null;
//        int lastDot = className.lastIndexOf('.');
//        if (lastDot > 0) {
//            packageName = className.substring(0, lastDot);
//        }
//
//        String simpleClassName = className.substring(lastDot + 1);
//        String builderClassName = className + "Builder";
//        String builderSimpleClassName = builderClassName.substring(lastDot + 1);
//
//        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);
//        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {
//
//            if (packageName != null) {
//                out.print("package ");
//                out.print(packageName);
//                out.println(";");
//                out.println();
//            }
//
//            out.print("public class ");
//            out.print(builderSimpleClassName);
//            out.println(" {");
//            out.println();
//
//            out.print("    private ");
//            out.print(simpleClassName);
//            out.print(" object = new ");
//            out.print(simpleClassName);
//            out.println("();");
//            out.println();
//
//            out.print("    public ");
//            out.print(simpleClassName);
//            out.println(" build() {");
//            out.println("        return object;");
//            out.println("    }");
//            out.println();
//
//            setterMap.entrySet().forEach(setter -> {
//                String methodName = setter.getKey();
//                String argumentType = setter.getValue();
//
//                out.print("    public ");
//                out.print(builderSimpleClassName);
//                out.print(" ");
//                out.print(methodName);
//
//                out.print("(");
//
//                out.print(argumentType);
//                out.println(" value) {");
//                out.print("        object.");
//                out.print(methodName);
//                out.println("(value);");
//                out.println("        return this;");
//                out.println("    }");
//                out.println();
//            });
//
//            out.println("}");
//
//        }
//    }

}
