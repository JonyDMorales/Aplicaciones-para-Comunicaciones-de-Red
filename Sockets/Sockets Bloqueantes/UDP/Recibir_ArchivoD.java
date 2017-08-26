import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;

public class Recibir_Archivos{
	public static void main(String[] args) {
		try{
			int pto = 8000;
			//Se crea socket cliente
			ServerSocket s = new ServerSocket(pto);
			for (; ; ) {
				System.out.println("Servidor...");
				//Se acepta socket cliente
				Socket c = s.accept();
				//Se crea para recibir archivo
				DataInputStream dis = new DataInputStream(c.getInputStream());
				//Se lee del cliente
				String nombre = dis.readLine();
				long tam = dis.read();
				String path = (File.separator + "home"+ File.separator + "jonyd"+File.separator+"Escritorio"+File.separator+nombre);
				System.out.println("Archivo: "+ nombre);
				System.out.println(path);
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(path));
				
				long n = 0;
				int i = 0, porcentaje = 0;
				while(n < tam){
					byte[] b = new byte[2000];
					i = dis.read(b);
					dos.write(b);
					n = n + i;
					porcentaje = (int)((n*100) / tam);
					System.out.print("\rRecibiendo "+ porcentaje+"%"); 
				}
				dos.flush();
				System.out.println("\nArchivo Recibido");
				dis.close();
				dos.close();
				c.close();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}