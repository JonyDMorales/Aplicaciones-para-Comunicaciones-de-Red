package practica2_axel_servidor;

import java.net.*;
import java.io.*;

public class Servidor1 {
    
    int pto = 5000;
    ServerSocket s;
    PrintWriter pw;
    BufferedReader br;
    int camisa = 10;
    int pantalon = 7;
    int bolsa = 8;
    int computadora = 6;
    int cel = 5;
    int tablet = 11;
    int mochila = 5;
    int tel = 10;
    int zapato = 6;
    int chamarra = 8;
    int mouse = 7;
    int teclado = 2;
    int monitor = 10;
    int impresora = 5;
    int playera = 11;
    int chocolate = 20;
    int sandwich = 4;
    int audifonos = 22;
    int modem = 6;
    int lentes = 10;
    
    public void Servidor(){
        try{
            while(true){
                s = new ServerSocket(pto);
                Socket c = s.accept();
                pw = new PrintWriter( c.getOutputStream());
                br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                for(;;){
                    String ids = br.readLine();
                    String cant = br.readLine();
                    int id = Integer.parseInt(ids);
                    int cantidad = Integer.parseInt(cant);
                    if(id== 1 && cantidad < camisa){
		    	camisa -= cantidad;
                        pw.println("1");
                        pw.flush();
		    }else if(id== 2 && cantidad < pantalon){
		    	pantalon -= cantidad;
                        pw.println("1");
                    	pw.flush();
		    }else if(id==3&&cantidad < bolsa){
		    	bolsa -= cantidad;
                        pw.println("1");
                    	pw.flush();
		    }else if(id==4&&cantidad < computadora){
		    	computadora -= cantidad;
                        pw.println("1");
                    	pw.flush();
		    }else if(id==5&&cantidad < cel){
                        cel -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==6&&cantidad < tablet){
                        tablet -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==7&&cantidad < mochila){
                        mochila -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==8&&cantidad < tel){
                        tel -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==9&&cantidad < zapato){
                        zapato -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==10&&cantidad < chamarra){
                        chamarra -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==11&&cantidad< mouse){
                        mouse -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==12&&cantidad < teclado){
                        teclado -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==13&&cantidad < monitor){
                        monitor -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==14&&cantidad < impresora){
                        impresora -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==15&&cantidad < playera){
                        playera -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==16&&cantidad < chocolate){
                        chocolate -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==17&&cantidad < sandwich){
                        sandwich -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==18&&cantidad < audifonos){
                        audifonos -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==19&&cantidad < modem){
                        modem -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else if(id==20&&cantidad < lentes){
                        lentes -= cantidad;
		    	pw.println("1");
                    	pw.flush();
		    }else{
		    	pw.println("0");
                    	pw.flush();
		    }
                    br.close();
                    pw.close();
                    s.close();
                    //System.exit(0);
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
	}
    }
}
