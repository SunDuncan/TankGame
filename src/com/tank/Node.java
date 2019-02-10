package com.tank;

public class Node {
	int x;
	int y;
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

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	int direction;
	
	public Node(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}
