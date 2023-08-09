package com.zonesoft.annotations.e2e_testing;

import static com.zonesoft.annotations.utilities.WriteMessage.writeMsg;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;

import com.google.auto.service.AutoService;

@SupportedAnnotationTypes({
	"com.zonesoft.annotations.e2e_testing.PageModel",
	"com.zonesoft.annotations.e2e_testing.PageModelElement"
})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class PageModelProcessor extends AbstractProcessor {
	private static final String PAGE_MODEL_SUFFIX = "PageModel";
	@Override
    public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {		
		writeMsg("supportedAnnotations.size() = {0}", supportedAnnotations.size());
		for (Element supportedAnnotation: supportedAnnotations) {
			Set<Element> annotatedElements = getAnnotatedElements(supportedAnnotation, roundEnv);
			writeMsg("annotatedElements.size() = {0}", annotatedElements.size());
			for (Element annotatedElement: annotatedElements) {
				inspectElement(annotatedElement);
//				writePageModelFiles(annotatedElements);
			}
		}
    	return true;
    }
	
	private void inspectElement(Element annotatedElement) {
			writeMsg("annotatedElement.getKind()={0}", annotatedElement.getKind().toString());
			writeMsg("annotatedElement.getSimpleName()={0}", annotatedElement.getSimpleName().toString());
			writeMsg("annotatedElement.asType()={0}", annotatedElement.asType().toString());			
			boolean isClassOrInteface = ((annotatedElement.getKind() == ElementKind.CLASS) ||(annotatedElement.getKind() == ElementKind.INTERFACE));
			writeMsg("isClassOrInteface={0}", isClassOrInteface);
			if (isClassOrInteface) {
				String fullyQualifiedClassName = ((TypeElement) annotatedElement).getQualifiedName().toString();
				writeMsg("fullyQualifiedClassName={0}", fullyQualifiedClassName);
			}else {
				
			}
//		    try (PrintWriter pageModelFile = openFile( fullyQualifiedClassName + PAGE_MODEL_SUFFIX)){			  
//		    	writePageModelFile(pageModelFile, annotatedElement);
//		    } catch (IOException e) {
//		    	e.printStackTrace();
//		    }	
//		}		
	}

	private Set<Element> getAnnotatedElements(Element supportedAnnotation,RoundEnvironment roundEnv) {
//    	List<Element> validElements = new ArrayList<>();
    	Set<Element> allAnnotatedElements = new HashSet<>();
//    	for (TypeElement supportedAnnotation : supportedAnnotations) {
    		@SuppressWarnings("unchecked")
			Set<Element> selectedElements = (Set<Element>) roundEnv.getElementsAnnotatedWith((TypeElement) supportedAnnotation);    		
    		allAnnotatedElements.addAll(selectedElements);
//    	}
		return allAnnotatedElements;
	}
	
//	private List<Element> getAnnotatedElements(Set<? extends TypeElement> supportedAnnotations,RoundEnvironment roundEnv) {
//    	List<Element> validElements = new ArrayList<>();    	
//    	for (TypeElement supportedAnnotation : supportedAnnotations) {
//    		@SuppressWarnings("unchecked")
//    		Set<Element> allAnnotatedElements = (Set<Element>) roundEnv.getElementsAnnotatedWith(supportedAnnotation);
//    		validElements.addAll(allAnnotatedElements);
//    	}
//		return validElements;
//	}

	private void writePageModelFiles(List<Element> annotatedClassElements) {
		for (Element classElement : annotatedClassElements) {
			String fullyQualifiedClassName = ((TypeElement) classElement).getQualifiedName().toString();
		    try (PrintWriter pageModelFile = openFile( fullyQualifiedClassName + PAGE_MODEL_SUFFIX)){			  
		    	writePageModelFile(pageModelFile, classElement);
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }	
		}
	}
	
	private void writePageModelFile(PrintWriter pageModelFile, Element classElement) {
		pageModelFile.println("//------------- pageModelFile Inspection Result ---------------- ");
			Map<ElementType, String> attributes = getElementAttributes(classElement);
			pageModelFile.print("//PACKAGE-NAME: ");
			pageModelFile.print(attributes.get(ElementType.PACKAGE));
			pageModelFile.print(", CLASS-NAME: ");
			pageModelFile.print(attributes.get(ElementType.TYPE));
			pageModelFile.println();
		pageModelFile.println("//------------- ****** ---------------- ");		
	}
	
	private Map<ElementType, String> getElementAttributes(Element element){
		String className = null;
		String packageName = null;
		String fullyQualifiedClassName = ((TypeElement) element).getQualifiedName().toString();
		int lastDot = fullyQualifiedClassName.lastIndexOf('.');
		if (lastDot > 0) {
			packageName = fullyQualifiedClassName.substring(0, lastDot);
			className = fullyQualifiedClassName.substring(lastDot + 1);
		}else {
			packageName = "";
			className = fullyQualifiedClassName;
		}
		Map<ElementType, String> elementAttributes =  new HashMap<>();
		elementAttributes.put(ElementType.PACKAGE, packageName);
		elementAttributes.put(ElementType.TYPE, className);
		return elementAttributes;
	}

	private PrintWriter openFile(String fileName) throws IOException {
		return new PrintWriter(processingEnv.getFiler().createSourceFile(fileName).openWriter());
	}
}
