import java.net.*;
import java.io.*;

public class C_ECO{
	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int pto = 5000;
			String dir = "";
			InetAddress host = null;

			/*for (; ; ) {
				System.out.println("Escribe la direccion del servidor");
				dir = br.readLine();
				try{
					host = InetAddress.getByName(dir);
				}catch(UnknownHostException e){
					System.out.println("Direccion Invalida");
					continue;
				}
				break;	
			}*/

			//Creacion del socket cliente
			Socket c = new Socket("127.0.0.1", pto);
			/*
				c = new Socket();
				c.bind(new InetSocketAddress(1234));
				c.connect(new InerSocketAddress("127.0.0.1",7000));
				c.shutdownInput(true);
			*/
			System.out.println("Conexion Establecida\nEscribir mensaje <Enter> para enviar Salir para terminar");
			
			//Se crea para mandar el mensaje
			PrintWriter pw = new PrintWriter(c.getOutputStream());
			//Se crea para recibir respuesta
			BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getInputStream()));
			
			for (; ; ) {
				String msj = br.readLine();
				//Se manda mensaje al servidor
				pw.println(msj);
				pw.flush();
				if (msj.compareToIgnoreCase("salir")==0) {
					br1.close();
					br.close();
					pw.close();
					c.close();
					System.exit(0);
				}else{
					//Se lee mensaje del servidor
					String eco = br1.readLine();
					System.out.println("Eco recibido: "+eco);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}