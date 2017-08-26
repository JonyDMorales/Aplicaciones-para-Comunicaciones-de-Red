package practica4_axel_cliente;
import java.net.*;


public class Conectados extends Thread{
   
    MulticastSocket recibe;
    InetAddress gpo;
    int pto;
    Cliente c;
    
    public Conectados(Cliente c1){
        c = c1;
    }
    
    public void run(){
        try{
            pto = 8001;
            recibe = new MulticastSocket(pto);
            gpo = InetAddress.getByName("227.1.1.1");
            recibe.setTimeToLive(255);
            recibe.joinGroup(gpo);

            while(true){
                DatagramPacket p = new DatagramPacket(new byte[128], 128);
                recibe.receive(p);
                String aux = new String(p.getData(), 0, p.getLength());
                c.Conectado(aux);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
