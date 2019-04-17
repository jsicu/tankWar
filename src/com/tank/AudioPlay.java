package com.tank;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author linzhongqi
 * @data 2019��3��18��
 * ���ֲ�����
 * 
 */
public class AudioPlay {
	public static AudioClip clip;	
	/**���ֲ��ŷ���*/
	public void play(final String path){
		File file = new File(path); 
		URL radio = null;
		try {
			radio = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		clip = Applet.newAudioClip(radio);
		clip.play();
	}
	/**����ѭ�����ŷ���*/
	public void loop(String path){
		File file = new File(path); 
		URL radio = null;
		try {
			radio = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		clip = Applet.newAudioClip(radio);
		clip.loop();
	}
	/**������ͣ����*/
	public void stop(){
		clip.stop();
	}
}
