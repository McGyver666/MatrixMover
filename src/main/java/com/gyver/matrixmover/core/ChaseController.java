/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.core;

import com.gyver.matrixmover.core.timer.ChaserTimerTask;
import com.gyver.matrixmover.gui.ChaseConfiguration;
import com.gyver.matrixmover.gui.Frame;
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
import java.util.Timer;
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
    private Timer oldTimer, newTimer;
    private boolean isRunning = false;

    public ChaseController() {
        
        this.chaseList = new ArrayList<ChaseItem>();
        this.chaseName = "<empty>";
        this.chaseFilePath = "";
    }
        
    public void startChase(){
        isRunning = true;
        Timer t = new Timer();
        oldTimer = t;
        newTimer = t;
        
        boolean leftIsActiveVisual = true;
        if (!Controller.getControllerInstance().isPlayer()) {
            int currentPosition = Frame.getFrameInstance().getMasterPanel().getSFadePosition().getValue();
            if (currentPosition < 500) {
                leftIsActiveVisual = true;
            } else {
                leftIsActiveVisual = false;
            }
        }
        
        ChaserTimerTask ctt = new ChaserTimerTask(Controller.getControllerInstance(), chaseList, true, t, 0, leftIsActiveVisual);
        try {
            //start first timer directly and then let it manage itself
            t.schedule(ctt, 50);
            //ctt.run();
            ChaseConfiguration.getInstance().setTextRunnig();
        } catch (NumberFormatException nfe) {
            Frame.getFrameInstance().showWarning("Input is not valid. Check number format.");
        }
        
    }
    
    public void setTimerForNextChaseStep(int newTimerIntervall, int nextChaseIndex, boolean leftIsActiveVisual) {
        if (isRunning){
            Timer t = new Timer();
            oldTimer = newTimer;
            newTimer = t;
            System.out.println("Creating new Timer with time "+newTimerIntervall);
            ChaserTimerTask ctt = new ChaserTimerTask(Controller.getControllerInstance(), chaseList, true, t, nextChaseIndex, leftIsActiveVisual);
            t.schedule(ctt, newTimerIntervall);
        }
    }
    
    public void stopChase(){
        oldTimer.cancel();
        oldTimer.purge();
        newTimer.cancel();
        newTimer.purge();
        ChaseConfiguration.getInstance().setTextStopped();
        isRunning = false;
    }
    
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
