package com.tank;

import java.awt.Graphics;
/**
 * @author linzhongqi
 * @data 2019年3月30日
 * 爆炸效果类
 * 
 */
public class Effect extends Material{//子弹特效
	private static final long serialVersionUID = 3402035641659328509L;
	
	private int fps;
	private int num1;			//坦克状态值1（4：游戏结束，3：击毁敌方，2：撞击道具，1：基地被摧毁）
	private int num2 = 1;		//坦克状态值1（控制效果显示时间）
	public int die = 0;			//对外游戏结束标志
	
	public Effect(int img_x, int img_y, int material_x, int material_y, int refurbish, int num) {
		super(img_x, img_y, material_x, material_y, refurbish);
		this.num1 = num / 10;
		//如果基地被毁，设置die
		if (num1 == 4 && !Data.style) {
			((TankA)(Data.mat)).setDie(true);
		}else if(num1 == 4 && Data.style){
			((TankA)(TankOther.matA)).setDie(true);
			((TankB)(TankOther.matB)).setDie(true);
		}
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
		this.fps++;
		if (this.fps % (Data.WINDOW_FPS / refurbish  * 2.5) <= Data.WINDOW_FPS / refurbish * 0.5){
			imgid = 1;
		}else if(this.fps % (Data.WINDOW_FPS / refurbish  * 2.5) <= Data.WINDOW_FPS / refurbish * 1){
			imgid = 2;
		}else if(this.fps % (Data.WINDOW_FPS / refurbish  * 2.5) <= Data.WINDOW_FPS / refurbish * 1.5){
			imgid = 3;
		}else if(this.fps % (Data.WINDOW_FPS / refurbish  * 2.5) <= Data.WINDOW_FPS / refurbish * 2){
			imgid = 4;
		}else if(this.fps % (Data.WINDOW_FPS / refurbish  * 2.5) <= Data.WINDOW_FPS / refurbish * 2.5){
			imgid = 5;
		}else if(this.fps % (Data.WINDOW_FPS / refurbish  * 2.5) <= Data.WINDOW_FPS / refurbish * 3){
			imgid = 6;
		}
		if(fps >= Data.WINDOW_FPS * 100 - 10){
			Data.matarry.remove(this);
		}
	}
	
	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		//基地被摧毁
		if (num1 == 1){
			//保证图标显示完全，不会立马消失
			if (imgid <= num2){
				g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X - 16, material_y + Data.MIN_Y - 16, material_x + 48 + Data.MIN_X, material_y + 48 + Data.MIN_Y, 
						34 * (img_x + 3) + 1, 34 * img_y + 1, 34 * (img_x + 5) - 1, 34 * (img_y +2) - 1, cc);
			}else{
				Data.matarry.remove(this);
			}
		//撞击道具
		}else if(num1 == 2){
			if (imgid <= num2){
				g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
						34 * img_x + 1, 34 * img_y + 1, 34 * (img_x + 1) - 1, 34 * (img_y + 1) - 1, cc);
			}else{
				Data.matarry.remove(this);
			}
		//敌方被摧毁
		}else if(num1 == 3){
			if (imgid <= num2){
				g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 32 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
						34 * (img_x + 2) + 1, 34 * img_y + 1, 34 * (img_x + 3) - 1, 34 * (img_y + 1) - 1, cc);
			}else{
				Data.matarry.remove(this);
			}
		//我方被摧毁
		}else if(num1 == 4){
			gameover();
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 66 + Data.MIN_X, material_y + 32 + Data.MIN_Y, 
					34 * 4 + 1, 34 * img_y + 1, 34 * 6 - 1, 34 * (img_y + 1) - 1, cc);
		//胜利
		}else if(num1 == 5){
			gameover();
			g.drawImage(Data.TANK_WIN, material_x, material_y, material_x + 123 + Data.MIN_X, material_y + 75 + Data.MIN_Y, 
					0, 0, 123, 75, cc);
		}else {
			Data.matarry.remove(this);
		}
	}

	@Override
	public void wounded(Bullet bullet, int principal, int dire , int effect_x, int effect_y) {
		// TODO 自动生成的方法存根
		
	}
	
	//gameover图标
	public void gameover(){
		if (material_y >= Data.MAX_Y / 2 - 40){
			material_y--;
		}
	}

}
