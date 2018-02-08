package engine;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.omg.CORBA.PUBLIC_MEMBER;

import model.Player;

public class MainFrame extends JFrame{
	
	public static final int WIDTH = gameUtil.Constants.MainFrameWidth+10;
	public static final int HEIGHT = gameUtil.Constants.MainFrameHeight+10;
	public static final int UNIT = gameUtil.Constants.MainFrameUnit;
	
	public static Gui gui;
	//public static LoadImages loadimage;
	public static Image imgCity;
	public static Image imgTerrain;
	public static Image imgUnits;
	public static GameMap map;
	
	public static enum State{START,PLAYING};
	public static State GameState = State.PLAYING;
	
	
	public int fps;
	public int fpsCounter;
	
	public Controller controller;
	public static View view;
	public static Toolkit toolkit;

	
	public Battle battle = new Battle();
	public static List<Player> players = new ArrayList<Player>();
	
	
	
	public MainFrame() {
		super("Advance War");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUndecorated(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(new Dimension(22*gameUtil.Constants.ScrennBase+6,20*gameUtil.Constants.ScrennBase+12));
		setBounds(0,0,20*gameUtil.Constants.ScrennBase+6,12*gameUtil.Constants.ScrennBase+12);
		//add gui
		gui = new Gui();
		this.add(gui);
		gui.setFocusable(true);
		gui.requestFocusInWindow();
		
		//add players
		players.add(new Player("P1"));
		players.add(new Player("P2"));
		
		controller = new Controller();
		view = new View();
		
		//load images
		this.toolkit = Toolkit.getDefaultToolkit();
		this.imgTerrain = toolkit.getImage(getClass().getResource("/img/"+"Terrain"+".png"));
		this.imgCity = toolkit.getImage(getClass().getResource("/img/"+"Units"+".png"));
		this.imgUnits = toolkit.getImage(getClass().getResource("/img/"+"Units"+".png"));
		
		map = new GameMap();
		controller = new Controller();
		GameRunning(view);
	}
	
	
	public void GameRunning(View view) {
		
		boolean running = true;
		long last = 0;
		long lastCPSTime = 0;
		long lastCPSTime2 = 0;
		//int logics = 0;
		
		while(running) {
			long delay = (System.nanoTime()-last)/1000000;
			delay++;
			last = System.nanoTime();
			/**
			if(System.currentTimeMillis() - lastCPSTime>1000) {
				lastCPSTime = System.currentTimeMillis();
				fpsCounter = fps;
				fps = 0;
			}
			**/
			
			if (System.currentTimeMillis() - lastCPSTime2 > 100) {
				lastCPSTime2 = System.currentTimeMillis();
				
				if(GameState == State.PLAYING) {
					view.MoveView(this);
				}
				gui.repaint();
			}
			
			try {
				Thread.sleep(5);
			}catch(Exception e) {
				System.out.println("Class MainFrame --> GameRuning");
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		System.out.println("xxxx");
		new MainFrame();
	}
	

}
