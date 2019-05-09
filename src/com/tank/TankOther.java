package com.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class TankOther {
	private static int enemy_random = 1;	//敌方坦克出生随机数
	private static int birth_randomA = 1;	//我方坦克出生随机数
	private static int birth_randomB = 1;	//我方坦克出生随机数
	public static int enemySum = 20;		//敌人数
	public static int myTankA = 4;			//甲方数
	public static int myTankB = 4;			//乙方数
	public static Material matA;
	public static Material matB;
	public static int onlineEnemyNum = 0;		//场上敌方坦克数
	
	//抑制单类型的警告
	@SuppressWarnings("unchecked")
	public TankOther() {
		super();
		try {	
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Map.tmp"));
				Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
				ois.close();
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
//		System.out.println("TankOther()"+Data.matarry);
//		Data.matarry.add(new Boss(19, 5, 192, 384, 3));
		new AudioPlay().play("bgmusic\\inter.wav");
	}
	//添加坦克
	public static void newtank(){
		onlineEnemyNum = 0;		//场上敌方坦克数
		int enemyBornRandom = 0;	//敌方坦克出生地随机数
		int onlineTankANum = 0;		//场上甲方坦克数
		int onlineTankBNum = 0;		//场上乙方坦克数
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
		
		
		//乙方坦克复活
		if (Data.style) {
			if (onlineTankANum <= 0){
				if (myTankA > 0){
					if (birth_randomA == 1){
						myTankA--;
						//坦克出现的位置
						matA = new TankA(0, 11, 128, 384, 3, 1, 1);
						Data.matarry.add(0 ,matA);
					}
					//获取0-100随机数，让坦克死亡后不至于立即出现
					birth_randomA = new Random().nextInt(100);
				}
			}
			if (onlineTankBNum <= 0){
				if (myTankB > 0){
					if (birth_randomB == 1){
						myTankB--;
						//坦克出现的位置
						matB = new TankB(0, 12, 256, 384, 3, 1, 1);
						Data.matarry.add(0 ,matB);
					}
					//获取0-100随机数，让坦克死亡后不至于里面出现
					birth_randomB = new Random().nextInt(100);
				}
			}
		}else {
			//甲方坦克复活
			if (onlineTankANum <= 0){
				if (myTankA > 0){
					if (birth_randomA == 1){
						myTankA--;
						//坦克出现的位置
						Data.mat = new TankA(0, 11, 128, 384, 3, 1, 1);
						Data.matarry.add(0 ,Data.mat);
					}
					//获取0-100随机数，让坦克死亡后不至于立即出现
					birth_randomA = new Random().nextInt(100);
				}
			}
		}
		
		//敌方坦克复活
//		System.out.println("newtank() => "+onlineEnemyNum);
		if (enemySum > 0){
			if (onlineEnemyNum < 4){
				if (enemy_random == 1){
					enemySum--;
					enemyBornRandom = new Random().nextInt(3);
					//复活地方
					if (enemyBornRandom == 0){
						Data.matarry.add(0, new TankEnemy(0, 2, 192, 0, 3, 6, 5));					
					}else if (enemyBornRandom == 1){
						Data.matarry.add(0, new TankEnemy(0, 2, 384,0, 3, 7, 5));
					}else if (enemyBornRandom == 2){
						Data.matarry.add(0, new TankEnemy(0, 2, 0, 0, 3, 8, 5));
					}
				}
				//获取0-100随机数，让坦克死亡后不至于里面出现
				enemy_random = new Random().nextInt(100);
			}
		}
	}
	//绘制对战状态
	public static void draw(Graphics g, CreateCanvas cc) {
		//绘制敌方剩余数量
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
        // 双人模式
		if (Data.style) {
			// 甲方
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 15, Data.MIN_Y + 230, Data.MAX_X + 47, Data.MIN_Y + 230 +32, 
					34 * 2 + 1, 34 * 4 + 1, 34 * 3 - 1, 34 * 5 - 1, cc);
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 8, Data.MIN_Y + 250, Data.MAX_X + 40, Data.MIN_Y + 250 +32, 
					34 * 10 + 1, 34 * 6 + 1, 34 * 11 - 1, 34 * 7 - 1, cc);
			g.setColor(Color.blue);
			g.setFont( new Font("微软雅黑",Font.BOLD,19));//设置字体
	        g.drawString("" + myTankA, Data.MAX_X + 34, Data.MIN_Y + 273);//画文本
	        // 乙方
	        g.drawImage(Data.TANK_PLAN, Data.MAX_X + 60, Data.MIN_Y + 230, Data.MAX_X + 92, Data.MIN_Y + 230 +32, 
					34 * 2 + 1, 34 * 4 + 1, 34 * 3 - 1, 34 * 5 - 1, cc);
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 53, Data.MIN_Y + 250, Data.MAX_X + 85, Data.MIN_Y + 250 +32, 
					34 * 10 + 1, 34 * 6 + 1, 34 * 11 - 1, 34 * 7 - 1, cc);
			g.setColor(Color.red);
			g.setFont( new Font("微软雅黑",Font.BOLD,19));//设置字体
	        g.drawString("" + myTankB, Data.MAX_X + 79, Data.MIN_Y + 273);//画文本
		}else {
			// 单人模式
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 43, Data.MIN_Y + 230, Data.MAX_X + 75, Data.MIN_Y + 230 +32, 
					34 * 2 + 1, 34 * 4 + 1, 34 * 3 - 1, 34 * 5 - 1, cc);
			g.drawImage(Data.TANK_PLAN, Data.MAX_X + 36, Data.MIN_Y + 248, Data.MAX_X + 68, Data.MIN_Y + 248 +32, 
					34 * 10 + 1, 34 * 6 + 1, 34 * 11 - 1, 34 * 7 - 1, cc);
			g.setColor(Color.black);
			g.setFont( new Font("微软雅黑",Font.BOLD,19));//设置字体
	        g.drawString("" + myTankA, Data.MAX_X + 62, Data.MIN_Y + 271);//画文本
		}
	}
}
