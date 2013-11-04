package com.coco.marumaru.Sprite;

import android.graphics.Canvas;

import com.coco.marumaru.config.ImageConfig;

public class BoxerSprite extends Sprite
{
	public static final int PICK=1;
	public static final int EFFECT=2;
	public BoxerSprite(ImageConfig imageConfig)
	{
		super(imageConfig);
	}
	@Override
	public void update()
	{
		if(state==this.PICK)
		{
			if(this.nextTime(timeSpeed))
			{
				this.setState(this.EFFECT);
			}
		}
		else if(state==this.EFFECT)
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
		if(state==this.PICK)
		{
			this.drawImage(canvas, ImageConfig.BOXER01, x, y);
		}
		else if(state==this.EFFECT)
		{
			this.drawImage(canvas, ImageConfig.BOXER02, x, y);
		}
	}

}
