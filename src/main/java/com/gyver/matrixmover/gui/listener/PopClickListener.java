/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.gui.listener;

import com.gyver.matrixmover.core.Controller;
import com.gyver.matrixmover.core.SceneReader;
import com.gyver.matrixmover.core.VisualSetup;
import com.gyver.matrixmover.gui.component.FileTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 *
 * @author Jonas
 */
public class PopClickListener extends MouseAdapter {

    private JTree jTree = null;

    public PopClickListener(JTree tree) {
        this.jTree = tree;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        doPop(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        doPop(e);
    }

    private void doPop(MouseEvent e) {
        TreePath selPath = jTree.getPathForLocation(e.getX(), e.getY());
        if (selPath == null) {
            return;
        } else if (e.isPopupTrigger()) {
            if (selPath.getLastPathComponent() instanceof FileTreeNode) {
                final FileTreeNode node = (FileTreeNode) selPath.getLastPathComponent();
                if (node.isLeaf()) {
                    jTree.setSelectionPath(selPath);
                    JPopupMenu menu = new JPopupMenu();
                    JMenuItem anItem;
                    anItem = new JMenuItem("to left Player <==");
                    anItem.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            toLeftPlayer(evt, node.getFileObject());
                        }
                    });
                    menu.add(anItem);
                    JMenuItem anotherItem = new JMenuItem("to right Player ==>");
                    anotherItem.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            toRightPlayer(evt, node.getFileObject());
                        }
                    });
                    menu.add(anotherItem);
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }

        }
    }

    private void toLeftPlayer(ActionEvent evt, File file) {
        VisualSetup vs = SceneReader.loadVisualSetup(file, Controller.getControllerInstance().getMatrixData());
        Controller.getControllerInstance().setVisualSetup(vs, Controller.LEFT_SIDE);
        //System.out.println(file.getAbsoluteFile());
    }

    private void toRightPlayer(ActionEvent evt, File file) {
        VisualSetup vs = SceneReader.loadVisualSetup(file, Controller.getControllerInstance().getMatrixData());
        Controller.getControllerInstance().setVisualSetup(vs, Controller.RIGHT_SIDE);
        //System.out.println(file.getAbsoluteFile());
    }
}