import java.net.*;
import java.io.*;

public class AnuncioC{
	public static void main(String[] args) {
		try{
			MulticastSocket s = new MulticastSocket(8000);
			s.setTimeToLive(255);
			int t = 5;
			String msj = "Hola";
			byte[] b = msj.getBytes();
			InetAddress gpo = InetAddress.getByName("227.1.1.1");
			s.joinGroup(gpo);

			for (; ; ) {
				DatagramPacket p = new DatagramPacket(b, b.length, gpo, 8000);
				s.send(p);
				try{
					Thread.sleep(t*1000);
				}catch(InterruptedException ie){}
			}
		}catch(Exception e){
      		e.printStackTrace();
  		}
	}
}