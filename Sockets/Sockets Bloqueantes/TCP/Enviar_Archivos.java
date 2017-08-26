import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;

public class Enviar_Archivos{
	public static void main(String[] args) {
		try{
			String host = "127.0.0.1";
			int pto = 8000;
			JFileChooser jF = new JFileChooser();
			int r = jF.showOpenDialog(null);

			if( r == JFileChooser.APPROVE_OPTION){
				File f = jF.getSelectedFile();
				String nombre = f.getName();
				String path = f.getAbsolutePath();
				long tam = f.length();
				//Se crea socket de cleinte 
				Socket c = new Socket(host,pto);
				System.out.println("Conexion Establecida\nSe envia el archivo: " + nombre + "\nQue mide: "+tam);
				//Se crea para enviar archivo
				DataOutputStream dos = new DataOutputStream(c.getOutputStream());
				DataInputStream dis = new DataInputStream(new FileInputStream(path));
				dos.writeUTF(nombre);
				//dos.flush();
				dos.writeLong(tam);
				dos.flush();
				long n = 0;
				int i = 0, porcentaje = 0;

				while(n < tam){
					byte[] b = new byte[2000];
					i = dis.read(b);
					dos.write(b,0,i);
					n = n + i;
					porcentaje = (int)((n*100) / tam);
					System.out.print("\rEnviando "+ porcentaje+"%"); 
				}
				System.out.println("\nArchivo Enviado");
				dis.close();
				dos.close();
				c.close();
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}