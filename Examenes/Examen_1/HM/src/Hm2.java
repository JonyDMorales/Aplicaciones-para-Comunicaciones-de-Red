import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
public class Hm2 {

   public static void main(String args[]) {
       String palabra = "Hola";
       MessageCS cs = new MessageCS(0, palabra.length(), 0, palabra);
       byte[] b = cs.getByteRepr();
   }
}