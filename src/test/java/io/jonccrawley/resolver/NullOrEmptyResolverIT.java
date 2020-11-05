package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NullOrEmptyResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new NullOrEmptyResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(String.class)
				.stringValue(null)
				.expectedResult(null)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(List.class)
				.stringValue("")
				.expectedResult(Collections.emptyList())
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Set.class)
				.stringValue("")
				.expectedResult(Collections.emptySet())
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(int.class)
				.stringValue("1")
				.expectError()
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
