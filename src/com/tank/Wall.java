package com.tank;

import java.awt.Graphics;

/**
 * @author linzhongqi
 * @data 2019年3月18日
 * 制造墙类
 */
public class Wall extends Material{
	private static final long serialVersionUID = 7273384988167714846L;
	
	public Wall(int img_x, int img_y, int material_x, int material_y, int refurbish) {
		super(img_x, img_y, material_x, material_y, refurbish);
//		super.attack_id = 1;
		super.size_x = 16;
		super.size_y = 16;
	}

	@Override
	public void setMaterial_x(int material_x) {
		if (material_x <= Data.TANKE_REGION_MIN_X){
			this.material_x = Data.TANKE_REGION_MIN_X;
		}else if (material_x >= Data.TANKE_REGION_MAX_X - size_x){
			this.material_x = Data.TANKE_REGION_MAX_X - size_x;
		}else{
			this.material_x = material_x;
		}
	}

	@Override
	public void setMaterial_y(int material_y) {
		if (material_y <= Data.TANKE_REGION_MIN_Y){
			this.material_y = Data.TANKE_REGION_MIN_Y;
		}else if (material_y >= Data.TANKE_REGION_MAX_Y - size_y){
			this.material_y = Data.TANKE_REGION_MAX_Y - size_y;
		}else{
			this.material_y = material_y;
		}
	}
	
	@Override
	public void anew(int fps) {}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X , material_y + Data.MIN_Y , material_x + 16 + Data.MIN_X, material_y + 16 + Data.MIN_Y, 
				613, 171, 629, 187, cc);
		
	}

	@Override
	public void wounded(Bullet bullet, int principal, int dire, int effect_x, int effect_y) {
		Data.matarry.remove(bullet);
		Data.matarry.remove(this);
		if (principal <= 2){
			new AudioPlay().play("bgmusic\\brickErase.wav");
		}
		
	}
}
