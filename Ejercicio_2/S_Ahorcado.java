import java.nio.channels.*;
import java.nio.*;
import java.net.*;
import java.util.Iterator;

public class S_Ahorcado{
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

	static String anterior = "";

	public static void main(String[] args) {
		int pto = 9999;
		boolean marcador = false;
		String nivel[][] = {{"HOLA","NADA","PARA"}, {"NUNCA","JAMAS","NOBLE"}, {"PRONTO","TIERNO","ENTERO"}};
       	String palabra = "";
       	char [] aux = null;
       	String car = "";
       	try{
          	ServerSocketChannel s = ServerSocketChannel.open();
          	s.configureBlocking(false);
          	s.setOption(StandardSocketOptions.SO_REUSEADDR, true);
          	s.socket().bind(new InetSocketAddress(pto));
          	Selector sel = Selector.open();
          	s.register(sel,SelectionKey.OP_ACCEPT);
           	System.out.println("Servicio iniciado..esperando clientes..");
          	while(true){
              	sel.select();
              	Iterator<SelectionKey> it = sel.selectedKeys().iterator();
              	while(it.hasNext()){
                  	SelectionKey k = (SelectionKey)it.next();
                  	it.remove();
                  	if(k.isAcceptable()){
                      	SocketChannel cl = s.accept();
                      	System.out.println("Cliente conectado desde->"+cl.socket().getInetAddress().getHostAddress()+":"+cl.socket().getPort());
                      	cl.configureBlocking(false);
                      	cl.register(sel,SelectionKey.OP_READ);
                      	continue;
                  	}//if
                  	if(k.isReadable()){
                      	SocketChannel ch =(SocketChannel)k.channel();
                      	ByteBuffer b = ByteBuffer.allocate(2);
                      	b.clear();
                      	int n = ch.read(b);
                      	b.flip();
                      	String respuesta = new String(b.array(),0,n);
                      	if(isNumeric(respuesta)){
                      		/*Si es un numero se escoje la palabra a encontrar*/
                      		palabra = nivel[Integer.parseInt(respuesta)][(int)(Math.random()*3 + 1)];
                      		System.out.println("La palabra es: " + palabra);
                      		aux = palabra.toCharArray();
                       		for (int i = 0; i < aux.length; i++) {
                       			aux[i] = '_';	
                       		}
                       		car = String.valueOf(aux);
                       		anterior = car;
                      		ByteBuffer b2 = ByteBuffer.wrap(car.getBytes());
                          	ch.write(b2);
                      		continue;
                      	}else{
                      		/*Se recibe letra para compara en el arreglo*/
                          	System.out.println("letra recibida " + respuesta);
                          	char letra = respuesta.charAt(0);
                          	for (int i = 0; i < aux.length; i++) {
                          		if(palabra.charAt(i) == letra) {
                          			aux[i] = letra;	
                          		}
                       		}
                       		car = String.valueOf(aux);
                       		/*Se compara si es igual al anterior*/
                       		if (car.equals(palabra)) {
                       			car = "Ganas!";
                          		ByteBuffer b2 = ByteBuffer.wrap(car.getBytes());
                          		ch.write(b2);
                       			ch.close();
                          		continue;	
                       		}else if (anterior.equals(car)) {
                       			car = "NNNNNN";
                          		ByteBuffer b2 = ByteBuffer.wrap(car.getBytes());
                          		ch.write(b2);
                          		continue;
                       		}else{
                       			anterior = car;
                          		ByteBuffer b2 = ByteBuffer.wrap(car.getBytes());
                          		ch.write(b2);
                          		continue;
                       		}
                       		
                      	}//else
                  	}//if_readable
              	}//while
          	}//while
       	}catch(Exception e){
           	e.printStackTrace();
       	}//catch
    }//main
}