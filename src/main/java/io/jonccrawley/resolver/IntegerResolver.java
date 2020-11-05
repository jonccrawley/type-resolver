package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;

class IntegerResolver implements Resolver {

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		return Integer.class.isAssignableFrom(type);
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return resolveInteger(arg);
	}

	private static Integer resolveInteger(String arg) {

		return Integer.valueOf(arg.trim());
	}

}
