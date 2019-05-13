package com.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileInputStream;
import java.io.FileOutputStream;			//�ֽ�����������������ͼ�ļ�
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;			//���������
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author linzhongqi
 * @data 2019��3��17��
 * �����ͼ��
 */
public class CreateMap {
	public int img_x = 0;
	public int img_y = 11;
	public int material_x;
	public int material_y;
	public int refurbish = 100;
	public int imgid = 1;
	public int size_x = 32;		//Ĭ�ϳߴ�32
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
				//�ͳ�����λ��
				if (i >= 1 && j >= 1 && i < 3){
					continue;
				}
				Data.matarry.add(new Wall(0, 0, 176 + i * 16 , 368 + j * 16, 3));
			}
		}
		try {
			//�ı��ļ���д����������Ϣ�����л���
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map\\"+1+".tmp"));
			Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
			ois.close();
		} catch (Exception e1) {
			// TODO �Զ����ɵ� catch ��
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
		// �ؿ�ѡ��
		g.drawImage(Data.TANK_POINT, point_x + Data.MIN_X, point_y + Data.MIN_Y, point_x + 32 + Data.MIN_X, point_y + 32 + Data.MIN_Y, 
				34 * pointImg_x + 1, 34 * pointImg_y, 34 * (pointImg_x + 1), 34 * (pointImg_y + 1), cc);
		g.drawImage(Data.TANK_POINT, point_x + Data.MIN_X + 64, point_y + Data.MIN_Y, point_x + 96 + Data.MIN_X, point_y + 32 + Data.MIN_Y, 
				34 * (pointImg_x + 1) + 1, 34 * pointImg_y, 34 * (pointImg_x + 2), 34 * (pointImg_y + 1), cc);
		g.setColor(Color.black);
		g.setFont( new Font("����",Font.BOLD,16));//��������
        g.drawString("�ȹؿ�ѡ��", Data.MAX_X + 4, Data.MIN_Y + 10);//���ı�
        g.drawString("ȷ����", Data.MAX_X + 4, Data.MIN_Y + 54);//���ı�
        g.drawString("����+/-��", Data.MAX_X + 4, Data.MIN_Y + 98);//���ı�
        g.drawString("�˳�/���棺", Data.MAX_X + 4, Data.MIN_Y + 142);//���ı�
        g.setColor(Color.blue);
        g.setFont( new Font("����",Font.BOLD,12));//��������
        g.drawString("S(��) W(��)", Data.MAX_X + 30, Data.MIN_Y + 32);//���ı�
        g.drawString("enter��", Data.MAX_X + 30, Data.MIN_Y + 76);//���ı�
        g.drawString("A��/D��", Data.MAX_X + 30, Data.MIN_Y + 120);//���ı�
        g.drawString("Q��/E��", Data.MAX_X + 30, Data.MIN_Y + 164);//���ı�
        g.setColor(Color.black);
		g.setFont( new Font("����",Font.BOLD,15));//��������
        g.drawString("" + Checkpoint, Data.MIN_X + point_x + 42, Data.MIN_Y + point_y + 21);//���ı�
	}
	//���õ�ͼ�����������¼�
	@SuppressWarnings("unchecked")
	public void downKey(int num){
		//�ƶ�
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
					//�ı��ļ���д����������Ϣ�����л���
//					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("map.tmp"));
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("map\\"+Checkpoint+".tmp"));
					oos.writeObject(Data.matarry);
					oos.close();
					System.out.println("�ѱ���");
				} catch (Exception e1) {
					// TODO �Զ����ɵ� catch ��
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
				//�ı��ļ���д����������Ϣ�����л���
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map\\"+Checkpoint+".tmp"));
				Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
				ois.close();
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			System.out.println("downKey() => "+this.Checkpoint);
		}else if (num == 9) {
			this.Checkpoint--;
			if (this.Checkpoint < 1) {
				this.Checkpoint = 30;
			}
			try {
				//�ı��ļ���д����������Ϣ�����л���
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map\\"+Checkpoint+".tmp"));
				Data.matarry = (CopyOnWriteArrayList<Material>) ois.readObject();
				ois.close();
			} catch (Exception e1) {
				// TODO �Զ����ɵ� catch ��
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
	
	/**ѡ������*/
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
	/**ѡ��С����*/
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
	
	/**�Ƴ���һ�εĵ���*/
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
	/**�Ƴ���һ�ε�С����*/
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
