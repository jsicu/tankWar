package com.tank;

import java.awt.Graphics;
/**
 * @author linzhongqi
 * @data 2019年3月22日
 * 子弹类
 */
public class Bullet extends Material{	//子弹
	private static final long serialVersionUID = -8469328198288631536L;
	int principal;//子弹归属
	int direction;
		
	public int getPrincipal() {
		return principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	/**
	 * 坦克模型及属性
	 * 
	 * @param img_x 模型在图集的x轴位置
	 * @param img_y 模型在图集的y轴位置
	 * @param material_x 模型在地图出生的x轴位置
	 * @param material_y 模型在地图出生的y轴位置
	 * @param refurbish 特效刷新速度
	 * @param principal 子弹归属(我方：1，敌方：6、7、8)
	 * @param direction 子弹方向
	 */
	public Bullet(int img_x, int img_y, int material_x, int material_y, int refurbish, int principal, int direction) {
		super(img_x, img_y, material_x, material_y, refurbish);
		this.principal = principal;
		this.direction = direction;
		super.size_x = 6;
		super.size_y = 8;
		super.ispass = true; // 设置子弹通过，否则敌方坦克打中敌方子弹会无法穿透
		super.attack_id = 5;
		super.ispenetrate = false;
		if (principal <= 2){
			new AudioPlay().play("bgmusic\\shoot.wav");	//fire	
		}
	}


	@Override
	public void setMaterial_x(int material_x) {
		if (material_x <= Data.TANKE_REGION_MIN_X){
			this.material_x = Data.TANKE_REGION_MIN_X;
			bulletOut();
		}else if (material_x >= Data.TANKE_REGION_MAX_X - size_x){
			this.material_x = Data.TANKE_REGION_MAX_X - size_x;
			bulletOut();
		}else{
			this.material_x = material_x;
		}
	}

	@Override
	public void setMaterial_y(int material_y) {
		if (material_y <= Data.TANKE_REGION_MIN_Y){
			this.material_y = Data.TANKE_REGION_MIN_Y;
			bulletOut();
		}else if (material_y >= Data.TANKE_REGION_MAX_Y - size_y){
			this.material_y = Data.TANKE_REGION_MAX_Y - size_y;
			bulletOut();
		}else{
			this.material_y = material_y;
		}
	}
	
	@Override
	public void anew(int fps) {
		//向上发射
		if (direction == 1){
			move(0, -1);
		//向下发射
		}else if (direction == 2){
			move(0, 1);
		//向右发射
		}else if (direction == 3){
			move(1, 0);
		//向左发射
		}else if (direction == 4){
			move(-1, 0);
		}
	}

	@Override
	public void draw(Graphics g, CreateCanvas cc) {
		// 保证知道头的方向正确
		if (direction == 1){
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X , material_y + Data.MIN_Y, material_x + 6 + Data.MIN_X , material_y + 8 + Data.MIN_Y, 
					34 * img_x +1 , 34 * img_y + 1, 34 * (img_x + 1) - 27, 34 * (img_y + 1) - 25, cc);//上
		}else if (direction == 2){
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 6 + Data.MIN_X, material_y + 8 + Data.MIN_Y, 
					34 * img_x + 11, 34 * img_y + 1, 34 * (img_x + 1) - 17, 34 * (img_y + 1) - 25, cc);//下
		}else if (direction == 3){
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 8 + Data.MIN_X, material_y + 6 + Data.MIN_Y, 
					34 * img_x + 11, 34 * img_y + 10, 34 * (img_x + 1) - 15, 34 * (img_y + 1) - 16, cc);//左
		}else if (direction == 4){
			g.drawImage(Data.TANK_PLAN, material_x + Data.MIN_X, material_y + Data.MIN_Y, material_x + 8 + Data.MIN_X, material_y + 6 + Data.MIN_Y, 
					34 * img_x + 1, 34 * img_y + 10, 34 * (img_x + 1) - 26, 34 * (img_y + 1) - 16, cc);//右
		}
	}
	
	public void move(int x,int y){
		setMaterial_x(getMaterial_x() + (x * refurbish));
		setMaterial_y(getMaterial_y() + (y * refurbish));
		//道具和道具的碰撞检查
		moveJudge();
	}

	public void bulletOut(){//子弹出界
		if (direction == 1){
			Data.matarry.add(new Effect(20, 4, material_x - 12, material_y - 16, 12, 22));
		}else if (direction == 2){
			Data.matarry.add(new Effect(20, 4, material_x - 12, material_y - 8, 12, 22));
		}else if (direction == 3){
			Data.matarry.add(new Effect(20, 4, material_x - 8, material_y - 12, 12, 22));
		}else if (direction == 4){
			Data.matarry.add(new Effect(20, 4, material_x - 16, material_y - 12, 12, 22));
		}
		if (principal <= 2){
			new AudioPlay().play("bgmusic\\hit.wav");
		}
		//移除
		Data.matarry.remove(this);
	}
	
	private void moveJudge() {		//碰撞检测
		for (Material mat : Data.matarry) {
			// 子弹碰到敌方坦克并子弹属于我方 || 子弹碰到我方坦克并子弹属于敌方 || 碰到道具
			if (((mat instanceof Tank_man && principal > 2) || (mat instanceof Tank_npc && principal <= 2)) || ((!(mat instanceof Tank_npc) && !(mat instanceof Tank_man)) && (mat != this))){
				//子弹是否可穿过
				if (!mat.ispenetrate){
					if ((mat.getMaterial_y() + mat.size_y >= material_y && mat.getMaterial_y() < material_y) | (mat.getMaterial_y() + mat.size_y >= material_y + size_y && mat.getMaterial_y() < material_y + size_y)) {
						if ((mat.getMaterial_x() <= material_x && material_x < mat.getMaterial_x() + mat.size_x) | (mat.getMaterial_x() <= material_x + size_x && material_x + size_x < mat.getMaterial_x() + mat.size_x)) {
							if (direction == 1) {
								mat.wounded(this,principal, direction, material_x-12, material_y-12);
							} else if(direction == 2) {
								mat.wounded(this,principal, direction, material_x-12, material_y-16);
							} else if (direction == 3) {
								mat.wounded(this,principal, direction, material_x-15, material_y-12);
							} else if (direction == 4) {
								mat.wounded(this,principal, direction, material_x-15, material_y-10);
							}
						}
					}
//					System.out.println(mat.getClass());
				}
			}
		}
	}

	@Override
	public void wounded(Bullet bullet, int principal, int dire, int effect_x, int effect_y) {
		Data.matarry.remove(bullet);
		Data.matarry.remove(this);
		new AudioPlay().play("bgmusic\\hit.wav");//fire
	}
	
}
