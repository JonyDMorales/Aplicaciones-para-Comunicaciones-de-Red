import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Algoritmo {

	static String vector = "AUTOMATASCELULAR";
    static IvParameterSpec iv;

    public byte [] cifrar(String key, byte[] archivo) throws Exception {
    	iv = new IvParameterSpec(vector.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),"AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec,iv);
        return cipher.doFinal(archivo);
    }
 
    public byte[] descifrar(String key, byte[] encrypted) throws Exception {
    	iv = new IvParameterSpec(vector.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(),"AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec,iv);
        return cipher.doFinal(encrypted);
    }

    public byte [] Separar_Cabecera(byte [] leer, int tam){
        int tamaño = tam % 16;
        byte [] cabecera = new byte [tamaño];
        //System.out.println("Cabecera: " + tamaño);
        int i;
        for(i = 0; i < tamaño; i++)
            cabecera[i] = leer[i];
    return cabecera;    
    }
    
    public byte [] Separar_Datos(byte [] leer, int tam){
    	int tamaño = tam % 16;
        int i,j,cantidad = leer.length - tamaño;
        byte [] datos = new byte [cantidad];
        for(i = tamaño, j = 0; i <= cantidad; i++, j++){
            datos[j] = leer[i];
        }
    return datos;    
    }
    
    public byte [] Unir(byte [] cabecera, byte[] datos, int dat){
    	int tamaño = dat % 16;
        int tam = cabecera.length + datos.length;
        byte [] archivo = new byte [tam];
        int i,j = 0;
        for(i = 0; i < tam; i++){
            if(i < tamaño)
                archivo[i] = cabecera[i];
            else{
                archivo[i] = datos[j];
                j++;
            }
        }
    return archivo;    
    }
}