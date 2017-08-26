import java.net.*;

/*Palabras Encontradas*/
public class Sopa_S2 extends Thread{
    
    public void run(){
     	try{
            int pto = 9000;
            MulticastSocket servidor = new MulticastSocket(pto);
            InetAddress gpo = InetAddress.getByName("227.1.1.1");
            servidor.setTimeToLive(255);
            servidor.joinGroup(gpo);
            System.out.println("Servidor Palabra");
            byte[] b;
            while(true){
                DatagramPacket p = new DatagramPacket(new byte[128], 128);
                servidor.receive(p);
                String aux = new String(p.getData(), 0, p.getLength());
                System.out.println("Se encontro la palabra: " + aux);
                pto = 9001;
                b = aux.getBytes();
                p = new DatagramPacket(b, b.length, gpo, pto);
                servidor.send(p);   
            }
		}catch(Exception e){
            e.printStackTrace();
  		}   
    }
}