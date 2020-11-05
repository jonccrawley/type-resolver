package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;

import java.math.BigDecimal;

class BigDecimalResolver implements Resolver {

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		return type == BigDecimal.class;
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return resolveBigDecimal(arg);
	}

	private static BigDecimal resolveBigDecimal(String arg) {

		return new BigDecimal(arg.trim());
	}
}
