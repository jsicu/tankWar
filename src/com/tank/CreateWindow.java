package com.tank;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * @author linzhongqi
 * @data 2019��3��15��
 * ��ʾ������
 * 
 */
public class CreateWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private int fps;
	private Image logo;
	private CreateCanvas cc;
	
	int n = 0;
	/**
	 * ��������
	 * 
	 * @param title ���ڱ���
	 * @param width ���ڿ��
	 * @param height ���ڸ߶�
	 * @param fps ÿ����ˢ�´���
	 * @param 
	 */
	public CreateWindow(String title, int width, int height, int fps, Image logo) throws HeadlessException {
		super(title);
		this.width = width;
		this.height = height;
		this.fps = fps;
		this.logo = logo;
		createJFrame();
	}
	
	private final void createJFrame(){
		
		//���ڴ�С
		setSize(width, height);		
		//����λ��
		setLocation(350, 100);		
		final CreateCanvas cc = new CreateCanvas();
		add(cc);
		setResizable(false);
		setIconImage(logo);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);		
		new GameStart();
		
		//���ƶ�������1000/fps���봥������ʱ��
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
		//addKeyListener ���ָ���İ������������Խ��շ��Դ�����İ����¼���
		addKeyListener(new KeyListener() {
			
			@Override
			//����һ����Ȼ���ͷŸü��󱻵���
			public void keyTyped(KeyEvent e) {
				// TODO �Զ����ɵķ������
			}
			
			@Override
			//�ͷ�ĳ����ʱ���ô˷�����
			public void keyReleased(KeyEvent e) {
				// TODO �Զ����ɵķ������
				if (Data.start == 1){
					//getKeyCode ��������¼��еļ����������� keyCode��
					if (e.getKeyCode() == KeyEvent.VK_UP){
						((Tank_man)(Data.mat)).upKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((Tank_man)(Data.mat)).upKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((Tank_man)(Data.mat)).upKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((Tank_man)(Data.mat)).upKey(3);
					}
				}
			}
			
			@Override
//			�����ͷ --> 37 �����ַ����-VK_LEFT 
//			���ϼ�ͷ --> 38 �����ַ����-VK_UP
//			���Ҽ�ͷ --> 39 �����ַ����-VK_RIGHT
//			���¼�ͷ --> 40 �����ַ����-VK_DOWN
//			����� --> F��-VK_F
			//����ĳ����ʱ���ô˷�����
			public void keyPressed(KeyEvent e) {
				//����ģʽ
				if (Data.start == 1){ 
					//getKeyCode ��������¼��еļ����������� keyCode��
					if (e.getKeyCode() == KeyEvent.VK_UP){ 
						((Tank_man)(Data.mat)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((Tank_man)(Data.mat)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((Tank_man)(Data.mat)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((Tank_man)(Data.mat)).downKey(3);
					}
					//����F�������ڵ�
					if (e.getKeyCode() == KeyEvent.VK_F){
						((Tank_man)(Data.mat)).attack();
					}
					//�˳�����
					if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
						Data.matarry.clear();
						//���ط��棬��������
						TankOther.myTank = 4;			
						TankOther.enemySum = 20;
						//�ر�����
						new AudioPlay().stop();			
						Data.start = 3;
					}
				}//���õ�ͼ
				else if (Data.start == 2){ 
					//getKeyCode ��������¼��еļ����������� keyCode��
					if (e.getKeyCode() == KeyEvent.VK_UP){
						Data.create_map.downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						Data.create_map.downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						Data.create_map.downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
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
				}
				//��Ϸ��ʼҳ��
				else if (Data.start == 3){ 
//					���� �س���-VK_ENTER
//					ѡ�� A��-VK_A
					if (e.getKeyCode() == KeyEvent.VK_ENTER){
						GameStart.downKey(6);
					}
					if (e.getKeyCode() == KeyEvent.VK_A){
						GameStart.downKey(7);
					}
					
				}
			}
		});
		
		
	}
	
	public int getWindowHeight(){
		System.out.println("getHeight");
		return getHeight();
	}
	
	public int getWindowWidth(){
		return getWidth();
	}
	
	public void canvasRepaint(){
		cc.repaint();
	}
	
}
