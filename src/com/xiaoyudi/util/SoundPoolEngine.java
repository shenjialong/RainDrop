package com.xiaoyudi.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class SoundPoolEngine {
	
	private static int MAX_STREAM = 1;                                //同时播放的音效数
	private static int STREAM_TYPE = AudioManager.STREAM_MUSIC;       //音频类型
	private static int SRC_QUALITY = 0;                               //音频文件质量，默认为0
	private static final int PRIORITY = 1;
	
	private HashMap<Integer, Integer> mSoundPoolMap;
	
	private SoundPool mSoundPool;
	
	private Context mContext;
	
	private static SoundPoolEngine soundPoolEngine;
	
	private SoundPoolEngine(Context context){
		this.mContext = context;
	}

	public static SoundPoolEngine getInstance(Context context) {
		synchronized(SoundPoolEngine.class) {
			if(soundPoolEngine == null) {
				synchronized(SoundPoolEngine.class) {
					soundPoolEngine = new SoundPoolEngine(context);
				}
			}
			return soundPoolEngine;
		}
	}
	
	/**
	 * SoundPool 加载资源
	 * @param context
	 * @param srcIds
	 * @return
	 */
	public ArrayList<Integer> loadRes(int[] srcIds) {
		mSoundPool = new SoundPool(MAX_STREAM, STREAM_TYPE, SRC_QUALITY);
		mSoundPoolMap = new HashMap<Integer, Integer>();
		
		ArrayList<Integer> soundPoolIds = new ArrayList<Integer>();
		
		for(int i = 0; i < srcIds.length; ++i) {
			Log.i("sjl", "正在加载声音文件："+mSoundPool.load(mContext, srcIds[i], 1));
			mSoundPoolMap.put(i, mSoundPool.load(mContext, srcIds[i], 1));
			soundPoolIds.add(i);
		}
		
		return soundPoolIds;
	}
	
	
	public void play(int id) {
		//TODO 判断id合法
		
		AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
		float audioMaxVolume = audioManager.getStreamMaxVolume(STREAM_TYPE);
		float audioCorrentVolume = audioManager.getStreamVolume(STREAM_TYPE);
		float volume = audioCorrentVolume / audioMaxVolume;
		
//		mSoundPool.stop(mSoundPoolMap.get(id));
		
		mSoundPool.play(mSoundPoolMap.get(id),  /*播放音乐的Id*/  
				audioMaxVolume,                         /*左声道音量*/
				audioMaxVolume,                         /*右声道音量*/
				PRIORITY,                              /*优先级，0为最低*/
				0,                              /*循环次数，0为不循环，-1为无限寻婚*/
				1);                             /*回放速度，0.5-2.0之间，1为正常*/
	}
	
	/**
	 * 
	 * @param streamID
	 */
	public void pause(int id) {
		mSoundPool.pause(id);
	}
	
	/**
	 * 
	 */
	public void release() {
		if(mSoundPool != null) {
			mSoundPool.release();
			mSoundPool = null;
		}
		if(mSoundPoolMap != null) {
			mSoundPoolMap.clear();
			mSoundPoolMap = null;
		}
	}
	
}
