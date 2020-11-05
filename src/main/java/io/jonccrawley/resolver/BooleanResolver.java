package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;

class BooleanResolver implements Resolver{

	@Override
	public boolean isResolvable(Class type, String arg, String accept) {
		return Boolean.class.isAssignableFrom(type);
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return resolveBoolean(arg);
	}

	private static boolean resolveBoolean(String arg) {

		if ("t".equalsIgnoreCase(arg)) {
			return true;
		}
		return Boolean.parseBoolean(arg.trim());
	}
}
