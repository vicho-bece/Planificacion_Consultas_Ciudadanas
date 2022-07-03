/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;
import Codigo.*;
import javax.swing.JFrame;
/**
 * Clase para mostrar las Consultas de la Coleccion
 * Interfaz Grafica
 * @author vicho
 */
public class Ventana_Mostrar_Consultas extends javax.swing.JFrame {
    /**
     * Coleccion de Consultas
     * Ventana del Menu Principal
     */
    private ConsultaPorID consultas;
    private JFrame menu;
    /**
     * Creates new form Ventana_Mostrar_Consultas
     */
    
    /**
     * Constructor con parametros
     * @param consultas Coleccion de Consultas 
     * @param menu Interfaz Grafica del Menu Principal
     * 
     * Muestra todas las Consulta de la Coleccion con su informacion
     */
    public Ventana_Mostrar_Consultas(ConsultaPorID consultas, JFrame menu) {
        initComponents();
        this.consultas = consultas;
        this.menu = menu;
        mostrar.setLineWrap(true);
        mostrar.setText(consultas.mostrarConsultas());
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Salir_Mostrar_Consulta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mostrar = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Salir_Mostrar_Consulta.setText("Cerrar");
        Salir_Mostrar_Consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Salir_Mostrar_ConsultaActionPerformed(evt);
            }
        });

        mostrar.setColumns(20);
        mostrar.setRows(5);
        jScrollPane1.setViewportView(mostrar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 416, Short.MAX_VALUE)
                        .addComponent(Salir_Mostrar_Consulta))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Salir_Mostrar_Consulta)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Salir_Mostrar_ConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Salir_Mostrar_ConsultaActionPerformed
        menu.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_Salir_Mostrar_ConsultaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Salir_Mostrar_Consulta;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea mostrar;
    // End of variables declaration//GEN-END:variables
}