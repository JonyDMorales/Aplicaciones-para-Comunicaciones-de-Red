package sopa;
import java.net.*;

public class Encontrada extends Thread{
    Principal prin;
    
    public Encontrada(Principal pr){
        prin = pr;
    }
    
    public void run(){
     	try{
            int pto = 9001;
            MulticastSocket servidor = new MulticastSocket(pto);
            InetAddress gpo = InetAddress.getByName("227.1.1.1");
            servidor.setTimeToLive(255);
            servidor.joinGroup(gpo);
            while(true){
                DatagramPacket p = new DatagramPacket(new byte[30], 30);
                servidor.receive(p);
                String aux = new String(p.getData(), 0, p.getLength());
                //System.out.println("Se encontro palabra: " + aux);
                prin.se_encontro(aux);
            }
	}catch(Exception e){
            e.printStackTrace();
  	}   
    }
}
