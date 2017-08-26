class C1 extends Thread{
	static int cont = 0;
	public void run(){
		c = cont++;
	}
}

class C2 extends Runnable{
	public void run(){

	}
}

class C3{
	public static void main(String[] args) {
		try{
			C1 h1 = new C1();
			C2 h2 = new C2();
			Thread h2 = new Thread(t2);
			Thread h3 = new Thread(t2);
			h1.start();
			h2,start();
			
		}catch(){}
	}
}