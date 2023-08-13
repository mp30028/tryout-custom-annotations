package com.zonesoft.annotation.usage;
//out.write("package "); out.write(packageName); out.write(";\n\n");

import static com.zonesoft.annotation.usage.Colours.*;
//out.write("import static "); out.write(fullyQualifiedClassName); out.write(".*;\n\n");


public class ColoursExtender_DISAB {
//out.write("public class "); out.write(targetSimpleClassName); out.write(" {\n");
	
	public ColoursExtender_DISAB() {
	//out.write("public "); out.write(targetSimpleClassName); out.write("() {\n");
		
		GREEN = "GREEN";
		BLUE = "BLUE";
		YELLOW = "YELLOW";
		//out.write("\t"); out.write(elementName); out.write(" = "); out.write("\""); out.write(elementName); out.write("\";\n");
	}
	//out.write("}\n");	
	
	public Colours compareWith(final Colours page) {
		return page;
	}
	
}
