package com.special;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import com.tank.Bullet;
import com.tank.CreateCanvas;
import com.tank.Data;
import com.tank.Effect;
import com.tank.Hard;
import com.tank.Material;
import com.tank.TankA;
import com.tank.TankB;
import com.tank.TankEnemy;
import com.tank.TankOther;

/**
* @author 林中奇
* @version 创建时间：2019年5月8日 
* 战场坦克全部死亡特效类
*/
public class Special extends Material {
	private static final long serialVersionUID = -5830733238810374715L;
	private int type;
	
	public Special(int img_x, int img_y, int material_x, int material_y, int refurbish, int type) {
		super(img_x, img_y, material_x, material_y, refurbish);
		super.ispenetrate = true;
		super.ispass = true;
		this.type = type; 
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
		if (fps % (Data.WINDOW_FPS / refurbish) <= Data.WINDOW_FPS / refurbish * 0.8) {
			imgid = 1;
		}else {
			imgid = 0;
		}
		moveJudge();
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
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
	
	private void moveJudge() {	
		for (Material mat : Data.matarry) {
			// 子弹碰到敌方坦克并子弹属于我方 || 子弹碰到我方坦克并子弹属于敌方 || 碰到道具
			if (mat instanceof TankA || mat instanceof TankB){
				// 道具
//				System.out.println("moveJudge() => "+mat);
				if ((mat.getMaterial_y() + mat.size_y >= material_y && mat.getMaterial_y() < material_y) | (mat.getMaterial_y() + mat.size_y >= material_y + size_y && mat.getMaterial_y() < material_y + size_y)) {
					if ((mat.getMaterial_x() <= material_x && material_x < mat.getMaterial_x() + mat.size_x) | (mat.getMaterial_x() <= material_x + size_x && material_x + size_x < mat.getMaterial_x() + mat.size_x)) {
						remove(this, true);
						switch (this.type) {
						case 1:
							// 无敌
							if (mat instanceof TankA) {
								TankA.defend = true;
							}else {
								TankB.defend = true;
							}
							break;
						case 2:
							// 全死
							for (Material enemy : Data.matarry) {
								if (enemy instanceof TankEnemy) {
									Data.matarry.remove(enemy);
									for (int i = 0; i < 10; i++) {
										Data.matarry.add(new Effect(21, 4, enemy.material_x, enemy.material_y, 5, 32));
									}
								}
							}
							break;
						case 3:
							// 修改基地
							for (int i = -1; i < 5; i++) {
								for (int j = -1; j < 3; j++) {
									//掏出基地位置
									if (i >= 1 && j >= 1 && i < 3){
										continue;
									}
									Data.matarry.add(new Hard(0, 0, 176 + i * 16 , 368 + j * 16, 1));
								}
							}
							removeHard();
							break;
						case 4:
							// 生命+1
							if (mat instanceof TankA) {
								TankOther.myTankA ++;
							}else {
								TankOther.myTankB ++;
							}
							break;
						case 5:
							// 坦克升级
							if (mat instanceof TankA) {
								int tankGrade = 0;
								// 双人
								if (Data.style) {
									tankGrade = ((TankA)(TankOther.matA)).getRank() + 1;
									((TankA)(TankOther.matA)).setRank(tankGrade);
								}else if (!Data.style) {
									tankGrade = ((TankA)(Data.mat)).getRank() + 1;
									((TankA)(Data.mat)).setRank(tankGrade);
								}
							}else {
								((TankB)(TankOther.matB)).setRank(((TankB)(TankOther.matB)).getRank() + 1);
							}
							break;
						default:
							break;
						}
					}
				}
			}
		}
	}
	/**移去特效*/
	public void remove(Special allDie,boolean immediately) {
		Timer timer = new Timer();
		if (immediately) {
			Data.matarry.remove(allDie);
		}else {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					Data.matarry.remove(allDie);
				}
			}, 10000);
		}
		
	}
	/**移去基地水泥墙特效*/
	public void removeHard() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (Material mat : Data.matarry) {
					if (mat instanceof Hard && mat.refurbish == 1) {
						Data.matarry.remove(mat);
					}
				}
			}
		}, 20000);
		
	}

}
