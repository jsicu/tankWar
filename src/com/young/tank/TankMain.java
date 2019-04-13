package com.young.tank;

import java.net.MalformedURLException;

/**
 * @author linzhongqi
 * @data 2019年3月15日
 * 程序入口类
 * 
 */
public class TankMain{
	static CreateWindow window;

	public static void main(String[] args) throws MalformedURLException {
		window = new CreateWindow(Data.WINDOW_TITLE, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT, Data.WINDOW_FPS, Data.WINDOW_ICOIMG);
	}
}