package Groopr.Model.FactoryDesignForPillar;

import java.util.ArrayList;
import java.util.Arrays;

public class EPD extends Pillar {
    public EPD(String term){
        if(term.equalsIgnoreCase("5")){
            this.setModuleList(new ArrayList<>(Arrays.asList("30.002 Circuits & Electronics","30.100 Computational and Data-Driven Engineering","30.102 Electromagnetics & Applications")));
        } else if (term.equalsIgnoreCase("4")) {
            this.setModuleList(new ArrayList<>(Arrays.asList("30.101 Systems & Control","30.007 Engineering Design Innovation","30.001 Structures & Materials")));
        }
    }
}
