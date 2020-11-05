package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class TypeResolverIT {

	@Test
	public void instantiation(){
		new TypeResolver();
	}

	@Test
	public void testInvalidType() throws Exception {
		try {
			TypeResolver.resolveType(Object.class, "---", "invalid/mime-type");
		} catch (Exception e) {
			return;
		}

		throw new Exception("Expected Error but one was not produced");
	}

	@Test
	public void testisResolvableTrue() throws Exception {

		Assert.assertTrue(new TypeResolver().isResolvable(String.class,"123",""));
	}

	@Test
	public void testisResolvableFalse() throws Exception {

		Assert.assertFalse(new TypeResolver().isResolvable(Object.class,"123",""));
	}

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new TypeResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver).desiredType(String.class)
				.stringValue("123")
				.expectedResult("123")
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver).desiredType(Integer.class)
				.stringValue("123")
				.expectedResult(123)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver).desiredType(Long.class)
				.stringValue("123")
				.expectedResult(123L)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver).desiredType(BigDecimal.class)
				.stringValue("10.34")
				.expectedResult(new BigDecimal("10.34"))
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
