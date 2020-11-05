package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;

class LongResolver implements Resolver {

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		return Long.class.isAssignableFrom(type);
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return resolveLong(arg);
	}

	private static Long resolveLong(String arg) {

		return Long.valueOf(arg.trim());
	}

}
