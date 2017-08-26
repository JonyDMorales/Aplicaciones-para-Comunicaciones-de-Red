public class Principal{
	public static void main(String[] args) {
		Thread hiloA = new Servidor();
		Thread hiloB = new Servidor1();
		hiloA.start();
		hiloB.start();
	}
}