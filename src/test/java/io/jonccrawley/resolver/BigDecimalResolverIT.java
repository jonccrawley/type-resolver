package io.jonccrawley.resolver;


import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class BigDecimalResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new BigDecimalResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(BigDecimal.class)
				.stringValue("0.01")
				.expectedResult(new BigDecimal("0.01"))
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}
}
