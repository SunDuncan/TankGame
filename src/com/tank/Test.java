package com.tank;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import java.awt.*;
import java.awt.event.*;


public class Test extends JFrame implements ActionListener{
	MyPanel mp = null;
	StartPanel sp = null;
	JMenuBar jmb = null;
	JMenuItem jmli = null;
	JMenu jml = null;
	JMenuItem jmli2 = null;
	JMenuItem jmli3 = null;
	JMenuItem jmli4 = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test test1 = new Test();
	}
	
	
	public Test() {
		//创建游戏菜单
		jmb = new JMenuBar();
		jml = new JMenu("游戏(G)");
		jml.setMnemonic('G');
		jmli = new JMenuItem("开始游戏(N)");
		jmli2 = new JMenuItem("退出游戏(E)");
		jmli2.setMnemonic('E');
		jmli2.addActionListener(this);
		jmli2.setActionCommand("exit");
		jmli.setMnemonic('N');
		jmli3 = new JMenuItem("保存并退出游戏(S)");
		jmli3.setMnemonic('S');
		jmli3.addActionListener(this);
		jmli3.setActionCommand("saveExit");
		jmli4 = new JMenuItem("继续上局游戏(C)");
		jmli4.setMnemonic('C');
		jmli4.addActionListener(this);
		jmli4.setActionCommand("conGame");
		jml.add(jmli);
		jml.add(jmli3);
		jml.add(jmli4);
		jml.add(jmli2);
		jmb.add(jml);
		jmli.addActionListener(this);
		jmli.setActionCommand("startnew");
		this.setJMenuBar(jmb);
		sp = new StartPanel();
		Thread s = new Thread(sp);
		s.start();
		this.add(sp);
		this.setSize(600, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getActionCommand().equals("startnew")) {
			mp = new MyPanel("new");
			Thread t = new Thread(mp);
			t.start();
			this.addKeyListener(mp);
			this.add(mp);
			this.remove(sp);
			this.setVisible(true);
		} else if (arg0.getActionCommand().equals("exit")) {
			System.exit(0);
		} else if (arg0.getActionCommand().equals("saveExit")) {
			Recorder r = new Recorder();
			r.setEts(mp.getEnemys());
			r.saveEnemyTank();
			System.exit(0);
		} else if (arg0.getActionCommand().equals("conGame")) {
			mp = new MyPanel("con");
			Thread t = new Thread(mp);
			t.start();
			this.addKeyListener(mp);
			this.add(mp);
			this.remove(sp);
			this.setVisible(true);
		}
	}
}
