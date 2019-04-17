package com.tank;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author linzhongqi
 * @data 2019年3月18日
 * 音乐播放类
 * 
 */
public class AudioPlay {
	public static AudioClip clip;	
	/**音乐播放方法*/
	public void play(final String path){
		File file = new File(path); 
		URL radio = null;
		try {
			radio = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		clip = Applet.newAudioClip(radio);
		clip.play();
	}
	/**音乐循环播放方法*/
	public void loop(String path){
		File file = new File(path); 
		URL radio = null;
		try {
			radio = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		clip = Applet.newAudioClip(radio);
		clip.loop();
	}
	/**音乐暂停方法*/
	public void stop(){
		clip.stop();
	}
}
