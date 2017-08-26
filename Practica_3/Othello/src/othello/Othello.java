package othello;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.event.*;
import javax.swing.text.html.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.channels.*;
import java.nio.*;
import java.net.*;
import java.util.Iterator;

public class Othello extends JFrame implements ActionListener{
    static String host = "127.0.0.1";
    static int pto = 9999;
    JEditorPane editorPane; 
    static final String WindowTitle = "Othello";
    static final String ABOUTMSG = WindowTitle+"\n\n26-12-2006\njavalc6";
    static GPanel gpanel;
    static JMenuItem hint;
    static boolean helpActive = false;
    static final int Square_L = 33; // length in pixel of a square in the grid
    static final int  Width = 8 * Square_L; // Width of the game board
    static final int  Height = 8 * Square_L; // Width of the game board
 
    static OthelloBoard board;
    static JLabel score_black, score_white;
    JMenu level, theme;
 
    public Othello() {
        super(WindowTitle);
	score_black=new JLabel("2"); // the game start with 2 black pieces
	score_black.setForeground(Color.blue);
	score_black.setFont(new Font("Dialog", Font.BOLD, 16));
	score_white=new JLabel("2"); // the game start with 2 white pieces
	score_white.setForeground(Color.red);
	score_white.setFont(new Font("Dialog", Font.BOLD, 16));
	board = new OthelloBoard();
	gpanel = new GPanel(board, score_black, score_white,"Electric", 3);
 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setupMenuBar();
	gpanel.setMinimumSize(new Dimension(Othello.Width,Othello.Height));
 
	JPanel status = new JPanel();
	status.setLayout(new BorderLayout());
	status.add(score_black, BorderLayout.WEST);
	status.add(score_white, BorderLayout.EAST);
        //status.setMinimumSize(new Dimension(100, 30));
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, gpanel, status);
	splitPane.setOneTouchExpandable(false);
	getContentPane().add(splitPane);
 
