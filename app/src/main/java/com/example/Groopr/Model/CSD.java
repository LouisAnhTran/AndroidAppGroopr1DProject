package com.example.Groopr.Model;

import java.util.HashMap;

public class CSD extends Pillar {
    private static CSD instance=null;
    static HashMap<String,String> term4=new HashMap<String,String>();
    static HashMap<String,String> term5=new HashMap<String,String>();

    private CSD(){
        term4.put("50.001","Information Systems & Programming");
        term4.put("50.002","Computation Structures");
        term4.put("50.004","Algorithms");
        term5.put("50.003","Elements of Software Construction");
        term5.put("50.005","Computer System Engineering");
    }

    public static CSD getInstance(){
        if(instance==null){
            return new CSD();
        }
        return instance;
    }




}
