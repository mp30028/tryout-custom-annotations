package com.zonesoft.annotation.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.google.auto.service.AutoService;
import static com.zonesoft.annotation.utils.Utilities.writeMsg;

@SupportedAnnotationTypes("com.zonesoft.annotation.processor.BuilderProperty")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
@AutoService(Processor.class)
public class BuilderProcessor extends AbstractProcessor {
	
//	private static final Logger LOGGER = LoggerFactory.getLogger(BuilderProcessor.class);
    
	@Override
    public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {
		
		writeMsg("supportedAnnotations.size() = {0}", supportedAnnotations.size());
		if (supportedAnnotations.size() > 0) {
			List<Element> annotatedSetters = getAnnotatedSetters(supportedAnnotations, roundEnv);
			writeMsg("annotatedSetters.size() = {0}", annotatedSetters.size());
//			String className = ((TypeElement) annotatedSetters.get(0).getEnclosingElement()).getQualifiedName().toString();
		    try (PrintWriter inspectionFile = openInspectionFile("INSPECTION")){			  
				  writeInspectionFile(inspectionFile, annotatedSetters);
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }	    		     	    	    				
		}
		
//    	printMessage("supportedAnnotations.size()=", supportedAnnotations.iterator().next());
//    	List<Element> annotatedSetters = getAnnotatedSetters(supportedAnnotations, roundEnv);
//    	String className = ((TypeElement) annotatedSetters.get(0).getEnclosingElement()).getQualifiedName().toString();
//	    try (PrintWriter inspectionFile = openInspectionFile(className + "_INSPECTION")){			  
//			  writeInspectionFile(inspectionFile, annotatedSetters);
//	    } catch (IOException e) {
//	    	e.printStackTrace();
//	    }	    		     	    	    	
    	return true;
    }
    
    


	private PrintWriter openInspectionFile(String inspectionFileName) throws IOException {
		return new PrintWriter(processingEnv.getFiler().createSourceFile(inspectionFileName).openWriter());
	}




	private void writeInspectionFile(PrintWriter out, List<Element> annotatedSetters) {
		
		for (Element annotatedSetter : annotatedSetters) {
			out.println("//------------- Inspection Data ---------------- ");
			String packageName = null;
			String className = ((TypeElement) annotatedSetter.getEnclosingElement()).getQualifiedName().toString();
			int lastDot = className.lastIndexOf('.');
			if (lastDot > 0) {
				packageName = className.substring(0, lastDot);
			}
			out.println();
			out.print("//PACKAGE-NAME: ");
			out.print(packageName);
			out.print(", CLASS-NAME: ");
			out.print(className);
			out.print(", METHOD-NAME: ");
			out.print(annotatedSetter.getSimpleName());
			out.println();
			out.println();
		}
	}



	private List<Element> getAnnotatedSetters(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {
    	List<Element> setterMethods = null;
    	List<Element> nonSetterMethods = null;
//    	printMessage("supportedAnnotations.size() = " + supportedAnnotations.size(), supportedAnnotations.iterator().next());
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
	
//	private void printMessage(String message,Element element) {
//		processingEnv.getMessager().printMessage(Diagnostic.Kind.OTHER, "->->->->->" + message, element);		
//	}

	private void markWithErrors(List<Element> invalidMethods) {
		invalidMethods.forEach(element -> processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@BuilderProperty must be applied to a setXxx method with a single argument", element));		
	}

//	public boolean process_DISABLED(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {    	
//        for (TypeElement annotation : annotations) {
//
//            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
//
//            Map<Boolean, List<Element>> annotatedMethods = annotatedElements.stream().collect(
//            		Collectors.partitioningBy(element -> (
//            				(ExecutableType) element.asType()).getParameterTypes().size() == 1 && element.getSimpleName().toString().startsWith("set")
//            		)
//            );
//
//            List<Element> setters = annotatedMethods.get(true);
//            List<Element> otherMethods = annotatedMethods.get(false);
//
//            otherMethods.forEach(element -> processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "@BuilderProperty must be applied to a setXxx method with a single argument", element));
//
//            if (setters.isEmpty()) {
//                continue;
//            }
//
//            String className = ((TypeElement) setters.get(0).getEnclosingElement()).getQualifiedName().toString();
//
//            Map<String, String> setterMap = setters.stream().collect(
//            		Collectors.toMap(setter -> 
//            			className + "." + setter.getSimpleName().toString(), setter -> (
//            					(ExecutableType) setter.asType())
//            			.getParameterTypes().get(0).toString()
//            		)
//            );
//
//            try {
////                writeBuilderFile(className, setterMap);
//                writeInspectionFile(className, setterMap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return true;
//    }
//
//    private void writeInspectionFile(String className, Map<String, String> setterMap) throws IOException {
//        String packageName = null;
//        int lastDot = className.lastIndexOf('.');
//        if (lastDot > 0) {
//            packageName = className.substring(0, lastDot);
//        }
//
//        String simpleClassName = className.substring(lastDot + 1);
//        String inspectionFileName = simpleClassName + ".INSPECT";
//        JavaFileObject inspectionFile = processingEnv.getFiler().createSourceFile(inspectionFileName);
//        try (PrintWriter out = new PrintWriter(inspectionFile.openWriter())) {
//        	
//        	
//            if (packageName != null) {
//                out.print("//PACKAGE-NAME: ");
//                out.print(packageName);                
//                out.println();
//            }
//            out.println();
//            out.println();
//            out.println();
//            out.println("//------------- Inspection Data ---------------- ");
//            out.print("//CLASS-NAME: ");out.println(className);
//            out.println();
//            out.println();
//            out.println();
//            out.println("//------------- Setters Found ---------------- ");           
//            setterMap.entrySet().forEach(setter -> {
//                String methodName = setter.getKey();
//                String argumentType = setter.getValue();
//                out.print("//METHOD-NAME:");out.print(methodName); out.print("//INPUT-PARAM-TYPE:") ;out.print(argumentType);out.println();
//                
//            });            
//            
//        }
//    	
//    }
    
    private void writeBuilderFile(String className, Map<String, String> setterMap) throws IOException {

        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String simpleClassName = className.substring(lastDot + 1);
        String builderClassName = className + "Builder";
        String builderSimpleClassName = builderClassName.substring(lastDot + 1);

        JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(builderClassName);
        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }

            out.print("public class ");
            out.print(builderSimpleClassName);
            out.println(" {");
            out.println();

            out.print("    private ");
            out.print(simpleClassName);
            out.print(" object = new ");
            out.print(simpleClassName);
            out.println("();");
            out.println();

            out.print("    public ");
            out.print(simpleClassName);
            out.println(" build() {");
            out.println("        return object;");
            out.println("    }");
            out.println();

            setterMap.entrySet().forEach(setter -> {
                String methodName = setter.getKey();
                String argumentType = setter.getValue();

                out.print("    public ");
                out.print(builderSimpleClassName);
                out.print(" ");
                out.print(methodName);

                out.print("(");

                out.print(argumentType);
                out.println(" value) {");
                out.print("        object.");
                out.print(methodName);
                out.println("(value);");
                out.println("        return this;");
                out.println("    }");
                out.println();
            });

            out.println("}");

        }
    }

}
