package com.special;

import java.awt.Graphics;

import com.tank.Bullet;
import com.tank.CreateCanvas;
import com.tank.Data;
import com.tank.Material;

/**
* @author 林中奇linzhongqi
* @version 创建时间：2019年5月8日
* 短暂无敌特效类
*/
public class Invincible extends Material{
	private static final long serialVersionUID = 1268338830300950026L;
	
	public Invincible(int img_x, int img_y, int material_x, int material_y, int refurbish) {
		super(img_x, img_y, material_x, material_y, refurbish);
		super.ispenetrate = true;
		super.ispass = true;
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
	public void anew(int fps) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		// (18,6)
		g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X , material_y + Data.MIN_Y , material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
				34 * img_x, 34 * img_y, 34 * img_x + 32, 34 * img_y + 32, cc);
	}

	@Override
	public void wounded(Bullet bullet, int principal, int directions, int effect_x, int effect_y) {
		// TODO 自动生成的方法存根
		
	}

}
