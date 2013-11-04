package com.coco.marumaru.been;

import android.content.Context;
import android.content.SharedPreferences;

public class GameBean
{
	private static final String DATA_NAME="MARUMARU";
	private static final String SCALE="SCALE";
	private static final String ROTATE="ROTATE";
	private static final String WAVE="WAVE";
	private static final String CRUNODE="CRUNODE";
	private static final String MARU_X="MARU_X";
	private static final String MARU_Y="MARU_Y";
	private float scale;//圈圈倍數
	private int rotate;//波形旋轉角度
	private int wave;//波形放大倍數
	private int crunode;//結點
	private int maru_x;
	private int maru_y;
	private Context context;
	public GameBean(Context context)
	{
		this.context=context;
	}
	public void restoreGameBean()
	{
		SharedPreferences data=this.context.getSharedPreferences(DATA_NAME,Context.MODE_PRIVATE);
		if(data!=null)
		{
			scale=data.getFloat(SCALE, 1);
			rotate=data.getInt(ROTATE, 45);
			wave=data.getInt("WAVE", 20);
			crunode=data.getInt("CRUNODE", 8);
			maru_x=data.getInt("MARU_X", 0);
			maru_y=data.getInt("MARU_Y", 0);
		}
	}
	public void storeGameBean()
	{
		SharedPreferences data=this.context.getSharedPreferences(DATA_NAME,Context.MODE_PRIVATE);
		data.edit().putFloat(SCALE,scale).
		putInt(ROTATE, rotate).
		putInt(WAVE,wave).
		putInt(CRUNODE, crunode).
		putInt(MARU_X,maru_x).
		putInt(MARU_Y,maru_y).
		commit();
	}
	
	public int getRotate()
	{
		return rotate;
	}
	public void setRotate(int rotate)
	{
		this.rotate = rotate;
	}
	
	public float getScale()
	{
		return scale;
	}
	public void setScale(float scale)
	{
		this.scale = scale;
	}
	public int getWave()
	{
		return wave;
	}
	public void setWave(int wave)
	{
		this.wave = wave;
	}
	public int getCrunode()
	{
		return crunode;
	}
	public void setCrunode(int crunode)
	{
		this.crunode = crunode;
	}
	public int getMaru_x()
	{
		return maru_x;
	}
	public void setMaru_x(int maruX)
	{
		maru_x = maruX;
	}
	public int getMaru_y()
	{
		return maru_y;
	}
	public void setMaru_y(int maruY)
	{
		maru_y = maruY;
	}
}
