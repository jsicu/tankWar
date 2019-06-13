package com.tank;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CreateCanvas extends JPanel{
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		//���paint
		super.paint(g);	
		//��дָ���ľ���
		g.fillRect(Data.MIN_X, Data.MIN_Y, 416, 416);
		for (Material mat : Data.matarry) {
			mat.draw(g, this);
		}
		// ��˫��
		if (Data.start == 1 || Data.start == 0){
			TankOther.draw(g, this);
		}
		if (Data.start == 2) {
			Data.create_map.draw(g, this);
		}
		// ����
		if (Data.start == 3){
			GameStart.draw(g, this);
		}
	}
	
//	public Image imge(String path){
//		return Toolkit.getDefaultToolkit().getImage(new Data().getClass().getResource(path));
//	}
	
	
}
