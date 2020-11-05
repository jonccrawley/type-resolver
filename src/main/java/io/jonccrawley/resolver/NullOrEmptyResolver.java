package io.jonccrawley.resolver;

import java.util.Collections;

class NullOrEmptyResolver implements Resolver{

	@Override
	public boolean isResolvable(Class<?> type,String arg, String accept) {
		return (arg == null || arg.trim().isEmpty());
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) {
		return resolveNullOrEmpty(type);
	}

	private static Object resolveNullOrEmpty(Class<?> type) {

		if (type.isPrimitive()) {
			throw new NullPointerException("Unable to resolve primitive type for '" + type.getName() + "'");
		}
		if (ListResolver.isList(type)) {
			return Collections.emptyList();
		}
		if (SetResolver.isSet(type)) {
			return Collections.emptySet();
		}
		return null;
	}

}
