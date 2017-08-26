import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
import java.io.OutputStream;

public class Enviar_ArchivoD{
	public static void main(String[] args) {
		try{
			InetAddress host = InetAddress.getByName("127.0.0.1");
      		int pto = 9000;
      		DatagramSocket c = new DatagramSocket();
			JFileChooser jF = new JFileChooser();

			while(true){
				int r = jF.showOpenDialog(null);
				if( r == JFileChooser.APPROVE_OPTION){
					File f = jF.getSelectedFile();
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					long tam = f.length();
					DataInputStream dis = new DataInputStream(new FileInputStream(path));
					ByteArrayOutputStream baos = ByteArrayOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(baos);
					int n = 0, i = 0, j = 0;
					byte[] b = new byte[1500];
					while(n < tam){
						i = dis.read(b);
						Object d = new Datos(i, j, "0", b);
						if (true) {
							oos.writeObject(d);
    						oos.flush();
    						byte[] buf = baos.toByteArray();
    						
						}

					}
				} 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}