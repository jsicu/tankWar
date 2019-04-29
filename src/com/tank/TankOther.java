package com.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TankOther {
	private static int enemy_random = 1;	//�з�̹�˳��������
	private static int birth_random = 1;	//�ҷ�̹�˳��������
	public static int enemySum = 20;		//������
	public static int myTank = 4;			//������
	
	//���Ƶ����͵ľ���
	@SuppressWarnings("unchecked")
	public TankOther() {
		super();
		try {	
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Map.tmp"));
				Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
				ois.close();
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		System.out.println("TankOther()"+Data.matarry);
//		for (Material mat : Data.matarry) {
////			System.out.println("TankOther() => "+mat.material_x);
//			// (6,0)
//			if (mat.material_x / 32 == 192 /32 && mat.material_y / 32 == 0 / 32){
//				Data.matarry.remove(mat);
//			}
//			// (12,0)
//			if (mat.material_x / 32 == 384 /32 && mat.material_y / 32 == 0 / 32){
//				Data.matarry.remove(mat);
//			}
//			// (0,0)
//			if (mat.material_x / 32 == 0 /32 && mat.material_y / 32 == 0 / 32){
//				Data.matarry.remove(mat);
//			}
//			// (4,12)
//			if (mat.material_x / 32 == 128 /32 && mat.material_y / 32 == 384 / 32){
//				Data.matarry.remove(mat);
//			}
//			// (6,12)
//			if (mat.material_x / 32 == 192 /32 && mat.material_y / 32 == 384 / 32){
//				Data.matarry.remove(mat);
//			}
//			// (8,12)
//			if (mat.material_x / 32 == 256 /32 && mat.material_y / 32 == 384 / 32){
//				Data.matarry.remove(mat);
//			}
//		}
//		System.out.println("TankOther()"+Data.matarry);
//		Data.matarry.add(new Boss(19, 5, 192, 384, 3));
		new AudioPlay().play("bgmusic\\inter.wav");
	}
	//���̹��
	public static void newtank(){
		int temp = 0;		//���ϵз�̹����
		int temp_1 = 0;
		int temp1 = 0;		//�����ҷ�̹����
		for (Material mat : Data.matarry) {
			if (mat instanceof Tank_npc){
				temp++;
			}
			if (mat instanceof Tank_man){
				temp1++;
			}
		}
		
		//�ҷ�̹�˸���
		if (temp1 <= 0){
			if (myTank > 0){
				if (birth_random == 1){
					myTank--;
					//̹�˳��ֵ�λ��
					Data.mat = new Tank_man(0, 8, 128, 384, 3, 1, 1);
					Data.matarry.add(0 ,Data.mat);
				}
				//��ȡ0-100���������̹�������������������
				birth_random = new Random().nextInt(100);
			}
		}
		//�з�̹�˸���
		if (enemySum > 0){
			if (temp < 4){
				if (enemy_random == 1){
					enemySum--;
					temp_1 = new Random().nextInt(3);
					//����ط�
					if (temp_1 == 0){
						Data.matarry.add(0, new Tank_npc(0, 2, 192, 0, 3, 6, 5));					
					}else if (temp_1 == 1){
						Data.matarry.add(0, new Tank_npc(0, 2, 384,0, 3, 7, 5));
					}else if (temp_1 == 2){
						Data.matarry.add(0, new Tank_npc(0, 2, 0, 0, 3, 8, 5));
					}
				}
				//��ȡ0-100���������̹�������������������
				enemy_random = new Random().nextInt(100);
			}
		}
	}
	//���ƶ�ս״̬
	public static void draw(Graphics g, CreateCanvas cc) {
		for (int i = 0; i < enemySum; i++) {
			int height = i / 2;
			if (i % 2 == 1) {
				g.drawImage(Data.TANK_PLAN, Data.MAX_X + 52, Data.MIN_Y + height * 16, Data.MAX_X + 84, Data.MIN_Y + height * 16 + 32, 
						34 * 1 + 1, 34 * 4 + 1, 34 * 2 - 1, 34 * 5 - 1, cc);
			} else {
				g.drawImage(Data.TANK_PLAN, Data.MAX_X + 32, Data.MIN_Y + height * 16, Data.MAX_X + 64, Data.MIN_Y + height * 16 + 32, 
						34 * 1 + 1, 34 * 4 + 1, 34  * 2 - 1, 34 * 5 - 1, cc);
			}
		}
//		for (int i = 0; i <= enemySum / 2; i++) {
//			// ����
//			if (i == enemySum / 2 && enemySum % 2 == 1){
//				g.drawImage(Data.TANK_PLAN, Data.MAX_X + 32, Data.MIN_Y + i * 16, Data.MAX_X + 64, Data.MIN_Y + i * 16 + 32, 
//						34 * 1 + 1, 34 * 4 + 1, 34 * 2 - 1, 34 * 5 - 1, cc);
//			}else if(i < enemySum / 2){
//				g.drawImage(Data.TANK_PLAN, Data.MAX_X + 32, Data.MIN_Y + i * 16, Data.MAX_X + 64, Data.MIN_Y + i * 16 + 32, 
//						34 * 1 + 1, 34 * 4 + 1, 34 * 2 - 1, 34 * 5 - 1, cc);
//				g.drawImage(Data.TANK_PLAN, Data.MAX_X + 52, Data.MIN_Y + i * 16, Data.MAX_X + 84, Data.MIN_Y + i * 16 +32, 
//						34 * 1 + 1, 34 * 4 + 1, 34  * 2 - 1, 34 * 5 - 1, cc);
//			}
//		}
		g.drawImage(Data.TANK_PLAN, Data.MAX_X + 43, Data.MIN_Y + 230, Data.MAX_X + 75, Data.MIN_Y + 230 +32, 
				34 * 2 + 1, 34 * 4 + 1, 34 * 3 - 1, 34 * 5 - 1, cc);
		g.drawImage(Data.TANK_PLAN, Data.MAX_X + 36, Data.MIN_Y + 248, Data.MAX_X + 68, Data.MIN_Y + 248 +32, 
				34 * 10 + 1, 34 * 6 + 1, 34 * 11 - 1, 34 * 7 - 1, cc);
		g.setColor(Color.BLACK);
		g.setFont( new Font("΢���ź�",Font.BOLD,19));//��������
        g.drawString("" + myTank, Data.MAX_X + 62, Data.MIN_Y + 271);//���ı�
		
	}
}
