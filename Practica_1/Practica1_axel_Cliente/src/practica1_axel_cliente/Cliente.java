package practica1_axel_cliente;
import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;

public class Cliente extends javax.swing.JFrame {
    
    Socket c;
    DataOutputStream dos;
    DataInputStream dis;
    JFileChooser jF;
    File f;
    String host = "127.0.0.1";
    int pto = 8000;
    String[] nombre = new String[5];
    long[] tam = new long[5];
    String[] path = new String[5];
    int count_arch = 0;

    public Cliente() {
        initComponents();
        new FileDrop( System.out, editPane, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {  String tmp="";
                for( int i = 0; i < files.length; i++, count_arch++ )
                {   try
                    {   tmp = editPane.getText();
                    tmp = tmp + files[i].getCanonicalPath() + "\n" ; 
                    path[count_arch] = files[i].getCanonicalPath();
                    nombre[count_arch] = files[i].getName();
                    tam[count_arch] = files[i].length();
                    editPane.setText(tmp);
                        //text.append( files[i].getCanonicalPath() + "\n" );
                    }   // end try
                    catch( java.io.IOException e ) {}
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JToggleButton();
        btnEnviar = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        editPane = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Cliente");

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jLabel2.setText("Porcentaje de envio:");

        editPane.setToolTipText("Arrastrar archivo sobre este espacio");
        jScrollPane2.setViewportView(editPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnEnviar)
                            .addComponent(btnSeleccionar)
                            .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSeleccionar)
                        .addGap(66, 66, 66)
                        .addComponent(btnEnviar)
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        jF = new JFileChooser();
        int r = jF.showOpenDialog(null);
        int k = 0; 
        if( r == JFileChooser.APPROVE_OPTION){
            f = jF.getSelectedFile();
            nombre[count_arch] = f.getName();
            tam[count_arch] = f.length();
            System.out.println("");
            String tmp = editPane.getText();
            path[count_arch] = f.getAbsolutePath();
            tmp = tmp + path[count_arch] +"\n";
            //System.out.println(path[count_arch]);
            editPane.setText(tmp);
            count_arch++;
            
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        try{
            for(int j=0; j<count_arch; j++){
                c = new Socket(host,pto);
                System.out.println("Conexion Establecida...");
                dos = new DataOutputStream(c.getOutputStream());
                System.out.println(path[j]);
                dis = new DataInputStream(new FileInputStream(path[j]));
                dos.writeUTF(nombre[j]);
                dos.flush();
                dos.writeLong(tam[j]);
                dos.flush();
                System.out.println(nombre[j]);
                System.out.println(tam[j]);
                long n = 0;
                int i = 0, porcentaje = 0;
                while(n < tam[j]){
                    byte[] b = new byte[2000];
                    i = dis.read(b);
                    dos.write(b,0,i);
                    n = n + i;
                    porcentaje = (int)((n*100) / tam[j]);
		txtPorcentaje.setText(""+porcentaje+"%"); 
                }
                System.out.println("Se envio:" +nombre[j]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    /**
     * @param args the command line arguments
     */
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
    private javax.swing.JToggleButton btnEnviar;
    private javax.swing.JToggleButton btnSeleccionar;
    private javax.swing.JEditorPane editPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}
