package com.tank;

public class Bomb {
	int x;
	int y;
	int speed=1;
	int life = 9;
	boolean isActive = true;	
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void lifeDown() {
		if (life > 0) {
			life -= speed;
		} else {
			isActive = false;
		}
	}
}
