package com.zonesoft.annotations.e2e_testing;

import static com.zonesoft.annotations.utilities.WriteMessage.writeMsg;

import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import com.google.auto.service.AutoService;
import com.zonesoft.annotations.e2e_testing.helpers.PageModelHelper;

@SupportedAnnotationTypes("com.zonesoft.annotations.e2e_testing.PageModel")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class PageModelProcessor extends AbstractProcessor {
	private static final String PAGE_MODEL_ELEMENT = "com.zonesoft.annotations.e2e_testing.PageModelElement";
	@Override
    public boolean process(Set<? extends TypeElement> supportedAnnotations, RoundEnvironment roundEnv) {		
		writeMsg("supportedAnnotations.size() = {0}", supportedAnnotations.size());
		PageModelHelper helper = new PageModelHelper(supportedAnnotations, roundEnv);
		Set<Element> annotatedClasses = helper.fetchAllAnnotatedClasses();
		helper.inspectElements("After-Fetch:annotatedClasses", annotatedClasses);
		for (Element annotatedClass: annotatedClasses) {
			Set<Element> annotatedClassElements = helper.fetchAllClassElementsAnnotatedBy(annotatedClass, PAGE_MODEL_ELEMENT);
			helper.inspectElements("After-Fetch:annotatedClassElements:" + annotatedClass.getSimpleName() + ": ", annotatedClassElements);
		}
    	return true;
    }
}
