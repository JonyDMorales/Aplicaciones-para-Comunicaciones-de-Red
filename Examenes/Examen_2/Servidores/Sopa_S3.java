import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

/*Ganador*/
public class Sopa_S3 extends Thread{

    public class Reloj {
 
        Timer timer = new Timer(); // El timer que se encarga de administrar los tiempo de repeticion
        public int segundos = 0; // manejar el valor del contador
        public int minutos = 0; //
        public boolean frozen; // manejar el estado del contador TIMER AUTOMATICO -- True Detenido | False Corriendo
 
        // clase interna que representa una tarea, se puede crear varias tareas y asignarle al timer luego
        class MiTarea extends TimerTask {
            public void run() {
                if (minutos == 5) {
                    minutos = 0;
                }else if (segundos > 58) {
                    segundos = 0;
                    minutos ++;
                }else{
                    segundos++;
                }
                //System.out.println("Tiempo: " + minutos + ":" + segundos);
            }// end run()
        }// end SincronizacionAutomatica
 
        public void Start(int pSeg) throws Exception {
            frozen = false;
            // le asignamos una tarea al timer
            timer.schedule(new MiTarea(), 0, pSeg * 1000);
        }// end Start
 
        public void Stop() {
            System.out.println("Stop");
            frozen = true;
        }// end Stop
 
        public String Cuenta(){
            return minutos + ":" + segundos;
        }

        public void Reset() {
            System.out.println("Reset");
            frozen = true;
            segundos = 0;
        }// end Reset
 
    }// end Reloj

    public void run(){
     	try{
            Reloj reloj = new Reloj();
            int pto = 6000;
            MulticastSocket servidor = new MulticastSocket(pto);
            InetAddress gpo = InetAddress.getByName("227.1.1.1");
            servidor.setTimeToLive(255);
            servidor.joinGroup(gpo);
            System.out.println("Servidor Tiempo");
            byte[] b;
            DatagramPacket p = new DatagramPacket(new byte[10], 10);
            servidor.receive(p);
            reloj.Start(1);
            System.out.println("Se comenzo");
            String aux;   
            while(true){    
                pto = 6001;
                aux = reloj.Cuenta();
                b = aux.getBytes();
                p = new DatagramPacket(b, b.length, gpo, pto);
                servidor.send(p); 
            }
		}catch(Exception e){
            e.printStackTrace();
  		}   
    }
}