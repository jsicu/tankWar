package com.tank;

import java.awt.Graphics;
/**
 * @author linzhongqi
 * @data 2019年3月18日
 * 制造混凝土墙类
 */
public class Hard extends Material{
	private static final long serialVersionUID = 6215779321001507681L;

	public Hard(int img_x, int img_y, int material_x, int material_y, int refurbish) {
		super(img_x, img_y, material_x, material_y, refurbish);
		super.size_x = 16;
		super.size_y = 16;
	}

	@Override
	public void setMaterial_x(int material_x) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void setMaterial_y(int material_y) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void anew(int fps) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X , material_y + Data.MIN_Y , material_x + 16 + Data.MIN_X, material_y + 16 + Data.MIN_Y, 
				34 * 0 + 1, 34 * 6 + 1, 34 * 1 - 17, 34 * 7 - 17, cc);
	}

	@Override
	public void wounded(Bullet bullet, int principal, int directions, int effect_x, int effect_y) {
		Data.matarry.add(new Effect(20, 4, effect_x, effect_y, 12, 22));
		Data.matarry.remove(bullet);
		if (principal <= 2){
			new AudioPlay().play("bgmusic\\hit.wav");
		}
	}	
}
