import java.net.*;
import java.io.*;

public class Servidor extends Thread{

    public void run(){
        try{
            int pto = 5000;
            MulticastSocket servidor = new MulticastSocket(pto);
            InetAddress gpo = InetAddress.getByName("227.1.1.1");
            servidor.setTimeToLive(255);
            servidor.joinGroup(gpo);
            System.out.println("Servidor...");
            
            while(true){
           
                DatagramPacket p = new DatagramPacket(new byte[128], 128);
                servidor.receive(p);
                String msj = new String(p.getData(), 0);
                String delims = "[ ]+";
                String[] tokens = msj.split(delims);
                byte[] b = msj.getBytes();
                if (tokens[0].equals("privado")) {
                    msj = "";
                    for (int i = 0; i < tokens.length ; i++ ) {
                        if (i == 0) {
                            msj += "(privado) ";
                        }else if(i == 1 || i == 2){
                            msj += "(" + tokens[i] + ") ";
                        }else{
                            msj += tokens[i] + " ";
                        }
                    }
                    b = msj.getBytes();
                    pto = 6000;
                    p = new DatagramPacket(b, b.length, gpo, pto);
                    servidor.send(p);
                    System.out.println(msj);
                }else{
                    msj = "";
                    for (int i = 0; i < tokens.length ; i++ ) {
                        if (i == 0) {
                            msj += "(" + tokens[i] + ") ";
                        }else{
                            msj += tokens[i] + " ";
                        }
                    }
                    b = msj.getBytes();
                    pto = 6000;
                    p = new DatagramPacket(b, b.length, gpo, pto);
                    servidor.send(p);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}