package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

public class SetResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new SetResolver();

		Set expectedResult = new HashSet<>(Arrays.asList(new String[]{"1","2","3"}));

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Set.class)
				.stringValue("1|2|3")
				.expectedResult(expectedResult)
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(Set.class)
				.stringValue("1,2,3")
				.expectedResult(expectedResult)
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
