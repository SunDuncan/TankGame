package com.tank;

import java.util.Vector;

public class Enemy extends Tank implements Runnable {

	public Vector<Shot> getEs() {
		return es;
	}

	public void setEs(Vector<Shot> es) {
		this.es = es;
	}

	private int speed;
	private Vector<Shot> es = new Vector<Shot>();
	private Shot s = null;
	private Vector<Enemy> ens = new Vector<Enemy>();

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int type;

	public Enemy(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		type = 1;
		speed = 1;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// ���������ͬ�����̹�˲�������
		while (true) {
			switch (this.getDirection()) {
			case 0:
				for (int i = 0; i < 30; ++i) {
					if (y > 0 && !this.isEnemysCoincide()) {
						y -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.getStackTrace();
					}
				}

				break;
			case 1:
				for (int i = 0; i < 30; ++i) {
					if (x > 0 && !this.isEnemysCoincide()) {
						x -= speed;
					}

					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				break;
			case 2:
				for (int i = 0; i < 30; ++i) {
					if (x < 370 && !this.isEnemysCoincide()) {
						x += speed;
					}

					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				break;
			case 3:
				for (int i = 0; i < 30; ++i) {
					if (y < 270 && !this.isEnemysCoincide()) {
						y += speed;
					}
					try {
						Thread.sleep(50);
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				break;
			}

			this.setDirection((int) (Math.random() * 4));
			if (this.getIsActive() == false) {
				break;
			}
		}
	}

	// ��������ϵ����е�̹��
	public void setMypanelTanks(Vector<Enemy> vv) {
		this.ens = vv;
	}

	// �ж��Ƿ��ص�
	public boolean isEnemysCoincide() {
		boolean flag = false;
		switch (this.getDirection()) {
		case 0:
			// ̹������
			for (int i = 0; i < this.ens.size(); ++i) {
				Enemy en = ens.get(i);
				if (en != this) {
					// ���˵�̹���ǳ��ϻ����µ�
					if (en.getDirection() == 0 || en.getDirection() == 3) {
						if (this.x >= en.getX() && this.x <= en.getX() + 20 && this.y >= en.y - 30
								&& this.y <= en.getY()) {
							return true;
						}

						if (this.x + 20 >= en.getX() && this.x + 20 <= en.getX() + 20 && this.y >= en.y - 30
								&& this.y <= en.getY()) {
							return true;
						}
					}

					// ���˵ķ����ǳ�����߳��ҵ�
					if (en.getDirection() == 1 || en.getDirection() == 3) {
						if (this.x >= en.getX() && this.x <= en.getX() + 30 && this.y >= en.getY() - 20
								&& this.y <= en.getY()) {
							return true;
						}

						if (this.x + 20 >= en.getX() && this.x + 20 <= en.getX() + 30 && this.y >= en.y - 20
								&& this.y <= en.getY()) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			// ̹������
			for (int i = 0; i < this.ens.size(); ++i) {
				Enemy en = ens.get(i);
				if (en != this) {
					// ���˵�̹�����ϻ�������
					if (en.getDirection() == 0 || en.getDirection() == 3) {
						if (this.getX() >= en.getX() && this.getX() <= en.getX() + 20 && this.y <= en.getY()
								&& this.y >= en.getY() - 30) {
							return true;
						}

						if (this.getX() >= en.getX() && this.getX() <= en.getX() + 20 && this.y - 20 <= en.getY()
								&& this.y - 20 >= en.getY() - 30) {
							return true;
						}
					}

					// ���˵�̹�������������
					if (en.getDirection() == 1 || en.getDirection() == 2) {
						if (this.getX() >= en.getX() && this.getX() <= en.getX() + 30 && this.getY() <= en.getY()
								&& this.getY() >= en.getY() - 20) {
							return true;
						}

						if (this.getX() >= en.getX() && this.getX() <= en.getX() + 30 && this.getY() - 20 <= en.getY()
								&& this.getY() - 20 >= en.getY() - 20) {
							return true;
						}
					}
				}
			}
			break;
		case 2:
			// ̹������
			for (int i = 0; i < this.ens.size(); ++i) {
				Enemy en = ens.get(i);
				if (en != this) {
					// ���˵�̹�����ϻ�������
					if (en.getDirection() == 0 || en.getDirection() == 3) {
						if (this.getX() + 30 >= en.getX() && this.getX() + 30 <= en.getX() + 20 && this.y <= en.getY()
								&& this.y >= en.getY() - 30) {
							return true;
						}

						if (this.getX() + 30 >= en.getX() && this.getX() + 30 <= en.getX() + 20
								&& this.y - 20 <= en.getY() && this.y - 20 >= en.getY() - 30) {
							return true;
						}
					}

					// ���˵�̹�������������
					if (en.getDirection() == 1 || en.getDirection() == 2) {
						if (this.getX() + 30 >= en.getX() && this.getX() + 30 <= en.getX() + 30
								&& this.getY() <= en.getY() && this.getY() >= en.getY() - 20) {
							return true;
						}

						if (this.getX() + 30 >= en.getX() && this.getX() + 30 <= en.getX() + 30
								&& this.getY() - 20 <= en.getY() && this.getY() - 20 >= en.getY() - 20) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			// ̹������
			for (int i = 0; i < this.ens.size(); ++i) {
				Enemy en = ens.get(i);
				if (en != this) {
					// ���˵�̹���ǳ��ϻ����µ�
					if (en.getDirection() == 0 || en.getDirection() == 3) {
						if (this.x >= en.getX() && this.x <= en.getX() + 20 && this.y - 30 >= en.y - 30
								&& this.y - 30 <= en.getY()) {
							return true;
						}

						if (this.x + 20 >= en.getX() && this.x + 20 <= en.getX() + 20 && this.y - 30 >= en.y - 30
								&& this.y - 30 <= en.getY()) {
							return true;
						}
					}

					// ���˵ķ����ǳ�����߳��ҵ�
					if (en.getDirection() == 1 || en.getDirection() == 3) {
						if (this.x >= en.getX() && this.x <= en.getX() + 30 && this.y - 30 >= en.getY() - 20
								&& this.y - 30 <= en.getY()) {
							return true;
						}

						if (this.x + 20 >= en.getX() && this.x + 20 <= en.getX() + 30 && this.y - 30 >= en.y - 20
								&& this.y - 30 <= en.getY()) {
							return true;
						}
					}
				}

			}
			break;
		}

		return flag;
	}

	public void shotHero() {
		switch (this.getDirection()) {
		case 0:
			s = new Shot(x + 8, y - 2, this.getDirection());
			es.add(s);
			break;
		case 1:
			s = new Shot(x - 7, y + 9, this.getDirection());
			es.add(s);
			break;
		case 2:
			s = new Shot(x + 26, y + 9, this.getDirection());
			es.add(s);
			break;
		case 3:
			s = new Shot(x + 8, y + 31, this.getDirection());
			es.add(s);
			break;
		}

		for (int i = 0; i < es.size(); ++i) {
			Shot st = es.get(i);
			Thread t = new Thread(st);
			t.start();
		}
	}

}
