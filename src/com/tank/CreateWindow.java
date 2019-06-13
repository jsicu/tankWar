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
 * @data 2019年3月15日
 * 显示操作类
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
	 * 创建窗口
	 * 
	 * @param title 窗口标题
	 * @param width 窗口宽度
	 * @param height 窗口高度
	 * @param fps 每秒钟刷新次数
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
		
		//窗口大小
		setSize(width, height);		
		//窗口位置
		setLocation(350, 100);		
		final CreateCanvas cc = new CreateCanvas();
		add(cc);
		//禁止最大化
		setResizable(false);
		setIconImage(logo);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);	
		new GameStart();
		
		//绘制动画，以1000/fps毫秒触发动作时间
		new Timer(1000 / fps ,new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				n++;
				if (n >= fps * 100){
					n = 0;
				}
				// 单双人
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
		//addKeyListener 添加指定的按键侦听器，以接收发自此组件的按键事件。
		addKeyListener(new KeyListener() {
			
			@Override
			//按下一个键然后释放该键后被调用
			public void keyTyped(KeyEvent e) {
				// TODO 自动生成的方法存根
			}
			
			@Override
			//释放某个键时调用此方法。
			public void keyReleased(KeyEvent e) {
				 // 单人
				if (Data.start == 1){
					//getKeyCode 返回与此事件中的键关联的整数 keyCode。
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
				// 双人
				if (Data.start == 0){
//					material=new TankA(0, 8, 128, 384, 3, 1, 1);
					// 甲坦克
					if (e.getKeyCode() == KeyEvent.VK_W){
						((TankA)(TankOther.matA)).upKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_S){
						((TankA)(TankOther.matA)).upKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_A){
						((TankA)(TankOther.matA)).upKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_D){
						((TankA)(TankOther.matA)).upKey(3);
					}
					// 乙坦克
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
//			向左箭头 --> 37 非数字方向键-VK_LEFT 
//			向上箭头 --> 38 非数字方向键-VK_UP
//			向右箭头 --> 39 非数字方向键-VK_RIGHT
//			向下箭头 --> 40 非数字方向键-VK_DOWN
//			开火键 --> F键-VK_F
			//按下某个键时调用此方法。
			public void keyPressed(KeyEvent e) {
				// 双人模式
				if (Data.start == 0){ 
					
//					Material material =new TankA(0, 8, 128, 384, 3, 1, 1);
					// 甲坦克
					if (e.getKeyCode() == KeyEvent.VK_W){
						((TankA)(TankOther.matA)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_S){
						((TankA)(TankOther.matA)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_A){
						((TankA)(TankOther.matA)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_D){
						((TankA)(TankOther.matA)).downKey(3);
					}
					//按下F键发射炮弹
					if (e.getKeyCode() == KeyEvent.VK_F){
						((TankA)(TankOther.matA)).attack();
					
					}
					
					// 乙坦克
					if (e.getKeyCode() == KeyEvent.VK_UP){ 
						((TankB)(TankOther.matB)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((TankB)(TankOther.matB)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((TankB)(TankOther.matB)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((TankB)(TankOther.matB)).downKey(3);
					}
					//按下j键发射炮弹
					if (e.getKeyCode() == KeyEvent.VK_J){
						((TankB)(TankOther.matB)).attack();
					}
					
					//退出按键
					if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
						Data.matarry.clear();
						//返回封面，重置数量
						Data.myTankA = 4;			
						Data.myTankB = 4;			
						TankOther.enemySum = 20;
						//关闭音乐
						new AudioPlay().stop();			
						Data.start = 3;
					}
				}// 单人模式
				else if (Data.start == 1){ 
					//getKeyCode 返回与此事件中的键关联的整数 keyCode。
					if (e.getKeyCode() == KeyEvent.VK_UP){ 
						((TankA)(Data.mat)).downKey(0);
					}else if (e.getKeyCode() == KeyEvent.VK_DOWN){
						((TankA)(Data.mat)).downKey(1);
					}else if (e.getKeyCode() == KeyEvent.VK_LEFT){
						((TankA)(Data.mat)).downKey(2);
					}else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
						((TankA)(Data.mat)).downKey(3);
					}
					//按下F键发射炮弹
					if (e.getKeyCode() == KeyEvent.VK_F){
						((TankA)(Data.mat)).attack();
					}
					//退出按键
					if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
						Data.matarry.clear();
						//返回封面，重置数量
						Data.myTankA = 4;			
						TankOther.enemySum = 20;
						//关闭音乐
						new AudioPlay().stop();			
						Data.start = 3;
					}
				}//设置地图
				else if (Data.start == 2 && !Data.startpoint){ 
					if (e.getKeyCode() == KeyEvent.VK_W){
						Data.create_map.downKey(8);
					}
					if (e.getKeyCode() == KeyEvent.VK_S){
						Data.create_map.downKey(9);
					}
					// 确定
					if (e.getKeyCode() == KeyEvent.VK_ENTER){
						Data.create_map.downKey(10);
					}
					// 退出
					if (e.getKeyCode() == KeyEvent.VK_Q){
						Data.create_map.downKey(7);
					}
				}else if (Data.start == 2 && Data.startpoint) {
					//getKeyCode 返回与此事件中的键关联的整数 keyCode。
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
				//游戏开始页面
				else if (Data.start == 3){ 
//					进入 回车键-VK_ENTER
//					选择 A键-VK_A
					if (e.getKeyCode() == KeyEvent.VK_ENTER){
						//关闭音乐
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
