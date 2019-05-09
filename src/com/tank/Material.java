package com.tank;

import java.awt.Graphics;
import java.io.Serializable;
/**
 * @author linzhongqi
 * @data 2019年3月16日
 * 抽象类
 * 
 */
public abstract class Material implements Serializable{
	private static final long serialVersionUID = 1L;
	public int img_x;
	public int img_y;
	public int material_x;
	public int material_y;
	public int refurbish;
	public int imgid = 0;
	public int size_x = 32;		//模型占据空间的大小，默认尺寸32
	public int size_y = 32;
	
	public int attack_id;		//可被攻击子弹等级
	public boolean ispenetrate;	//是否可被穿透（false：可穿透，true：不可穿透 ）
	public boolean ispass;		//是否子弹可通过
	public boolean isspecial;	//特效道具
	
	public Material(int img_x, int img_y, int material_x, int material_y, int refurbish) {
		super();
		this.img_x = img_x;
		this.img_y = img_y;
		this.material_x = material_x;
		this.material_y = material_y;
		this.refurbish = refurbish;
	} 

	public int getImg_x() {
		return img_x;
	}

	public void setImg_x(int img_x) {
		this.img_x = img_x;
	}

	public int getImg_y() {
		return img_y;
	}

	public void setImg_y(int img_y) {
		this.img_y = img_y;
	}

	public int getMaterial_x() {
		return material_x;
	}

	public int getMaterial_y() {
		return material_y;
	}

	public int getRefurbish() {
		return refurbish;
	}

	public void setRefurbish(int refurbish) {
		this.refurbish = refurbish;
	}
	
//	/**特效移除抽象方法*/
//	public abstract void remove(int material_x);
	/**x轴出界判断抽象方法*/
	public abstract void setMaterial_x(int material_x);
	/**y轴出界判断抽象方法*/
	public abstract void setMaterial_y(int material_y);
	/**imgid设置抽象方法*/
	public abstract void anew(int fps);
	/**模型绘制抽象方法*/
	public abstract void draw(Graphics g, CreateCanvas cc);
	/**碰撞效果处理抽象方法*/
	public abstract void wounded(Bullet bullet, int principal, int directions, int effect_x, int effect_y);
}
