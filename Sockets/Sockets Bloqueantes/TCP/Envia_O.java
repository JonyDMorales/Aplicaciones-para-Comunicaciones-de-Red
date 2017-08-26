import java.net.*;
import java.io.*;

public class Envia_O{
	public static void main(String[] args) {
		try{
			String host = "127.0.0.1";
			int pto = 9000;
			//Se crea socket de cleinte 
			Socket c = new Socket(host,pto);
			System.out.println("Conexion Establecida..");
			Object O = new Objeto("Nombre", "Materno", "Paterno", (short)22, 123, "Desarrollo");
			ObjectOutputStream oos = new ObjectOutputStream(c.getOutputStream());
			oos.writeObject(O);
			oos.flush();

			ObjectInputStream ois = new ObjectInputStream(c.getInputStream());
			Objeto Ob = (Objeto) ois.readObject();
			System.out.println("Nombre: "+ Ob.getNombre());
			System.out.println("Apellido Paterno: "+ Ob.getApaterno());
			System.out.println("Apellido Materno: "+ Ob.getAmaterno());
			System.out.println("Edad: "+ Ob.getEdad());
			System.out.println("Clave: "+ Ob.getClave());
			System.out.println("Departamento: "+ Ob.getDepto());
			
			oos.close();
			ois.close();
			c.close(); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}