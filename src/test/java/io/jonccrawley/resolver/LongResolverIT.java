package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class LongResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new LongResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Long.class)
				.stringValue("1")
				.expectedResult(1L)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Long.class)
				.stringValue("9223372036854775807")
				.expectedResult(9223372036854775807L)
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
