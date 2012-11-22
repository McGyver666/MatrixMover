/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gyver.matrixmover.gui.listener;

import javax.swing.event.TreeExpansionListener;
import com.gyver.matrixmover.gui.component.FileTreeNode;
import com.gyver.matrixmover.gui.Frame;
import java.awt.Cursor;
import java.util.Enumeration;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class HierarchyBrowserUpdater implements TreeExpansionListener {

    FileTreeNode root;
    JTree jtree;
    DefaultTreeModel treemodel;
    Frame frame;

    public HierarchyBrowserUpdater(JTree tree, FileTreeNode node, DefaultTreeModel tmodel, Frame theFrame) {
        root = node;
        jtree = tree;
        treemodel = tmodel;
        frame = theFrame;
    }

    @Override
    public void treeExpanded(TreeExpansionEvent event) {

        TreePath path = event.getPath();

        FileTreeNode node = (FileTreeNode) path.getLastPathComponent();
        
        frame.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        frame.setEnabled(false);

        if (node.readTree()) {
            int childrenIdx[] = new int[node.getChildCount()];
            int i = 0;
            for (Enumeration e = node.children(); e.hasMoreElements();) {
                Object obj = e.nextElement();
                childrenIdx[i] = node.getIndex((TreeNode) obj);
                i++;
            }
            treemodel.nodesWereInserted(node, childrenIdx);
        }
        frame.setEnabled(true);
        frame.setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
    }
}