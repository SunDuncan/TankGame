package com.tank;

/**
 * 
 * @author Duncan
 * 子弹类
 */
public class Shot implements Runnable{
	int x;
	int y;
	int direction;
	int speed;
	boolean isDead = false;
	public Shot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.speed = 2;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(50);
			}catch (Exception e) {
				e.getStackTrace();
			}
			switch(direction) {
			case 0:
				y -= speed;
				break;
			case 1:
				x -= speed;
				break;
			case 2:
				x += speed;
				break;
			case 3:
				y += speed;
				break;
			}
					
			//子弹何时死亡
			//判断该子弹是否碰到边缘
			if(x < 0 || x > 400 || y < 0 || y > 300) {
				isDead = true;
				break;
			}
		}
	}
}

