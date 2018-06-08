/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class ChaseController {
    
    private ArrayList<ChaseItem> chaseList;
    private String chaseName;
    private String chaseFilePath;
    private static final Logger LOG = Logger.getLogger(ChaseController.class.getName());

    public ChaseController() {
        
        this.chaseList = new ArrayList<>();
        this.chaseName = "<empty>";
        this.chaseFilePath = "";
    }
    
    /*public void addChaseItem(){
        chaseList.add(new ChaseItem());
    }
    
    public void removeChaseItem(int i){
        if (i >= 0 && i < chaseList.size()) {
            chaseList.remove(i);
        }
    }*/
    
    public void startChase(){}
    
    public void stopChase(){}
    
    public void loadChase(String filename){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line;
            
            this.setChaseFilePath(new File(filename).getParent());
            
            this.chaseList = new ArrayList<>();
            
            line = reader.readLine();
            
            while(!line.equals("EOF")) {
                if(line.startsWith("chaseName")) {
                    String[] split = line.split("=");
                    this.setChaseName(split[1]);
                    line = reader.readLine();
                    continue;
                }
                
                if(line.startsWith("newChaseItem")) {
                    line = reader.readLine();
                    String chaseItemConfigString = "";
                    while(line.startsWith("itemName") || line.startsWith("sceneTime") || line.startsWith("fadeInTime") || line.startsWith("sceneFileName") ) {
                        chaseItemConfigString += line + ";";
                        line = reader.readLine();
                    }
                    ChaseItem ci = new ChaseItem();
                    ci.configureFromString(chaseItemConfigString);
                    chaseList.add(ci);
                }
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
    public boolean saveChase() {
        
        boolean ret = false;
        BufferedWriter bw = null;
        
        Path pathToFile = Paths.get(this.getFullChaseFilePath());
        try {
            Files.createDirectories(pathToFile.getParent());
            if(!Files.exists(pathToFile)){
                Files.createFile(pathToFile);
            }
        } catch (IOException ex) {
            Logger.getLogger(ChaseController.class.getName()).log(Level.WARNING, null, ex);
        }
        
        try {
            bw = new BufferedWriter(new FileWriter(this.getFullChaseFilePath()));
            bw.write(parameterToString());
            ret = true;
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            //Close the ObjectOutputStream
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                //closed anyway
            }
            return ret;
        }
        
        
    }
    
    public String parameterToString(){
        String ret = "chaseName="+this.chaseName+"\n";
        for (int i = 0; i < chaseList.size(); i++) {
            ret += "newChaseItem\n";
            ret += chaseList.get(i).parameterToString()+"\n";
        }
        ret += "EOF";
        return ret;
    }

    public List<ChaseItem> getChaseItemList() {
        return chaseList;
    }
    
    public String getChaseName() {
        return this.chaseName;
    }
    
    public void setChaseName(String chaseName) {
        this.chaseName = chaseName;
    }
    
    public String getChaseFilePath() {
        return this.chaseFilePath;
    }
    
    public void setChaseFilePath(String path) {
        this.chaseFilePath = path;
    }
    
    public String getFullChaseFilePath() {
        return this.chaseFilePath+"\\"+this.chaseName+".mmc";
    }
}
