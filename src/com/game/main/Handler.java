package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

import com.tutorial.mario.entity.Entity;
import com.tutorial.mario.tile.Tile;

public class Handler {
    //LinkedList is a list of Entities that we can add and remove entities at any time
	//helpful to render all of our entities (all at the same time)
	//Inside what is the LinkedList<of??>
	public LinkedList<Entity> entity = new LinkedList<Entity>();
	public LinkedList<Tile> tile = new LinkedList<Tile>();
	
	public void render(Graphics g) {
		//for every entity in the list render
		for(Entity en:entity) {
			en.render(g);
			}
		//for every tile in the list render
		for(Tile ti:tile) {
			ti.render(g);
			}
	}
	public void tick() {
		for(Entity en:entity) {
			en.tick();
			}
		for(Tile ti:tile) {
			ti.tick();
			}
		
	}
	public void addEntity(Entity en) {
		//adds the entity to our linkedlist
		entity.add(en);
	}
	public void removeEntity(Entity en){
		entity.remove(en);
	}
	public void addTile(Tile ti) {
		//adds the entity to our linkedlist
		tile.add(ti);
	}
	public void removeTile(Tile ti){
		tile.remove(ti);
	}
	
}
