/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.gui.component;

import java.io.File;
import javax.swing.tree.DefaultMutableTreeNode;

public class FileTreeNode extends DefaultMutableTreeNode {

    boolean hasbeenread;

    public FileTreeNode(File file) {
        super(file);
        hasbeenread = false;
    }

    public boolean readTree() {
        return readTree(false);
    }

    public boolean readTree(boolean b) {
        if (hasbeenread) {
            return false;
        }

        String list[] = getFileObject().list();
        System.out.println(list);
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                FileTreeNode subnode = new FileTreeNode(new File(getFileObject(), list[i]));
                add(subnode);
                if (b) {
                    subnode.readTree(b);
                }
            }
        }
        hasbeenread = true;
        return true;
    }

    public File getFileObject() {
        return (File) getUserObject();
    }

    @Override
    public String toString() {
        return getFileObject().getName();
    }

    @Override
    public boolean isLeaf() {
        return ((File) userObject).isFile();
    }
}