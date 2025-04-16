package ru.itmo.Presenatation.Scenarios;

/**
 * describes a variant of choice for a customer
 */
public interface IScenario {
    /**
     * show the content of the app state
     */
    void run();

    /**
     * gives name of the scenario
     * @return name
     */
    String getName();
}
