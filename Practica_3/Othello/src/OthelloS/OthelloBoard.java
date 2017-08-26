package OthelloS;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
 
class Move {
    int i, j;
    public Move () {
    }
    public Move (int i, int j) {
	this.i = i;
	this.j = j;
    }
};
 
enum TKind {nil ,black , white}; // don't change the order for any reason!
 
public class OthelloBoard {
    TKind [][] board = new TKind[8][8];
    int [] counter = new int[2]; // 0 = black, 1 = white
    boolean PassCounter;
 
    public OthelloBoard() {
        clear();
    }
 
    public TKind get(int i, int j) {
        return board[i][j];
    }
 
    public void set(Move move, TKind player) {
        //System.out.println("i: " + move.i + "  j:" + move.j + "   Jugador: " + player);
	switch (board[move.i][move.j]) {
            case white:  counter[1]--; break;
            case black:  counter[0]--; break;
	}
	board[move.i][move.j]=player;
	switch (player) {
            case white:  counter[1]++; break;
            case black:  counter[0]++; break;
	}
    }
 
    public int getCounter(TKind player) {
	return counter[player.ordinal()-1];
    }
 
    public void clear() {
	for (int i = 0 ; i < 8 ; i++)
            for (int j = 0 ; j < 8 ; j++)
                board[i][j]=TKind.nil;
	board[3][4]=TKind.black;
	board[4][3]=TKind.black;
	board[3][3]=TKind.white;
	board[4][4]=TKind.white;
	counter[0] = 2;
	counter[1] = 2;
	PassCounter = false;
    }
 
    public void println() {
        System.out.print("[");
        for (int i = 0 ; i < 8 ; i++) {
            for (int j = 0 ; j < 8 ; j++)
		System.out.print(board[i][j]+",");
		System.out.println((i == 7? "]":""));
            }
    }
 
    public int move(Move move, TKind kind) {
        //System.out.println("i: " + move.i + "  j:" + move.j + "   Jugador: " + kind);
	return checkBoard(move,kind);
    }
 
    public boolean gameEnd() {
	return counter[0]+counter[1]==64;
    }
 
    private int Check(Move move, int incx, int incy, TKind kind , boolean set)  {
        TKind opponent;
	int x=move.i;
	int y=move.j;
	if (kind == TKind.black) opponent=TKind.white; else opponent=TKind.black;
	int n_inc=0;
	x+=incx; y+=incy;
	while ((x<8) && (x>=0) && (y<8) && (y>=0) && (board[x][y]==opponent)) {
            x+=incx; y+=incy;
            n_inc++;
	}
	if ((n_inc != 0) && (x<8) && (x>=0) && (y<8) && (y>=0) && (board[x][y]==kind)) {
            if (set)
		for (int j = 1 ; j <= n_inc ; j++) {
                    x-=incx; y-=incy;
                     set(new Move(x,y),kind);
                }
        return n_inc;
	}else return 0;
    }
 
    private int checkBoard(Move move, TKind kind) {
	// check increasing x
	int j=Check(move,1,0,kind,true);
	// check decreasing x
	j+=Check(move,-1,0,kind,true);
	// check increasing y
	j+=Check(move,0,1,kind,true);
	// check decreasing y
	j+=Check(move,0,-1,kind,true);
	// check diagonals
	j+=Check(move,1,1,kind,true);
	j+=Check(move,-1,1,kind,true);
	j+=Check(move,1,-1,kind,true);
	j+=Check(move,-1,-1,kind,true);
	if (j != 0) set(move,kind);
    return j;
    }
 
    private boolean isValid(Move move, TKind kind) {
	// check increasing x 
	if (Check(move,1,0,kind,false) != 0) return true;
        // check decreasing x 
	if (Check(move,-1,0,kind,false) != 0) return true;
        // check increasing y 
	if (Check(move,0,1,kind,false) != 0) return true;
	// check decreasing y 
	if (Check(move,0,-1,kind,false) != 0) return true;
	// check diagonals 
	if (Check(move,1,1,kind,false) != 0) return true;
	if (Check(move,-1,1,kind,false) != 0) return true;
	if (Check(move,1,-1,kind,false) != 0) return true;
	if (Check(move,-1,-1,kind,false) != 0) return true;
	return false;
    }
 
