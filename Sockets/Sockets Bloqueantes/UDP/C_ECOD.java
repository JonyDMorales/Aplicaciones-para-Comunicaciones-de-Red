import java.net.*;
import java.io.*;

public class C_ECOD{
  public static void main(String[] args) {
    try{
      InetAddress host = InetAddress.getByName("127.0.0.1");
      int pto = 9000;
      DatagramSocket c = new DatagramSocket();
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Servicio de Eco \nEscribe un mensaje..");
      while(true){
        String msj = br.readLine();
        byte[] b = msj.getBytes();
        if (msj.indexOf("Salir") >= 0) {
          System.out.println("Termina aplicacion");
          if (b.length > 1500) {
            ByteArrayInputStream bais = new ByteArrayInputStream(b);
            byte[] tmp = new byte[1500];
            int n = 0;
            while((n = bais.read(tmp)) != -1){
              DatagramPacket p = new DatagramPacket(tmp, n, host, pto);
              c.send(p);
            }//while
          }else{
              DatagramPacket p = new DatagramPacket(b, b.length, host, pto);
              c.send(p);
          }
      }else{
        if (b.length > 1500) {
          ByteArrayInputStream bais = new ByteArrayInputStream(b);
          byte[] tmp = new byte [1500];
          int n = 0;

          while((n = bais.read(tmp)) != -1){
            DatagramPacket p = new DatagramPacket(tmp, n, host, pto);
            c.send(p);
            DatagramPacket eco = new DatagramPacket(new byte[1510], 1510);
            c.receive(eco);
            System.out.println("Eco recibido: " + eco.getAddress() + " " + new String(eco.getData(), 0, eco.getLength()));
          }//while
        }else{
          DatagramPacket p = new DatagramPacket(b, b.length, host, pto);
          c.send(p);
          DatagramPacket eco = new DatagramPacket(new byte[1510], 1510);
          c.receive(eco);
          System.out.println("Eco recibido: " + eco.getAddress() + " " + new String(eco.getData(), 0, eco.getLength()));
        }
      }
    }
  }catch(Exception e){
      e.printStackTrace();
  }
  }
}
