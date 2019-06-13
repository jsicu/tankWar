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
	int n = 0;
	/**
	 * ��������
	 * 
	 * @param title ���ڱ���
	 * @param width ���ڿ��
	 * @param height ���ڸ߶�
	 * @param fps ÿ����ˢ�´���
	 * @param logo logo
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
		//��ֹ���
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
				// ��˫��
				if (Data.start == 1 || Data.start == 0){
					TankOther.newtank();
				}
				
				for (Material mat : Data.matarry) {
					mat.anew(n);
				}
				
				if (Data.start == 2) {
					Data.create_map.anew(n);
				}
				if (Data.start == 3){
					Data.startpoint = false;
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
				 // ����
				if (Data.start == 1){
					//getKeyCode ��������¼��еļ����������� keyCode��
					if (e.getKeyCode() == KeyEvent.VK_UP){
						((TankA)(Data.mat)).upKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((TankA)(Data.mat)).upKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((TankA)(Data.mat)).upKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((TankA)(Data.mat)).upKey(3);
					}
				}
				// ˫��
				if (Data.start == 0){
//					material=new TankA(0, 8, 128, 384, 3, 1, 1);
					// ��̹��
					if (e.getKeyCode() == KeyEvent.VK_W){
						((TankA)(TankOther.matA)).upKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_S){
						((TankA)(TankOther.matA)).upKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_A){
						((TankA)(TankOther.matA)).upKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_D){
						((TankA)(TankOther.matA)).upKey(3);
					}
					// ��̹��
					if (e.getKeyCode() == KeyEvent.VK_UP){
						((TankB)(TankOther.matB)).upKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((TankB)(TankOther.matB)).upKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((TankB)(TankOther.matB)).upKey(2);
					}else  if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((TankB)(TankOther.matB)).upKey(3);
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
				// ˫��ģʽ
				if (Data.start == 0){ 
					
//					Material material =new TankA(0, 8, 128, 384, 3, 1, 1);
					// ��̹��
					if (e.getKeyCode() == KeyEvent.VK_W){
						((TankA)(TankOther.matA)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_S){
						((TankA)(TankOther.matA)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_A){
						((TankA)(TankOther.matA)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_D){
						((TankA)(TankOther.matA)).downKey(3);
					}
					//����F�������ڵ�
					if (e.getKeyCode() == KeyEvent.VK_F){
						((TankA)(TankOther.matA)).attack();
					
					}
					
					// ��̹��
					if (e.getKeyCode() == KeyEvent.VK_UP){ 
						((TankB)(TankOther.matB)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((TankB)(TankOther.matB)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((TankB)(TankOther.matB)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((TankB)(TankOther.matB)).downKey(3);
					}
					//����j�������ڵ�
					if (e.getKeyCode() == KeyEvent.VK_J){
						((TankB)(TankOther.matB)).attack();
					}
					
					//�˳�����
					if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
						Data.matarry.clear();
						//���ط��棬��������
						Data.myTankA = 4;			
						Data.myTankB = 4;			
						TankOther.enemySum = 20;
						//�ر�����
						new AudioPlay().stop();			
						Data.start = 3;
					}
				}// ����ģʽ
				else if (Data.start == 1){ 
					//getKeyCode ��������¼��еļ����������� keyCode��
					if (e.getKeyCode() == KeyEvent.VK_UP){ 
						((TankA)(Data.mat)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((TankA)(Data.mat)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((TankA)(Data.mat)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((TankA)(Data.mat)).downKey(3);
					}
					//����F�������ڵ�
					if (e.getKeyCode() == KeyEvent.VK_F){
						((TankA)(Data.mat)).attack();
					}
					//�˳�����
					if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
						Data.matarry.clear();
						//���ط��棬��������
						Data.myTankA = 4;			
						TankOther.enemySum = 20;
						//�ر�����
						new AudioPlay().stop();			
						Data.start = 3;
					}
				}//���õ�ͼ
				else if (Data.start == 2 && !Data.startpoint){ 
					if (e.getKeyCode() == KeyEvent.VK_W){
						Data.create_map.downKey(8);
					}
					if (e.getKeyCode() == KeyEvent.VK_S){
						Data.create_map.downKey(9);
					}
					// ȷ��
					if (e.getKeyCode() == KeyEvent.VK_ENTER){
						Data.create_map.downKey(10);
					}
					// �˳�
					if (e.getKeyCode() == KeyEvent.VK_Q){
						Data.create_map.downKey(7);
					}
				}else if (Data.start == 2 && Data.startpoint) {
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
					if (e.getKeyCode() == KeyEvent.VK_SPACE){
						Data.create_map.downKey(4);
					}
					if (e.getKeyCode() == KeyEvent.VK_D){
						Data.create_map.downKey(5);
					}
					if (e.getKeyCode() == KeyEvent.VK_E){
						Data.create_map.downKey(6);
					}
					if (e.getKeyCode() == KeyEvent.VK_A){
						Data.create_map.downKey(11);
					}
				}
				//��Ϸ��ʼҳ��
				else if (Data.start == 3){ 
//					���� �س���-VK_ENTER
//					ѡ�� A��-VK_A
					if (e.getKeyCode() == KeyEvent.VK_ENTER){
						//�ر�����
						new AudioPlay().stop();	
						GameStart.downKey(6);
					}
					if (e.getKeyCode() == KeyEvent.VK_A){
						GameStart.downKey(7);
					}
					
				}
			}
		});
		
		
	}
	
//	public int getWindowHeight(){
//		return getHeight();
//	}
//	
//	public int getWindowWidth(){
//		return getWidth();
//	}
//	
//	public void canvasRepaint(){
//		cc.repaint();
//	}
	
}
