package com.tutorial.mario.tile;

import java.awt.Graphics;

import com.game.main.Handler;
import com.game.main.Id;

public class Tile {
	
	public int x, y;
	public int width, height;
	
	public boolean solid;
	
	public int velX, velY;
	
	public Id id;
	
	public Handler handler;
    
	public Tile(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.solid = solid;
		this.id = id;
		this.handler = handler;
	}
	//We use Graphics instead of BufferStrategy because BufferStrategy would be laggy
	//since we have already created Graphics Object in the game class, we can use it as
	//this graphics object
	public void render(Graphics g) {
		
	}
	
	public void tick() {
		x += velX;
		y += velY;
	}
	
	public void die() {
		handler.removeTile(this);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Id getId() {
		return id;
	}
	public boolean isSolid() {
		return solid;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	
	
}
