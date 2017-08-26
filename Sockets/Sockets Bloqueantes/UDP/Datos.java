import java.io.Serializable;

class Datos implements Serializable{
	long segmento;
  	long acuse;
  	String id_archivo;
  	byte[] datos;

	public Datos(long s, long a, String id , byte[] d){
		this.segmento = s;
		this.acuse = a;
		this.id_archivo = id;
		this.datos = d;
	}

	public long getSegmento(){
		return this.segmento;
	}

	public long getAcuse(){
		return this.acuse;
	}

	public String getId(){
		return this.id_archivo;
	}

	public byte[] getDatos(){
		return this.datos;
	}
}