package com.tank;

import java.awt.Graphics;

//�̳г����࣬��Ҫʵ������ĳ��󷽷�
public class TankA extends Material{
	private static final long serialVersionUID = 8135824104107706697L;
	
	private int directions_x = 0;
	private int directions_y = 0;
	private int temp_x = 0;
	private int temp_y = -1;
	private int fps;			//���١���Ч��ͼ��������
	private int fps_1;			//��ʼ̹���޷��ƶ�����
	private int speed = 2;		//�ƶ��ٶȳ�ֵ
	private int attackspeed;	//�ӵ��ƶ��ٶ�
	private int tempimg_x;		//̹�˵ȼ���ʾģ��
	private int rank = 0;		//�ȼ���������
	private int enemy;
	private int imgid_1;		//��ͼ��Ч��ʾid
	private int imgid_2;		//��Χ��Чid
	private int defend_time;	//�޵�ʱ��
	
	private boolean die;			//������־λ
	private boolean take_up;
	private boolean take_down;
	private boolean take_left;
	private boolean take_right;
	public static boolean defend;			//�޵б�־λ
	
	/**
	 * ̹��ģ�ͼ�����
	 * 
	 * @param img_x ģ����ͼ����x��λ��
	 * @param img_y ģ����ͼ����y��λ��
	 * @param material_x ģ���ڵ�ͼ������x��λ��
	 * @param material_y ģ���ڵ�ͼ������y��λ��
	 * @param refurbish ��Чˢ���ٶ�
	 * @param speed �ҷ��ƶ��ٶ�
	 * @param enemy ���������ʶ
	 */
	public TankA(int img_x, int img_y, int material_x, int material_y, int refurbish,int enemy, int speed) {
		super(img_x, img_y, material_x, material_y, refurbish);
		super.attack_id = 1;
		super.ispenetrate = false;
		
		TankA.defend = true;
		this.speed = speed;
//		System.out.println("TankA() => "+enemy);
		this.enemy = enemy;
	}
	
	public boolean isDie() {
		return die;
	}
	
	public void setDie(boolean die) {
		this.die = die;
	}
	//λ����ʾ��ȷ��ģ����ʾ�ڵ�ͼ��
	@Override
	public void setMaterial_x(int material_x) {
		if (material_x <= Data.TANKE_REGION_MIN_X){
			this.material_x = Data.TANKE_REGION_MIN_X;
		}else if (material_x >= Data.TANKE_REGION_MAX_X - size_x){
			this.material_x = Data.TANKE_REGION_MAX_X - size_x;
		}else{
			this.material_x = material_x;
		}
	}

