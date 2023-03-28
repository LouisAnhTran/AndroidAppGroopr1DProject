package Groopr.Model.FactoryDesignForPillar;

import java.util.ArrayList;

abstract public class Pillar {
    private ArrayList<String> term4ModuleList;
    private ArrayList<String> term5ModuleList;

    public Pillar(){
        term4ModuleList=new ArrayList<String>();
        term5ModuleList=new ArrayList<String>();
    }

    public ArrayList<String> getTerm4ModuleList() {
        return term4ModuleList;
    }

    public ArrayList<String> getTerm5ModuleList() {
        return term5ModuleList;
    }

    protected void setTerm4ModuleList(ArrayList<String> term4ModuleList) {
        this.term4ModuleList = term4ModuleList;
    }

    protected void setTerm5ModuleList(ArrayList<String> term5ModuleList) {
        this.term5ModuleList = term5ModuleList;
    }
}
