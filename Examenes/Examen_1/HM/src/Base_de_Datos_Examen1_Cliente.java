import java.net.*;
import java.io.*;

public class Base_de_Datos_Examen1_Cliente{
	public static void main(String[] args) {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int pto = 5000;
			String dir = "";
			InetAddress host = null;

			//Creacion del socket cliente
			Socket c = new Socket("127.0.0.1", pto);

			System.out.println("Conexion Establecida\n");
			
			//Se crea para mandar el mensaje
			PrintWriter pw = new PrintWriter(c.getOutputStream());
			//Se crea para recibir respuesta
			BufferedReader br1 = new BufferedReader(new InputStreamReader(c.getInputStream()));
			
			for (; ; ) {
				String consulta = "";
				System.out.print("\nmysqlD>");
				consulta = br.readLine();
				//Se manda mensaje al servidor
				pw.println(consulta);
				pw.flush();
				if (consulta.compareToIgnoreCase("salir")==0) {
					br1.close();
					br.close();
					pw.close();
					c.close();
					System.exit(0);
				}else{
					//Se lee mensaje del servidor
					String respuesta = br1.readLine();
					System.out.println("\n"+respuesta);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}