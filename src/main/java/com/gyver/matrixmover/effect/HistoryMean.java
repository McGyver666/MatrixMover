/*
 * Copyright (C) 2011 Michael Vogt <michu@neophob.com>
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

package com.gyver.matrixmover.effect;

import com.gyver.matrixmover.core.MatrixData;
import java.util.LinkedList;

public class HistoryMean extends Effect {

    private LinkedList<int[]> historyList;
    
    private int historyLength;
    /**
     * Instantiates a new historymean.
     *
     * @param controller the controller
     */
    public HistoryMean(MatrixData md) {
        super(EffectName.HISTORYMEAN, md);
        
        historyLength = 5;
        historyList = new LinkedList<>();

    }

    @Override
    public int[] getBuffer(int[] buffer) {
        int ret[] = new int[buffer.length];
        int retr[] = new int[buffer.length];
        int retg[] = new int[buffer.length];
        int retb[] = new int[buffer.length];
        
        historyList.add(buffer.clone());
        while (historyList.size() > getHistoryLength()) {
            historyList.removeFirst();
        }

        for (int[] curr: historyList) {
            for (int i = 0; i < curr.length; i++) {
                retr[i] += (int) ((curr[i] >> 16) & 255);
                retg[i] += (int) ((curr[i] >> 8) & 255);
                retb[i] += (int) ((curr[i]) & 255);
            }
        }
        
        for (int i = 0; i < ret.length; i++) {
                    ret[i] = (int) ((retr[i] / historyList.size()) << 16) | ((retg[i] / historyList.size()) << 8) | (retb[i] / historyList.size());
        }
        
        return ret;
    }

    /**
     * @return the historyLength
     */
    public int getHistoryLength() {
        return historyLength;
    }

    /**
     * @param historyLength the historyLength to set
     */
    public void setHistoryLength(int historyLength) {
        this.historyLength = historyLength;
    }
    
    /**
     * Gets the parameter of the generator as String
     * 
     * @return the parameter as String
     */
    @Override
    public String parameterToString(){
        String ret = "historyLength="+historyLength+"\n";
        return ret;
    }
    
    
    @Override
    public void configureFromString(String configuration) {
        String[] config = configuration.split(";");
        for(String conf : config) {
            String par = conf.split("=")[0];
            String var = conf.split("=")[1];
            
            switch (par) {
                case "historyLength":
                    setHistoryLength(Integer.valueOf(var));
                    break;
                default: 
                    throw new UnsupportedOperationException("Unknown Parameter for Generator "+this.getName()+": '"+conf+"'.");
            }
        }
    }
}