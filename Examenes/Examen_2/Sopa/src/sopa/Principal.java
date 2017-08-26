package sopa;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.net.*;

public class Principal extends javax.swing.JFrame {

    String [][]Tema = {{"MANGO","FRESA","UVA","GUAYABA","MELON","LIMON","PLATANO","NARANJA","MANDARINA","DURAZNO"},
                       {"ALEMANIA","FRANCIA","COLOMBIA","PERU","PANAMA","CUBA","CANADA","HUNGRIA","HOLANDA","ITALIA"},
                       {"MANGO","STRAWBERRY","GRAPE","GUABA","MELON","LEMON","BANANA","ORANGE","TANGERINE","PEACH"},
                       {"GERMANY","FRANCE","COLOMBIA","PERU","PANAMA","CUBA","CANADA","HUNGARY","NETHERLANDS","ITALY"},
                       {"GONAM","SAFRE","VAU","YAGUABA","LEMON","MILON","PLATONA","JARANNA","RINADAMAN","RAZNODU"},
                       {"MANIALEA","CIAFRAN","LOMCOBIA","RUPE","NAPAMA","BACU","NACADA","GRIAHUN","LANHODA","LIATAI"}};
    int pos = 0;
    int iniciox[];
    int inicioy[];
    JLabel letra[][];
    boolean direccion[];
    boolean gano;
    String palabra[] =  new String[10];
    JLabel palabras[];
    
    MulticastSocket s;
    InetAddress gpo;
    DatagramPacket p;
    int pto;
    
    public Principal() {
        initComponents();
        txtTema.setEditable(false);  
        txtTiempo.setEditable(false);
        txtPalabras.setEditable(false);
        txtLongitud.setEditable(false);
    }
    
    public void Jugar(){
        gano = false;
        iniciox = new int[20];//crea un arreglo de enteros para guadar las posiciones de las palabras en x
        inicioy = new int[20];//crea un arreglo de enteros para guadar las posiciones de las palabras en y
        direccion = new boolean[10];//crea un arreglo de enteros para guadar las direcion de las palabras ya sea hacia alante o hacia tras
        celdasdeletras();
        colocarpalabras();
        llenarespaciosvacios();
        txtPalabras.setText("0");
        txtLongitud.setText("0");
    }
    