   private int strategy(TKind me, TKind opponent) {
        int tstrat=0;
        for (int i = 0 ; i < 8 ; i++)
            if (board[i][0]==opponent)
                tstrat++;
            else if (board[i][0]==me)
                tstrat--;
        for (int i = 0 ; i < 8 ; i++)
            if (board[i][7]==opponent)
                tstrat++;
            else if (board[i][7]==me)
                tstrat--;
        for (int i = 0 ; i < 8 ; i++)
            if (board[0][i]==opponent)
                tstrat++;
            else if (board[0][i]==me)
                tstrat--;
        for (int i = 0 ; i < 8 ; i++)
            if (board[7][i]==opponent)
                tstrat++;
            else if (board[7][i]==me)
                tstrat--;
    return tstrat;
    }
 
    private class resultFindMax {
        int max, nb, nw;
    };
 
    private resultFindMax FindMax(int level, TKind me, TKind opponent)  {
	int min,score,tnb,tnw;
	TKind [][] TempBoard = new TKind[8][8];
	int [] TempCounter = new int[2];
	resultFindMax res = new resultFindMax();
	level--;
	res.nb=counter[0];
	res.nw=counter[1];
	for (int i = 0 ; i < 8 ; i++)
            System.arraycopy(board[i], 0, TempBoard[i], 0, 8);
	System.arraycopy(counter, 0, TempCounter, 0, 2);
	min=10000;  // high value
 
	for (int i = 0 ; i < 8 ; i++)
            for (int j = 0 ; j < 8 ; j++)
		if ((board[i][j] == TKind.nil) && (checkBoard(new Move(i,j),me) != 0)) {
                    if (level != 0) {
			resultFindMax tres = FindMax(level,opponent,me);
                        tnb = tres.nb;
			tnw = tres.nw;
			score = tres.max;
                    }else {
                        tnb=counter[0];tnw=counter[1];
			score=counter[opponent.ordinal()-1]-counter[me.ordinal()-1]+strategy(me, opponent);
                    }if (min > score) {
			min=score;
			res.nb=tnb;
			res.nw=tnw;
                    }
                    for (int k = 0 ; k < 8 ; k++)
			System.arraycopy(TempBoard[k], 0, board[k], 0, 8);
			System.arraycopy(TempCounter, 0, counter, 0, 2);
		}
		res.max = -min;
    return res;
    }
 
    public boolean findMove(TKind player, int llevel, Move move) {
        //System.out.println(player + "   " + llevel + "  " + move);
	TKind [][] TempBoard = new TKind[8][8];
	int [] TempCounter = new int[2];
	int nb,nw,min,n_min;
	boolean found;
	resultFindMax res = new resultFindMax();
	Random random = new Random();
 
	if (counter[0]+counter[1] >= 52 + llevel) {
            llevel=counter[0]+counter[1] - 52;
            if (llevel > 5) llevel=5;
	}
 
	for (int i = 0 ; i < 8 ; i++)
            System.arraycopy(board[i], 0, TempBoard[i], 0, 8);
        System.arraycopy(counter, 0, TempCounter, 0, 2);
        found=false;
        min=10000;  // high value
        n_min=1;
	for (int i = 0 ; i < 8 ; i++)
            for (int j = 0 ; j < 8 ; j++)
		if ((board[i][j] == TKind.nil) && (checkBoard(new Move(i,j),player) != 0)) {
                    if (player == TKind.black)
                        res=FindMax(llevel-1,TKind.white,player);
                    else res = FindMax(llevel-1,TKind.black,player);
                    if ((!found)||(min > res.max)) {
                        min=res.max;
			nw=res.nw;nb=res.nb;
			move.i=i;move.j=j;
                        found=true;
                    }else if (min == res.max) { // RANDOM MOVE GENERATOR
                        n_min++;
			if (random.nextInt(n_min) == 0) {
                            nw=res.nw;nb=res.nb;
                            move.i=i;move.j=j;
                        }
                    }//if found
                    //then PreView(nw,nb);
                    for (int k = 0 ; k < 8 ; k++)
			System.arraycopy(TempBoard[k], 0, board[k], 0, 8);
                    System.arraycopy(TempCounter, 0, counter, 0, 2);
		}
	return found;
	}
 
