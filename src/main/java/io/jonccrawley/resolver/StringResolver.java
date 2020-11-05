package io.jonccrawley.resolver;

class StringResolver implements Resolver {

	@Override
	public boolean isResolvable(Class<?> type,String arg, String accept) {
		return type == String.class;
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) {
		return arg;
	}
}
