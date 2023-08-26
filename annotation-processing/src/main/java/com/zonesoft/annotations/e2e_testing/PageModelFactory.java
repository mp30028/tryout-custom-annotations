package com.zonesoft.annotations.e2e_testing;

import java.lang.reflect.InvocationTargetException;
import static com.zonesoft.annotations.e2e_testing.Constants.IMPLEMENTATION_SUFFIX;

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
