package com.coco.marumaru.Sprite;

import android.graphics.Canvas;

import com.coco.marumaru.config.ImageConfig;

public class FunctionSprite extends Sprite
{
	public static final int FINGER=1;
	public static final int BOXER=2;
	public FunctionSprite(ImageConfig imageConfig)
	{
		super(imageConfig);
	}
	@Override
	public void drawView(Canvas canvas)
	{
		if(state==this.FINGER)
		{
			this.drawImage(canvas, ImageConfig.UI_FINGER, x, y);
		}
		else if(state==this.BOXER)
		{
			this.drawImage(canvas, ImageConfig.UI_BOXER, x, y);
		}
	}
}
