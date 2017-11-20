package com.atgeretg.util.media;

import java.io.IOException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.atgeretg.util.thread.ThreadUtil;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class PlayMusic {
	private static Logger logger = Logger.getLogger(PlayMusic.class);
	private static AudioStream as; // 单次播放声音用
//	public static void main(String[] args) {
//		play(MyContance.OPEN_DOOR_FAIL);
//		play(MyContance.NET_EXCEPTION);
//		play(MyContance.OPEN_DOOR_INTO_FINISH);
//		while (true) {
//			ThreadUtil.sleep(300);
//		}
//	}

	/**
	 * 调一次播放一次,异步线程播放
	 * @param url
	 * @param sleepTime 开始播放完后，如果需要延时可让其值（sleepTime）大于0
	 */
	public static void playMusic(URL url,int sleepTime) {
		if(url == null)
			return;
		try {
			as = new AudioStream(url.openStream());
			AudioPlayer.player.start(as);
			if(sleepTime < 0)
				sleepTime = 0;
			ThreadUtil.sleep(sleepTime);
		} catch (IOException e) {
			logger.error(e);
		}
	}
	


}
