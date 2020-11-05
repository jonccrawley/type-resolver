package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;
import io.jonccrawley.resolver.common.MIMEType;

class JsonResolver implements Resolver {

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {

		MIMEType mimeType = MIMEType.getType(accept);

		if (mimeType == null) {
			return false;
		}

		return MIMEType.JSON.equals(mimeType);
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return Resolver.deserialize(type, arg, accept);
	}
}
