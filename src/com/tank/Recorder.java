package com.tank;
import java.io.*;
import java.util.*;

public class Recorder {
	//记录每关有多少敌人
	private static int enNum = 20;
	//记录你的战绩数目
	private static int allEnemy = 0;
	public static void setAllEnemy(int allEnemy) {
		Recorder.allEnemy = allEnemy;
	}
	
	public static int getAllEnemy() {
		return allEnemy;
	}
	
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	private static int myLife = 3;
	
	public static void reduceNum() {
		enNum--;
	}
	
	public static void addEnemy() {
		allEnemy++;
	}
	
	public static void reduceLife() {
		myLife--;
	}
	
	//读取文件的操作
	private static FileWriter fw = null;
	private static FileReader fr  = null;
	private static BufferedReader br = null;
	private static BufferedWriter bw = null;
	private Vector<Enemy> ets = new Vector<Enemy>();
	private Vector<Node> nodes = new Vector<Node>();
	
	public Vector<Enemy> getEts() {
		return ets;
	}

	public void setEts(Vector<Enemy> ets) {
		this.ets = ets;
	}

	//从文件中读取
	public static void getRecording() {
		try {
			fr = new FileReader("E:\\Duncan\\java_project_file\\tank_recording.txt");
			br = new BufferedReader(fr);
			String num = br.readLine();
			if (num != null) {
				allEnemy = Integer.parseInt(num);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//将文件总成绩保存在文件中
	public static void keepRecording() {
		try {
			fw = new FileWriter("E:\\Duncan\\java_project_file\\tank_recording.txt");
			bw = new BufferedWriter(fw);
			bw.write(allEnemy + "" + "\r\n");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	//将仍然存活的坦克坐标存入文件中
	public void saveEnemyTank() {
		try {
			fw = new FileWriter("E:\\Duncan\\java_project_file\\tank_recording.txt");
			bw = new BufferedWriter(fw);
			bw.write(allEnemy + "" + "\r\n");
			String record = "";
			for (int i = 0;i < ets.size(); ++i) {
				Enemy et = ets.get(i);
				if (et.getIsActive()) {
					record += et.getX() + " " + et.getY() + " " + et.getDirection() + "\r\n";
				}
			}
			
			bw.write(record);
		
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Vector<Node> getNodes() {
		try {
			fr = new FileReader("E:\\Duncan\\java_project_file\\tank_recording.txt");
			br = new BufferedReader(fr);
			//先读取一个总数
			String num = br.readLine();
			String s = "";
			//再读取坐标
			while ((s = br.readLine()) != null) {
				String []xyz = s.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return nodes;
	}
	
	
	
}
