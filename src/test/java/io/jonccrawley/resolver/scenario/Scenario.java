package io.jonccrawley.resolver.scenario;

import java.util.List;

public interface Scenario {

	static Object[][] scenariosObjectArray(List<Scenario> testScenarios){
		Object[][] scenarios = new Object[testScenarios.size()][1];

		for (int i = 0; i < testScenarios.size(); i ++){
			scenarios[i][0] =testScenarios.get(i);
		}

		return scenarios;
	}

	void run() throws Exception;

}