	@Override
	public void setMaterial_y(int material_y) {
		if (material_y <= Data.TANKE_REGION_MIN_Y){
			this.material_y = Data.TANKE_REGION_MIN_Y;
		}else if (material_y >= Data.TANKE_REGION_MAX_Y - size_y){
			this.material_y = Data.TANKE_REGION_MAX_Y - size_y;
		}else{
			this.material_y = material_y;
		}
	}
	
	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		if (rank > 3){
			this.rank = 3;
		}else{
			this.rank = rank;
		}
	}
	
	@Override
	//�����ҷ�̹��
	public void anew(int fps) {
		//������Чid�趨���޵�ʱ���ж�
		if (defend){
			defend_time++;
			//�޵�ʱ��
			if (defend_time >= Data.WINDOW_FPS * 4){
				defend_time = 0;
				defend = false;
			}
			if (defend_time % (Data.WINDOW_FPS / refurbish) <= Data.WINDOW_FPS / refurbish * 0.25){
				imgid_2 = 1;
			}
			if (defend_time % (Data.WINDOW_FPS / refurbish) <=  Data.WINDOW_FPS / refurbish * 0.5 && defend_time % (Data.WINDOW_FPS / refurbish) > Data.WINDOW_FPS / refurbish * 0.25){
				imgid_2 = 2;
			}
			if (defend_time % (Data.WINDOW_FPS / refurbish) <=  Data.WINDOW_FPS / refurbish * 0.75 && defend_time % (Data.WINDOW_FPS / refurbish) > Data.WINDOW_FPS / refurbish * 0.5){
				imgid_2 = 3;
			}
			if (defend_time % (Data.WINDOW_FPS / refurbish) >  Data.WINDOW_FPS / refurbish * 0.75){
				imgid_2 = 4;
			}
		}
		//��ЧID
		this.fps++;
		this.fps_1++;
		if (this.fps >= 100000){
			this.fps = 0;
		}
		//��Ч 
		if (fps_1 > Data.WINDOW_FPS * 0.6){
			// �ƶ�Ч��
			if (enemy <= 2){
				// ��ֹûЧ��
				if (directions_x == 0 ^ directions_y == 0){
					if (imgid == 0 && fps % (Data.WINDOW_FPS / refurbish) <= Data.WINDOW_FPS / refurbish / 2){
						imgid = 1;
					}
					if (imgid == 1 && fps % (Data.WINDOW_FPS / refurbish) <=  Data.WINDOW_FPS / refurbish && fps % (Data.WINDOW_FPS / refurbish) > Data.WINDOW_FPS / refurbish / 2){
						imgid = 0;
					}
				}
			}else {
//			this.temp_y = 1;
				if (imgid == 0 && fps % (Data.WINDOW_FPS / refurbish) <= Data.WINDOW_FPS / refurbish / 2){
					imgid = 1;
				}
				if (imgid == 1 && fps % (Data.WINDOW_FPS / refurbish) <=  Data.WINDOW_FPS / refurbish && fps % (Data.WINDOW_FPS / refurbish) > Data.WINDOW_FPS / refurbish / 2){
					imgid = 0;
				}
			}
			//̹���ƶ� 
			if (!die){
				move();			
			}
		}else {
			//��ͼ��Ч��ʾ��ȡ10������ͼ�л�����
			imgid_1 = fps % 10;
		}
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		tempimg_x = rank * 8 + img_x;
		//����Ϸ�������ҷ�̹�˲��ڻ���
		if (!die) {
			if (fps_1 > Data.WINDOW_FPS * 0.6){	
				//��ģ��
				if (temp_y == -1){
					if (imgid == 1){
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * tempimg_x + 1, 34 * img_y + 1, 34 * (tempimg_x + 1) - 1, 34 * (img_y + 1) - 1, cc);
					}else {
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * (tempimg_x + 1) + 1, 34 * img_y + 1, 34 * (tempimg_x + 2) - 1, 34 * (img_y + 1) - 1, cc);
					}
				//��ģ��
				} else if (temp_x == 1){
					if (imgid == 1){
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * (tempimg_x + 2) + 1, 34 * img_y + 1, 34 * (tempimg_x + 3) - 1, 34 * (img_y + 1) - 1, cc);
					}else {
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * (tempimg_x + 3) + 1, 34 * img_y + 1, 34 * (tempimg_x + 4) - 1, 34 * (img_y + 1) - 1, cc);
					}
				} else if (temp_y == 1){
					//��ģ��
					if (imgid == 1){
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * (tempimg_x + 4) + 1, 34 * img_y + 1, 34 * (tempimg_x + 5) - 1, 34 * (img_y + 1) - 1, cc);
					}else {
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * (tempimg_x + 5) + 1, 34 * img_y + 1, 34 * (tempimg_x + 6) - 1, 34 * (img_y + 1) - 1, cc);
					}
					//��ģ��
				}else if (temp_x == -1){
					if (imgid == 1){
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * (tempimg_x + 6) + 1, 34 * img_y + 1, 34 * (tempimg_x + 7) - 1, 34 * (img_y + 1) - 1, cc);
					}else {
						g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
								34 * (tempimg_x + 7) + 1, 34 * img_y + 1, 34 * (tempimg_x + 8) - 1, 34 * (img_y + 1) - 1, cc);
					}
				}
			}else{
				//̹��δ�����ǵ���ͼ
				if (imgid_1 == 1){
					g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
							34 * 19 + 1, 34 * 4 + 1, 34 * 20 - 1, 34 * 5 - 1, cc);
				}else if (imgid_1 == 2){
					g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
							34 * 18 + 1, 34 * 4 + 1, 34 * 19 - 1, 34 * 5 - 1, cc);
				}else if (imgid_1 == 3){
					g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
							34 * 17 + 1, 34 * 4 + 1, 34 * 18 - 1, 34 * 5 - 1, cc);
				}else if (imgid_1 == 4){
					g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
							34 * 16 + 1, 34 * 4 + 1, 34 * 17 - 1, 34 * 5 - 1, cc);
				}
			}
		}
		//������Ч
		if (defend){
			if (imgid_2 == 1){
				g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
						34 * 13 + 1, 34 * 7 + 1, 34 * 14 - 1, 34 * 8 - 1, cc);
			}else if (imgid_2 == 2){
				g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
						34 * 14 + 1, 34 * 7 + 1, 34 * 15 - 1, 34 * 8 - 1, cc);
			}else if (imgid_2 == 3){
				g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
						34 * 15 + 1, 34 * 7 + 1, 34 * 16 - 1, 34 * 8 - 1, cc);
			}else if (imgid_2 == 4){
				g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
						34 * 14 + 1, 34 * 7 + 1, 34 * 15 - 1, 34 * 8 - 1, cc);
			}
		}
		
	}

	public void move(){
		directions();
		if (fps % speed == 0){
			setMaterial_x(getMaterial_x() + directions_x);
			setMaterial_y(getMaterial_y() + directions_y);
		}
		//���ߺ��ҷ�̹�˵���ײ���
		moveJudge();
	}
	
	/**��ײ���*/	
	private void moveJudge() {		
		for (Material mat : Data.matarry) {
//			System.out.println("moveJudge() => "+this);
			if (mat != this){
				if (temp_x == 1){
					if (!mat.ispass){
						if (mat.getMaterial_x() < material_x + size_x && mat.getMaterial_x() >= material_x + size_x - size_x / 4){
							if (!(mat.getMaterial_y() +  1 > material_y + size_y || mat.getMaterial_y() + mat.size_y < material_y + 1)){
//								if (mat.getMaterial_x() <= material_x + size_x){
									material_x = mat.getMaterial_x() - size_x;
//								}
							}
						}
					}
				}else if(temp_x == -1){
					if (!mat.ispass){
						if (mat.getMaterial_x() + mat.size_x > material_x&& mat.getMaterial_x() + mat.size_x <= material_x + size_x / 4){
							if (!(mat.getMaterial_y() +  1 > material_y + size_y || mat.getMaterial_y() + mat.size_y < material_y + 1)){
//								if (mat.getMaterial_x() <= material_x + size_x){
									material_x = mat.getMaterial_x() + mat.size_x;
//								}
							}
						}
					}
				}else if(temp_y == 1){
					if (!mat.ispass){
						if (mat.getMaterial_y() < material_y + size_y && mat.getMaterial_y() >= material_y + size_y - size_y / 4){
							if (!(mat.getMaterial_x() +  1 > material_x + size_x || mat.getMaterial_x() + mat.size_x < material_x + 1)){
//								if (mat.getMaterial_y() <= material_y + size_y){
									material_y = mat.getMaterial_y() - size_y;
//								}
							}
						}
					}
				}else if(temp_y == -1){
					if (!mat.ispass){
						if (mat.getMaterial_y() + mat.size_y > material_y && mat.getMaterial_y() + mat.size_y <= material_y + size_y / 4){
							if (!(mat.getMaterial_x() +  1 > material_x + size_x || mat.getMaterial_x() + mat.size_x < material_x + 1)){
//								if (mat.getMaterial_y() <= material_y + size_y){
									material_y = mat.getMaterial_y() + mat.size_y;
//								}
							}
						}
					}
				}
			}
		}
	}
	//���򷽷�	
	public void downKey(int num){
		if (num == 0){
			take_up = true;
		}else if (num == 1){
			take_down = true;
		}else if (num == 2){
			take_left = true;
		}else if (num == 3){
			take_right = true;
		}
		if (!die){
			directions();
		}
//		System.out.println(directions_x + "..." + directions_x);
	}
	/**����*/
	private void directions() {
		if (directions_x == 0 && directions_y == 0){
			if (take_up){
				directions_x = 0;
				directions_y = -3;
				temp_x = 0;
				temp_y = -1;
//				reviseXY(1);
			}else if (take_down){
				directions_x = 0;
				directions_y = 3;
				temp_x = 0;
				temp_y = 1;
//				reviseXY(1);
			}else if (take_left){
				directions_x = -3;
				directions_y = 0;
				temp_x = -1;
				temp_y = 0;
//				reviseXY(2);
			}else if (take_right){
				directions_x = 3;
				directions_y = 0;
				temp_x = 1;
				temp_y = 0;
//				reviseXY(2);
			}
		}
	}

	public void upKey(int num){
		if (num == 0){
			take_up = false;
			directions_x = 0;
			directions_y = 0;
		}else if (num == 1){
			take_down = false;
			directions_x = 0;
			directions_y = 0;
		}else if (num == 2){
			take_left = false;
			directions_x = 0;
			directions_y = 0;
		}else if (num == 3){
			take_right = false;
			directions_x = 0;
			directions_y = 0;
		}
		
	}
	//���ƶ�����У��
