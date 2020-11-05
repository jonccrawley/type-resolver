package io.jonccrawley.resolver.scenario;

import io.jonccrawley.resolver.Resolver;
import org.testng.Assert;


public class ResolverTestScenario implements Scenario {

	public enum ScenarioType {RESOLVER,VALIDATION}

	public static class Builder{
		private final Resolver resolver;
		private Class<?> klazz;
		private String stringValue = "";
		private Object expectedResult = null;
		private String contentType = "";
		private boolean expectError;
		private ScenarioType scenarioType = ScenarioType.RESOLVER;

		public Builder(Resolver resolver) {
			this.resolver = resolver;
		}

		public Builder desiredType(Class<?> klazz){
			this.klazz = klazz;
			return this;
		}

		public Builder stringValue(String stringValue) {
			this.stringValue = stringValue;
			return this;
		}

		public Builder expectedResult(Object expectedResult) {
			this.expectedResult = expectedResult;
			return this;
		}

		public Builder scenarioType(ScenarioType scenarioType) {
			this.expectedResult = expectedResult;
			return this;
		}

		public Builder expectError() {
			this.expectError = true;
			return this;
		}

		public Builder contentType(String contentType) {
			this.contentType = contentType;
			return this;
		}

		public ResolverTestScenario build() {
			return new ResolverTestScenario(resolver, scenarioType, klazz, stringValue, expectedResult, contentType, expectError);
		}

	}

	private final Resolver resolver;
	private final Class<?> klazz;
	private final String stringValue;
	private final Object expectedResult;
	private final String contentType;
	private final boolean expectError;
	private final ScenarioType scenarioType;

	private ResolverTestScenario(Resolver resolver, ScenarioType scenarioType, Class<?> klazz, String stringValue, Object expectedResult, String contentType, boolean expectError) {
		this.scenarioType = scenarioType;
		this.expectError = expectError;
		this.resolver = resolver;
		this.klazz = klazz;
		this.stringValue = stringValue;
		this.expectedResult = expectedResult;
		this.contentType = contentType;
	}

	public void run() throws Exception {

		switch (scenarioType){
			case RESOLVER:
				processResolver();
				break;
			case VALIDATION:
				processValidation();
				break;
		}
	}

	private void processValidation() throws Exception{
		if(expectError) {
			runValidationSuccess();
		} else {
			runValidationException();
		}
	}

	private void processResolver() throws Exception{
		if(expectError) {
			runResolverException();
		} else {
			runResolverSuccess();
		}
	}

	private void runValidationSuccess() throws Exception{
		try {

			Assert.assertEquals(expectedResult,resolver.isResolvable(klazz,
					stringValue,
					contentType));


		} catch (Exception e) {
			System.out.println("Error running Scenario with String Value: "+stringValue+" Object: "+expectedResult);
			throw e;
		}
	}

	private void runResolverSuccess() throws Exception{
		try {

			Assert.assertEquals(expectedResult,resolver.resolve(klazz,
					stringValue,
					contentType));


		} catch (Exception e) {
			System.out.println("Error running Scenario with String Value: "+stringValue+" Object: "+expectedResult);
			throw e;
		}
	}

	private void runValidationException() throws Exception{
		try {

			Assert.assertEquals(expectedResult,resolver.isResolvable(klazz,
					stringValue,
					contentType));


		} catch (Exception e) {
			return;
		}

		throw new Exception("Expected failure, but the resolver completed successfully when running scenario with String Value: "+stringValue+" Object: "+expectedResult);
	}

	private void runResolverException() throws Exception{
		try {

			Assert.assertEquals(expectedResult,resolver.resolve(klazz,
					stringValue,
					contentType));


		} catch (Exception e) {
			return;
		}

		throw new Exception("Expected failure, but the resolver completed successfully when running scenario with String Value: "+stringValue+" Object: "+expectedResult);
	}
}
