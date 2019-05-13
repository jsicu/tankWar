package com.tank;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author linzhongqi
 * @data 2019年3月15日
 * 数据类
 * 
 */
public class Data {
	public static final int WINDOW_WIDTH = 600;							 //窗口宽度
	public static final int WINDOW_HEIGHT = 480+30; 					 //窗口高度
	public static final int WINDOW_FPS = 100;    						 //坦克移动速度
	public static final String WINDOW_TITLE = "《坦克大战》 作者：林中奇";   //窗口标题
	public static final Image WINDOW_ICOIMG = Toolkit.getDefaultToolkit().getImage("img/logo.png");		//显示logo
	public static final Image TANK_WIN = Toolkit.getDefaultToolkit().getImage("img/victory.png");		//获胜
	public static final Image TANK_PLAN = Toolkit.getDefaultToolkit().getImage("img/tankIcon.png");		//模型图集
	public static final Image TANK_POINT = Toolkit.getDefaultToolkit().getImage("img/tankIcon_back.png");		//模型图集
	public static final Image Start_Img = Toolkit.getDefaultToolkit().getImage("img/tankCover.png");	//开始页面背景图
	
	public static CopyOnWriteArrayList<Material> matarry = new CopyOnWriteArrayList<Material>();
	public static CreateMap create_map = new CreateMap();
	public static Checkpoint checkpoint = new Checkpoint();
	public static Material mat;
	
	public static final int TANKE_REGION_MIN_X = 0;				//坦克战斗区域
	public static final int TANKE_REGION_MIN_Y = 0;				//坦克战斗区域
	public static final int TANKE_REGION_MAX_X = 416;			//坦克战斗区域
	public static final int TANKE_REGION_MAX_Y = 416;			//坦克战斗区域
	public static final int MIN_X = 65;							//战斗区域起始位
	public static final int MIN_Y = 35;
	public static final int MAX_X = MIN_X + 416;				//战斗属性显示位
	public static final int MAX_Y = MIN_Y + 416;
	
	public static int shiftNum = 2;							//切换输入法用,
	public static int start = 3;
	public static int checkNum = 1;							//关卡
	public static int myTankA = 4;							//甲方数
	public static int myTankB = 4;							//乙方数
	public static boolean style;							//单双人模式标志
	public static boolean startpoint;						//选择关卡标志
	
	/**移去基地水泥墙特效*/
	public void delay(int delay) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// 通关
				if (Data.checkNum > 30) {
					Data.matarry.add(new Effect(0, 0, Data.MIN_X + 112, Data.MAX_Y, 12, 50));
				}
//				Data.checkNum ++;
				Data.myTankA++;
				Data.myTankB++;
				TankOther.enemySum = 20;
				new TankOther();	
			}
		}, delay);
		
	}
}
