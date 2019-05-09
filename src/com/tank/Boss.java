package com.tank;

import java.awt.Graphics;
/**
 * @author linzhongqi
 * @data 2019��3��11��
 * ������
 */
public class Boss extends Material{
	private static final long serialVersionUID = 1818992270089337283L;

	public Boss(int img_x, int img_y, int material_x, int material_y, int refurbish) {
		//super���ø���ĵĹ��췽��
		super(img_x, img_y, material_x, material_y, refurbish);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void setMaterial_x(int material_x) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void setMaterial_y(int material_y) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void anew(int fps) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
				34 * img_x + 1, 34 * img_y + 1, 34 * (img_x + 1) - 1, 34 * (img_y + 1) - 1, cc);
	}

	@Override
	public void wounded(Bullet bullet, int principal, int directions, int effect_x, int effect_y) {
		Data.matarry.add(new Effect(20, 4, effect_x, effect_y, 12, 22));
		Data.matarry.remove(bullet);
		new AudioPlay().play("bgmusic\\kill.wav");
		new AudioPlay().play("bgmusic\\gameOver.wav");
		Data.matarry.add(new Effect(20, 4, material_x, material_y, 6, 14));
		Data.matarry.add(new Effect(20, 4, Data.MIN_X + 112, Data.MAX_Y, 12, 40));
		if (Data.style) {
			((TankA)(TankOther.matA)).setDie(true);
			((TankB)(TankOther.matB)).setDie(true);
		} else if(!Data.style){
			((TankA)(Data.mat)).setDie(true);
		}
		
		img_x += 1; // ͼ��仯
	}

}
