package com.young.tank;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class CreateWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private int fps;
	private Image icoimg;
	private CreateCanvas cc;
	
	boolean bup = false;
	boolean bdo = false;
	boolean ble = false;
	boolean bri = false;
	int n = 0;
	/**
	 * ��������
	 * 
	 * @param title
	 *            ���ڱ���
	 * @param width
	 *            ���ڿ��
	 * @param height
	 *            ���ڸ߶�
	 * @param fps
	 *            ÿ����ˢ�´���
	 * @param icoimg
	 *            ����ͼ��
	 */
	public CreateWindow(String title, int width, int height, int fps, Image icoimg) throws HeadlessException {
		super(title);
		this.width = width;
		this.height = height;
		this.fps = fps;
		this.icoimg = icoimg;
		CreateJFrame();
//		frame.requestFocus();  //JPanelҪ��Ӧ�����¼����������ý���
	}
	
	/**
	 * ��������
	 * 
	 * @param title
	 *            ���ڱ���
	 * @param width
	 *            ���ڿ��
	 * @param height
	 *            ���ڸ߶�
	 * @param fps
	 *            ÿ����ˢ�´���
	 */
	public CreateWindow(String title, int width, int height,  int fps){
		super(title);
		this.width = width;
		this.height = height;
		this.fps = fps;
		CreateJFrame();
//		frame.requestFocus();  //JPanelҪ��Ӧ�����¼����������ý���
	}
	
	private final void CreateJFrame(){
		setLayout(null);
		setSize(width+5, height+29);
		
		final CreateCanvas cc = new CreateCanvas();
		cc.setBackground(new Color(193, 193, 193));
		cc.setBounds(0, 0, width, height);
		add(cc);

		setResizable(false);
		setIconImage(icoimg);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		/////////////////////////////////////////////////////
//		Data.matarry.add(new Boss(19, 5, 192, 384, 3));
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 6; j++) {
//				if (i >= 2 && j >= 2 && i < 6){
//					continue;
//				}
//				Data.matarry.add(new Wall(0, 0, 176 + i * 8 , 368 + j * 8, 3));
//			}
//		}

		
//		if (Data.start == 1){
//			
//		}else if (Data.start == 2){
//			Data.create_map = new CreateMap();
//		}
		
		

		
		
//		Data.matarry.add(0, new Tank_npc(0, 2, 192, 0, 3, 6, 5));
//		Data.matarry.add(0, new Tank_npc(0, 2, 384,0, 3, 7, 5));
//		Data.matarry.add(0, new Tank_npc(0, 2, 0, 0, 3, 8, 5));
		

		new GameStart();
	
//		new TankOther();
		
		
//		Data.matarry.add(new Sea(0, 7,128,192, 1));
//		Data.matarry.add(new Sea(0, 7,160,192, 1));
//		for (int i = 0; i < 3; i++) {
//			for (int j = 0; j < 3; j++) {
//				Data.matarry.add(new Grass(4, 7, i * 32, j * 32, 1));
//			}
//		}
		
//		Data.matarry.add(new Bullet(0, 5, 64, 64, 40, 5))
		///////////////////////////////////////////////////////
		
		new Timer(1000 / fps ,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				n++;
				if (n >= fps * 100){
					n = 0;
				}
				
				if (Data.start == 1){
					TankOther.newtank();
				}
				
				
			
				for (Material mat : Data.matarry) {
					mat.anew(n);
				}
					
				if(Data.start == 2){
					Data.create_map.anew(n);
				}
				
				if (Data.start == 3){
					GameStart.anew(n);
				}
				
				cc.repaint();
			}
			
		}).start();
		
		addKeyListener(new KeyListener() {//addKeyListener ���ָ���İ������������Խ��շ��Դ�����İ����¼���
			
			@Override
			public void keyTyped(KeyEvent e) {//����ĳ����ʱ���ô˷�����
				// TODO �Զ����ɵķ������
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {//�ͷ�ĳ����ʱ���ô˷�����
				// TODO �Զ����ɵķ������
				if (Data.start == 1){
					if (e.getKeyCode() == KeyEvent.VK_UP){ //getKeyCode ��������¼��еļ����������� keyCode��//38��
						((Tank_man)(Data.mat)).upKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){//40 ��
						((Tank_man)(Data.mat)).upKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){//37��
						((Tank_man)(Data.mat)).upKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){//37��
						((Tank_man)(Data.mat)).upKey(3);
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {//����ĳ����ʱ���ô˷�����
				// TODO �Զ����ɵķ������
				if (Data.start == 1){
					if (e.getKeyCode() == KeyEvent.VK_UP){ //getKeyCode ��������¼��еļ����������� keyCode��//38��
						((Tank_man)(Data.mat)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){//40 ��
						((Tank_man)(Data.mat)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){//37��
						((Tank_man)(Data.mat)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){//37��
						((Tank_man)(Data.mat)).downKey(3);
					}
					
					if (e.getKeyCode() == KeyEvent.VK_S){
						((Tank_man)(Data.mat)).attack();
					}
				}else if (Data.start == 2){
					if (e.getKeyCode() == KeyEvent.VK_UP){ //getKeyCode ��������¼��еļ����������� keyCode��//38��
						Data.create_map.downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){//40 ��
						Data.create_map.downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){//37��
						Data.create_map.downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){//37��
						Data.create_map.downKey(3);
					}
					if (e.getKeyCode() == KeyEvent.VK_S){
						Data.create_map.downKey(4);
					}
					if (e.getKeyCode() == KeyEvent.VK_D){
						Data.create_map.downKey(5);
					}
					if (e.getKeyCode() == KeyEvent.VK_U){
						Data.create_map.downKey(6);
					}
					if (e.getKeyCode() == KeyEvent.VK_Y){
						Data.create_map.downKey(7);
					}

				}else if (Data.start == 3){
					if (e.getKeyCode() == KeyEvent.VK_UP){ //getKeyCode ��������¼��еļ����������� keyCode��//38��
						GameStart.downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){//40 ��
						GameStart.downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){//37��
						GameStart.downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){//37��
						GameStart.downKey(3);
					}
					if (e.getKeyCode() == KeyEvent.VK_S){
						GameStart.downKey(4);
					}
					if (e.getKeyCode() == KeyEvent.VK_D){
						GameStart.downKey(5);
					}
					if (e.getKeyCode() == KeyEvent.VK_U){
						GameStart.downKey(6);
					}
					if (e.getKeyCode() == KeyEvent.VK_Y){
						GameStart.downKey(7);
					}
					
				}
			}
		});
		
		
	}
	
	public int getWindowHeight(){
		return getHeight();
	}
	
	public int getWindowWidth(){
		return getWidth();
	}
	
	public void canvasRepaint(){
		cc.repaint();
	}
	
}
