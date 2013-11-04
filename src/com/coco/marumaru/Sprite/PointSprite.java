package com.coco.marumaru.Sprite;

import android.graphics.Canvas;

import com.coco.core.utils.NumberUtil;
import com.coco.marumaru.config.ImageConfig;

public class PointSprite extends Sprite
{

	public static final int POINT=1;
	private int number;
	private int numberWidth;
	private int numberHeight;
	private int[] numberArray;
	public PointSprite(ImageConfig imageConfig)
	{
		super(imageConfig);
		numberWidth=this.getWidth(ImageConfig.UI_NUMBER)/10;
		numberHeight=this.getHeight(ImageConfig.UI_NUMBER);
	}
	@Override
	public void drawView(Canvas canvas)
	{
		if(state==this.POINT)
		{
			for(int i=0;i<numberArray.length;i++)
			{
				this.drawImage(canvas, ImageConfig.UI_NUMBER, 
						x-i*numberWidth, y, 
						numberWidth, numberHeight, 
						numberArray[i]);
			}
		}
	}
	public int getNumber()
	{
		return number;
	}
	public void setNumber(int number)
	{
		this.number = number;
		numberArray=NumberUtil.transformArrary(number, 10);
	}
	
}
