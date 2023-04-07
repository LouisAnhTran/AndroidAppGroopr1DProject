package Groopr.Model.FactoryDesignForPillar;

import java.util.ArrayList;
import java.util.Arrays;

public class DAI extends Pillar{
    public DAI(String term){
        if(term.equalsIgnoreCase("4")){
            this.setModuleList(new ArrayList<>(Arrays.asList("60.002 AI applications in Design", "60.003 Product Design Studio","60.004 Service Design Studio")));
        } else if (term.equalsIgnoreCase("5")) {
            this.setModuleList(new ArrayList<>(Arrays.asList("60.008 Systems Design Studio", "60.006 Spatial Design Studio","50.017 Graphics and Visualization")));
        }
    }
}
