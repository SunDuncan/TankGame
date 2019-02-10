package com.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * 
 * @author Duncan
 * ���ܣ� ̹����Ϸ
 * 1. ����̹��
 * 2. �ҵ�̹�˿������������ƶ�
 * 3. ���Է����ӵ����ӵ����������5��
 * 4. ���ҵ�̹�˻��е���̹��ʱ�����˾���ʧ����ը��Ч����
 * 5. �ұ����к���ʾ��ըЧ��
 * 6. ��ֹ����̹�����ƶ���ʱ����ص��˶�
 * 7. ���Էֹ�
 * 8. ����������Ϸ��ʱ����ͣ�ͼ���
 * 9. ���Լ�¼��ҵĳɼ�
 * 	9.1 ��ʾ��Ϣ
 * 10. java��β��������ļ�
 */
public class MyPanel extends JPanel implements KeyListener,Runnable{
	//����дpaint
		Hero hero;
		//����һ���ҵ�̹��
		private int direction;
		private int type;
		private int speed;
		private Image image1 = null;
		private Image image2 = null;
		private Image image3 = null;
		private Vector <Bomb> bs = null;
		private Vector<Enemy> enemys = null;
		private String flag1;
		private int size = 3;
		public Vector<Enemy> getEnemys() {
			return enemys;
		}

		public void setEnemys(Vector<Enemy> enemys) {
			this.enemys = enemys;
		}

