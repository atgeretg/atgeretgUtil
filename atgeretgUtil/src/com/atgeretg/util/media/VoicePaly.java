package com.atgeretg.util.media;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class VoicePaly {
	// public static void main(String[] args) throws Exception {
	//
	//
	// }

	/**
	 * 播放指定文字，有些电脑不行，是电脑没有windows的语音播报功能
	 * 
	 * @param word
	 */
	public static void palyVoice(String word) {
		ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");

		Dispatch sapo = sap.getObject();
		try {

			// 音量 0-100
			sap.setProperty("Volume", new Variant(100));
			// 语音朗读速度 -10 到 +10
			sap.setProperty("Rate", new Variant(-2));

			// 执行朗读
			Dispatch.call(sapo, "Speak", new Variant(word));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sapo.safeRelease();
			sap.safeRelease();
		}
	}
}
