package io.jonccrawley.resolver;

import io.jonccrawley.resolver.scenario.ResolverTestScenario;
import io.jonccrawley.resolver.scenario.Scenario;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

public class XMLResolverIT {

	@Test(dataProvider = "getScenarios")
	public void processScenarios(Scenario scenario) throws Exception {

		scenario.run();
	}

	@DataProvider
	public Object[][] getScenarios() {

		List<Scenario> scenarios = new LinkedList<>();
		Resolver resolver = new XMLResolver();

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(TestObject.class)
				.stringValue("<TestObject><testValue>1</testValue></TestObject>")
				.expectedResult(new TestObject(1))
				.contentType("application/xml")
				.build());

		scenarios.add(new ResolverTestScenario.Builder(resolver)
				.desiredType(TestObject.class)
				.stringValue("<badXML>1</badXML>")
				.expectError()
				.expectedResult(new TestObject(1))
				.contentType("application/xml")
				.build());

		return Scenario.scenariosObjectArray(scenarios);
	}

	@JacksonXmlRootElement
	static class TestObject {

		@JacksonXmlProperty
		int testValue;

		TestObject(){}

		TestObject(int testValue) {
			this.testValue = testValue;
		}

		public int getTestValue() {
			return testValue;
		}

		public void setTestValue(int testValue) {
			this.testValue = testValue;
		}

		@Override
		public boolean equals(Object obj) {

			return (obj != null && getClass().isAssignableFrom(obj.getClass()) && testValue == ((TestObject)obj).getTestValue());
		}
	}
}
