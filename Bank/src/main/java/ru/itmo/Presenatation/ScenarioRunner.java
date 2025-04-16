package ru.itmo.Presenatation;
import ru.itmo.Presenatation.ScenarioProvaiders.IScenarioProvider;
import ru.itmo.Presenatation.Scenarios.IScenario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * shows scenarios
 */
public class ScenarioRunner {
    public List<IScenarioProvider> providers;
    public ScenarioRunner(List<IScenarioProvider> _providers){
        providers = _providers;
    }

    /**
     * try to get the scenario from its provider and show to the customer
     */
    public void run(){

        List<IScenario> scenarios = new ArrayList<>();
        for (IScenarioProvider provider : providers){
            IScenario scenario = provider.tryGetScenario();
            if (scenario != null) scenarios.add(scenario);
        }

        System.out.println("Type the variant which you chose:\n");
        for (IScenario scenario : scenarios){
            System.out.println(scenario.getName());
        }
        System.out.println("\n");


        Scanner in = new Scanner(System.in);
        String ClientChoice = in.nextLine();

        for (IScenario scenario : scenarios){
            if (ClientChoice.equals(scenario.getName())){
                scenario.run();
                break;
            }
        }

    }
}
