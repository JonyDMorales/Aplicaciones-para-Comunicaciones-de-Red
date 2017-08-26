import java.util.Random;
import java.io.*;
import java.net.*;

public class C_bucket {
    
    public static void main(String[] args){
        try{
            Socket cl = new Socket("148.204.57.24",9999);
            Random r = new Random();
            System.out.println("Conexion establecida.. enviando datos..");
            int[] buf = new int[100];
            for(int i=0;i<buf.length;i++)
              buf[i]= Math.abs(r.nextInt()%100);
            System.out.println("Se enviaran los sig. datos:");
            for(int j=0;j<buf.length;j++)
                System.out.print(buf[j]+",");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(baos);
            for(int i=0;i<buf.length;i++){
                dos.writeInt(buf[i]);
                dos.flush();
            }//for
            byte[] buf2 = baos.toByteArray();
            BufferedOutputStream bos = new BufferedOutputStream(cl.getOutputStream());
            bos.write(buf2);
            bos.flush();
            bos.close();
            dos.close();
            baos.close();
            cl.close();
            System.out.println("Datos enviados...");
        }catch(Exception e){
            e.printStackTrace();
        }//catch
    }//main
}