    public boolean userCanMove(TKind player)  {
    for (int i = 0 ; i < 8 ; i++)
	for (int j = 0 ; j < 8 ; j++)
            if ((board[i][j] == TKind.nil) && isValid(new Move(i,j),player)) return true;
    return false;
    }
}


class GPanel extends JPanel {
    OthelloBoard board;
    int gameLevel;
    ImageIcon button_black, button_white;
    JLabel score_black, score_white;
    String gameTheme;
    Move hint=null;
    boolean inputEnabled, active;
    int [] aux = new int[2];
    int [] aux1 = new int[2]; 
    boolean listo = false;
    boolean llego = false;
    int iM;
    int jM;
    int iE;
    int jE;
 
    public GPanel (OthelloBoard board, JLabel score_black, JLabel score_white, String theme, int level) {
	super();
	this.board = board;
	this.score_black = score_black;
	this.score_white = score_white;
	gameLevel = level;
	setTheme(theme);
	inputEnabled = true;
	active = true;
    }
 
    public void setTheme(String gameTheme)  {
	hint = null;
	this.gameTheme = gameTheme;
	if (gameTheme.equals("Classic")) {
            button_black = new ImageIcon(OthelloS.class.getResource("button_black.jpg"));
            button_white = new ImageIcon(OthelloS.class.getResource("button_white.jpg"));
            setBackground(Color.green);
	}else if (gameTheme.equals("Electric")) {
            button_black = new ImageIcon(OthelloS.class.getResource("button_blu.jpg"));
            button_white = new ImageIcon(OthelloS.class.getResource("button_red.jpg"));
            setBackground(Color.white);
	}else{
            gameTheme = "Flat"; // default theme "Flat"
            setBackground(Color.green); 
	}
	repaint();
    }
 
    public void setLevel(int level) {
        if ((level > 1) && (level < 7)) gameLevel = level;
    }
 
