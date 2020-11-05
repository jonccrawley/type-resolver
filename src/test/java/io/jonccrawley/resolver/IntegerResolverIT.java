package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class IntegerResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new IntegerResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Integer.class)
				.stringValue("1")
				.expectedResult(1)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Integer.class)
				.stringValue("2147483647")
				.expectedResult(2147483647)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Integer.class)
				.stringValue("-1")
				.expectedResult(-1)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(int.class)
				.stringValue("1")
				.expectedResult(1)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(int.class)
				.stringValue("2147483647")
				.expectedResult(2147483647)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(int.class)
				.stringValue("-1")
				.expectedResult(-1)
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
