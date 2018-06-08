/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.gui;

import com.gyver.matrixmover.core.ChaseController;
import com.gyver.matrixmover.core.ChaseItem;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Jonas
 */
public class ChaseConfigurationListModel extends AbstractListModel {
    
    private ChaseController controller;

    public ChaseConfigurationListModel(ChaseController controller) {
        this.controller = controller;
    }

    @Override
    public int getSize() {
        return this.controller.getChaseItemList().size();
    }

    @Override
    public Object getElementAt(int i) {
        return this.controller.getChaseItemList().get(i);
    }

    public void addRow(int selectedRow) {
        if (selectedRow < 0) {
            this.controller.getChaseItemList().add(new ChaseItem()); 
        }
        else {
            this.controller.getChaseItemList().add(selectedRow+1, new ChaseItem());
        }
        fireContentsChanged(this, selectedRow, selectedRow+1);
    }
    
    public void dataChanged(){
        fireContentsChanged(this, 0, this.controller.getChaseItemList().size());
    }
    
    public void removeRow(int selectedRow) {
        if (this.controller.getChaseItemList().size() > 0) {
            if(selectedRow < 0){
                this.controller.getChaseItemList().remove(this.controller.getChaseItemList().size() - 1);
            }
            else {
                this.controller.getChaseItemList().remove(selectedRow);   
            }
            
            fireIntervalRemoved(this, 0, this.controller.getChaseItemList().size());  
        }
    }
    
    public List<ChaseItem> getChaseList() {
        if(this.controller.getChaseItemList().size() <= 0){
            addRow(-1);
        }
        return this.controller.getChaseItemList();
    }
    
}
