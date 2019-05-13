package com.tank;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
* @author 林中奇
* @version 创建时间：2019年4月11日
* 关卡选择
*/
public class Checkpoint {
	private int pointImg_x = 6;
	private int pointImg_y = 4;
	private int point_x = 155;
	private int point_y = 200;
	public int Checkpoint = 1;
	
	public void anew(int fps) {
		
	}
	public void draw(Graphics g, CreateCanvas cc) {
		g.drawImage(Data.TANK_PLAN, point_x + Data.MIN_X, point_y + Data.MIN_Y, point_x + 32 + Data.MIN_X, point_y + 32 + Data.MIN_Y, 
				34 * pointImg_x + 1, 34 * pointImg_y, 34 * (pointImg_x + 1), 34 * (pointImg_y + 1), cc);
		g.drawImage(Data.TANK_PLAN, point_x + Data.MIN_X + 64, point_y + Data.MIN_Y, point_x + 96 + Data.MIN_X, point_y + 32 + Data.MIN_Y, 
				34 * (pointImg_x + 1) + 1, 34 * pointImg_y, 34 * (pointImg_x + 2), 34 * (pointImg_y + 1), cc);
		g.setColor(Color.black);
		g.setFont( new Font("宋体",Font.BOLD,16));//设置字体
        g.drawString("关卡选择：", Data.MAX_X + 4, Data.MIN_Y + 10);//画文本
        g.drawString("确定：", Data.MAX_X + 4, Data.MIN_Y + 54);//画文本
        g.setColor(Color.blue);
        g.setFont( new Font("宋体",Font.BOLD,12));//设置字体
        g.drawString("S(↓) W(↑)", Data.MAX_X + 30, Data.MIN_Y + 32);//画文本
        g.drawString("enter键", Data.MAX_X + 30, Data.MIN_Y + 76);//画文本
        g.setColor(Color.white);
		g.setFont( new Font("宋体",Font.BOLD,15));//设置字体
        g.drawString("" + Checkpoint, Data.MIN_X + point_x + 42, Data.MIN_Y + point_y + 21);//画文本
	}
}