	pack();
	setVisible(true);
	setResizable(false);
    }

    void setupMenuBar() {
	JMenuBar menuBar = new JMenuBar();
	menuBar.add(buildGameMenu());
	menuBar.add(buildHelpMenu());
	setJMenuBar(menuBar);	
    }
 
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
	String action = source.getText();
	if (action.equals("Classic")) gpanel.setTheme(action);
	else if (action.equals("Electric")) gpanel.setTheme(action);
        else if (action.equals("Flat")) gpanel.setTheme(action);
    }
 
    protected JMenu buildGameMenu() {
	JMenu game = new JMenu("Game");
	JMenuItem newWin = new JMenuItem("New");
	level = new JMenu("Level");
	theme = new JMenu("Theme");
	JMenuItem undo = new JMenuItem("Undo");
	hint = new JMenuItem("Hint");
	undo.setEnabled(false);
	JMenuItem quit = new JMenuItem("Quit");
        // build level sub-menu
	ActionListener newLevel = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem)(e.getSource());
            gpanel.setLevel(Integer.parseInt(source.getText()));
	}}; 
	ButtonGroup group = new ButtonGroup();
	JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("2");
	group.add(rbMenuItem);
	level.add(rbMenuItem).addActionListener(newLevel);
	rbMenuItem = new JRadioButtonMenuItem("3", true);
	group.add(rbMenuItem);
	level.add(rbMenuItem).addActionListener(newLevel);
	rbMenuItem = new JRadioButtonMenuItem("4");
	group.add(rbMenuItem);
	level.add(rbMenuItem).addActionListener(newLevel);
	rbMenuItem = new JRadioButtonMenuItem("5");
	group.add(rbMenuItem);
	level.add(rbMenuItem).addActionListener(newLevel);
	rbMenuItem = new JRadioButtonMenuItem("6");
	group.add(rbMenuItem);
	level.add(rbMenuItem).addActionListener(newLevel);
        // build theme sub-menu
	group = new ButtonGroup();
	rbMenuItem = new JRadioButtonMenuItem("Classic");
	group.add(rbMenuItem);
	theme.add(rbMenuItem);
	rbMenuItem.addActionListener(this);
	rbMenuItem = new JRadioButtonMenuItem("Electric", true);
	group.add(rbMenuItem);
	theme.add(rbMenuItem);
	rbMenuItem.addActionListener(this);
	rbMenuItem = new JRadioButtonMenuItem("Flat");
	group.add(rbMenuItem);
	theme.add(rbMenuItem);
	rbMenuItem.addActionListener(this);
 
        // Begin "New"
	newWin.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
            gpanel.clear();
            hint.setEnabled(true);
            repaint();
	}});
        // End "New"
 
        // Begin "Quit"
	quit.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }});
        // End "Quit"
 
        // Begin "Hint"
	hint.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
            if (gpanel.active)	{
                Move move = new Move();
                if (board.findMove(TKind.black,gpanel.gameLevel,move))
                    gpanel.setHint(move);
                repaint();
            /*if (board.move(move,TKind.black) != 0) {
                score_black.setText(Integer.toString(board.getCounter(TKind.black)));
		score_white.setText(Integer.toString(board.getCounter(TKind.white)));
		repaint();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run() {
                    Cursor savedCursor = getCursor();
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    gpanel.computerMove();
                    setCursor(savedCursor);		
		}
                });
            }*/
            } else hint.setEnabled(false);
	}});
        // End "Hint"

	game.add(newWin);
	game.addSeparator();
	game.add(undo);
	game.add(hint);
	game.addSeparator();
	game.add(level);
	game.add(theme);
	game.addSeparator();
	game.add(quit);
	return game;
    }
 
 
    protected JMenu buildHelpMenu() {
	JMenu help = new JMenu("Help");
	JMenuItem about = new JMenuItem("About "+WindowTitle+"...");
	JMenuItem openHelp = new JMenuItem("Help Topics...");
 
        // Begin "Help"
	openHelp.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            createEditorPane();
	}});
        // End "Help"
 
        // Begin "About"
	about.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ImageIcon icon = new ImageIcon(Othello.class.getResource("reversi.jpg"));
            JOptionPane.showMessageDialog(null, ABOUTMSG, "About "+WindowTitle,JOptionPane.PLAIN_MESSAGE, icon);
	}});
        // End "About"
 
	help.add(openHelp);
	help.add(about);
 	return help;
    }
 
    protected void createEditorPane() {
        if (helpActive) return;
	editorPane = new JEditorPane(); 
        editorPane.setEditable(false);
	editorPane.addHyperlinkListener(new HyperlinkListener() {
	public void hyperlinkUpdate(HyperlinkEvent e) {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		if (e instanceof HTMLFrameHyperlinkEvent) {
                    ((HTMLDocument)editorPane.getDocument()).processHTMLFrameHyperlinkEvent(
                    (HTMLFrameHyperlinkEvent)e);
		} else {
                    try {
			editorPane.setPage(e.getURL());
                    } catch (java.io.IOException ioe) {
                        System.out.println("IOE: " + ioe);
                    }
		}
            }
	}
	});
        java.net.URL helpURL = Othello.class.getResource("HelpFile.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
		new HelpWindow(editorPane);
            } catch (java.io.IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: HelpFile.html");
        }
        return;
    }
    
    public class HelpWindow extends JFrame{
	public HelpWindow(JEditorPane editorPane) {
            super("Help Window");
            addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Othello.helpActive = false;
                setVisible(false);
            }
            });
 
            JScrollPane editorScrollPane = new JScrollPane(editorPane);
            editorScrollPane.setVerticalScrollBarPolicy(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            getContentPane().add(editorScrollPane);
            setSize(600,400);
            setVisible(true);
            helpActive = true;
	}
    }
 
    public HyperlinkListener createHyperLinkListener1() {
	return new HyperlinkListener() {
	    public void hyperlinkUpdate(HyperlinkEvent e) {
		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		    if (e instanceof HTMLFrameHyperlinkEvent) {
			((HTMLDocument)editorPane.getDocument()).processHTMLFrameHyperlinkEvent(
			(HTMLFrameHyperlinkEvent)e);
		    } else {
			try {
			    editorPane.setPage(e.getURL());
			} catch (java.io.IOException ioe) {
			    System.out.println("IOE: " + ioe);
			}
		    }
		}
	    }
	};
    }
    
    public static void main(String[] args) {

        try{
            Othello app = new Othello();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            SocketChannel cl = SocketChannel.open();
            cl.configureBlocking(false);
            Selector sel = Selector.open();
            cl.connect(new InetSocketAddress(host,pto));
            cl.register(sel,SelectionKey.OP_CONNECT);
           
            while(true){
                sel.select();
               	Iterator<SelectionKey> it = sel.selectedKeys().iterator();
               	while(it.hasNext()){
                    SelectionKey k = (SelectionKey)it.next();
                    it.remove();
                    if(k.isConnectable()){
                        SocketChannel ch = (SocketChannel)k.channel();
                        if(ch.isConnectionPending()){
                            try{
                                ch.finishConnect();
                            }catch(Exception e){e.printStackTrace();}
                        }
                    ch.register(sel, SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    continue;
                    }//if
                    if(k.isWritable()){
                        SocketChannel ch2 = (SocketChannel)k.channel();
                        if(board.gameEnd()){
                            ch2.close();
                            System.exit(0);
                        }else if(gpanel.listo()){
                            int [] aux = gpanel.envia();
                            String en = aux[0]+ " " + aux[1];
                            ByteBuffer b = ByteBuffer.wrap(en.getBytes());
                            ch2.write(b);
                            System.out.println(en);
                            k.interestOps(SelectionKey.OP_READ);
                            gpanel.Nolisto();
                            gpanel.tiraCliente(aux[0], aux[1]);
                            k.interestOps(SelectionKey.OP_READ);
                            continue;
                       }//else
                   }else if(k.isReadable()){
                        SocketChannel ch2 =(SocketChannel)k.channel();
                      	ByteBuffer b = ByteBuffer.allocate(6);
                      	b.clear();
                      	int n = ch2.read(b);
                      	b.flip();
                      	String respuesta = new String(b.array(),0,n);
                        int i = respuesta.charAt(0) - 48;
                        int j = respuesta.charAt(2) - 48;
                        
                        gpanel.tiraMaquina(i, j);
                        if(board.gameEnd()){  	
                            ch2.close();
                            System.exit(0);
                       	}else{
                            
                            k.interestOps(SelectionKey.OP_WRITE);
                        }
                    }
               	}
            }
       	}catch(Exception e){
           	e.printStackTrace();
       	}
    }
}

