package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class BooleanResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new BooleanResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Boolean.class)
				.stringValue("t")
				.expectedResult(true)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Boolean.class)
				.stringValue("T")
				.expectedResult(true)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Boolean.class)
				.stringValue("true")
				.expectedResult(true)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Boolean.class)
				.stringValue("f")
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Boolean.class)
				.stringValue("false")
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(boolean.class)
				.stringValue("t")
				.expectedResult(true)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(boolean.class)
				.stringValue("T")
				.expectedResult(true)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(boolean.class)
				.stringValue("true")
				.expectedResult(true)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(boolean.class)
				.stringValue("f")
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(boolean.class)
				.stringValue("false")
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(null)
				.stringValue("false")
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Integer.class)
				.stringValue("false")
				.expectedResult(false)
				.build());

		//testing isvalidatable.
		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.scenarioType(ResolverTestScenario.ScenarioType.VALIDATION)
				.desiredType(Integer.class)
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.scenarioType(ResolverTestScenario.ScenarioType.VALIDATION)
				.desiredType(int.class)
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.scenarioType(ResolverTestScenario.ScenarioType.VALIDATION)
				.desiredType(null)
				.expectedResult(false)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.scenarioType(ResolverTestScenario.ScenarioType.VALIDATION)
				.desiredType(Boolean.class)
				.expectedResult(false)
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
