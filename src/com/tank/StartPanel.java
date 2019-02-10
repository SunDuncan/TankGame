package com.tank;
import java.awt.*;
import javax.swing.*;
public class StartPanel extends JPanel implements Runnable{
	private int time = 0;
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		//������˸��Ч��
		if (time % 2 == 0) {
			g.setColor(Color.yellow);
			g.setFont(new Font("������κ", Font.BOLD, 30));
			g.drawString("Stage�� 1", 150, 150);
		}	
	}
	
	public void run() {
		while (true) {
			
			try {
				Thread.sleep(100);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			if (time == 100) {
				time = 0;
			} else {
				time++;
			}
			this.repaint();
		}	
	}
}
