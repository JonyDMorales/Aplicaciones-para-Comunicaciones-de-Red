package practica2_axel_s;

import java.net.*;
import java.io.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Date;

public class Servidor {
        int pto = 9000;
        ServerSocket s;
        BufferedReader br;
        FileOutputStream archivo;
        Document documento;
        int total;
        String productos; 
        
        public void Servidor(){
        try{
            while(true){
                s = new ServerSocket(pto);
                Socket c = s.accept();
                br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                for(;;){
                    productos = "";
                    String pedido = br.readLine();
                    System.out.println(pedido);
                    for(int i = 0; i < pedido.length() ; i += 4){
                        char id = pedido.charAt(i);
                        int cantidad = (int)pedido.charAt(i+2) - 48;
                        System.out.println(cantidad);
                        if(id == '1'){
                            productos += "\tCamisa: $100 Cantidad: "+cantidad + " Subtotal: " + 100 * cantidad +"\n";
                            total += 100 * cantidad;
                        }    
                        if(id == '2'){
                            productos += "\tPantalon: $200 Cantidad: " + cantidad + " Subtotal: " + 200 * cantidad +"\n";
                            total += 200 * cantidad;
                        }
                        if(id == '3'){
                            productos += "\tBolsa: $300 Cantidad: "+ cantidad + " Subtotal: " + 300 * cantidad +"\n";
                            total += 300 * cantidad;
                        }
                        if(id == '4'){
                            productos += "\tComputadora: $160 Cantidad: "+ cantidad + " Subtotal: " + 160 * cantidad +"\n";
                            total += 160 * cantidad;
                        }
                        if(id == '5'){
                            productos += "\tCelular: $230 Cantidad: " + cantidad + " Subtotal: " + 230 * cantidad +"\n";
                            total += 230 * cantidad;
                        }
                        if(id == '6'){
                            productos += "\tTablet: $500 Cantidad: " + cantidad + " Subtotal: " + 500 * cantidad +"\n";
                            total += 500 * cantidad;
                        }
                        if(id == '7'){
                            productos += "\tMochila: $7000 Cantidad: " + cantidad + " Subtotal: " + 700 * cantidad +"\n";
                            total += 700 * cantidad;
                        }
                        if(id == '8'){
                            productos += "\tTelevision: $100 Cantidad: " + cantidad + " Subtotal: " + 100 * cantidad +"\n";
                            total += 100 * cantidad;
                        }
                        if(id == '9'){
                            productos += "\tZapato: $700 Cantidad: " + cantidad + " Subtotal: " + 700 * cantidad +"\n";
                            total += 700 * cantidad;
                        }
                        if("10".equals(id)){
                            productos += "\tChamarra: $800 Cantidad: " + cantidad + " Subtotal: " + 800 * cantidad +"\n";
                            total += 800 * cantidad;
                        }
                        if("11".equals(id)){
                            productos += "\tMouse: $600 Cantidad: " + cantidad + " Subtotal: " + 600 * cantidad +"\n";
                            total += 600 * cantidad;
                        }
                        if("12".equals(id)){
                            productos += "\tTeclado: $170 Cantidad: " + cantidad + " Subtotal: " + 170 * cantidad +"\n";
                            total += 170 * cantidad;
                        }
                        if("13".equals(id)){
                            productos += "\tMonitor: $460 Cantidad: " + cantidad + " Subtotal: " + 460 * cantidad +"\n";
                            total += 460 * cantidad;
                        }
                        if("14".equals(id)){
                            productos += "\tImpresora: $120 Cantidad: " + cantidad + " Subtotal: " + 120 * cantidad +"\n";
                            total += 120 * cantidad;
                        }
                        if("15".equals(id)){
                            productos += "\tPlayera: $300 Cantidad: " + cantidad + " Subtotal: " + 300 * cantidad +"\n";
                            total += 300 * cantidad;
                        }
                        if("16".equals(id)){
                            productos += "\tChocolate: $140 Cantidad: " + cantidad + " Subtotal: " + 140 * cantidad +"\n";
                            total += 140 * cantidad;
                        }
                        if("17".equals(id)){
                            productos += "\tSandwich: $220 Cantidad: " + cantidad + " Subtotal: " + 220 * cantidad +"\n";
                            total += 220 * cantidad;
                        }
                        if("18".equals(id)){
                            productos += "\tAudifonos: $400 Cantidad: " + cantidad + " Subtotal: " + 400 * cantidad +"\n";
                            total += 400 * cantidad;
                        }
                        if("19".equals(id)){
                            productos += "\tModem: $510 Cantidad: " + cantidad + " Subtotal: " + 510 * cantidad +"\n";
                            total += 510 * cantidad;
                        }
                        if("20".equals(id)){
                            productos += "\tLentes: $710 Cantidad: " + cantidad + " Subtotal: " + 710 * cantidad +"\n";
                            total += 710 * cantidad;
                        }
                    }
                    br.close();
                    s.close();
                    break;
                }
                archivo = new FileOutputStream("//home//jonyd//Escritorio//ticket.pdf");
                documento = new Document();
                PdfWriter.getInstance(documento, archivo);
                documento.open();
                Paragraph paragraph;
                java.util.Date fecha = new Date();
                paragraph = new Paragraph("Tienda Online");
                paragraph.setAlignment(Element.ALIGN_CENTER);
                documento.add(paragraph);
                paragraph = new Paragraph("Todo para llevar");
                paragraph.setAlignment(Element.ALIGN_CENTER);
                documento.add(paragraph);
                paragraph = new Paragraph("*Inserte Frase aqui*");
                paragraph.setAlignment(Element.ALIGN_CENTER);
                documento.add(paragraph);
                documento.add(new Paragraph("***************************************************************************************************************"));
                paragraph = new Paragraph("Mexico CDMX ESCOM");
                paragraph.setAlignment(Element.ALIGN_CENTER);
                documento.add(paragraph);
                paragraph = new Paragraph(fecha.toString());
                paragraph.setAlignment(Element.ALIGN_CENTER);
                documento.add(paragraph);
                documento.add(new Paragraph("***************************************************************************************************************"));
                
                paragraph = new Paragraph("No. " + (int)(Math.random() * (19423-20145)+20145));
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                documento.add(paragraph);
                
                paragraph = new Paragraph("Tus productos son:");
                paragraph.setIndentationLeft(50);
                documento.add(paragraph);
                paragraph = new Paragraph(productos);
                paragraph.setIndentationLeft(50);
                documento.add(paragraph);
                paragraph = new Paragraph("Total: $"+total);
                paragraph.setAlignment(Element.ALIGN_RIGHT);
                documento.add(new Paragraph("***************************************************************************************************************"));
                documento.add(paragraph);
                documento.add(new Paragraph("*Gracias Por Su Compra*"));
                documento.close();
            }
        }catch(Exception e){
            e.printStackTrace();
	}
    }
}
