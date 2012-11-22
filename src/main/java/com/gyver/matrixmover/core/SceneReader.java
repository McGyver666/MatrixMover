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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gyver
 */
public abstract class SceneReader {

    /**
     * Reads two arrays of VisualSetups with all the scene informations
     * @param file
     * @return 
     */
    public static VisualSetup loadVisualSetup(File file, MatrixData md) {
        
        VisualSetup vs = null;
        
        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));

            Object obj = null;

            obj = inputStream.readObject();
            if (obj == null) {
                throw new NullPointerException();
            }

            if (obj instanceof VisualSetup) {
                vs = (VisualSetup) obj;
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 

        return vs;
    }

    /**
     * Saves a visual setup
     */
    public static void saveVisualSetup(VisualSetup setup, File file) {

        ObjectOutputStream outputStream = null;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));
            outputStream.writeObject(setup);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } finally {
            //Close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
                //closed anyway
            }
        }
    }
}
