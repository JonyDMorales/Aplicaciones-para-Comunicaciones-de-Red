import java.net.*;

/*Tema*/
public class Sopa_S1 extends Thread{  
    public void run(){
     	try{
            int pto = 8000;
            MulticastSocket servidor = new MulticastSocket(pto);
            InetAddress gpo = InetAddress.getByName("227.1.1.1");
            servidor.setTimeToLive(255);
            servidor.joinGroup(gpo);
            System.out.println("Servidor Tema");
            byte[] b;
            DatagramPacket p = new DatagramPacket(new byte[128], 128);
			servidor.receive(p);
           	String aux = new String(p.getData(), 0, p.getLength());
            System.out.println("Se jugara con el tema: " + aux);
            while(true){
            	Thread.sleep(7*1000);
            	b = aux.getBytes();
            	pto = 8001;
            	p = new DatagramPacket(b, b.length, gpo, pto);
            	servidor.send(p);	
            }
		}catch(Exception e){
            e.printStackTrace();
  		}   
    }
}