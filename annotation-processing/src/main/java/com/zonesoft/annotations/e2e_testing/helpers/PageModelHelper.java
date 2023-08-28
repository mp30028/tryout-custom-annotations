package com.zonesoft.annotations.e2e_testing.helpers;

import static com.zonesoft.utilities.WriteMessage.writeMsg;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

import com.zonesoft.annotations.e2e_testing.PageModel;
import com.zonesoft.annotations.e2e_testing.PageModelElement;
import com.zonesoft.modelling.framework.PageElementType;
import com.zonesoft.modelling.framework.SelectBy;



public class PageModelHelper {
	
	public enum NameTypes{
		fullyQualifiedClassName,
		simpleClassName,
		packageName
	}	
	
	private final Set<? extends TypeElement> classAnnotations;
	private final RoundEnvironment roundEnv;
	
	public PageModelHelper(Set<? extends TypeElement> classAnnotations, RoundEnvironment roundEnv) {
		this.classAnnotations = classAnnotations;
		this.roundEnv = roundEnv;
	}

	public Set<? extends TypeElement> getClassAnnotations() {
		return this.classAnnotations;
	}
	
	public Set<Element> fetchAllAnnotatedClasses(){
		Set<Element> annotatedClasses = new HashSet<>();
		for (Element annotation: this.classAnnotations) {
			@SuppressWarnings("unchecked")
			Set<Element> selectedElements = (Set<Element>) roundEnv.getElementsAnnotatedWith((TypeElement) annotation); 
			annotatedClasses.addAll(selectedElements);
		}
		return annotatedClasses;
	}
	
	@SuppressWarnings("unchecked")
	public Set<Element> fetchAllClassElementsAnnotatedBy(Element annotatedClass, String annotatedBy){ 										
		Set<Element> filteredElements = new HashSet<>();
			try {
				List<Element> enclosedElements = (List<Element>) annotatedClass.getEnclosedElements();
//				writeMsg("enclosedAnnotations.size()={0}", enclosedElements.size());
				if (Objects.nonNull(enclosedElements)) {
					for(Element enclosedElement: enclosedElements) {						
						Annotation pageModelElementAnnotation = getAnnotationByName(enclosedElement, annotatedBy);
						if (Objects.nonNull(pageModelElementAnnotation)) {
							filteredElements.add(enclosedElement);
						}
					}
				}
			}catch(Exception e) {
				e.printStackTrace();
			}					
		return filteredElements;
	}
	
	public Map<NameTypes, String> getNames(Element annotatedClass){
		String fullyQualifiedClassName = ((TypeElement) annotatedClass).getQualifiedName().toString();
		String simpleClassName = annotatedClass.getSimpleName().toString();				
		String packageName = parsePackageNameFromFullyQualifedName(fullyQualifiedClassName);
		Map<NameTypes, String> names = new HashMap<>();
		names.put(NameTypes.fullyQualifiedClassName, fullyQualifiedClassName);
		names.put(NameTypes.simpleClassName, simpleClassName);
		names.put(NameTypes.packageName, packageName);
		return names;		
	}
	
	public String parsePackageNameFromFullyQualifedName(String fullyQualifiedClassName) {
		String packageName;
		int lastDot = fullyQualifiedClassName.lastIndexOf('.');
		if (lastDot > 0) {
			packageName = fullyQualifiedClassName.substring(0, lastDot);
		}else {
			packageName = "";
		}
		return packageName;
	}
	
	@SuppressWarnings("unchecked")
	public Annotation getAnnotationByName(Element elementToInspect, String fullyQualifiedAnnotationName) {
    	try {		
    		Annotation[] pageModelElementAnnotation = null;
    		Class<?> annotationClass = Class.forName(fullyQualifiedAnnotationName);
    		pageModelElementAnnotation = elementToInspect.getAnnotationsByType((Class<PageModelElement>) annotationClass);
   		
			if (Objects.nonNull(pageModelElementAnnotation) && pageModelElementAnnotation.length > 0) {
				return pageModelElementAnnotation[0];
			}else {
				return null;
			}
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }				
	}
	
	public String getPagePathAttribute(Element annotatedClass) {		
	    PageModel pageModelAnnotation = annotatedClass.getAnnotation(PageModel.class);				
		return pageModelAnnotation.pagePath();
	}
	
	public PageElementType getElementTypeAttribute(Element annotatedClassElement) {
	    PageModelElement pageModelElmentAnnotation = annotatedClassElement.getAnnotation(PageModelElement.class);				
		return pageModelElmentAnnotation.elementType();
	}

	public SelectBy getElementByAttribute(Element annotatedClassElement) {
	    PageModelElement pageModelElmentAnnotation = annotatedClassElement.getAnnotation(PageModelElement.class);				
		return pageModelElmentAnnotation.elementBy();
	}

	public String getElementHavingAttribute(Element annotatedClassElement) {
	    PageModelElement pageModelElmentAnnotation = annotatedClassElement.getAnnotation(PageModelElement.class);				
		return pageModelElmentAnnotation.elementHaving();
	}

	public SelectBy getPromptByAttribute(Element annotatedClassElement) {
	    PageModelElement pageModelElmentAnnotation = annotatedClassElement.getAnnotation(PageModelElement.class);				
		return pageModelElmentAnnotation.promptBy();
	}

	public String getPromptHavingAttribute(Element annotatedClassElement) {
	    PageModelElement pageModelElmentAnnotation = annotatedClassElement.getAnnotation(PageModelElement.class);				
		return pageModelElmentAnnotation.promptHaving();
	}	
	
	public void inspectElements(String label, Set<Element> annotatedElements) {
		writeMsg("{0}:annotatedElements.size() = {1}",label, annotatedElements.size());
		for (Element annotatedElement: annotatedElements) {
			inspectElement(label, annotatedElement);
		}
	}
	
	public void inspectElement(String label, Element annotatedElement) {
			writeMsg("{0}:annotatedElement.getKind()={1}", label, annotatedElement.getKind().toString());
			writeMsg("{0}:annotatedElement.getSimpleName()={1}", label, annotatedElement.getSimpleName().toString());
			writeMsg("{0}:annotatedElement.asType()={1}", label, annotatedElement.asType().toString());			
			if (isClassOrInterface(annotatedElement)) {
				String fullyQualifiedClassName = ((TypeElement) annotatedElement).getQualifiedName().toString();
				writeMsg("{0}:fullyQualifiedClassName={1}", label, fullyQualifiedClassName);
			}else {
				
			}
	}

	public boolean isClassOrInterface(Element annotatedElement) {
		return ((annotatedElement.getKind() == ElementKind.CLASS) ||(annotatedElement.getKind() == ElementKind.INTERFACE));		
	}



}
