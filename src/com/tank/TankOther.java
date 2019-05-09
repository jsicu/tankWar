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
	private static int birth_randomA = 1;	//�ҷ�̹�˳��������
	private static int birth_randomB = 1;	//�ҷ�̹�˳��������
	public static int enemySum = 20;		//������
	public static int myTankA = 4;			//�׷���
	public static int myTankB = 4;			//�ҷ���
	public static Material matA;
	public static Material matB;
	public static int onlineEnemyNum = 0;		//���ϵз�̹����
	
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
//		System.out.println("TankOther()"+Data.matarry);
//		Data.matarry.add(new Boss(19, 5, 192, 384, 3));
		new AudioPlay().play("bgmusic\\inter.wav");
	}
	//���̹��
	public static void newtank(){
		onlineEnemyNum = 0;		//���ϵз�̹����
		int enemyBornRandom = 0;	//�з�̹�˳����������
		int onlineTankANum = 0;		//���ϼ׷�̹����
		int onlineTankBNum = 0;		//�����ҷ�̹����
		for (Material mat : Data.matarry) {
			if (mat instanceof TankEnemy){
				onlineEnemyNum++;
			}
			if (mat instanceof TankA){
				onlineTankANum++;
			}
			if (mat instanceof TankB){
				onlineTankBNum++;
			}
		}
		
		
		//�ҷ�̹�˸���
		if (Data.style) {
			if (onlineTankANum <= 0){
				if (myTankA > 0){
					if (birth_randomA == 1){
						myTankA--;
						//̹�˳��ֵ�λ��
						matA = new TankA(0, 11, 128, 384, 3, 1, 1);
						Data.matarry.add(0 ,matA);
					}
					//��ȡ0-100���������̹��������������������
					birth_randomA = new Random().nextInt(100);
				}
			}
			if (onlineTankBNum <= 0){
				if (myTankB > 0){
					if (birth_randomB == 1){
						myTankB--;
						//̹�˳��ֵ�λ��
						matB = new TankB(0, 12, 256, 384, 3, 1, 1);
						Data.matarry.add(0 ,matB);
					}
					//��ȡ0-100���������̹�������������������
					birth_randomB = new Random().nextInt(100);
				}
			}
		}else {
			//�׷�̹�˸���
			if (onlineTankANum <= 0){
				if (myTankA > 0){
					if (birth_randomA == 1){
						myTankA--;
						//̹�˳��ֵ�λ��
						Data.mat = new TankA(0, 11, 128, 384, 3, 1, 1);
						Data.matarry.add(0 ,Data.mat);
					}
					//��ȡ0-100���������̹��������������������
					birth_randomA = new Random().nextInt(100);
				}
			}
		}
		
		//�з�̹�˸���
//		System.out.println("newtank() => "+onlineEnemyNum);
		if (enemySum > 0){
			if (onlineEnemyNum < 4){
				if (enemy_random == 1){
					enemySum--;
					enemyBornRandom = new Random().nextInt(3);
					//����ط�
					if (enemyBornRandom == 0){
						Data.matarry.add(0, new TankEnemy(0, 2, 192, 0, 3, 6, 5));					
					}else if (enemyBornRandom == 1){
						Data.matarry.add(0, new TankEnemy(0, 2, 384,0, 3, 7, 5));
					}else if (enemyBornRandom == 2){
						Data.matarry.add(0, new TankEnemy(0, 2, 0, 0, 3, 8, 5));
					}
				}
				//��ȡ0-100���������̹�������������������
				enemy_random = new Random().nextInt(100);
			}
		}
	}
	//���ƶ�ս״̬
	public static void draw(Graphics g, CreateCanvas cc) {
		//���Ƶз�ʣ������
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
        // ˫��ģʽ
		if (Data.style) {
			// �׷�
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 15, Data.MIN_Y + 230, Data.MAX_X + 47, Data.MIN_Y + 230 +32, 
					34 * 2 + 1, 34 * 4 + 1, 34 * 3 - 1, 34 * 5 - 1, cc);
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 8, Data.MIN_Y + 250, Data.MAX_X + 40, Data.MIN_Y + 250 +32, 
					34 * 10 + 1, 34 * 6 + 1, 34 * 11 - 1, 34 * 7 - 1, cc);
			g.setColor(Color.blue);
			g.setFont( new Font("΢���ź�",Font.BOLD,19));//��������
	        g.drawString("" + myTankA, Data.MAX_X + 34, Data.MIN_Y + 273);//���ı�
	        // �ҷ�
	        g.drawImage(Data.TANK_PLAN, Data.MAX_X + 60, Data.MIN_Y + 230, Data.MAX_X + 92, Data.MIN_Y + 230 +32, 
					34 * 2 + 1, 34 * 4 + 1, 34 * 3 - 1, 34 * 5 - 1, cc);
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 53, Data.MIN_Y + 250, Data.MAX_X + 85, Data.MIN_Y + 250 +32, 
					34 * 10 + 1, 34 * 6 + 1, 34 * 11 - 1, 34 * 7 - 1, cc);
			g.setColor(Color.red);
			g.setFont( new Font("΢���ź�",Font.BOLD,19));//��������
	        g.drawString("" + myTankB, Data.MAX_X + 79, Data.MIN_Y + 273);//���ı�
		}else {
			// ����ģʽ
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 43, Data.MIN_Y + 230, Data.MAX_X + 75, Data.MIN_Y + 230 +32, 
					34 * 2 + 1, 34 * 4 + 1, 34 * 3 - 1, 34 * 5 - 1, cc);
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 36, Data.MIN_Y + 248, Data.MAX_X + 68, Data.MIN_Y + 248 +32, 
					34 * 10 + 1, 34 * 6 + 1, 34 * 11 - 1, 34 * 7 - 1, cc);
			g.setColor(Color.black);
			g.setFont( new Font("΢���ź�",Font.BOLD,19));//��������
	        g.drawString("" + myTankA, Data.MAX_X + 62, Data.MIN_Y + 271);//���ı�
		}
	}
}
