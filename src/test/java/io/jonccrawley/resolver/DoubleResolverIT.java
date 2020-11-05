package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class DoubleResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new DoubleResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Double.class)
				.stringValue("90")
				.expectedResult(new Double("90"))
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Double.class)
				.stringValue("10.35")
				.expectedResult(new Double("10.35"))
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
