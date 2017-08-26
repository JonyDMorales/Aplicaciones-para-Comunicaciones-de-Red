public class Principal{
	public static void main(String[] args) {
		Thread hiloA = new Sopa_S1(); /*Tema*/
		Thread hiloB = new Sopa_S2(); /*Palabras Encontradas*/
		Thread hiloC = new Sopa_S3(); /*Ganador*/
		hiloA.start();
		hiloB.start();
		hiloC.start();
	}
}