package com.tank;

import java.awt.Graphics;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * @author linzhongqi
 * @data 2019年3月18日
 * 封面操作类
 * 
 */
public class GameStart {
	//封面进入id
	private static int start_id;		
	private static int fps = 0;
	private static Robot robot;
	
	public GameStart() {
		new AudioPlay().play("bgmusic\\hi.wav");
	}

	public static void downKey(int i) {
		if (i == 7){
			start_id++;
			if (start_id >= 3){
				start_id = 0;
			}
		}else if (i == 6){
			if (start_id == 2){
				Data.matarry.clear();
				new CreateMap();		//自定义地图
//				new Checkpoint();		//自定义地图
				Data.start = 2;
			}else if (start_id == 0){
				Data.matarry.clear();
				Data.style = false;		//单人模式
				new TankOther();		//战场地图绘制
				Data.start = 1;
			}else if (start_id == 1){
				Data.matarry.clear();
				Data.style = true;		//双人模式
				new TankOther();		//战场地图绘制
				Data.start = 0;
			}
		}
		
	}

	public static void anew(int n) {
		if (Data.shiftNum != 0) {
			try {
				robot = new Robot();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			// 切换输入法
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			// 切换到英文模式
			robot.keyPress(KeyEvent.VK_SHIFT);
			robot.keyRelease(KeyEvent.VK_SHIFT);
			Data.shiftNum--;
		}
	}

	public static void draw(Graphics g, CreateCanvas createCanvas) {
		
		fps++;
		if (fps >= 10000){
			fps = 0;
		}
		
		g.drawImage(Data.Start_Img, 0, 0, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT,0, 0, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT, createCanvas);
		
		if (fps % (Data.WINDOW_FPS / 5) <= Data.WINDOW_FPS / 5 / 2){
			g.drawImage(Data.TANK_PLAN, 170, 270 + start_id * 32, 170 +32, 270 +32 + start_id * 32, 
					68 + 1, 34 * 8 + 1, 102 - 1, 34 * 9 - 1, createCanvas);
		}
		if (fps % (Data.WINDOW_FPS / 5) <=  Data.WINDOW_FPS / 5 && fps % (Data.WINDOW_FPS / 5) > Data.WINDOW_FPS / 5 / 2){
			g.drawImage(Data.TANK_PLAN, 170, 270 + start_id * 32, 170 +32, 270 +32 + start_id * 32, 
					102 + 1, 34 * 8 + 1, 136 - 1, 34 * 9 - 1, createCanvas);
		}
		
		
	}

}
