package ru.itmo.Presenatation.ScenarioProvaiders;

import ru.itmo.Presenatation.Scenarios.IScenario;

/**
 * gives corresponding scenario or not according to current app state
 */
public interface IScenarioProvider {
    /**
     * gives the scenario or not
     * @return scenario
     */
    IScenario tryGetScenario();
}