    public void Coloca_Tema(String op){
        String delims = "[ ,();]+";
	String[] tokens = op.split(delims);
        txtTema.setText(op);
        if(tokens[0].equals("Concepto")){
            if(tokens[1].equals("Frutas")){
                palabra = Tema[0];
                p1.setText(Tema[0][0]);
                p2.setText(Tema[0][1]);
                p3.setText(Tema[0][2]);
                p4.setText(Tema[0][3]);
                p5.setText(Tema[0][4]);
                p6.setText(Tema[0][5]);
                p7.setText(Tema[0][6]);
                p8.setText(Tema[0][7]);
                p9.setText(Tema[0][8]);
                p10.setText(Tema[0][9]);
            }else if(tokens[1].equals("Paises")){
                palabra = Tema[1];
                p1.setText(Tema[1][0]);
                p2.setText(Tema[1][1]);
                p3.setText(Tema[1][2]);
                p4.setText(Tema[1][3]);
                p5.setText(Tema[1][4]);
                p6.setText(Tema[1][5]);
                p7.setText(Tema[1][6]);
                p8.setText(Tema[1][7]);
                p9.setText(Tema[1][8]);
                p10.setText(Tema[1][9]);
            }    
        }else if (tokens[0].equals("Traduccion")){
            if(tokens[1].equals("Frutas")){
                palabra = Tema[2];
                p1.setText(Tema[0][0]);
                p2.setText(Tema[0][1]);
                p3.setText(Tema[0][2]);
                p4.setText(Tema[0][3]);
                p5.setText(Tema[0][4]);
                p6.setText(Tema[0][5]);
                p7.setText(Tema[0][6]);
                p8.setText(Tema[0][7]);
                p9.setText(Tema[0][8]);
                p10.setText(Tema[0][9]);
            }else if(tokens[1].equals("Paises")){
                palabra = Tema[3];
                p1.setText(Tema[1][0]);
                p2.setText(Tema[1][1]);
                p3.setText(Tema[1][2]);
                p4.setText(Tema[1][3]);
                p5.setText(Tema[1][4]);
                p6.setText(Tema[1][5]);
                p7.setText(Tema[1][6]);
                p8.setText(Tema[1][7]);
                p9.setText(Tema[1][8]);
                p10.setText(Tema[1][9]);
            }    
        }else if (tokens[0].equals("Anagrama")){
            if(tokens[1].equals("Frutas")){
                palabra = Tema[0];
                p1.setText(Tema[4][0]);
                p2.setText(Tema[4][1]);
                p3.setText(Tema[4][2]);
                p4.setText(Tema[4][3]);
                p5.setText(Tema[4][4]);
                p6.setText(Tema[4][5]);
                p7.setText(Tema[4][6]);
                p8.setText(Tema[4][7]);
                p9.setText(Tema[4][8]);
                p10.setText(Tema[4][9]);
            }else if(tokens[1].equals("Paises")){
                palabra = Tema[1];
                p1.setText(Tema[5][0]);
                p2.setText(Tema[5][1]);
                p3.setText(Tema[5][2]);
                p4.setText(Tema[5][3]);
                p5.setText(Tema[5][4]);
                p6.setText(Tema[5][5]);
                p7.setText(Tema[5][6]);
                p8.setText(Tema[5][7]);
                p9.setText(Tema[5][8]);
                p10.setText(Tema[5][9]);
            }
        }    
        palabras = new JLabel[]{p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};
    }
    
