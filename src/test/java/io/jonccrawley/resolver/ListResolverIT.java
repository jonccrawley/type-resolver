package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new ListResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(List.class)
				.stringValue("1|2|3")
				.expectedResult(new ArrayList<>(Arrays.asList(new String[]{"1","2","3"})))
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(List.class)
				.stringValue("1,2,3")
				.expectedResult(new ArrayList<>(Arrays.asList(new String[]{"1","2","3"})))
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
