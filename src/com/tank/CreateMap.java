package com.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;			//字节输出流，用于输出地图文件
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;			//对象输出流
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author linzhongqi
 * @data 2019年3月17日
 * 制造地图类
 */
public class CreateMap {
	public int img_x = 0;
	public int img_y = 11;
	public int material_x;
	public int material_y;
	public int refurbish = 100;
	public int imgid = 1;
	public int size_x = 32;		//默认尺寸32
	public int size_y = 32;
	public int directions_x;
	public int directions_y;
	public int fps;
	
	private int pointImg_x = 6;
	private int pointImg_y = 4;
	private int point_x = 420;
	private int point_y = 200;
	public int Checkpoint = 1;
	
	private int select;
	private int small;
	private boolean down_key;
	
	@SuppressWarnings("unchecked")
	public CreateMap() {
		super();
		Data.matarry.clear();
		Data.matarry.add(new Boss(19, 5, 192, 384, 3));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//掏出基地位置
				if (i >= 1 && j >= 1 && i < 3){
					continue;
				}
				Data.matarry.add(new Wall(0, 0, 176 + i * 16 , 368 + j * 16, 3));
			}
		}
		try {
			//文本文件中写多个对象的信息（序列化）
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map\\"+1+".tmp"));
			Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
			ois.close();
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
	}

	public void anew(int fps) {
		this.fps = fps;
		if (imgid == 0 && fps % (Data.WINDOW_FPS / refurbish) <= Data.WINDOW_FPS / refurbish / 2){
			imgid = 1;
		}
		if (imgid == 1 && fps % (Data.WINDOW_FPS / refurbish) <=  Data.WINDOW_FPS / refurbish && fps % (Data.WINDOW_FPS / refurbish) > Data.WINDOW_FPS / refurbish / 2){
			imgid = 0;
		}
	}
	
	public void draw(Graphics g, CreateCanvas cc) {
		if (imgid == 1){
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
					34 * img_x + 1, 34 * img_y + 1, 34 * (img_x + 1) - 1, 34 * (img_y + 1) - 1, cc);
		}else {
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
					34 * 20 + 1, 34 * 10 + 1, 34 * 21 - 1, 34 * 11 - 1, cc);
		}
		// 关卡选择
		g.drawImage(Data.TANK_POINT, point_x + Data.MIN_X, point_y + Data.MIN_Y, point_x + 32 + Data.MIN_X, point_y + 32 + Data.MIN_Y, 
				34 * pointImg_x + 1, 34 * pointImg_y, 34 * (pointImg_x + 1), 34 * (pointImg_y + 1), cc);
		g.drawImage(Data.TANK_POINT, point_x + Data.MIN_X + 64, point_y + Data.MIN_Y, point_x + 96 + Data.MIN_X, point_y + 32 + Data.MIN_Y, 
				34 * (pointImg_x + 1) + 1, 34 * pointImg_y, 34 * (pointImg_x + 2), 34 * (pointImg_y + 1), cc);
		g.setColor(Color.black);
		g.setFont( new Font("宋体",Font.BOLD,16));//设置字体
        g.drawString("先关卡选择：", Data.MAX_X + 4, Data.MIN_Y + 10);//画文本
        g.drawString("确定：", Data.MAX_X + 4, Data.MIN_Y + 54);//画文本
        g.drawString("道具+/-：", Data.MAX_X + 4, Data.MIN_Y + 98);//画文本
        g.drawString("退出/保存：", Data.MAX_X + 4, Data.MIN_Y + 142);//画文本
        g.setColor(Color.blue);
        g.setFont( new Font("宋体",Font.BOLD,12));//设置字体
        g.drawString("S(↓) W(↑)", Data.MAX_X + 30, Data.MIN_Y + 32);//画文本
        g.drawString("enter键", Data.MAX_X + 30, Data.MIN_Y + 76);//画文本
        g.drawString("A键/D键", Data.MAX_X + 30, Data.MIN_Y + 120);//画文本
        g.drawString("Q键/E键", Data.MAX_X + 30, Data.MIN_Y + 164);//画文本
        g.setColor(Color.black);
		g.setFont( new Font("宋体",Font.BOLD,15));//设置字体
        g.drawString("" + Checkpoint, Data.MIN_X + point_x + 42, Data.MIN_Y + point_y + 21);//画文本
	}
	//设置地图，按键按下事件
	@SuppressWarnings("unchecked")
	public void downKey(int num){
		//移动
		if (num == 0){
			material_y -= 16;
			if (material_y <= 0){
				material_y = 0;
			}
			down_key = false;
		}else if (num == 1){
			material_y += 16;
			if (material_y >= 384){
				material_y = 384;
			}
			down_key = false;
		}else if (num == 2){
			material_x -= 16;
			if (material_x <= 0){
				material_x = 0;
			}
			down_key = false;
		}else if (num == 3){
			material_x += 16;
			if (material_x >= 384){
				material_x = 384;
			}
			down_key = false;
		}else if (num == 4){
			if (down_key){
				select++;
				if (select > 12){
					select = 0;
				}
				selectMaterial();
			}else {
				selectMaterial();
			}
			down_key = true;
		}else if (num == 5){
			if (down_key){
				select--;
				if (select < 0){
					select = 12;
				}
				selectMaterial();
			}else {
				selectMaterial();
			}						
			down_key = true;
		}else if (num == 6){
			try {
					//文本文件中写多个对象的信息（序列化）
//					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("map.tmp"));
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("map\\"+Checkpoint+".tmp"));
					oos.writeObject(Data.matarry);
					oos.close();
					System.out.println("已保存");
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			Data.matarry.clear();
			new GameStart();
			Data.start = 3;
		}else if (num == 7) {
			Data.matarry.clear();
			new GameStart();
			Data.start = 3;
		}else if (num == 8) {
			this.Checkpoint++;
			if (this.Checkpoint > 30) {
				this.Checkpoint = 1;
			}
			try {
				//文本文件中写多个对象的信息（序列化）
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map\\"+Checkpoint+".tmp"));
				Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
				ois.close();
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			System.out.println("downKey() => "+this.Checkpoint);
		}else if (num == 9) {
			this.Checkpoint--;
			if (this.Checkpoint < 1) {
				this.Checkpoint = 30;
			}
			try {
				//文本文件中写多个对象的信息（序列化）
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map\\"+Checkpoint+".tmp"));
				Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
				ois.close();
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}else if (num == 10) {
			Data.startpoint = true;
		}else if (num == 11){
			if (down_key){
				small++;
				if (small > 8){
					small = 0;
				}
				smallMaterial();
			}else {
				smallMaterial();
			}
			down_key = true;
		}
//		System.out.println(small);
	}
	
	/**选择大道具*/
	public void selectMaterial(){
		if (select == 0){
			DelMaterial();
		}else if (select == 1){
			Material mat1 = new Grass(4, 7, material_x, material_y, 3);
			DelMaterial();
			Data.matarry.add(mat1);
		}else if (select == 2){
			Material mat1 = new Sea(0, 7, material_x, material_y, 2);
			DelMaterial();
			Data.matarry.add(mat1);
		}else if (select == 3){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					Data.matarry.add(new Hard(0, 0, material_x + i * 16, material_y + j * 16, 3));
				}
			}
		}else if (select == 4){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 1; j++) {
					Data.matarry.add(new Hard(0, 0, material_x + i * 16, material_y + j * 16, 3));
				}
			}
		}else if (select == 5){
			DelMaterial();
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 2; j++) {
					Data.matarry.add(new Hard(0, 0, material_x + i * 16, material_y + j * 16, 3));
				}
			}
		}else if (select == 6){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 1; j++) {
					Data.matarry.add(new Hard(0, 0, material_x + i * 16, material_y + j * 16 + 16, 3));
				}
			}
		}else if (select == 7){
			DelMaterial();
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 2; j++) {
					Data.matarry.add(new Hard(0, 0, material_x + i * 16 + 16, material_y + j * 16, 3));
				}
			}
		}else if (select == 8){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					Data.matarry.add(new Wall(0, 0, material_x + i * 16 ,material_y + j * 16, 3));
				}
			}
		}else if (select == 9){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				Data.matarry.add(new Wall(0, 0, material_x + i * 16 ,material_y, 3));
			}
		}else if (select == 10){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				Data.matarry.add(new Wall(0, 0, material_x ,material_y + i * 16, 3));
			}
		}else if (select == 11){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				Data.matarry.add(new Wall(0, 0, material_x + i * 16, material_y + 16, 3));
			}
		}else if (select == 12){
			DelMaterial();
			for (int i = 0; i < 2; i++) {
				Data.matarry.add(new Wall(0, 0, material_x + 16,material_y + i * 16, 3));
			}
		}
		
	}
	/**选择小道具*/
	public void smallMaterial(){
		if (small == 0) {
			DelSmallMaterial();
		}else if (small == 1) {
			DelSmallMaterial();
			Data.matarry.add(new Wall(0, 0, material_x, material_y, 1));
		}else if (small == 2) {
			DelSmallMaterial();
			Data.matarry.add(new Wall(0, 0, material_x + 16, material_y, 1));
		}else if (small == 3) {
			DelSmallMaterial();
			Data.matarry.add(new Wall(0, 0, material_x + 16, material_y + 16, 1));
		}else if (small == 4) {
			DelSmallMaterial();
			Data.matarry.add(new Wall(0, 0, material_x, material_y + 16, 1));
		}else if (small == 5) {
			DelSmallMaterial();
			Data.matarry.add(new Hard(0, 0, material_x, material_y, 1));
		}else if (small == 6) {
			DelSmallMaterial();
			Data.matarry.add(new Hard(0, 0, material_x + 16, material_y, 1));
		}else if (small == 7) {
			DelSmallMaterial();
			Data.matarry.add(new Hard(0, 0, material_x + 16, material_y + 16, 1));
		}else if (small == 8) {
			DelSmallMaterial();
			Data.matarry.add(new Hard(0, 0, material_x, material_y + 16, 1));
		}
	}
	
	/**移除上一次的道具*/
	public void DelMaterial(){
		for (Material mat : Data.matarry) {
			if (mat.material_x / 32 == material_x /32 && mat.material_y / 32 == material_y / 32){
				Data.matarry.remove(mat);
			}
			if ((mat instanceof Wall | mat instanceof Hard) && mat.refurbish == 1){
				System.out.println("DelMaterial() => "+mat.material_x+";"+material_x);
				Data.matarry.remove(mat);
			}
			
		}
	}
	/**移除上一次的小道具*/
	public void DelSmallMaterial(){
		for (Material mat : Data.matarry) {
			if ((mat instanceof Wall | mat instanceof Hard) && mat.refurbish == 1){
				if ((material_x <= mat.material_x && mat.material_x < material_x + 32) && (material_y <= mat.material_y && mat.material_y < material_y + 32)) {
					Data.matarry.remove(mat);
				}
				
			}
			
		}
	}
}
