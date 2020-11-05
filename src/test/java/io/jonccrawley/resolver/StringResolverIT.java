package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class StringResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new StringResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(String.class)
				.stringValue("1234567890!@#$%^&*()_+\"';.,qwertyuiopasdfghjklzxcvbnm.")
				.expectedResult("1234567890!@#$%^&*()_+\"';.,qwertyuiopasdfghjklzxcvbnm.")
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(String.class)
				.stringValue("testString0.1")
				.expectedResult("testString0.1")
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}

}
