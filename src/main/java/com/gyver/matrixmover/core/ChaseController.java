/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.core;

import java.util.ArrayList;

/**
 *
 * @author Jonas
 */
public class ChaseController {
    
    private ArrayList<ChaseItem> chaseList;

    public ChaseController() {
        this.chaseList = new ArrayList<>();
    }
    
    public void addChaseItem(){
        chaseList.add(new ChaseItem());
    }
    
    public void removeChaseItem(int i){
        if (i >= 0 && i < chaseList.size()) {
            chaseList.remove(i);
        }
    }
    
    public void startChase(){}
    
    public void stopChase(){}
    
    public void loadChase(String filename){}
    
    public void saveChase(String filename){}
    
    @Override
    public String toString(){
        return null;
    }
}
