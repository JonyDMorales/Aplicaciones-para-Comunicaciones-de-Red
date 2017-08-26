import java.util.*;
import java.util.HashMap;
import java.net.*;
import java.io.*;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.StandardJavaFileManager;

public class Base_de_Datos_Examen1_Servidor{

	static HashMap<String, LinkedList> hmap;
    Method setNameMethod;

	public String Create_Table(String[] tokens){

		StringWriter writer = new StringWriter();
    	PrintWriter out = new PrintWriter(writer);
    	String NameClass = tokens[2];

    	/*Se crea la clase Table1*/
    	//System.out.println("public class " + NameClass + " {");
    	out.println("public class " + NameClass + " {");
    	for (int i = 3; i < tokens.length; i += 2 ){ 
    		//System.out.println("public " + tokens[i+1] + "  " + tokens[i] + ";");
    		out.println("public " + tokens[i+1] + "  " + tokens[i] + ";");
    	}
		
    	for (int i = 3; i < tokens.length; i += 2 ){	
    		//System.out.println("public " + tokens[i+1] + " get" + tokens[i] + "() {");
    		out.println("public " + tokens[i+1] + " get" + tokens[i] + "() {");
    		//System.out.println("return this." + tokens[i] + ";");
    		out.println("return this." + tokens[i] + ";");
    		//System.out.println("}");
    		out.println("}");
    	}

    	for (int i = 3; i < tokens.length; i += 2 ){
    		//System.out.println("public void set" + tokens[i] + "(Object aux){");
    		out.println("public void set" + tokens[i] + "(Object aux){");
    		//System.out.println("this." + tokens[i] + " = ("+ tokens[i+1] +") aux; ");
    		out.println("this." + tokens[i] + " = ("+ tokens[i+1] +") aux; ");
    		//System.out.println("}");
    		out.println("}");
    	}
    	//System.out.println("}");
    	out.println("}");
    	out.close();
    	
    	/*Se compila la clase*/
    	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
    	JavaFileObject file = new JavaSourceFromString(NameClass, writer.toString());
    	Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
    	Iterable options = Arrays.asList("-d", "./build/classes");
    	CompilationTask task = compiler.getTask(null, null, diagnostics, options, null, compilationUnits);

		boolean success = task.call();

		if(success){
			File fil = new File("./build/classes");
			try{
				/*Se carga la clase*/
           		URL url = fil.toURL();
            	URL[] urls = new URL[] { url };
            	//System.out.println(url + "      " + urls);
            	ClassLoader loader = new URLClassLoader(urls);
            	Class thisClass = loader.loadClass(NameClass);
            	Class params[] = {};

        		Class<?> tClass = Class.forName(NameClass);
        		Object tabla = tClass.newInstance();     		

        		/*Se inserta en la lista ligada y tabla hash*/
				LinkedList<Object> linkedlist = new LinkedList<Object>();
				linkedlist.addFirst(tabla);
    			hmap.put(tokens[2], linkedlist);
    			
    			return "Tabla "+ tokens[2] +" creada con exito;";
        	}catch(Exception e){
        		e.printStackTrace();
        	}
    		return "La Tabla "+ tokens[2] +" no pudo ser creada;";
    	}else{
    		return "La Tabla "+ tokens[2] +" no pudo ser creada;";
    	}
	}

	public static boolean isNumericInt(String str){  
  		try {  
    		int d = Integer.parseInt(str);  
  		}catch(NumberFormatException nfe){  
    		return false;  
  		}  
  		return true;  
	}

	public static boolean isNumericFloat(String str){  
  		try {  
    		float d = Float.parseFloat(str);  
  		}catch(NumberFormatException nfe){  
    		return false;  
  		}  
  		return true;  
	}

	public static boolean isNumeric(String str){  
  		try {  
    		double d = Double.parseDouble(str);  
  		}catch(NumberFormatException nfe){  
    		return false;  
  		}  
  		return true;  
	}

