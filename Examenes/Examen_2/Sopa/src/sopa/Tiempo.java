package sopa;
import java.net.*;

public class Tiempo extends Thread{
    Principal prin;
    int min = 4;
    int seg = 59;
    
    public Tiempo(Principal pr){
        prin = pr;
    }
    
    public void run(){
     	try{
            int pto = 6001;
            MulticastSocket servidor = new MulticastSocket(pto);
            InetAddress gpo = InetAddress.getByName("227.1.1.1");
            servidor.setTimeToLive(255);
            servidor.joinGroup(gpo);
            while(true){
                DatagramPacket p = new DatagramPacket(new byte[30], 30);
                servidor.receive(p);
                String aux = new String(p.getData(), 0, p.getLength());
                String delims = "[ ,();:]+";
		String[] tokens = aux.split(delims);
                aux = (min - Integer.parseInt(tokens[0])) + ":" + (seg - Integer.parseInt(tokens[1]));
                if(aux.equals("4:58"))
                    prin.fin();
                else    
                    prin.tiempo(aux);
            }
	}catch(Exception e){
            e.printStackTrace();
  	}   
    }
}
