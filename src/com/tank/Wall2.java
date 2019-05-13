package com.tank;

import java.awt.Graphics;

public class Wall2 extends Material{
	//实现序列化类的不同版本间的兼容性
	private static final long serialVersionUID = 2919258279665521877L;

	
	public Wall2(int img_x, int img_y, int material_x, int material_y, int refurbish) {
		super(img_x, img_y, material_x, material_y, refurbish);
		super.ispass = true;	
		super.attack_id = 1;
		super.ispenetrate = true;
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
		moveJudge();
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		// TODO 自动生成的方法存根
		g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
				34 * img_x + 1, 34 * img_y + 1, 34 * (img_x + 1) - 1, 34 * (img_y + 1) - 1, cc);
	}

	@Override
	public void wounded(Bullet bullet, int num, int dire, int effect_x, int effect_y) {
		
	}
	
	@SuppressWarnings("unused")
	private void moveJudge() {		
		for (Material mat : Data.matarry) {
			
		}
	}
}
	
	