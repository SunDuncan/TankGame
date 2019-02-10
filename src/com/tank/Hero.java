package com.tank;

import java.util.Vector;

public class Hero extends Tank{
	private int type;
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}


	private int speed;
	public Hero(int x,int y) {
		super(x, y);
		speed = 2;
		type = 0;
		this.setDirection(0);
	}
	
	//向上
	public void moveUp() {
		y -= speed;
		if (y < 0) {
			y = 0;
		}
	}
	
	//向左
	public void moveLeft() {
		x -= speed;
		if (x < 5) {
			x = 5;
		}
	}
	
	//向右
	public void moveRight() {
		x += speed;
		if (x > 373) {
			x = 373;
		}
	}
	
	//向下
	public void moveDown() {
		y += speed;
		if (y > 270) {
			y = 270;
		}
	}
	

	//子弹
	//需要连发子弹
	//同步的集合
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;
	public void shotEnemy() {
		switch (this.getDirection()) {
		case 0:
			s = new Shot(x + 8, y-2, this.getDirection());
			ss.add(s);
			break;
		case 1:
			s = new Shot(x - 7, y + 9,this.getDirection());
			ss.add(s);
			break;
		case 2:
			s = new Shot(x + 26, y + 9, this.getDirection());
			ss.add(s);
			break;
		case 3:
			s = new Shot(x + 8 , y + 31, this.getDirection());
			ss.add(s);
			break;
		}
		
		for (int i = 0;i < ss.size(); ++ i) {
			Shot st = ss.get(i);
			Thread t = new Thread(st);
			t.start();
		}
		
	}
}
