package practica2_axel_cliente;

import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

public class Cliente extends javax.swing.JFrame {
    
    int pto = 5000;
    Socket c;
    BufferedReader br;
    PrintWriter pw;
    Carrito ca = new Carrito();
    int item, consulta, cantidad, total;
    
    public Cliente() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        editPane = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cmboxNombre = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        cmboxCantidad = new javax.swing.JComboBox<>();
        txtPrecio = new javax.swing.JTextField();

        editPane.setToolTipText("Arrastrar archivo sobre este espacio");
        jScrollPane2.setViewportView(editPane);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cliente");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Descripcion: ");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmboxNombre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Camisa", "Pantalón", "Bolsa", "Computadora", "Celular", "Tablet", "Mochila", "Television", "Zapato", "Chamarra", "Mouse", "Teclado", "Monitor", "Pluma", "Playera", "Chocolate", "Sandwich", "Audífonos", "Modem", "Lentes" }));
        cmboxNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxNombreActionPerformed(evt);
            }
        });

        jButton2.setText("Ver carrito");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Generar Ticket");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        cmboxCantidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cantidad", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmboxCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxCantidadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(53, 53, 53)
                                .addComponent(jButton3)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmboxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmboxCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPrecio)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmboxNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmboxCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(45, 45, 45)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            c = new Socket("127.0.0.1", pto);
            item = cmboxNombre.getSelectedIndex();
            cantidad = cmboxCantidad.getSelectedIndex();
            br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            pw = new PrintWriter(c.getOutputStream());
            pw.println(item);
            pw.flush();
            pw.println(cantidad);
            pw.flush();
            String op = br.readLine();
            System.out.println("id: "+ item + "  Cantidad: " + cantidad + "  op:" + op);
            if("1".equals(op)){
                ca.Agregar(item, cantidad, total);
                JOptionPane.showMessageDialog(null, "Se ha agregado satisfactoriamente","Correcto",JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "No hay suficiente de ese producto","Error",JOptionPane.ERROR_MESSAGE);
            }
            br.close();
            pw.close();
            c.close();
        }catch(Exception e){
            e.printStackTrace();
	}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmboxNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxNombreActionPerformed
        item = cmboxNombre.getSelectedIndex();
        txtDescripcion.setAutoscrolls(true);
        if(item == 1){
            txtDescripcion.setText("Esta camisa es de seda \ncolor azul, slim fit \ntalla mediana.");
            txtPrecio.setText("$"+100);
        }
        if(item == 2){
            txtDescripcion.setText("Este pantalón es color negro \nentubado, talla 30.");
            txtPrecio.setText("$"+200);
        }
        if(item == 3){
            txtDescripcion.setText("Esta bolsa es de cuero \nnegra tamaño chico.");
            txtPrecio.setText("$"+300);
        }
        if(item == 4){
            txtDescripcion.setText("Esta computadora tiene \n un procesador intel core i5 de 6ta generación \nocho gb de ram, 1 tera de disco duro.");
            txtPrecio.setText("$"+160);
        }
        if(item == 5){
            txtDescripcion.setText("Este celular tiene dos núcleos \nmemoria ram de 1gb, memoria interna de 16gb.");
            txtPrecio.setText("$"+230);
        }
        if(item == 6){
            txtDescripcion.setText("Tablet marca samsung, con pantalla táctil \nbocinas y cámara integrada.");
            txtPrecio.setText("$"+500);
        }
        if(item == 7){
            txtDescripcion.setText("Mochila escolar marcha Ecko \nideal para cargar libros.");
            txtPrecio.setText("$"+700);
        }
        if(item == 8){
            txtDescripcion.setText("Televisión marca samsung \nconfigurable para interactuar con tus dispositivos móviles.");
            txtPrecio.setText("$"+100);
        }
        if(item == 9){
            txtDescripcion.setText("Zapato mocasín, marca polo, de tela.");
            txtPrecio.setText("$"+700);
        }
        if(item == 10){
            txtDescripcion.setText("Chamarra negra tipo hoodie \nideal para que te la robe la novia.");
            txtPrecio.setText("$"+800);
        }
        if(item == 11){
            txtDescripcion.setText("Mouse inalámbrico con baterías incluídas.");
            txtPrecio.setText("$"+600);
        }
        if(item == 12){
            txtDescripcion.setText("Teclado inalámbrico con baterías incluídas.");
            txtPrecio.setText("$"+170);
        }
        if(item == 13){
            txtDescripcion.setText("Monitor de pantalla plana, 32 pulgadas \nideal para gaming.");
            txtPrecio.setText("$"+460);
        }
        if(item == 14){
            txtDescripcion.setText("Con opcion de recargar tinta \nen color negro ");
            txtPrecio.setText("$"+120);
        }
        if(item == 15){
            txtDescripcion.setText("En distintos colores y tallas.");
            txtPrecio.setText("$"+300);
        }
        if(item == 16){
            txtDescripcion.setText("La mejor calidad para que ya no seas sad.");
            txtPrecio.setText("$"+140);
        }
        if(item == 17){
            txtDescripcion.setText("Lo mas delicioso para empeazar tu dia \ny empaquetes de 20.");
            txtPrecio.setText("$"+220);
        }
        if(item == 18){
            txtDescripcion.setText("De la marca bose, y con opcion de bluethoot.");
            txtPrecio.setText("$"+400);
        }
        if(item == 19){
            txtDescripcion.setText("Te ofrece 20 Mbps de velocidad \nvas a volar al navegar.");
            txtPrecio.setText("$"+510);
        }
        if(item == 20){
            txtDescripcion.setText("Listos para usarse y con proteccion de rayos UV \nen coloro negro.");
            txtPrecio.setText("$"+710);
        }
    }//GEN-LAST:event_cmboxNombreActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //this.setVisible(false);
        ca.Total();
        ca.setTitle("Carrito");
        ca.setVisible(true);
        ca.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmboxCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxCantidadActionPerformed
        String precio = txtPrecio.getText();
        item = cmboxCantidad.getSelectedIndex();
        int a = (Integer.parseInt(String.valueOf(precio.charAt(1))) * 100 + Integer.parseInt(String.valueOf(precio.charAt(2))) * 10 + Integer.parseInt(String.valueOf(precio.charAt(3))));
        total = item * a;
        txtPrecio.setText("$"+total);
    }//GEN-LAST:event_cmboxCantidadActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            pto = 9000;
            c = new Socket("127.0.0.1", pto);
            br = new BufferedReader(new InputStreamReader(c.getInputStream()));
            pw = new PrintWriter(c.getOutputStream());
            String pedido = ca.Pedio();
            pw.println(pedido);
            pw.flush();
            br.close();
            pw.close();
            c.close();
            JOptionPane.showMessageDialog(null, "Se ha creado tu ticket satisfactoriamente","Correcto",JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
	}
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmboxCantidad;
    private javax.swing.JComboBox<String> cmboxNombre;
    private javax.swing.JEditorPane editPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
