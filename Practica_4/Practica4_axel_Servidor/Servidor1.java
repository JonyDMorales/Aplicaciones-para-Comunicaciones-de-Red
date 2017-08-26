import java.net.*;
import java.io.*;
import java.util.ArrayList;

/*Nuevos Usuarios Conectados*/
public class Servidor1 extends Thread{
    
    public void run(){
     	try{
            int pto = 8000;
            MulticastSocket servidor = new MulticastSocket(pto);
            InetAddress gpo = InetAddress.getByName("227.1.1.1");
            servidor.setTimeToLive(255);
            servidor.joinGroup(gpo);
            System.out.println("Servidor1...");
            String usuarios = "";
            byte[] b;

            for (; ; ) {
                DatagramPacket p = new DatagramPacket(new byte[128], 128);
                /*Se recibe nuevo usuario conectado*/
				servidor.receive(p);
                String aux = new String(p.getData(), 0, p.getLength());
                //System.out.println(aux);
                String delims = "[ ]+";
                String[] tokens = aux.split(delims);

                if (tokens[0].equals("borrar")) {
                    delims = "[,]+";
                    String[] cad = usuarios.split(delims);
                    usuarios = "";
                    //System.out.println("Se quiere eliminar: " + tokens[1]);
                    for (String element : cad) 
                         if (tokens[1].equals(element)){
                            System.out.println("Se borro: " + element); 
                        }else{
                            //System.out.println("Entro con: " + element); 
                            usuarios += element + ",";
                        }
                }else{
                    usuarios += aux + ",";
                }
                /*Se envia usuarios conectados*/
                Thread.sleep(2*1000);
                //System.out.println("Se envia: " + usuarios);
                b = usuarios.getBytes();
                pto = 8001;
                p = new DatagramPacket(b, b.length, gpo, pto);
                servidor.send(p);
            }
		}catch(Exception e){
            e.printStackTrace();
  		}   
    }
}