		//�ȶ���һ������̹��
		private Enemy enemy;
		public MyPanel (String flag) {
			//�ָ�����
			this.flag1 = flag;
			if (flag1.equals("con")) {
				Recorder.getRecording();
				Vector<Node> ns = new Recorder().getNodes();
				enemys = new Vector<Enemy>();
				bs = new Vector<Bomb>();
				hero = new Hero(150,100);
				for (int i = 0;i < ns.size(); ++i) {
					Node n = ns.get(i);
					enemy = new Enemy(n.getX(), n.getY());
					enemy.setDirection(n.getDirection());	
					System.out.println("x = " + n.getX() + " y = " + n.getY() + " dir = " + n.getDirection());
					Thread td = new Thread(enemy);
					td.start();	
					//��̹������ӵ�
					enemys.add(enemy);
					while (enemy.getEs().size() < 1) {
						enemy.shotHero();
					}	
				}
			} else {
				enemys = new Vector<Enemy>();
				bs = new Vector<Bomb>();
				hero = new Hero(150,100);
				for (int i = 0;i < size; ++i) {
					enemy = new Enemy((i + 1) * 50,0);
					Thread td = new Thread(enemy);
					td.start();	
					//��̹������ӵ�
					enemys.add(enemy);
					while (enemy.getEs().size() < 1) {
						enemy.shotHero();
					}	
				}
				
			}	
			
			try {
				image1 = ImageIO.read(new File("bomb_1.png"));
				image2 = ImageIO.read(new File("bomb_2.png"));
				image3 = ImageIO.read(new File("bomb_3.png"));
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			Audio audio = new Audio("E:\\Duncan\\java_project_file\\tank.mp3");
			audio.start();
		}
		
		public void paint(Graphics g) {
			super.paint(g);
			g.fillRect(0, 0, 400, 300);
			if (hero.getIsActive()) {
				this.drawTank(hero.getX(),hero.getY(), g, hero.getDirection(), hero.getType());
			}
			
			
			//������ʾ��Ϣ��̹��
			
			for (int i = 0;i < hero.ss.size(); ++i) {
				Shot myshot = hero.ss.get(i);
				if (myshot != null && myshot.isDead == false) {
					g.fill3DRect(myshot.x, myshot.y, 2,2,false);
				}
				
				if (myshot.isDead == true) {
					hero.ss.remove(myshot);			
				}
			}	
			
			for (int i = 0;i < bs.size(); ++i) {
				Bomb b = bs.get(i);
				g.drawImage(image1, b.x, b.y, 30, 30, this);
				if (b.life > 6) {
					g.drawImage(image2, b.x, b.y, 30, 30, this);
				} else if (b.life > 3) {
					g.drawImage(image3, b.x, b.y, 30, 30, this);
				} else if (b.life > 0){
					g.drawImage(image1, b.x, b.y, 30, 30, this);
				} 
				b.lifeDown();
				
				if (b.life <= 0) {
					bs.remove(b);
				}
			}
			
			//�������˵�̹�ˣ��ڿ�ʼ��ʱ��Ȼ��ֻ��һ���ӵ�
			for (int i = 0;i < enemys.size(); ++i) {
				Enemy e = enemys.get(i);
				if (e.getIsActive()) {
					e.setMypanelTanks(enemys);
					this.drawTank(e.getX(), e.getY(), g, e.getDirection(), e.getType());
					for (int j = 0;j < e.getEs().size(); ++j) {
						Shot myshot = e.getEs().get(j);
						if (myshot != null && myshot.isDead == false) {
							g.fill3DRect(myshot.x, myshot.y, 2,2,false);
						}
						
						if (myshot.isDead == true) {
							e.getEs().remove(myshot);
							e.shotHero();
						}
					}
				}
			}	
			
			this.showInfo(g);
		}
		
		
		//������ʾ��Ϣ
		public void showInfo(Graphics g) {
			this.drawTank(110, 310, g, 0, 1);
			g.setColor(Color.black);
			g.drawString(Recorder.getEnNum() + "", 130, 330);
			this.drawTank(150, 310, g, 0, 0);
			g.setColor(Color.black);
			g.drawString(Recorder.getMyLife() + "", 170, 330);
			
			g.setColor(Color.BLACK);
			Font f = new Font("����", Font.BOLD, 20);
			g.setFont(f);
			g.drawString("�����ܳɼ�Ϊ��", 420, 30);
			this.drawTank(430, 60, g, 0, 1);
			g.setColor(Color.BLACK);
			g.drawString(Recorder.getAllEnemy() + "", 460, 80);
		}
		
		//����̹�˵ĺ���
		public void drawTank(int x,int y, Graphics g, int direction, int type) {
			switch(type) {
			case 0:
				g.setColor(Color.CYAN);
				break;
			case 1:
				g.setColor(Color.BLUE);
				break;
			}
			
			//�жϷ���
			switch(direction) {
			case 0:
				//����
				//�����ҵ�̹�ˣ���ʱ�ٷ�װ��һ��������
				//1.������ߵľ���
				g.fill3DRect(x, y, 5, 30,false);
				//2.�����ұ߾���
				g.fill3DRect(x + 15, y, 5, 30,false);
				//3.�����м����
				g.fill3DRect(x + 5, y + 5, 10, 20,false);		
				//4.����Բ��
				g.fillOval(x+5, y + 10, 8, 8);
				//����ֱ��
				g.drawLine(x + 9, y + 15, x + 9,y);
				break;
			case 1:
				//����
				//1.��������ľ���
				g.fill3DRect(x - 5, y, 30, 5,false);
				//2.�����м�ľ���
				g.fill3DRect(x, y + 5, 20, 10, false);
				//��������ľ���
				g.fill3DRect(x - 5, y + 15, 30, 5, false);
				//����Բ��
				g.fillOval(x + 5, y + 5, 8, 8);
				//ǹ����
				g.drawLine(x + 10, y + 9, x - 5, y + 9);
				break;
			case 2:
				//����
				//1.��������ľ���
				g.fill3DRect(x - 5, y, 30, 5,false);
				//2.�����м�ľ���
				g.fill3DRect(x, y + 5, 20, 10, false);
				//��������ľ���
				g.fill3DRect(x - 5, y + 15, 30, 5, false);
				//����Բ��
				g.fillOval(x + 7, y + 5, 8, 8);
				//ǹ����
				g.drawLine(x + 10, y + 9, x + 25, y + 9);
				break;
			case 3:
				//����
				//�����ҵ�̹�ˣ���ʱ�ٷ�װ��һ��������
				//1.������ߵľ���
				g.fill3DRect(x, y, 5, 30,false);
				//2.�����ұ߾���
				g.fill3DRect(x + 15, y, 5, 30,false);
				//3.�����м����
				g.fill3DRect(x + 5, y + 5, 10, 20,false);		
				//4.����Բ��
				g.fillOval(x+5, y + 10, 8, 8);
				//����ֱ��
				g.drawLine(x + 9, y + 15, x + 9,y + 30);
				break;
			}
		}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					hero.setDirection(0);
					hero.moveUp();
					
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					hero.setDirection(1);
					hero.moveLeft();
					
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					hero.setDirection(2);
					hero.moveRight();
					
				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					hero.setDirection(3);
					hero.moveDown();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_J) {
					if (hero.ss.size() < 5) {
						hero.shotEnemy();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			public boolean isHitTank(Shot s,Tank t) {
				boolean flag = false;
				switch(t.getDirection()) {
				case 0:
				case 3:
						if (s.x >= t.x && s.x <= t.x + 20 && s.y >= t.y && s.y <= t.y + 30) {
							s.isDead = true;
							t.setIsActive(false);
							flag = true;
							Bomb b = new Bomb(t.x,t.y);
							bs.add(b);
						}
					break;
				case 1:
				case 2:
						if (s.x >= t.x && s.x <= t.x + 30 && s.y >= t.y && s.y <= t.y + 20) {
							s.isDead = true;
							t.setIsActive(false);
							flag = true;
							Bomb b = new Bomb(t.x, t.y);
							bs.add(b);
						}
					break;
				}
				
				return flag;
			}
			
			//�ж��ҷ����ӵ��Ƿ���ез���̹��
			public void hitEnemyTank() {
				for (int i = 0;i < hero.ss.size(); ++i) {
					Shot myShot = hero.ss.get(i);
					for (int j = 0;j < enemys.size(); ++j) {
						Enemy e = enemys.get(j);
						if (this.isHitTank(myShot, e)) {
							Recorder.reduceNum();
							Recorder.addEnemy();	
						}
						
						if (!e.getIsActive()) {
							enemys.remove(e);
						}
					}
					
				}
			}
			
			//�жϵз����ӵ��Ƿ�����ҷ���̹��
			public void hitHero() {
				for (int i = 0;i < enemys.size(); ++i) {
					Enemy e = enemys.get(i);
					for (int j = 0; j < e.getEs().size(); ++j) {
						Shot shot = e.getEs().get(j);
						if (hero.getIsActive()) {
							if (this.isHitTank(shot, hero)) {
								Recorder.reduceLife();
							}
						}	
					}
				}
			}
			
			public void run() { 
				while(true) {
					try {
						Thread.sleep(100);
					} catch(Exception e) {
						e.getStackTrace();
					}
					
					this.hitEnemyTank();
					this.hitHero();
					this.repaint();
				}
			}
}
