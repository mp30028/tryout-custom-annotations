package com.zonesoft.modelling.framework;

import static com.zonesoft.modelling.framework.Constants.IMPLEMENTATION_SUFFIX;
import static com.zonesoft.modelling.framework.Constants.EXPECTATION_SUFFIX;

import java.lang.reflect.InvocationTargetException;

public class Factory {
	
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
	
	
	@SuppressWarnings("unchecked")
	public static <T extends IPageModel> IExpectation<T> createExpectation(Class<T> klass, T arg) {
		try {
			return (IExpectation<T>) Class.forName(klass.getName() + EXPECTATION_SUFFIX).getConstructor(klass).newInstance(arg);

		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}	
}
