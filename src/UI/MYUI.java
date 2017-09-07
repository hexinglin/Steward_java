/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import NetCommunication.NetTCP;
import java.io.IOException;

/**
 *
 * @author TOKGO
 */
public class MYUI extends javax.swing.JFrame {

    private NetTCP serversCom =null;
    private boolean  isopenservers = false;
    /**
     * Creates new form JFrame
     */
    public MYUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtshow = new javax.swing.JLabel();
        btnopen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtshow.setText("服务器未运行");

        btnopen.setText("开启服务器");
        btnopen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnopenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(txtshow, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(btnopen, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(129, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(btnopen, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(txtshow)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnopenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnopenActionPerformed
        // TODO add your handling code here:
        if (!isopenservers) {
            try {
                serversCom = new NetTCP();
                isopenservers = true;
                btnopen.setText("关闭服务器");
                new ShowPrompt().start();
            } catch (IOException ex) {
            }
        }
        else{
            serversCom.CloseServers();
            serversCom=null;
            isopenservers = false;
            btnopen.setText("开启服务器");
        }
    }//GEN-LAST:event_btnopenActionPerformed

    
    private class ShowPrompt extends Thread{

        @Override
        public void run() {
            int i=1;
           while(isopenservers){
                try {
                      Thread.sleep(500);
                 } catch (InterruptedException ex) {}
                if (i==1) 
                   txtshow.setText("服务器运行中 . ");
                if (i==2) 
                   txtshow.setText("服务器运行中 . .");
                if (i==3) 
                   txtshow.setText("服务器运行中 . . .");
                if (i==4){ 
                    i=0;
                   txtshow.setText("服务器运行中 . . . .");
                }
                i++; 
           }
           txtshow.setText("服务器未运行");
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnopen;
    private javax.swing.JLabel txtshow;
    // End of variables declaration//GEN-END:variables
}