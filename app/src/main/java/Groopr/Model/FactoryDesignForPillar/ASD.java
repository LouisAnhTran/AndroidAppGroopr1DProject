package Groopr.Model.FactoryDesignForPillar;

import java.util.ArrayList;
import java.util.Arrays;

public class ASD extends Pillar{
    public ASD(String term){
        if(term.equalsIgnoreCase("5")){
            this.setModuleList(new ArrayList<>(Arrays.asList("20.102 Architecture Core Studio 2","20.202 Architectural Structure & Enclosure Design","20.212 Digital Design and Fabrication")));
        } else if (term.equalsIgnoreCase("4")) {
            this.setModuleList(new ArrayList<>(Arrays.asList("20.101 Architecture Core Studio 1","20.201 Architecture Science & Technology","20.211 Introduction to Design Computation")));
        }
    }

}
