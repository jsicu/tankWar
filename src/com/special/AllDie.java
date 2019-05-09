package com.special;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import com.tank.Bullet;
import com.tank.CreateCanvas;
import com.tank.Data;
import com.tank.Material;
import com.tank.TankA;

/**
* @author 林中奇
* @version 创建时间：2019年5月8日 
* 战场坦克全部死亡特效类
*/
public class AllDie extends Material {
	private static final long serialVersionUID = -5830733238810374715L;
	private int fpsId = 0;
	
	public AllDie(int img_x, int img_y, int material_x, int material_y, int refurbish) {
		super(img_x, img_y, material_x, material_y, refurbish);
		super.ispenetrate = true;
		super.ispass = true;
		super.isspecial = true;
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
		fpsId++;
		if (fpsId == 100) {
			fpsId = 0;
		}
		if (fps % (Data.WINDOW_FPS / refurbish) <= Data.WINDOW_FPS / refurbish * 0.8) {
			imgid = 1;
		}else {
			imgid = 0;
		}
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		// (20,6)
		if (imgid == 1) {
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X , material_y + Data.MIN_Y , material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
					34 * img_x, 34 * img_y, 34 * img_x + 32, 34 * img_y + 32, cc);
		} else {
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X , material_y + Data.MIN_Y , material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
					34 * (img_x + 1), 34 * img_y, 34 * (img_x + 1) + 32, 34 * img_y + 32, cc);	
		}
		remove(this, false);
	}
	
	@Override
	public void wounded(Bullet bullet, int principal, int directions, int effect_x, int effect_y) {
		// TODO 自动生成的方法存根
		
	}
	
	/**移去特效*/
	public void remove(AllDie allDie,boolean immediately) {
		Timer timer = new Timer();
		if (immediately) {
			System.out.println("remove() => "+ Data.matarry);
			System.out.println("remove() => "+ allDie);
//			TankA.remove.remove(allDie);
		}else {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Data.matarry.remove(allDie);
				}
			}, 10000);
		}
		
	}

	public void forOut(boolean immediately) {
//		System.out.println("remove() => sdasdasd"+ this);
		remove(this, immediately);
	}
	
}
