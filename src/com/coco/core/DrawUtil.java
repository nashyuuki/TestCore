package com.coco.core;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class DrawUtil
{
	public static void drawRect(Canvas canvas, int x, int y, int width,
			int height, int a, int r, int g, int b)
	{
		canvas.save();
		Paint paint = new Paint();
		paint.setARGB(a, r, g, b);
		Rect rect = new Rect(x, y, x + width, y + height);
		if(setWindowClip(canvas,x,y,width,height))
		{
			canvas.drawRect(rect, paint);
		}
		canvas.restore();

	}
	public static void drawImage(Canvas canvas, Drawable drawable, int x, int y)
	{
		int width = drawable.getIntrinsicWidth();
		int height = drawable.getIntrinsicHeight();
		drawImage(canvas, drawable, x, y,width,height);
	}
	public static void drawImage(Canvas canvas, Drawable drawable, int x, int y,float scaleX,float scaleY)
	{
		int width = (int)(drawable.getIntrinsicWidth()*scaleX);
		int height = (int)(drawable.getIntrinsicHeight()*scaleY);
		drawImage(canvas, drawable, x, y,width,height);
	}
	public static void drawImage(Canvas canvas, Drawable drawable, int x, int y,int width,int height)
	{
		canvas.save();
		drawable.setBounds(x, y, x + width, y + height);
		setWindowClip(canvas,x,y,width,height);
		if(setWindowClip(canvas,x,y,width, height))
		{
			drawable.draw(canvas);
		}
		canvas.restore();
	}
	public static void drawImage(Canvas canvas, Drawable drawable, int x,
			int y, int width, int height, int frameInt)
	{
		int imageWidth = drawable.getIntrinsicWidth();
		int imageHeight = drawable.getIntrinsicHeight();
		drawImage(canvas,drawable,x,y,width,height,frameInt,imageWidth,imageHeight);
		
	}
	public static void drawImage(Canvas canvas, Drawable drawable, int x,
			int y, int width, int height, int frameInt,float scaleX,float scaleY)
	{
		int imageWidth = (int)(drawable.getIntrinsicWidth()*scaleX);
		int imageHeight = (int)(drawable.getIntrinsicHeight()*scaleY);
		int width1=(int)(width*scaleX);
		int height1=(int)(height*scaleY);
		drawImage(canvas,drawable,x,y,width1,height1,frameInt,imageWidth,imageHeight);
		
	}
	public static void drawImage(Canvas canvas, Drawable drawable, int x,
			int y, int width, int height, int frameInt,int imageWidth,int imageHeight)
	{
		canvas.save();
		int widthInt=imageWidth/width;
		drawable.setBounds(
				x - width * (frameInt%widthInt), 
				y -	height*(frameInt/widthInt), 
				x - width * (frameInt%widthInt)+ imageWidth, 
				y -	height*(frameInt/widthInt) + imageHeight);
		if(setWindowClip(canvas,x,y,width, height))
		{
			drawable.draw(canvas);
		}
		canvas.restore();
	}
	public static boolean setWindowClip(Canvas canvas, int x, int y, int width,
			int height)
	{//判斷是否會繪於螢幕上 true=畫 false=不畫
	 //設定clip的大小
		int windowWidth = Consts.SCREEN_WIDTH;
		int windowHeight = Consts.SCREEN_HEIGHT;
		if(x>windowWidth)
		{
			return false;
		}
		if(y>windowHeight)
		{
			return false;
		}
		if(x<-width)
		{
			return false;
		}
		if(y<-height)
		{
			return false;
		}
		if (x + width > windowWidth)
		{
			width = windowWidth - x;
		}
		if (y + height > windowHeight)
		{
			height = windowHeight - y;
		}
		if (x < 0)
		{
			width += x;
			x = 0;
		}
		if (y < 0)
		{
			height += y;
			y = 0;
		}
		canvas.clipRect(x, y, x+width, y+height);
		return true;
	}
}
