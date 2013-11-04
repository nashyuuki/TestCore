package com.coco.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.coco.marumaru.been.GameBean;
import com.coco.marumaru.config.ImageConfig;

public abstract class CoreModel
{
	private CoreController coreController;
	public Context context;
	public GameBean gameBean;
	public int subState;
	public CoreModel(CoreController coreController)
	{
		this.coreController=coreController;
		this.context=coreController.getContext();
		this.gameBean=coreController.getGameBean();
	}
	public abstract void init();
	public abstract void update();
	public abstract void drawView(Canvas canvas);
	public abstract void onKeyDown(int keyCode);
	public abstract void onKeyUp(int keyCode);
	public void onTouchEvent(int x,int y, MotionEvent event){};
	public void setNextState(int nextState)
	{
		coreController.setNextState(nextState);
	}
	public ImageConfig getImageConfig()
	{
		return coreController.getImageConfig();
	}
	public Drawable getDrawable(int image)
	{
		return this.getImageConfig().getDrawable(image);
	}
	public void playMusic(int music)
	{
		coreController.getMusciConfig().play(music);
	}
	public void exitGame()
	{
		TestCoreActivity.getInstance().finish();
	}
	public void restart()
	{
		
	}
}
