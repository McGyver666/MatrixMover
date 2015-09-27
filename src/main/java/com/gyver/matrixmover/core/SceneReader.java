/*
 * Copyright (C) 2012 Gyver
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gyver.matrixmover.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Gyver
 */
public abstract class SceneReader {

    /**
     * Reads two arrays of VisualSetups with all the scene informations
     * @param file
     * @param md
     * @return 
     */
    public static VisualSetup loadVisualSetup(File file, MatrixData md) {
        
        VisualSetup vs = null;
        
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));

            String line;
            
            // this creates a setup containing a basic generator
            vs = new VisualSetup(md);
            // we remove this initial generator after loading to get the exact 
            // number of generators
            
            
            int generators = 0;
            int generatornumber = -1;
            
            while(!(line = reader.readLine()).equals("EOF")) {
                System.out.println(line);
                if(line.startsWith("nrOfGens")) {
                    String[] split = line.split("=");
                    generators = Integer.valueOf(split[1]);
                    for (int i = 0; i < generators; i++) {
                        vs.addGeneratorSetup(md);
                    }
                    continue;
                } 
                
                if (line.startsWith("generator")) {
                    generatornumber++;
                    String gen = line.split("=")[1];
                    
                    String generatorConfigurationString = "";
                    while(!(line = reader.readLine()).startsWith("effect")) {
                        generatorConfigurationString += line + ";";
                    }
                    
                    vs.setGeneratorFromString(generatornumber, gen);
                    vs.getGenerator(generatornumber).configureFromString(generatorConfigurationString);
                    
                    
                    // we do not continue! we fount "effect"
                }
                
                if (line.startsWith("effect")) {
                    String eff = line.split("=")[1];
                    
                    String effectConfigurationString = "";
                    while(!(line = reader.readLine()).startsWith("mixer")) {
                        effectConfigurationString += line + ";";
                    }
                    
                    vs.setEffectFromString(generatornumber, eff);
                    vs.getEffect(generatornumber).configureFromString(effectConfigurationString);
                    
                    // we do not continue! we fount "mixer"
                }
                
                
                if (line.startsWith("mixer")) {
                    String mix = line.split("=")[1];
                    vs.setMixerFromString(generatornumber, mix);
                    continue;
                }
                
                if (line.startsWith("intensity")) {
                    String intens = line.split("=")[1];
                    vs.setGeneratorIntensity(Integer.valueOf(intens), generatornumber);
                    continue;
                }
                
            }
            
            vs.removeGeneratorSetup(generators);
                        
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 

        return vs;
    }

    /**
     * Saves a visual setup
     */
    public static void saveVisualSetup(VisualSetup setup, File file) {

        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(setup.toString());
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
            } catch (IOException ex) {
                //closed anyway
            }
        }
    }
}
