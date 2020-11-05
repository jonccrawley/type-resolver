package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;

class DoubleResolver implements Resolver{

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		return Double.class.isAssignableFrom(type);
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return resolveDouble(arg);
	}

	private static Double resolveDouble(String arg) {

		return Double.valueOf(arg.trim());
	}
}
