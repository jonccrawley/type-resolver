package io.jonccrawley.resolver;

import io.jonccrawley.resolver.exception.HTTPStatusException;
import org.testng.annotations.Test;

public class ResolverIT {

	@Test
	public void testResolverError() throws Exception {

		try {
			Resolver.deserialize(String.class, "{}", "invalid/mime-type");
		} catch (HTTPStatusException e) {
			return;
		}

		throw new Exception("Expected Error but one was not produced");
	}
}
