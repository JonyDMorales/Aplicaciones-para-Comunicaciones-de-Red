import java.net.*;
import java.io.*;

public class Recibir_O{
	public static void main(String[] args) {
		try{
			int pto = 9000;
			ServerSocket s = new ServerSocket(pto);
			System.out.println("Servidor..");
			for (; ; ) {
				Socket c = s.accept();
				ObjectInputStream ois = new ObjectInputStream(c.getInputStream());
				Objeto Ob = (Objeto) ois.readObject();
				System.out.println("Nombre: "+ Ob.getNombre());
				System.out.println("Apellido Paterno: "+ Ob.getApaterno());
				System.out.println("Apellido Materno: "+ Ob.getAmaterno());
				System.out.println("Edad: "+ Ob.getEdad());
				System.out.println("Clave: "+ Ob.getClave());
				System.out.println("Departamento: "+ Ob.getDepto());
				
				Object O = new Objeto("Nombre","Materno","Paterno",(short)22,123,"Desarrollo");
				ObjectOutputStream oos = new ObjectOutputStream(c.getOutputStream());
				oos.writeObject(O);
				oos.flush();

				oos.close();
				ois.close();
				c.close();
				break;
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}