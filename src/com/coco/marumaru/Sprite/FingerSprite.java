package com.coco.marumaru.Sprite;

import android.graphics.Canvas;

import com.coco.marumaru.config.ImageConfig;
import com.coco.marumaru.consts.GameConsts;

public class FingerSprite extends Sprite
{
	public static final int EFFECT=1;
	public FingerSprite(ImageConfig imageConfig)
	{
		super(imageConfig);
	}
	@Override
	public void update()
	{
		if(state==this.EFFECT)
		{
			if(this.nextTime(timeSpeed+1))
			{
				this.setState(this.DISABLE);
			}
		}
	}
	@Override
	public void drawView(Canvas canvas)
	{
		if(state==this.EFFECT)
		{
			this.drawImage(canvas, ImageConfig.FINGER, x, y);
		}
	}
}
