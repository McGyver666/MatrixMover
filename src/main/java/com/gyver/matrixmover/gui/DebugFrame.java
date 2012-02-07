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

/*
 * DebugFrame.java
 *
 * Created on 07.02.2012, 13:32:16
 */
package com.gyver.matrixmover.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author Gyver
 */
public class DebugFrame extends javax.swing.JFrame {

    /** Creates new form DebugFrame */
    public DebugFrame() {
        initComponents();
    }

    /** Creates new form DebugFrame
     * @param e The error to get the stack trace from
     */
    public DebugFrame(Throwable e) {
        initComponents();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        e.printStackTrace(pw);

        pw.flush();
        pw.close();

        StringBuffer sb = sw.getBuffer();

        sb.append("\n\nSystem Informations:\n");

        sb.append("java.vendor = ").append(System.getProperty("java.vendor")).append("\n");
        sb.append("java.vm.vendor = ").append(System.getProperty("java.vm.vendor")).append("\n");
        sb.append("java.vm.name = ").append(System.getProperty("java.vm.name")).append("\n");
        sb.append("java.vm.version = ").append(System.getProperty("java.vm.version")).append("\n");
        sb.append("java.runtime.name = ").append(System.getProperty("java.runtime.name")).append("\n");
        sb.append("java.runtime.version = ").append(System.getProperty("java.runtime.version")).append("\n");
        sb.append("os.name = ").append(System.getProperty("os.name")).append("\n");
        sb.append("os.arch = ").append(System.getProperty("os.arch")).append("\n");
        sb.append("os.version = ").append(System.getProperty("os.version")).append("\n");
        sb.append("sun.java.command = ").append(System.getProperty("sun.java.command")).append("\n");
        sb.append("java.home = ").append(System.getProperty("java.home")).append("\n");

        jTextArea1.setText(sb.toString());
    }

    public void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = this.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        this.setLocation((screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(705, 405));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("<html>There was an error and the application has crashed.<br>Send this report to the developer to help finding the error.</html>");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 410, 36);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 670, 260);

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(600, 330, 80, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(EXIT_ON_CLOSE);
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
