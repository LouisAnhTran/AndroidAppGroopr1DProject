package Groopr.Model.FactoryDesignForPillar;

import java.util.ArrayList;
import java.util.Arrays;

public class ESD extends Pillar {
    public ESD(String term){
        if(term.equalsIgnoreCase("4")){
            this.setModuleList(new ArrayList<>(Arrays.asList("40.002 Optimisation","40.011 Data and Business Analytics","40.015 Simulation Modelling and Analysis")));
        } else if (term.equalsIgnoreCase("5")) {
            this.setModuleList(new ArrayList<>(Arrays.asList("40.012 Manufacturing and Service Operations","40.014 Engineering Systems Architecture","40.016 The Analytics Edge")));
        }
    }
}
