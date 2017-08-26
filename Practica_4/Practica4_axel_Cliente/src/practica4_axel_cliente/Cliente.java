package practica4_axel_cliente;
import java.io.*;
import java.net.*;
import javax.swing.DefaultListModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class Cliente extends javax.swing.JFrame{
    
    MulticastSocket envia; 
    String nombreU;
    InetAddress gpo;
    int pto;
    HTMLDocument doc;
    HTMLEditorKit editorKit;
    
    public Cliente() {
        initComponents(); 
        txtConversacion.setEditable(false);
    }
    
    public void Nombre(String u){
        nombreU = u;
    }
    
    public void Conectado(String u){
        DefaultListModel modelo = new DefaultListModel();
        modelo.addElement("Conectados");
        String delims = "[,]+";
	String[] tokens = u.split(delims);
        for (String element : tokens) {
            modelo.addElement(element);
        }
        listConexion.setModel(modelo);
    }
    
    public void ReciboMsj(String msj) throws BadLocationException, IOException{
        try{
            String delims = "[ ]+";
            String[] tokens = msj.split(delims);
            StringWriter writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            txtConversacion.setContentType("text/html");
            doc = (HTMLDocument)txtConversacion.getDocument();
            editorKit = (HTMLEditorKit)txtConversacion.getEditorKit();
            File ff = new File(".");
            String path = ff.getAbsolutePath();
                
            if(tokens[0].equals("(privado)")){
                if(tokens[1].equals("("+nombreU+")") || tokens[2].equals("("+nombreU+")")){
                    /*Mensajes privados*/
                    for(String element : tokens){
                        if(-1 <  element.indexOf(":)")){
                            element = " <img src=\"File:///"+ path.substring(0, path.length()-2 )+"/feliz.png\" />" + " ";
                            out.print(element + " ");
                        }else if(-1 <  element.indexOf(":/")){
                            element = " <img src= \"File:///"+ path.substring(0, path.length()-2 )+"/enojado.png\" />" + " ";
                            out.print(element + " ");
                        }else if(-1 <  element.indexOf("<3")){
                            element = " <img src= \"File:///"+ path.substring(0, path.length()-2 )+"/cora.png\">" + " ";
                            out.print(element + " ");
                        }else{
                            out.print(element + " ");
                        }
                    }
                    out.println();
                    out.close();
                    System.out.println(writer.toString());
                    editorKit.insertHTML(doc, doc.getLength(), writer.toString(), 0, 0, null);
                }else{
                    //No hace nada
                }
            }else{    
                for(String element : tokens){
                    if(-1 <  element.indexOf(":)")){
                        element = " <img src=\"File:///"+ path.substring(0, path.length()-2 )+"/feliz.png\" />" + " ";
                        out.print(element + " ");
                    }else if(-1 <  element.indexOf(":/")){
                        element = " <img src= \"File:///"+ path.substring(0, path.length()-2 )+"/enojado.png\" />" + " ";
                        out.print(element + " ");
                    }else if(-1 <  element.indexOf("<3")){
                        element = " <img src= \"File:///"+ path.substring(0, path.length()-2 )+"/cora.png\">" + " ";
                        out.print(element + " ");
                    }else{
                        out.print(element + " ");
                    }
                }
                out.println();
                out.close();
                System.out.println(writer.toString());
                editorKit.insertHTML(doc, doc.getLength(), writer.toString(), 0, 0, null);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listConexion = new javax.swing.JList<>();
        txtEnviar = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtConversacion = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Bienvenido");

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        listConexion.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Conectados" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listConexion);

        jScrollPane3.setViewportView(txtConversacion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 343, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(12, 12, 12)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtEnviar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(130, 130, 130))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(txtEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            String usuario = listConexion.getSelectedValue();
            if(listConexion.getSelectedValue() == null)
                usuario = "nada";
            pto = 5000;
            envia = new MulticastSocket(pto);
            envia.setTimeToLive(255);
            String msj = nombreU + " " + txtEnviar.getText();
            
            gpo = InetAddress.getByName("227.1.1.1");
            envia.joinGroup(gpo);
            if (usuario.equals("Conectados") || usuario.equals("nada")){
                byte[] b = msj.getBytes();
                DatagramPacket p = new DatagramPacket(b, b.length, gpo, pto);
                envia.send(p);
            }else{
                msj = "privado " + usuario + " " + msj;
                System.out.println("Total: " + msj);
                byte[] b = msj.getBytes();
                DatagramPacket p = new DatagramPacket(b, b.length, gpo, pto);
                envia.send(p);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> listConexion;
    private javax.swing.JEditorPane txtConversacion;
    private javax.swing.JTextField txtEnviar;
    // End of variables declaration//GEN-END:variables
}