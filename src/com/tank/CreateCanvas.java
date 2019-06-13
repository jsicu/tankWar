package com.tank;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CreateCanvas extends JPanel{
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		//清除paint
		super.paint(g);	
		//填写指定的矩形
		g.fillRect(Data.MIN_X, Data.MIN_Y, 416, 416);
		for (Material mat : Data.matarry) {
			mat.draw(g, this);
		}
		// 单双人
		if (Data.start == 1 || Data.start == 0){
			TankOther.draw(g, this);
		}
		if (Data.start == 2) {
			Data.create_map.draw(g, this);
		}
		// 封面
		if (Data.start == 3){
			GameStart.draw(g, this);
		}
	}
	
//	public Image imge(String path){
//		return Toolkit.getDefaultToolkit().getImage(new Data().getClass().getResource(path));
//	}
	
	
}