    public void llenarespaciosvacios() {
        String abc[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (letra[i][j].getText().equals("")) {//si la casilla esta vacia pongale una letra del arreglo abc
                    letra[i][j].setText(abc[(int)(random.nextDouble()*abc.length-1)]);//aqui pone la letra
                }
            }
        }
    }
    
    public int[] NumerosSinRepeticiones(int repeticiones) {
        int numeros[] = new int[repeticiones];
        for (int i = 0; i < repeticiones; i++) {
            numeros[i]=-1;
        }
        Random random = new Random();
        boolean aux ;//informa si la fila esta o no repetida
        int numero = 0;
        for (int x = 0; x < repeticiones; x++) {
            aux = true;
            while(aux) {  
                aux = false;
                numero = (int)(random.nextDouble()*11-1);
                for (int j = 0; j < numeros.length; j++) {
                    if (numeros[j] == numero) {
                        aux=true;
                        break;
                    }
                }
            }
            numeros[x] = numero;
        }
        return numeros;
    }
    
    public boolean  tacharLetra(int x,int y,int tamaño,boolean direccion) {
        boolean respuesta = true;
        if (direccion) {
            for (int i = y; i < tamaño + y; i++) {
                if (letra[x][i].getBackground().equals(Color.WHITE)) {
                    respuesta = false;
                    break;
                }
            }
        }else{
            for (int j = y; j >y-tamaño; j--) {
                if (letra[x][j].getBackground().equals(Color.WHITE)) {
                    respuesta=false;
                    break;
                }
            }
        }
        return respuesta;
    }
    
    public void tiempo(String tmp){
        txtTiempo.setText(tmp);
    }
    
    public void fin(){
        this.setVisible(false);
        JOptionPane.showMessageDialog(null,"Game Over","Fin de juego",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void se_encontro(String aux){
        for (int i = 0; i < 10; i++){
            String pal = new String(palabra[i].getBytes(), StandardCharsets.UTF_8);
            if(pal.equals(aux)){
                palabras[i].setText("<html><body><s>"+palabras[i].getText()+"</s></body></html>");//tacha la palabra
                JOptionPane.showMessageDialog(null,aux,"Se encontro palabra",JOptionPane.INFORMATION_MESSAGE);
            }     
        }
    }
    
    public void tachar() {
        int cont;
        int lon;
        try{
            pto = 9000;
            s = new MulticastSocket(pto);
            s.setTimeToLive(255);
            
            gpo = InetAddress.getByName("227.1.1.1");
            s.joinGroup(gpo);
            for (int i = 0; i < 10; i++){
                if (!palabras[i].getText().substring(0, 1).equals("<")) {
                    if (tacharLetra(iniciox[i],inicioy[i],palabras[i].getText().length(),direccion[i])){//pregunta si hay una palabra encontrada
                        byte[] b = palabras[i].getText().getBytes();
                        p = new DatagramPacket(b, b.length, gpo, pto);
                        s.send(p);
                        palabras[i].setText("<html><body><s>"+palabras[i].getText()+"</s></body></html>");//tacha la palabra
                        cont = Integer.parseInt(txtPalabras.getText());
                        cont ++;
                        txtPalabras.setText(String.valueOf(cont));
                        lon = Integer.parseInt(txtLongitud.getText());
                        lon += (palabras[i].getText().length() - 33);
                        txtLongitud.setText(String.valueOf(lon));
                        break;
                    }
                }
            }
            boolean aux = true;//ayuda para saber si ya todas las palabras estan tachadas
        
            for (int i = 0; i < letra.length; i++){
                if (palabras[i].getText().substring(0, 1).equals("<")) {
                    //System.out.println(palabras[i].getText().substring(13, 14));
                }else{
                    aux = false;
                    break;
                }
            }if (aux){
                JOptionPane.showMessageDialog(null,"Ganador","Haz Ganado",JOptionPane.INFORMATION_MESSAGE);
            }else{
                //JOptionPane.showMessageDialog(null,"Perdiste","Haz Perdido",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception e){
            e.printStackTrace();
  	}
    }
    
    public void presioneClic(java.awt.event.MouseEvent evt) {
        if (!gano) {//verifica si gano el juego
            if (evt.getComponent().getBackground().equals(Color.WHITE)){//verifica si la casilla esta de color blanco
                evt.getComponent().setBackground(new java.awt.Color(204, 209, 209));//si esta de color blanco la pone de color azul
                tachar();
            }else if(evt.getComponent().getName().equals("")){//pregunta si la casilla no tiene una letra de alguna palabra
                evt.getComponent().setBackground(Color.WHITE);//pone la casilla de color blanco
            }
        }
    }
    
    public void celdasdeletras(){
        letra = new JLabel[20][20];//crea la matriz de celdas donde va cada letra
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++) {
                letra[i][j] = new JLabel("", javax.swing.SwingConstants.CENTER);//crea la casilla la vacia y con una alineacion centrada
                letra[i][j].setName("");//le pone un nombre a la casilla en este caso no le pone ninguno
                letra[i][j].setBackground(Color.WHITE);//coloca la casilla de color blanco
                letra[i][j].setFont(new java.awt.Font("Segoe UI Symbol", 1, 10)); // asigna el tipo y el tamaño de la letra
                letra[i][j].setForeground(new java.awt.Color(0, 5, 2));
                letra[i][j].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                letra[i][j].setOpaque(true);//esto es para que se pueda ver el color de la casilla o cajonsito donde va la letra
                letra[i][j].setBorder(new javax.swing.border.LineBorder(Color.BLACK, 1));//pone a la casiilla en borde con una linea negra
                letra[i][j].addMouseListener(new java.awt.event.MouseAdapter() {//pone a la casilla a la escucha del mouse para saber cuando se esta dando clic
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        presioneClic(evt);//llama al metodo que debe ejecutarse cuando se da clic
                    }
                });
                Sopa_de_letra.add(letra[i][j]);//coloca la pasilla en el panel Sopa_de_letra
            }
        }
    }
    
    public void colocarpalabras() {
        Random random = new Random();//estemetodo ayuda a crear numeros aleatorios
        int iniciax = 0;//posicion x donde inicia la palabra
        int iniciay;//posicion y donde inicia la palabra
        int unico[] = NumerosSinRepeticiones(10);//evita que en una fila se generen mas de una vez
        iniciox = unico;
        for (int i = 0; i < palabra.length; i++) {
            if (palabra[i].length() < 14) {
                iniciax = unico[i];
                iniciay = (int)(random.nextDouble()*14-1);
                int estrae = 0;//ayuda para estraer las letras de la palabra
                if (iniciay + palabra[i].length() < 14) {
                    for (int j = iniciay; j < iniciay + palabra[i].length(); j++) {
                        letra[iniciax][j].setText(palabra[i].substring(estrae, estrae+1));//estrae una letra de la palabra
                        letra[iniciax][j].setName("1");//pone el nombre a la casilla para que se sepa que hay va una letra de una palabra
                        estrae++;//esto es para que se estraiga la siguiente letra de la palabra
                        inicioy[i]=iniciay;
                        direccion[i] = true;
                    }
                } else if (iniciay-palabra[i].length()>0){
                    for (int j = iniciay; j >iniciay-palabra[i].length() ; j--) {
                        letra[iniciax][j].setText(palabra[i].substring(estrae, estrae+1));
                        letra[iniciax][j].setName("1");
                        estrae++;
                        inicioy[i]=iniciay;
                        direccion[i] = false;
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        Sopa_de_letra = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTema = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPalabras = new javax.swing.JTextField();
        txtLongitud = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        p2 = new javax.swing.JLabel();
        p1 = new javax.swing.JLabel();
        p4 = new javax.swing.JLabel();
        p3 = new javax.swing.JLabel();
        p6 = new javax.swing.JLabel();
        p5 = new javax.swing.JLabel();
        p8 = new javax.swing.JLabel();
        p7 = new javax.swing.JLabel();
        p9 = new javax.swing.JLabel();
        p10 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Sopa_de_letra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Sopa_de_letra.setLayout(new java.awt.GridLayout(20, 20));

        jLabel1.setText("Tema:");

        jLabel2.setText("Tiempo:");

        jLabel3.setText("Puntuación:");

        jLabel4.setText("Palabras:");

        jLabel5.setText("Longitud de palabras:");

        jPanel2.setBackground(new java.awt.Color(74, 74, 116));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        p2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p2.setOpaque(true);

        p1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p1.setOpaque(true);

        p4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4.setText(" ");
        p4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p4.setOpaque(true);

        p3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3.setText(" ");
        p3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p3.setOpaque(true);

        p6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p6.setText(" ");
        p6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p6.setOpaque(true);

        p5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p5.setText(" ");
        p5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p5.setOpaque(true);

        p8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p8.setText(" ");
        p8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p8.setOpaque(true);

        p7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p7.setText(" ");
        p7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p7.setOpaque(true);

        p9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p9.setText(" ");
        p9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p9.setOpaque(true);

        p10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p10.setText(" ");
        p10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        p10.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p10, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p7, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(p1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p10)
                .addGap(104, 104, 104))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Sopa_de_letra, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtPalabras, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                                    .addComponent(txtLongitud))))))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Sopa_de_letra, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPalabras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtLongitud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Sopa_de_letra;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel p1;
    private javax.swing.JLabel p10;
    private javax.swing.JLabel p2;
    private javax.swing.JLabel p3;
    private javax.swing.JLabel p4;
    private javax.swing.JLabel p5;
    private javax.swing.JLabel p6;
    private javax.swing.JLabel p7;
    private javax.swing.JLabel p8;
    private javax.swing.JLabel p9;
    private javax.swing.JTextField txtLongitud;
    private javax.swing.JTextField txtPalabras;
    private javax.swing.JTextField txtTema;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
