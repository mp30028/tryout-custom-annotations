package com.zonesoft.modelling.framework;

import static com.zonesoft.modelling.framework.Constants.IMPLEMENTATION_SUFFIX;

import java.lang.reflect.InvocationTargetException;

public class PageModelFactory {
	
	@SuppressWarnings("unchecked")
	public static <T> T createPageModel(Class<T> klass) {
		try {
			return (T) Class.forName(klass.getName() + IMPLEMENTATION_SUFFIX).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
