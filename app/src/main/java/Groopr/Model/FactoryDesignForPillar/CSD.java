package Groopr.Model.FactoryDesignForPillar;

import java.util.ArrayList;
import java.util.Arrays;

public class CSD extends Pillar {
    public CSD(String term){
        if(term.equalsIgnoreCase("5")){
            this.setModuleList(new ArrayList<>(Arrays.asList("50.003 Elements of Software Construction","50.005 Computer System Engineering")));
        } else if (term.equalsIgnoreCase("4")) {
            this.setModuleList(new ArrayList<>(Arrays.asList("50.001 Information Systems & Programming","50.002 Computation Structures","50.004 Algorithms")));
        }
    }
}
