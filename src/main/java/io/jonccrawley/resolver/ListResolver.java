package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;
import com.google.common.base.Splitter;

import java.util.Collection;
import java.util.List;

public class ListResolver implements Resolver{

	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		return isList(type);
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		return resolveList(arg);
	}

	public static boolean isList(Class<?> type) {

		return List.class.isAssignableFrom(type) || type == Collection.class || type == Iterable.class;
	}

	private static List<?> resolveList(String arg) {

		return Splitter.onPattern("([,.|])")
				.trimResults()
				.omitEmptyStrings()
				.splitToList(arg);
	}
}
