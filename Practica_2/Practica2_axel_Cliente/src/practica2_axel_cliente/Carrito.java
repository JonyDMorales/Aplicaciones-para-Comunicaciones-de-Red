package practica2_axel_cliente;

import javax.swing.table.DefaultTableModel;

public class Carrito extends javax.swing.JFrame {

    DefaultTableModel model;
    int Total;
    String pedido;
    
    public Carrito() {
        initComponents();
        model = (DefaultTableModel) tblCarrito.getModel();        
    }
    
    public void Total(){
        Total = 0;
        for(int i = 0; i < model.getRowCount(); i++)
            Total += Integer.valueOf((String)model.getValueAt(i,2));
        txtTotal.setText("$"+Total);
    }

    public String Pedio(){
        pedido = "";
        for(int i = 0; i < model.getRowCount(); i++){
            String id = (String)model.getValueAt(i,0);
            String canti = (String)model.getValueAt(i,1);
            if("Camisa".equals(id))
                pedido += "1" + " "+ canti + " ";
            if("Pantalon".equals(id))
                pedido += "2" + " "+ canti + " ";
            if("Bolsa".equals(id))
                pedido += "3" + " "+ canti + " ";
            if("Computadora".equals(id))
                pedido += "4" + " "+ canti + " ";
            if("Celular".equals(id))
                pedido += "5" + " "+ canti + " ";
            if("Tablet".equals(id))
                pedido += "6" + " "+ canti + " ";
            if("Mochila".equals(id))
                pedido += "7" + " "+ canti + " ";
            if("Television".equals(id))
                pedido += "8" + " "+ canti + " ";
            if("Zapato".equals(id))
                pedido += "9" + " "+ canti + " ";
            if("Chamarra".equals(id))
                pedido += "10" + " "+ canti + " ";
            if("Mouse".equals(id))
                pedido += "11" + " "+ canti + " ";
            if("Teclado".equals(id))
                pedido += "12" + " "+ canti + " ";
            if("Monitor".equals(id))
                pedido += "13" + " "+ canti + " ";
            if("Impresora".equals(id))
                pedido += "14" + " "+ canti + " ";
            if("Playera".equals(id))
                pedido += "15" + " "+ canti + " ";
            if("Chocolate".equals(id))
                pedido += "16" + " "+ canti + " ";
            if("Sandwich".equals(id))
                pedido += "17" + " "+ canti + " ";
            if("Audifonos".equals(id))
                pedido += "18" + " "+ canti + " ";
            if("Modem".equals(id))
                pedido += "19" + " "+ canti + " ";
            if("Lentes".equals(id))
                pedido += "20" + " "+ canti + " ";
        }    
        //System.out.println(pedido);
    return pedido;
    }
    
    public void Agregar(int id, int cantidad, int total){
        if(id == 1){
            model.addRow(new Object[]{"Camisa",""+cantidad,""+total});
        }
        if(id == 2){
            model.addRow(new Object[]{"Pantalon",""+cantidad,""+total});
        }
        if(id == 3){
            model.addRow(new Object[]{"Bolsa",""+cantidad,""+total});
        }
        if(id == 4){
            model.addRow(new Object[]{"Computadora",""+cantidad,""+total});
        }
        if(id == 5){
            model.addRow(new Object[]{"Celular",""+cantidad,""+total});
        }
        if(id == 6){
            model.addRow(new Object[]{"Tablet",""+cantidad,""+total});
        }
        if(id == 7){
            model.addRow(new Object[]{"Mochila",""+cantidad,""+total});
        }
        if(id == 8){
            model.addRow(new Object[]{"Television",""+cantidad,""+total});
        }
        if(id == 9){
            model.addRow(new Object[]{"Zapato",""+cantidad,""+total});
        }
        if(id == 10){
            model.addRow(new Object[]{"Chamarra",""+cantidad,""+total});
        }
        if(id == 11){
            model.addRow(new Object[]{"Mouse",""+cantidad,""+total});
        }
        if(id == 12){
            model.addRow(new Object[]{"Teclado",""+cantidad,""+total});
        }
        if(id == 13){
            model.addRow(new Object[]{"Monitor",""+cantidad,""+total});
        }
        if(id == 14){
            model.addRow(new Object[]{"Impresora",""+cantidad,""+total});
        }
        if(id == 15){
            model.addRow(new Object[]{"Playera",""+cantidad,""+total});
        }
        if(id == 16){
            model.addRow(new Object[]{"Chocolate",""+cantidad,""+total});
        }
        if(id == 17){
            model.addRow(new Object[]{"Sandwich",""+cantidad,""+total});
        }
        if(id == 18){
            model.addRow(new Object[]{"Audifonos",""+cantidad,""+total});
        }
        if(id == 19){
            model.addRow(new Object[]{"Modem",""+cantidad,""+total});
        }
        if(id == 20){
            model.addRow(new Object[]{"Lentes",""+cantidad,""+total});
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCarrito = new javax.swing.JTable();
        txtTotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tu Carrito");

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblCarrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad", "Costo"
            }
        ));
        jScrollPane1.setViewportView(tblCarrito);

        jLabel2.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 16, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        model.removeRow(model.getRowCount()-1);
        Total();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Carrito.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Carrito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCarrito;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
