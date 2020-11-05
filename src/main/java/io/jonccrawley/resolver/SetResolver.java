package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;
import com.google.common.base.Splitter;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetResolver implements Resolver {

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		return isSet(type);
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return resolveSet(arg);
	}

	public static boolean isSet(Class<?> type) {

		return Set.class.isAssignableFrom(type) || type == Collection.class || type == Iterable.class;
	}

	@SuppressWarnings("unchecked")
	private static Set<?> resolveSet(String arg) throws HTTPStatusException {

		List<?> list = Splitter.onPattern("([,.|])")
								.trimResults()
								.omitEmptyStrings()
								.splitToList(arg);

		return new HashSet(list);
	}
}
