import java.net.*;
import java.io.*;

public class AnuncioS{
	public static void main(String[] args) {
		try{
			MulticastSocket s = new MulticastSocket(8000);
			s.setTimeToLive(255);
			System.out.println("Recibiendo mensaje...");
			InetAddress gpo = InetAddress.getByName("227.1.1.1");
			s.joinGroup(gpo);

			for (; ; ) {
				DatagramPacket p = new DatagramPacket(new byte[256], 256);
				s.receive(p);
				String msj = new String(p.getData(), 0);
				System.out.println("Se recibio: " + msj + "  de:" + p.getAddress());
			}
		}catch(Exception e){
      		e.printStackTrace();
  		}
	}
}