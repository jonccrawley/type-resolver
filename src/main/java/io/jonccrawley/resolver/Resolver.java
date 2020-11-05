package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;
import io.jonccrawley.resolver.common.MIMEType;
import io.jonccrawley.resolver.serialization.Deserializer;

public interface Resolver {

	static Object deserialize(Class<?> type, String arg, String accept) throws HTTPStatusException {
		try {
			Deserializer desr = MIMEType.getType(accept).getDeserializer();
			return desr.deserialize(type,arg);
		} catch (Exception e) {
			throw new HTTPStatusException(HTTPStatusException.HTTPStatusCode.BAD_REQUEST,"Unable to deserialize parameters");
		}
	}

	boolean isResolvable(Class<?> type,String arg, String accept);

	Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException;

}
