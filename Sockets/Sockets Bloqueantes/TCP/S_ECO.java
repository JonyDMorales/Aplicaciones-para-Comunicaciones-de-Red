import java.net.*;
import java.io.*;

public class S_ECO{
	public static void main(String[] args) {
		try{
			int pto = 5000;
			//Se crea socket cliente
			ServerSocket s = new ServerSocket(pto);
			for (; ; ) {
				System.out.println("Servidor...");
				//Se acepta socket cliente
				Socket c = s.accept();
				//Se crea para mandar respuesta
				PrintWriter pw = new PrintWriter( c.getOutputStream());
				//Se crea para leer respuesta
				BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
				String msj = "";

				for (; ; ) {
					//se lee del cliente
					msj = br.readLine();

					if (msj.compareToIgnoreCase("salir")==0) {
						br.close();
						pw.close();
						c.close();
						System.exit(0);
					}else{
						System.out.println("Mensaje recibido: " +msj);
						String eco = msj + " ECO";
						//Se manda respuesta
						pw.println(eco);
						pw.flush();
						continue;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}