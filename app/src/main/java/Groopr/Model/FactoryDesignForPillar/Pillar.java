package Groopr.Model.FactoryDesignForPillar;

import java.util.ArrayList;

abstract public class Pillar {
    private ArrayList<String> moduleList;

    public Pillar(){
        moduleList=new ArrayList<String>();
    }

    public ArrayList<String> getModuleList() {
        return moduleList;
    }

    protected void setModuleList(ArrayList<String> moduleList) {
        this.moduleList = moduleList;
    }
}
