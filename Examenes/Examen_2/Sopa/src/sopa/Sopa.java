package sopa;
import java.net.*;

public class Sopa {
    
    static String Tema;
    static MulticastSocket recibe;
    static InetAddress gpo;
    static int pto;
    
    public static void main(String[] args) {
        try{ 
            pto = 8001;
            recibe = new MulticastSocket(pto);
            gpo = InetAddress.getByName("227.1.1.1");
            recibe.setTimeToLive(255);
            recibe.joinGroup(gpo);
            DatagramPacket p = new DatagramPacket(new byte[30], 30);
            recibe.receive(p);
            Tema = new String(p.getData(), 0, p.getLength());    
            System.out.println("Se recibio tema: " + Tema);
            pto = 6000;
            byte[] b = "iniciado".getBytes();
            p = new DatagramPacket(b, b.length, gpo, pto);
            recibe.send(p);
            Principal pr = new Principal();
            Thread hiloA = new Encontrada(pr);
            Thread hiloB = new Tiempo(pr);
            hiloA.start();
            hiloB.start();
            pr.setVisible(true);
            pr.setLocationRelativeTo(null);
            pr.setTitle("Sopa de letras");
            pr.Coloca_Tema(Tema);
            pr.Jugar();
        }catch(Exception e){
            e.printStackTrace();
	}
    }
}
