/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ModificarFrames;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Ficha;
import process.CRUDClientes.ModificarCliente;
import process.CRUDProveedores.ModificarProveedor;
import process.limpiador.LimpiarTexto;
import view.clientes.FichaClienteFrame;

/**
 *
 * @author Aaron
 */
public class ModificarProvinciaFrame extends javax.swing.JFrame {

    Integer idFila;
    String quienMeLlama;
    Ficha ficha;
  
     public ModificarProvinciaFrame(Integer idFila, String quienMeLlama, Ficha ficha) {
        initComponents();
        this.idFila = idFila;
        this.ficha = ficha;
        this.quienMeLlama = quienMeLlama;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextFieldProvincia = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Nueva provincia:");

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jButton1)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       switch (quienMeLlama) { 
              
                case "proveedor":
                            {
                                try {
                                    new ModificarProveedor(idFila,"provincia",new LimpiarTexto().limpiar(this.jTextFieldProvincia.getText())).modificarProveedor();
                                    ficha.consultar();
                                    ficha.actualizar();
                                } catch (IOException ex) {
                                    Logger.getLogger(ModificarNombreFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                             break;
                case "cliente":
                            {
                                try {
                                    new ModificarCliente(idFila,"provincia",new LimpiarTexto().limpiar(this.jTextFieldProvincia.getText())).modificarCliente();
                                    ficha.consultar();
                                    ficha.actualizar();
                                } catch (IOException ex) {
                                    Logger.getLogger(ModificarNombreFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                              break;
  
                }
         this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextFieldProvincia;
    // End of variables declaration//GEN-END:variables
}
