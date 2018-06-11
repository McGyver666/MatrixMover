/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.core;


/**
 *
 * @author Jonas
 */
public class ChaseItem {
    public String itemName = "<empty>";
    public double sceneTime = 300;
    public double fadeInTime = 1;
    public String sceneFileName = "";
    
    public ChaseItem() { }
    
    @Override
    public String toString() {
        return itemName;
    }
    
    public String parameterToString(){
        String ret = "itemName="+this.itemName+"\n";
        ret += "sceneTime="+sceneTime+"\n";
        ret += "fadeInTime="+fadeInTime+"\n";
        ret += "sceneFileName="+sceneFileName;
        return ret;
    }
    
    public void configureFromString(String configuration) {
        String[] config = configuration.split(";");
        for(String conf : config) {
            System.out.println(conf);
            String[] split = conf.split("=");
            String par = split[0];
            String var = "";
            if(split.length > 1){
                var = conf.split("=")[1];
            }

            switch (par) {
                case "itemName":
                    itemName = var;
                    break;
                case "sceneTime":
                    sceneTime = Double.valueOf(var);
                    break;
                case "fadeInTime":
                    fadeInTime = Double.valueOf(var);
                    break;
                case "sceneFileName":
                    sceneFileName = var;
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for ChaseItem: '"+conf+"'.");
            }
        }
    }
}
