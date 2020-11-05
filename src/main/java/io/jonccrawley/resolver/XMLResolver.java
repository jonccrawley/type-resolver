package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;
import io.jonccrawley.resolver.serialization.XMLSerializer;

public class XMLResolver implements Resolver {

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		return (type != null && arg.startsWith("<"));
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {

		try {
			return new XMLSerializer().deserialize(type,arg);
		} catch (Exception e) {
			throw new HTTPStatusException(HTTPStatusException.HTTPStatusCode.BAD_REQUEST,"Unable to deserialize parameters");
		}
	}
}
