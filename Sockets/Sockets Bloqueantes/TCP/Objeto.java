import java.io.Serializable;

class Objeto implements Serializable{
	String nombre;
	String apaterno;
	String amaterno;
	short edad;
	int clave;
	String depto;

	public Objeto(String n, String p, String m, short e, int c, String d){
		this.nombre = n;
		this.apaterno = p;
		this.amaterno = m;
		this.edad = e;
		this.clave = c;
		this.depto = d;
	}

	public String getNombre(){
		return this.nombre;
	}

	public String getApaterno(){
		return this.apaterno;
	}

	public String getAmaterno(){
		return this.amaterno;
	}

	public short getEdad(){
		return this.edad;
	}

	public int getClave(){
		return this.clave;
	}

	public String getDepto(){
		return this.depto;
	}
}