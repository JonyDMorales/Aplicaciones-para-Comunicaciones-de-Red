import java.net.*;
import java.io.*;

public class S_ECOD{
  public static void main(String[] args) {
    try{
      int pto = 9000;
      DatagramSocket s = new DatagramSocket(pto);
      while(true){
        DatagramPacket p = new DatagramPacket(new byte[1500],1500);
        s.receive(p);
        System.out.println("Mensaje recibido: " + p.getAddress());
        String msj = new String(p.getData(),0,p.getLength());
        if (msj.indexOf("salir") == 0) {
          continue;
        }else{
          String Eco = msj + " ECO";
          byte[] b1 = Eco.getBytes();
          DatagramPacket p1 = new DatagramPacket(b1, b1.length, p.getAddress(), p.getPort());
          s.send(p1);
        }
      }
    }catch(Exception e){
        e.printStackTrace();
    }
  }
}
