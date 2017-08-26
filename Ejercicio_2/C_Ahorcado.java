import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.*;
import java.nio.*;
import java.net.*;
import java.util.Iterator;

public class C_Ahorcado{
	static String palabra = "";
	static boolean marcador = false;

	static void Dibuja(){
		if (palabra.equals("")) {
			palabra = "A";
			
		}else if (palabra.equals("A")) {
			palabra = "Ah";
		}else if (palabra.equals("Ah")) {
			palabra = "Aho";
		}else if (palabra.equals("Aho")) {
			palabra = "Ahor";
		}else if (palabra.equals("Ahor")) {
			palabra = "Ahorc";
		}else if (palabra.equals("Ahorc")) {
			palabra = "Ahorca";
		}else if (palabra.equals("Ahorca")) {
			palabra = "Ahorcad";
		}else if (palabra.equals("Ahorcad")) {
			palabra = "Ahorcado";
			marcador = true;
		}
		System.out.println("******** " + palabra + " ********");
	}

	public static void main(String[] args) {
		String host = "127.0.0.1";
       	int pto = 9999;
       	
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           	SocketChannel cl = SocketChannel.open();
           	cl.configureBlocking(false);
           	Selector sel = Selector.open();
           	cl.connect(new InetSocketAddress(host,pto));
           	cl.register(sel,SelectionKey.OP_CONNECT);
           
           	while(true){
               	sel.select();
               	Iterator<SelectionKey> it = sel.selectedKeys().iterator();
               		
               	while(it.hasNext()){
                   	SelectionKey k = (SelectionKey)it.next();
                   	it.remove();
                   	if(k.isConnectable()){
                      	SocketChannel ch = (SocketChannel)k.channel();
                       	if(ch.isConnectionPending()){
                       		try{
                            	ch.finishConnect();
                            	System.out.println("Coloca un numero de dificultad:");
                            	System.out.println("0. Palabras de cuatro letras");
                            	System.out.println("1. Palabras de cinco letras");
                            	System.out.println("2. Palabras de seis letras");
                        	}catch(Exception e){
                            	e.printStackTrace();
                        	}//catch
                    	}//if_conectionpending
                    	//ch.configureBlocking(false);
                    	ch.register(sel, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    	continue;
                   	}//if
                   	if(k.isWritable()){
                   		SocketChannel ch2 = (SocketChannel)k.channel();
                   		if(marcador){
                           	System.out.println("Perdiste: Termina juego");
                           	ch2.close();
                           	System.exit(0);
                       	}else{
                       		String msj = br.readLine();
                       		ByteBuffer b = ByteBuffer.wrap(msj.getBytes());
                       		ch2.write(b);
                       		k.interestOps(SelectionKey.OP_READ);
                       		continue;
                       	}//else
                   	} else if(k.isReadable()){
                       	SocketChannel ch2 = (SocketChannel)k.channel();
                       	ByteBuffer b = ByteBuffer.allocate(6);
                       	b.clear();
                       	int n = ch2.read(b);
                       	b.flip();
                       	String respuesta = new String(b.array());
                       	/*Se compara para saber si se equivoco*/
                       	if (respuesta.equals("NNNNNN")){
                       		Dibuja();
                       		System.out.println("\nIntroduce una letra:");
                       		k.interestOps(SelectionKey.OP_WRITE);
                       		continue;
                       	}else if(respuesta.equals("Ganas!")){
                       		System.out.println("Felicidades Ganaste! \n");
                       		ch2.close();
                           	System.exit(0);
                       	}else{
                       		System.out.println("Introduce una letra:");
                       		System.out.println(respuesta + "\n");
                       		k.interestOps(SelectionKey.OP_WRITE);
                       		continue;
                       	} 
                       	
                   	}//if
               	}//while
           	}//while
       	}catch(Exception e){
           	e.printStackTrace();
       	} 
    }//main
}