	public String Insert_Into(String[] tokens){
		try{
			LinkedList<Object> linkedlist = (LinkedList) hmap.get(tokens[2]);
			Object tabla = (Object) linkedlist.getFirst();
			Class<?> tClass = Class.forName(tokens[2]);
        	tabla = tClass.newInstance();
			Object[] aux = new Object[tokens.length/2];
			String methodName;	

			for (int i = 0; i < tokens.length; i++ ) {
				if (tokens[i].equals("VALUES")){
					for (int j = 0; i < tokens.length ; i++, j++){
						if(isNumericInt(tokens[i])){
							aux[j] = Integer.parseInt(tokens[i]);
							break;
						}
						if(isNumericFloat(tokens[i])){
							aux[j] = Float.parseFloat(tokens[i]);
							break;
						}
						else		
							aux[j] = tokens[i];	
					}
				}
			}

			for (int i = 3, j = 1; !tokens[i].equals("VALUES"); i++, j++ ) {
				methodName = "set" + tokens[i];
        		//System.out.println(tokens[i] + "  " + aux[j]);
        		setNameMethod = tabla.getClass().getMethod(methodName, Object.class);
        		setNameMethod.invoke(tabla, (Object)aux[j]);
			}
        	
			linkedlist.add(tabla);
			hmap.put(tokens[2], linkedlist);
			return "Se inserto correctamente en la tabla " + tokens[2] + ";";
		}catch(Exception e){
        	e.printStackTrace();
        }
		return "No se pudo insertar en la tabla "+ tokens[2] + ";";	
	}

	public String Select_From(String[] tokens){
		try{
			LinkedList<Object> linkedlist = (LinkedList) hmap.get(tokens[tokens.length - 1]);
			String[] aux = new String[tokens.length - 3];
			StringWriter writer = new StringWriter();
    		PrintWriter out = new PrintWriter(writer);
			String methodName;

			for (int i = 1, j = 0; i < tokens.length; i++, j++) {
				if (tokens[i].equals("FROM"))
					break;
				aux[j] = tokens[i];
			}

			for (int j = 1; j < linkedlist.size() ; j++ ){
				Object tabla = (Object) linkedlist.get(j);
				for (int i = 0; i < aux.length; i++) {
					methodName = "get" + aux[i];
        			setNameMethod = tabla.getClass().getMethod(methodName);
        			out.print(aux[i] + "  " + setNameMethod.invoke(tabla) + "  ");	
				}
			}

			return writer.toString();
		}catch(Exception e){
        	e.printStackTrace();
        }
		return "No se encontro la tabla "+ tokens[tokens.length - 1] + ";";	
	}

	public String Update(String[] tokens){
		try{
			LinkedList<Object> linkedlist = (LinkedList) hmap.get(tokens[1]);
			Object aux = (Object) tokens[3];
			String methodName;	
			Object tabla = null;
			int b = 0;

			for (int j = 1; j < linkedlist.size() ; j++ ){
				tabla = (Object) linkedlist.get(j);
				methodName = "get" + tokens[5];
        		setNameMethod = tabla.getClass().getMethod(methodName);
        		String a = (String) setNameMethod.invoke(tabla); 
        		if(a.equals(tokens[7])){
        			b = j;
        			methodName = "set" + tokens[5];	
        			setNameMethod = tabla.getClass().getMethod(methodName, Object.class);
        			setNameMethod.invoke(tabla, aux);
        		}	
			}
			
			linkedlist.set(b, tabla);
			hmap.put(tokens[2], linkedlist);
			return "Se actualizo correctamente en la tabla " + tokens[1] + ";";
		}catch(Exception e){
        	e.printStackTrace();
        }
		return "No se pudo actualizar en la tabla "+ tokens[1] + ";";
	}

	public String Drop_Table(String[] tokens){
		hmap.remove(tokens[2]);
		return "Se elimino la tabla " + tokens[2] + ";";
	}

	public String Delete_From(String[] tokens){
		hmap.remove(tokens[2]);
		return "Se elimino la tabla " + tokens[2] + ";";
	}

