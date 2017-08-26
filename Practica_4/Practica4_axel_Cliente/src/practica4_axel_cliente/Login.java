package practica4_axel_cliente;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.*;

public class Login extends javax.swing.JFrame {
    
    MulticastSocket s;
    InetAddress gpo;
    String conectado;
    DatagramPacket p;
    int pto;
    
    public Login() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAcceder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bienvenido");

        jLabel2.setText("Nombre:");

        btnAcceder.setText("Acceder");
        btnAcceder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccederActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAcceder)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(btnAcceder)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAccederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccederActionPerformed
        try{
            pto = 8000;
            s = new MulticastSocket(pto);
            s.setTimeToLive(255);
            conectado = txtNombre.getText();
            byte[] b = conectado.getBytes();
            gpo = InetAddress.getByName("227.1.1.1");
            s.joinGroup(gpo);
            
            /*Se envia nuevo usuario conectado*/
            p = new DatagramPacket(b, b.length, gpo, pto);
            s.send(p);
            
            /*Se crea ventana chat*/
            this.setVisible(false);
            Cliente c = new Cliente();
            Thread hiloB = new RecibiendoMsj(c);
            hiloB.start();
            Thread hiloA = new Conectados(c);
            hiloA.start();
            c.Nombre(txtNombre.getText());
            c.setVisible(true);
            c.setLocationRelativeTo(null);
            c.setTitle("Chat");
            c.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    try{
                        pto = 8000;
                        s = new MulticastSocket(pto);
                        s.setTimeToLive(255);
                        String conectado = "borrar " + txtNombre.getText();
                        byte[] b = conectado.getBytes();
                        gpo = InetAddress.getByName("227.1.1.1");
                        s.joinGroup(gpo);
            
                        /*Se envia nuevo usuario des-conectado*/
                        DatagramPacket p = new DatagramPacket(b, b.length, gpo, pto);
                        s.send(p);
                    }catch(Exception es){
                        es.printStackTrace();
                }}});
        }catch(Exception e){
            e.printStackTrace();
  	}
    }//GEN-LAST:event_btnAccederActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAcceder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
