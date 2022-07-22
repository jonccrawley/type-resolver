package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;
import javax.xml.bind.TypeConstraintException;

public class TypeResolver implements Resolver{

	private static final Resolver[] resolvers = new Resolver[]{new StringResolver(),
	   new NullOrEmptyResolver(),
	   new ListResolver(),
	   new SetResolver(),
	   new BooleanResolver(),
	   new LongResolver(),
	   new IntegerResolver(),
	   new DoubleResolver(),
	   new BigDecimalResolver(),
	   new JsonResolver(),
	   new XMLResolver()};

	TypeResolver(){}

	@SuppressWarnings({"unchecked","rawtypes"})
	public static Object resolveType(Class<?> type, String arg, String accept) throws HTTPStatusException {

		for(Resolver resolver : resolvers) {
			if (resolver.isResolvable(type, arg, accept)) {
				return resolver.resolve(type, arg, accept);
			}
		}

		throw new TypeConstraintException("Cannot resolve argument of type " + (type != null ? type.getName() : "null"));
	}


	@Override
	public boolean isResolvable(Class<?> type, String arg, String accept) {
		
		for(Resolver resolver : resolvers) {
			if (resolver.isResolvable(type, arg, accept)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object resolve(Class<?> type, String arg, String accept) throws HTTPStatusException {
		
		return resolveType(type,arg,accept);
	}
}
