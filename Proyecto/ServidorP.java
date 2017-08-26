import java.net.*;
import java.io.*;
import java.security.*;
import javax.crypto.Cipher;

public class ServidorP{
	static byte [] cifrada;
	static byte[] descifrada;
    static FileInputStream file;
    static FileOutputStream fileOutput;
    static BufferedOutputStream bufferedOutput;

	public static void main(String[] args) {
		try{
			String key = "JONATANMORALESTO";
			String host = "127.0.0.1";
			int pto = 8000;
			File tmpp = new File("");
			File f = new File(tmpp.getAbsolutePath() + File.separator + "Canciones");
			Algoritmo algoritmo = new Algoritmo();

			/*Se cifran archivos*/
			if(f.exists()){
				File[] ficheros = f.listFiles();
				for (int i = 0; i < ficheros.length; i++){
  					String nombre = ficheros[i].getName();
					String path = ficheros[i].getAbsolutePath();
					long tam = ficheros[i].length();
					//System.out.println(nombre);
  					//System.out.println(tam);
					byte[] b = new byte[(int)tam];
					DataInputStream dis = new DataInputStream(new FileInputStream(path));
					dis.read(b);
					byte[] cabecera = algoritmo.Separar_Cabecera(b, (int)tam);
					byte[] datos = algoritmo.Separar_Datos(b, (int)tam);
					byte[] cifrado = algoritmo.cifrar(key,datos);
					byte[] archivo_cifrado = algoritmo.Unir(cabecera,cifrado,(int)tam);
					fileOutput = new FileOutputStream ("CancionesC/" + nombre);
            		bufferedOutput = new BufferedOutputStream(fileOutput);
            		bufferedOutput.write(archivo_cifrado);
            		bufferedOutput.close();
				}
			}

			/*Se envian archivos*/
			f = new File(tmpp.getAbsolutePath() + File.separator + "CancionesC");
			if(f.exists()){
				File[] ficheros = f.listFiles();
				for (int i = 0; i < ficheros.length; i++){
  					String nombre = ficheros[i].getName();
					String path = ficheros[i].getAbsolutePath();
					long tam = ficheros[i].length();
  				
					//Se crea para enviar archivo
					Socket c = new Socket(host,pto);
					DataOutputStream dos = new DataOutputStream(c.getOutputStream());
					DataInputStream dis = new DataInputStream(new FileInputStream(path));
					dos.writeUTF(nombre);
					dos.flush();
					dos.writeLong(tam);
					dos.flush();
					
					byte[] b = new byte[(int)tam];
					dis.read(b);
					for( int k = 0; k < tam; k++ )
                		dos.write(b[k]);
             
					System.out.println("Archivo Enviado: " + nombre);
					dis.close();
					dos.close();
					c.close();
  				}
  			}
  			/*Se descifran archivos*/
  			f = new File(tmpp.getAbsolutePath() + File.separator + "CancionesC");
			if(f.exists()){
				File[] ficheros = f.listFiles();
				for (int i = 0; i < ficheros.length; i++){
  					String nombre = ficheros[i].getName();
					String path = ficheros[i].getAbsolutePath();
					long tam = ficheros[i].length();
					//System.out.println("Entro:" + nombre);
  					//System.out.println(tam);
					byte[] b = new byte[(int)tam];
					DataInputStream dis = new DataInputStream(new FileInputStream(path));
					dis.read(b);
					byte[] cabecera = algoritmo.Separar_Cabecera(b, (int)tam);
					byte[] datos_cifrados = algoritmo.Separar_Datos(b, (int)tam);
					byte[] datos = algoritmo.descifrar(key,datos_cifrados);
					byte[] archivo_cifrado = algoritmo.Unir(cabecera,datos,(int)tam);
					fileOutput = new FileOutputStream ("CancionesD/" + nombre);
           			bufferedOutput = new BufferedOutputStream(fileOutput);
           			bufferedOutput.write(archivo_cifrado);
           			bufferedOutput.close();
				}
			}	 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}