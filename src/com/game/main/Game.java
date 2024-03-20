package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
// Runnable must be  implemented for threads to work properly
public class Game extends Canvas implements Runnable {
	
	//Game window width
	public static final int WIDTH = 270;
	//Game window height
	public static final int HEIGHT = WIDTH/14*10;
	//Game window scale factor
	public static final int SCALE = 4;
	//JFrame title
	public static final String TITLE = "SUPER MARIO";
	//Thread allow a program to be executed continuously//Thread needs to be created//
	private Thread  thread;
	private boolean running = false;
	//protects the thread from any other threads or memory interference
	private synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this,"thread");
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
    //necessary method for Runnable to function	must be public
    public void run() {
    	//gets time in nano time
    	long lastTime = System.nanoTime();
    	//gives time in milli seconds
    	long timer = System.currentTimeMillis();
    	
    	double delta = 0.0;
    	double ns = 1000000000.0/60.0;
    	int frames = 0;
    	int ticks = 0;
    	
    	while(running) {
    		long now = System.nanoTime();
    		delta+=(now-lastTime)/ns;
      		lastTime = now;
      		while(delta >= 1) {
      		tick();
      		ticks++;
      		delta--;
      		}
      		render();
      		frames++;
      		if(System.currentTimeMillis() - timer > 1000) {
      			timer += 1000;
      			System.out.println(frames + " Frames Per Second " + ticks + " Updates Per Second" );
      			frames = 0;
      			ticks = 0;
      			
      		}
      	}
    	stop();
		
	}
    //renders graphics what you see on screen
    public void render() {
    	//BufferStrategy gets the images ready to be put on the screen we will use 3
    	//the last buffer strategy puts images onto the second which does the first and finally the screen
    	BufferStrategy bs = getBufferStrategy();
    	if(bs == null) {
    		createBufferStrategy(3);
    		return;
    		
    	}
    	//Below is our graphics object colored Magenta starts top left corner 0,0
    	Graphics g = bs.getDrawGraphics();
    	g.setColor(Color.BLUE);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	g.setColor(Color.RED);
    	g.fillRect(200, 200, getWidth()-400, getHeight()-400);
    	//disposes what has been created onto our bufferStrategy
      	g.dispose();
      	bs.show();
      	
      	
    }
	//tick means update
    public void tick() {
    	
    }
	
	public Game() {
	   Dimension size = new Dimension(WIDTH*SCALE,HEIGHT*SCALE );
	   setPreferredSize(size);
	   setMaximumSize(size);
	   setMinimumSize(size); 
	}
	
	//Main
	public static void main(String [] args) {
		Game game = new Game();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		// frame.pack automatically adjusts the size of the contents
		// within the frame
		frame.pack();
		//Below means we can't resize our games
		frame.setResizable(false);
		//Centers frame in our screen
		frame.setLocationRelativeTo(null);
		//Close window when screen is exited
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Makes the frame visible
		frame.setVisible(true);
		game.start();
		
	}

	
	
	
}