//	private void reviseXY(int num){		
//		if (num == 1){
//			if (material_x % 16 < 8){
//				material_x = material_x / 16 * 16;
//			}else if (material_x % 16 >= 9){
////				System.out.println("reviseXY() x => "+material_x % 16);
//				material_x = material_x / 16 * 16 + 16;
//			}
//		}else if (num == 2){
//			
//			if (material_y % 16 < 8){
//				material_y = material_y / 16 * 16;
//			}else if (material_y % 16 >= 9){
//				System.out.println("reviseXY() y => "+material_y / 16);
//				material_y = material_y / 16 * 16 + 16;
//			}
//		}
//	}
	
	public void attack(){
		if (fps_1 > Data.WINDOW_FPS * 0.6){
			if (!die){
				//�����ӵ�����
				int bullet_coumt = 0;
				int bullet_sum = 0;
				if (rank == 0){
					attackspeed = 4;
					bullet_sum = 1;		
				}else if (rank == 1){
					attackspeed = 6;
					bullet_sum = 1;
				}else {
					attackspeed = 6;
					bullet_sum = 2;
				}
				for (Material mat : Data.matarry) {
					if (mat instanceof Bullet){
						if (((Bullet)(mat)).getPrincipal() == enemy){
							bullet_coumt++;
						}				
					}
				}
				//�жϴ����ӵ��ǲ��ǳ���
				if (bullet_sum > bullet_coumt){
					//���Ʋ�ͬ�����ӵ�
					if (temp_y == -1){
						Data.matarry.add(new Bullet(0, 5, material_x + 12, material_y, attackspeed, enemy, 1));
					} else if (temp_x == 1){
						Data.matarry.add(new Bullet(0, 5, material_x + 26, material_y + 12, attackspeed, enemy, 3));
					} else if (temp_y == 1){
						Data.matarry.add(new Bullet(0, 5, material_x + 12, material_y + 26, attackspeed, enemy, 2));
					} else if (temp_x == -1){
						Data.matarry.add(new Bullet(0, 5, material_x, material_y + 12, attackspeed, enemy, 4));
					}
				}
			}
		}
	}

	public void wounded(Bullet bullet, int principal, int dire , int effect_x, int effect_y){
		if ((enemy <= 2 && principal > 2) || (enemy > 2 && principal <= 2)){
			Data.matarry.remove(bullet);
			Data.matarry.add(new Effect(20, 4, effect_x, effect_y, 12, 22));
			//���޵�
			if (!defend){
				if (rank - 1 >= 0){
					rank = rank - 1;
				}else{
					Data.matarry.add(new Effect(20, 4, material_x, material_y, 5, 32));
					Data.matarry.remove(this);
					die = true;
					if (Data.myTankA <= 0){
						new AudioPlay().play("bgmusic\\gameOver.wav");
						Data.matarry.add(new Effect(20, 4, Data.MIN_X + 112, Data.MAX_Y, 12, 40));
					}
					new AudioPlay().play("bgmusic\\kill.wav");//fire
				}
			}
		}
	}
}
