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
    public static List<VisualSetup[]> loadScenes(String file, MatrixData md) {
        VisualSetup[] leftScenes = new VisualSetup[GeneratorVisual.NUMBER_OF_SCENES];
        VisualSetup[] rightScenes = new VisualSetup[GeneratorVisual.NUMBER_OF_SCENES];

        ObjectInputStream inputStream = null;

        try {
            inputStream = new ObjectInputStream(new FileInputStream(file));

            Object obj = null;

            //read left first
            obj = inputStream.readObject();
            if (obj == null) {
                throw new NullPointerException();
            }

            if (obj instanceof VisualSetup[]) {
                leftScenes = (VisualSetup[]) obj;
            }

            //than right
            obj = inputStream.readObject();
            if (obj == null) {
                throw new NullPointerException();
            }

            if (obj instanceof VisualSetup[]) {
                rightScenes = (VisualSetup[]) obj;
            }

        } catch (Exception e) {
            e.printStackTrace();
            //fill all visualSetups with initial empty scenes
            //if there was an error!
            for (int i = 0; i < leftScenes.length; i++) {
                leftScenes[i] = new VisualSetup(md);
                rightScenes[i] = new VisualSetup(md);
            }
        }

        ArrayList<VisualSetup[]> arrays = new ArrayList<VisualSetup[]>();
        arrays.add(leftScenes);
        arrays.add(rightScenes);
        return arrays;
    }

    /**
     * Saves to arrays of VisualSetups to a file
     */
    public static void writeScenes(List<VisualSetup[]> setups, String file) {

        ObjectOutputStream outputStream = null;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(file));

            outputStream.writeObject(setups.get(0));
            outputStream.writeObject(setups.get(1));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the ObjectOutputStream
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException ex) {
            }
        }
    }
}