    public void drawPanel(Graphics g) {
        //int currentWidth = getWidth();
        //int currentHeight = getHeight();
	for (int i = 1 ; i < 8 ; i++) {
            g.drawLine(i * OthelloS.Square_L, 0, i * OthelloS.Square_L, OthelloS.Height);
	}
	g.drawLine(OthelloS.Width, 0, OthelloS.Width, OthelloS.Height);
	for (int i = 1 ; i < 8 ; i++) {
            g.drawLine(0, i * OthelloS.Square_L, OthelloS.Width, i * OthelloS.Square_L);
	}
	g.drawLine(0, OthelloS.Height, OthelloS.Width, OthelloS.Height);
	for (int i = 0 ; i < 8 ; i++)
            for (int j = 0 ; j < 8 ; j++)
		switch (board.get(i,j)) {
                    
                    case white:  
                        if (gameTheme.equals("Flat")){	
                            g.setColor(Color.white);
                            g.fillOval(1 + i * OthelloS.Square_L, 1 + j * OthelloS.Square_L, OthelloS.Square_L-1, OthelloS.Square_L-1);
                        }else 
                            g.drawImage(button_white.getImage(), 1 + i * OthelloS.Square_L, 1 + j * OthelloS.Square_L, this); 
                    break;
                    
                    case black:  
                        if (gameTheme.equals("Flat")){	
                            g.setColor(Color.black);
                            g.fillOval(1 + i * OthelloS.Square_L, 1 + j * OthelloS.Square_L, OthelloS.Square_L-1, OthelloS.Square_L-1);
			}else 
                            g.drawImage(button_black.getImage(), 1 + i * OthelloS.Square_L, 1 + j * OthelloS.Square_L, this); 
                    break;
		}
		if (hint != null) {
                    g.setColor(Color.darkGray);
                    g.drawOval(hint.i * OthelloS.Square_L+3, hint.j * OthelloS.Square_L+3, OthelloS.Square_L-6, OthelloS.Square_L-6);
		}
    }	
 
    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	drawPanel(g);
    }
 
    public Dimension getPreferredSize() {
	return new Dimension(OthelloS.Width,OthelloS.Height);
    }
    
    public int [] envia(){
        return aux;
    }
    public boolean listo(){
        return listo; 
    }
    
    public void Nolisto(){
        listo = false;
    }
    
    public boolean llego(){
        return llego;
    }
    
    public void setLlego(boolean t){
        llego = t;
    }
    
    public void SetCliente(int i, int j) {
        iE = i;
        jE = j;
        hint = null;
        if (llego() && (board.move(new Move(iE,jE),TKind.black) != 0)) {
            score_black.setText(Integer.toString(board.getCounter(TKind.black)));
            score_white.setText(Integer.toString(board.getCounter(TKind.white)));
            repaint();
            computerMove();
            setLlego(false);
        }
    }
 
    public void showWinner() {
        inputEnabled = false;
	active = false;
	if (board.counter[0] > board.counter[1])
            JOptionPane.showMessageDialog(this, "You win!","Othello",JOptionPane.INFORMATION_MESSAGE);
	else if (board.counter[0] < board.counter[1])
            JOptionPane.showMessageDialog(this, "I win!","Othello",JOptionPane.INFORMATION_MESSAGE);
	else JOptionPane.showMessageDialog(this, "Drawn!","Othello",JOptionPane.INFORMATION_MESSAGE);
    }
 
    public void setHint(Move hint) {
        //System.out.println(hint);
	this.hint = hint;
    }
 
    public void clear() {
        board.clear();
	score_black.setText(Integer.toString(board.getCounter(TKind.black)));
	score_white.setText(Integer.toString(board.getCounter(TKind.white)));
	inputEnabled = true;
	active = true;
    }
    
    public void setMaquina(int i, int j){
        aux1[0] = i;
        aux1[1] = j;
    }
    
    public int [] recibe(){
     return aux1;
    }
    
    public void computerMove() {
        if (board.gameEnd()) {
            showWinner();
            return;
	}else{
           
        Move move = new Move();
	if (board.findMove(TKind.white,gameLevel,move)) {
            //System.out.println("Maquina: " +move.i +"   "+ move.j);
            setMaquina(move.i, move.j);
            board.move(move,TKind.white);
            score_black.setText(Integer.toString(board.getCounter(TKind.black)));
            score_white.setText(Integer.toString(board.getCounter(TKind.white)));
            repaint();
            if (board.gameEnd()) showWinner();
            else if (!board.userCanMove(TKind.black)) {
                JOptionPane.showMessageDialog(this, "You pass...","Othello",JOptionPane.INFORMATION_MESSAGE);
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
                    public void run() {computerMove();}});
            }
	}else if (board.userCanMove(TKind.black))
            JOptionPane.showMessageDialog(this, "I pass...","Othello",JOptionPane.INFORMATION_MESSAGE);
        else showWinner();
        }
    }
    
    
 
    public void mouseEntered(MouseEvent e) {
        // generato quando il mouse entra nella finestra
    }
 
    public void mouseExited(MouseEvent e) {
        // generato quando il mouse esce dalla finestra
    }
 
    public void mousePressed(MouseEvent e) {
        // generato nell'istante in cui il mouse viene premuto
    }
 
    public void mouseReleased(MouseEvent e) {
        // generato quando il mouse viene rilasciato, anche a seguito di click
    }
};