	public static void main(String[] args) {
		try{
			Base_de_Datos_Examen1_Servidor aux = new Base_de_Datos_Examen1_Servidor();
			int pto = 5000;
			//Se crea socket cliente
			ServerSocket s = new ServerSocket(pto);
			hmap = new HashMap<String, LinkedList>();
			for (; ; ) {
				System.out.println("Servidor...");
				//Se acepta socket cliente
				Socket c = s.accept();
				//Se crea para mandar respuesta
				PrintWriter pw = new PrintWriter( c.getOutputStream());
				//Se crea para leer respuesta
				BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
				String consulta = "";

				for (; ; ) {
					//se lee del cliente
					consulta = br.readLine();
					String delims = "[ ,();]+";
					String[] tokens = consulta.split(delims);

					if (consulta.compareToIgnoreCase("salir")==0) {
						br.close();
						pw.close();
						c.close();
						System.exit(0);
					}if (tokens[0].equals("CREATE") && tokens[1].equals("TABLE")) {
						String respuesta = aux.Create_Table(tokens);
						pw.println(respuesta);
						pw.flush();
						continue;	
					}
					if (tokens[0].equals("INSERT") && tokens[1].equals("INTO")) {
						String respuesta = aux.Insert_Into(tokens);
						pw.println(respuesta);
						pw.flush();
						continue;	
					}
					if (tokens[0].equals("SELECT")) {
						String respuesta = aux.Select_From(tokens);
						System.out.println("Le envio: " + respuesta);
						pw.println(respuesta);
						pw.flush();
						continue;	
					}
					if (tokens[0].equals("DROP") && tokens[1].equals("TABLE")) {
						String respuesta = aux.Drop_Table(tokens);
						pw.println(respuesta);
						pw.flush();
						continue;
					}
					if (tokens[0].equals("UPDATE")) {
						String respuesta = aux.Update(tokens);
						pw.println(respuesta);
						pw.flush();
						continue;
					}
					if (tokens[0].equals("DELETE") && tokens[1].equals("FROM")) {
						String respuesta = aux.Delete_From(tokens);
						pw.println(respuesta);
						pw.flush();
						continue;
					}else{
						String eco = consulta;
						pw.println(eco);
						pw.flush();
						continue;
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

/*
CREATE TABLE Cliente (Nombre String, Apellido String, Direccion String, Ciudad String, Id Integer);
INSERT INTO Cliente (Nombre, Apellido, Direccion, Ciudad, Id) VALUES (Jonatan, Morales, Av.609#19, CDMX, 12);
UPDATE Cliente SET Jony Where Nombre = Jonatan; 
INSERT INTO Cliente (Nombre, Apellido, Direccion, Ciudad, Id) VALUES (Anibal, Larios, Balbuena, CDMX, 14);
SELECT (Nombre, Apellido, Direccion, Ciudad, Id) FROM Cliente;
DROP TABLE Cliente;

CREATE TABLE Empresa (Nombre String, Slogan String, Direccion String, Id Float);
INSERT INTO Empresa (Nombre, Slogan, Direccion, Id) VALUES (ESCOM, Alumnos, CDMX, 0.1);
INSERT INTO Empresa (Nombre, Slogan, Direccion, Id) VALUES (lenovo, Computadoras, CDMX, 0.2);
SELECT (Nombre, Slogan, Direccion, Id) FROM Empresa;
DROP TABLE Empresa;

CREATE TABLE Alumno (Nombre String, Apellido String, Direccion String, Id Integer);
INSERT INTO Alumno (Nombre, Apellido, Direccion, Id) VALUES (JonyD, Morales, Av.609#19, 0);
INSERT INTO Alumno (Nombre, Apellido, Direccion, Id) VALUES (Soro, Muguiwara, Balbuena, 1);
SELECT (Nombre, Apellido, Direccion, Id) FROM Alumno;
DROP TABLE Alumno;
*/