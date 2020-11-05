package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class JsonResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {
		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new JsonResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(TestObject.class)
				.stringValue("{'testValue':1}")
				.expectedResult(new TestObject(1))
				.contentType("application/json")
				.build());

		//TODO: check validation.

		return Scenario.scenariosObjectArray(scenarios);
	}

	static class TestObject {
		int testValue;

		TestObject(int testValue) {
			this.testValue = testValue;
		}

		int getTestValue() {
			return testValue;
		}

		@Override
		public boolean equals(Object obj) {

			return (obj != null && TestObject.class.isAssignableFrom(obj.getClass()) && testValue == ((TestObject)obj).getTestValue());
		}
	}
}
